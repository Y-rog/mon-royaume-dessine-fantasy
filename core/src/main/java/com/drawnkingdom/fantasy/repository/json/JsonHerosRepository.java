package com.drawnkingdom.fantasy.repository.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.drawnkingdom.fantasy.model.Heros;
import com.drawnkingdom.fantasy.repository.HerosRepository;
import com.drawnkingdom.fantasy.repository.json.dto.HerosData;
import com.drawnkingdom.fantasy.repository.json.mapper.HerosMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de {@link HerosRepository} qui charge les héros
 * depuis un fichier JSON situé dans le dossier assets/data.
 */
public class JsonHerosRepository implements HerosRepository {

    /** Chemin du fichier JSON contenant les héros, relatif au dossier assets. */
    private static final String CHEMIN_JSON = "data/heros.json";

    /** Convertit les données brutes du JSON en objets métier. */
    private final HerosMapper mapper = new HerosMapper();

    @Override
    public List<Heros> findAll() {
        FileHandle file = Gdx.files.internal(CHEMIN_JSON);
        ObjectMapper jsonMapper = new ObjectMapper();

        try {
            HerosData[] data = jsonMapper.readValue(file.read(), HerosData[].class);

            List<Heros> heros = new ArrayList<>();
            for (HerosData d : data) {
                heros.add(mapper.toHeros(d));
            }
            return heros;

        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger heros.json", e);
        }
    }
}

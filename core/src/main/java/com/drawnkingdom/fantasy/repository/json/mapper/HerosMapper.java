package com.drawnkingdom.fantasy.repository.json.mapper;

import com.drawnkingdom.fantasy.model.Heros;
import com.drawnkingdom.fantasy.repository.json.dto.HerosData;

/**
 * Convertit les données brutes issues du JSON (DTO) en objets métier {@link Heros}.
 */
public class HerosMapper {

    /**
     * Convertit un {@link HerosData} en {@link Heros}.
     *
     * @param data les données brutes du héros
     * @return le héros correspondant
     */
    public Heros toHeros(HerosData data) {
        return new Heros(
                data.nom(),
                data.pvMax(),
                data.attaque(),
                data.defense(),
                data.imagePath(),
                data.attaquesSpeciales()
        );
    }
}

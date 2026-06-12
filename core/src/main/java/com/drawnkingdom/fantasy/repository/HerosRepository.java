package com.drawnkingdom.fantasy.repository;

import com.drawnkingdom.fantasy.model.Heros;
import java.util.List;

/**
 * Source de données fournissant la liste des héros disponibles.
 */
public interface HerosRepository {

    /**
     * @return la liste de tous les héros disponibles
     */
    List<Heros> findAll();
}

package com.drawnkingdom.fantasy.repository.json.dto;

import com.drawnkingdom.fantasy.model.Attaque;
import java.util.List;

/**
 * Représentation brute d'un héros telle que définie dans le fichier JSON.
 * Les noms des champs correspondent exactement aux clés du JSON.
 */
public record HerosData(
        String nom,
        String type,
        int pvMax,
        int attaque,
        int defense,
        String imagePath,
        List<Attaque> attaquesSpeciales
) {}

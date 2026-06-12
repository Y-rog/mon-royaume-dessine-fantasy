# 🎮 Mon Royaume Dessiné Fantasy

Un RPG en monde ouvert avec combats tour par tour façon Pokémon, écrit en **Java** avec **LibGDX**.

Projet pédagogique développé en duo avec mon fils — l'objectif est de créer un jeu qu'il peut jouer, **et auquel il peut contribuer en dessinant ses propres personnages**.

---

## ✨ Concept

Un mini-monde ouvert façon Pokémon :

- Le joueur choisit un héros (Chevalier, Princesse, Lancier, ...)
- Il se déplace librement sur une **carte** (clavier)
- En rencontrant un monstre, l'écran bascule en **combat tour par tour**
- Attaque normale, attaque spéciale, potions de soin
- Écran de victoire / défaite, puis retour à la carte

### 🖍️ Fonctionnalité phare : des personnages dessinés à la main !

L'idée centrale du projet : **dessiner un personnage ou un monstre sur papier, prendre une photo, et l'intégrer automatiquement au jeu** sous forme de sprite pixel art — fond supprimé, recadré, prêt à combattre !

```
Dessin papier 📝 → Photo 📸 → Traitement automatique → Sprite pixel art 🎮
```

C'est ce qui transforme ce projet d'un simple jeu en **outil créatif** : n'importe qui peut créer ses propres héros, monstres, et (bientôt) décors. Le **Dragon**, boss du jeu, est d'ailleurs déjà issu d'un dessin original transformé en pixel art !

---

## 🛠️ Stack technique

- **Java 17+**
- **LibGDX** (moteur de jeu 2D : rendu, écrans, entrées clavier, caméra)
- **Jackson** (chargement des données JSON : héros, monstres)
- **Gradle** (build, géré automatiquement par LibGDX)
- **JUnit 5** (tests de la logique de combat)

### D'où vient ce projet ?

Le projet a démarré en **Swing** (interface graphique du JDK), ce qui a permis de poser une base solide et bien testable : modèles métier (`Personnage`, `Heros`, `Monstre`, `Attaque`), logique de combat (`CombatService`), et chargement des données (`repository`).

Pour le monde ouvert (déplacement fluide, tilemaps, animations, caméra suivant le héros), **LibGDX** est beaucoup plus adapté qu'une interface Swing classique — c'est un véritable moteur de jeu 2D, tout en restant 100% Java.

**Tout le code métier (`model`, `combat`, `repository`) est réutilisé sans changement** depuis la version Swing — seule la couche d'affichage a été reconstruite avec LibGDX.

---

## 🏗️ Architecture

LibGDX organise un projet en **modules** : `core` (code partagé, indépendant de la plateforme) et `desktop` (lanceur pour Windows/Mac/Linux).

```
mon-royaume-dessine-fantasy/
├── core/
│   └── src/main/java/com/royaumedessine/fantasy/
│       ├── model/        → Personnage (abstrait), Heros, Monstre, Attaque, TypeCase
│       ├── combat/       → CombatService, ResultatTour
│       ├── repository/   → HerosRepository, JsonHerosRepository
│       │                    └── json/ (dto, mapper)
│       ├── screens/      → MenuScreen, CarteScreen, CombatScreen, ResultatScreen
│       └── MonRoyaumeGame.java   (point d'entrée, classe Game LibGDX)
├── desktop/
│   └── src/main/java/.../DesktopLauncher.java
└── assets/
    ├── images/           → sprites des personnages et monstres
    └── data/             → heros.json, monstres.json
```

- **model/** et **combat/** : objets métier purs, aucune dépendance à LibGDX — testables indépendamment
- **repository/** : chargement des données JSON (pattern Repository : interface + implémentation JSON)
- **screens/** : un `Screen` LibGDX par écran du jeu (menu, carte, combat, résultat)

Cette séparation permet de tester la logique de combat **sans lancer la moindre fenêtre**.

---

## ▶️ Lancer le projet

Prérequis : **Java 17+** installé.

Avec IntelliJ IDEA :
1. Ouvrir le dossier du projet (Gradle est détecté automatiquement)
2. Lancer la configuration **DesktopLauncher** (module `desktop`)

En ligne de commande :
```bash
./gradlew desktop:run
```

---

## 🎮 Comment jouer

1. Choisis ton héros
2. Déplace-toi sur la carte avec les flèches directionnelles
3. Approche-toi d'un monstre pour démarrer un combat
4. **Attaquer** : attaque normale | **Coup Puissant** : attaque spéciale (plus de dégâts)
5. Le combat se termine quand l'un des deux est vaincu — retour à la carte ensuite

---

## 🧙 Héros jouables

| Nom | Type | PV Max | Attaque | Défense | Attaque spéciale |
|---|---|---|---|---|---|
| Ethan | Chevalier | 120 | 15 | 10 | Coup puissant |
| Raphael | Lancier | 100 | 18 | 7 | Charge |
| Camille | Princesse | 80 | 20 | 5 | Éclat royal |

## 🐉 Monstres

| Nom | Niveau | PV Max | Attaque | Défense |
|---|---|---|---|---|
| Squelette | 1 | 90 | 14 | 8 |
| Orc Élite | 2 | 110 | 16 | 9 |
| Orc Monture | 3 | 130 | 18 | 10 |
| Dragon (boss) | 4 | 150 | 20 | 12 |

---

## 🗺️ Feuille de route

- [x] Logique de combat (modèles + CombatService, testée)
- [x] Repository JSON pour les héros (Jackson + pattern Repository)
- [x] Première version jouable en Swing (preuve de concept)
- [x] Boss Dragon créé à partir d'un dessin original
- [ ] **Migration vers LibGDX** : nouveau projet, réutilisation de `model`/`combat`/`repository`
- [ ] Écran de menu (choix du héros) en LibGDX
- [ ] **Carte / overworld** : tilemap, déplacement fluide, caméra
- [ ] **Transition carte ↔ combat** : rencontre d'un monstre → écran de combat → retour à la carte
- [ ] Monstres en JSON (même pattern que les héros)
- [ ] 🖍️ **Éditeur de personnages** : photo → suppression du fond → pixel art → nouveau sprite jouable
- [ ] Inventaire et potions
- [ ] Décors personnalisés (même principe que les personnages dessinés)
- [ ] Déploiement sur Raspberry Pi avec écran tactile

---

## 🎨 Crédits

- Sprites de base : [Tiny RPG Character Asset Pack](https://zerie.itch.io/tiny-rpg-character-asset-pack) par **Zerie** (itch.io)
- Personnages additionnels (Princesse, Dragon) : créés/recolorés à la main ✏️

---

## 📄 Licence

Projet personnel / éducatif. Les assets graphiques tiers conservent leur licence d'origine (voir leur page itch.io respective).

---

## 🔧 Commandes Gradle utiles

Ce projet utilise [Gradle](https://gradle.org/) (wrapper inclus : `./gradlew`).

- `./gradlew lwjgl3:run` : lance le jeu (desktop)
- `./gradlew core:compileJava` : compile uniquement le module `core`
- `./gradlew build` : compile et construit les archives de tous les modules
- `./gradlew clean` : supprime les dossiers `build/`
- `./gradlew lwjgl3:jar` : génère un jar exécutable (`lwjgl3/build/libs`)

Drapeaux utiles : `--continue` (continuer malgré les erreurs), `--offline` (utiliser le cache), `--refresh-dependencies` (forcer la revalidation des dépendances).

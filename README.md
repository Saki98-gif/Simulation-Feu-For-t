# Simulation-Feu-Foret
## Description
Ce projet est une **simulation de la propagation d’un feu de forêt** développée en Java.  
La forêt est représentée par une **grille de cellules**, chaque cellule correspondant à un arbre, et elle peut être :
-  **NORMAL** : arbre sain  
- **FEU** : arbre en feu  
- **CENDRES** : arbre brûlé
- Le feu se propage **aléatoirement** aux cellules voisines(Haut, Bas, Gauche ou Droite) selon une probabilité configurable (P), et chaque cellule qui brûle se transforme en cendres.
- La simulation s’arrête quand il n’y a plus de case en feu.
## Obectif 
L’objectif principal est de créer un **modèle flexible et modulable**, capable de s’adapter facilement à différents scénarios. Ce projet illustre la **conception orientée objet**, la **gestion progressive d’une simulation** et la **modularité du code**.
Grâce à un fichier `config.txt` contenant les paramètres essentiels — tels que la **taille de la forêt**, la **probabilité de propagation** et les **positions initiales des feux** — il est possible de tester rapidement plusieurs scénarios et d’observer le comportement de la simulation **sans modifier le code source principal**.

## Fonctionnalités
- Le temps est discret : Simulation **étape par étape** de la propagation du feu.  
- Paramètres configurables :  
  - Taille de la grille (hauteur, largeur)   
  - Probabilité de propagation  (P)
  - Cellules initialement en feu  (positions initiales)
  - Code modulaire et extensible
## Choix architecturaux 
- Package `model/` : classes `Etat.java (Enum:NORMAL, FEU, CENDRES) )`, `Cellule.java (avec un attribut  de type Etat)`, `Foret.java (avec un attribut : grille de Cellule (2D) '  
- Package `simulation/` : classe principale `Simulation.java` → boucle principale de simulation  
- `utils/` : classe 'ConfigReader.java ' pour la lecture des fichiers de configuration  `config.txt` / `, Récupérer rapidement les valeurs à l’aide de leurs clés.
- Fichier de configuration des **paramètres initiaux** : config.txt, Stocker sous forme de **HashMap**, avec des **clés** et des **valeurs**.

## Usage
1. Configurer les paramètres dans le fichier de configuration 'config.txt'.  
2. Exécuter `Simulation.java` depuis Eclipse .  
3. Observer la propagation du feu étape par étape dans la console.
## conslusion
Cette approche garantit une solution **adaptable**, **modulaire** et **évolutive**.

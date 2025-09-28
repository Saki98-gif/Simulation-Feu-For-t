# Simulation-Feu-For-t
## Description
Ce projet est une **simulation de la propagation d’un feu de forêt** développée en Java.  
La forêt est représentée par une **grille de cellules**, où chaque cellule peut être :
-  **NORMAL** : arbre sain  
- **FEU** : arbre en feu  
- **CENDRES** : arbre brûlé
Le feu se propage **aléatoirement** aux cellules voisines(Haut, Bas, Gauche ou Droite) selon une probabilité configurable (P), et chaque cellule qui brûle se transforme en cendres.
## Fonctionnalités
- Simulation **étape par étape** de la propagation du feu.  
- Paramètres configurables :  
  - Taille de la grille (hauteur, largeur)   
  - Probabilité de propagation  (P)
  - Cellules initialement en feu  (positions initiales)
  - Code modulaire et extensible
## Structure du projet
- `model/` : classes `Cellule.java`, `Foret.java`, `Etat.java (Enum)`  
- `simulation/` : classe principale `Simulation.java`  
- `utils/` : classes pour la lecture des fichiers de configuration  `config.txt` / `
## Usage
1. Configurer les paramètres dans le fichier de configuration 'config.txt'.  
2. Exécuter `Simulation.java` depuis Eclipse .  
3. Observer la propagation du feu étape par étape dans la console.

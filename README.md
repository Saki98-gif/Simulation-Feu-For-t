# Simulation-Feu-Foret
## Description
Ce projet est une **simulation de la propagation d’un feu de forêt** développée en Java.  
La forêt est représentée par une **grille de cellules**, chaque cellule correspondant à un arbre, et elle peut être :
-  **NORMAL** : arbre sain  
- **FEU** : arbre en feu  
- **CENDRES** : arbre brûlé
- Le feu se propage **aléatoirement** aux cellules voisines(Haut, Bas, Gauche ou Droite) selon une probabilité configurable (P), et chaque cellule qui brûle se transforme en cendres.
- La simulation s’arrête quand il n’y a plus de case en feu.
## Objectif 
L’objectif principal est de créer un **modèle flexible et modulable**, capable de s’adapter facilement à différents scénarios. Ce projet illustre la **conception orientée objet**, la **gestion progressive d’une simulation** et la **modularité du code**.
Grâce à un fichier `config.txt` contenant les paramètres essentiels — tels que la **taille de la forêt h lignes × l colonnes)**, la **probabilité de propagation** et les **positions initiales des feux** — il est possible de tester rapidement plusieurs scénarios et d’observer le comportement de la simulation **sans modifier le code source principal**.

## Fonctionnalités
- Le temps est discret : Simulation **étape par étape** de la propagation du feu.  
- Paramètres configurables :  
  - Taille de la forêt (hauteur, largeur)   
  - Probabilité de propagation  (P)
  - Cellules initialement en feu  (positions initiales)
  - Code modulaire et extensible
## Choix architecturaux 
- Package `model/` : avec les classes suivantes :
    - `Etat.java` : Enum:NORMAL, FEU, CENDRES 
    - `Cellule.java`: avec un attribut  de type Etat
    - `Foret.java` : avec un attribut : grille de Cellule (2D)  
- Package `simulation/` : classe principale `Simulation.java` → boucle principale de simulation  
- `utils/` : classe 'ConfigReader.java ' pour la lecture des fichiers de configuration  `config.txt` / `, Récupérer rapidement les valeurs à l’aide de leurs clés.
- Fichier de configuration des **paramètres initiaux** : config.txt, Stocker sous forme de **HashMap**, avec des **clés** et des **valeurs**.
 ### Exemple de configuration
`
hauteur=10 
largeur=10
P=0.3
initialFeu=0,0;1,2;3,4
`
## Usage
1. Configurer les paramètres dans le fichier de configuration 'config.txt'.  
2. Exécuter `Simulation.java` depuis Eclipse .  
3. Observer la propagation du feu étape par étape dans la console.

## Logique principale de la simulation
## 1. Initialisation
- Lire les paramètres (hauteur, largeur, P, positions initiales des cellules en feu) depuis le fichier de configuration          (config.txt).
- Créer la grille représentant la forêt.
- Placer les feux initiaux sur les cellules spécifiées.
## 2. Boucle de simulation
- A **t = 0** : tant qu’il reste des cellules en feu, répéter les étapes suivantes : <br>
                - Mise à jour des cellules en feu : chaque cellule en feu devient cendres.<br>
                - Propagation du feu : pour chaque cellule adjacente normale :<br>
                    Vérifier que la cellule se trouve bien à l’intérieur des limites de la grille, en prenant soin des bords.<br>
                    Générer un nombre aléatoire entre 0 et 1.<br>
                    Si ce nombre est inférieur à la probabilité P, mettre la cellule en feu: Cela simule le caractère                             aléatoire et incertain de la propagation (influences du vent, humidité…).<br>
                - Affichage de la grille : mettre à jour l’état des cellules (nouvelles cellules en feu, cendres, cellules                               normales).<br>

-Pause courte (optionnelle) : permet de visualiser la propagation étape par étape et d’éviter que le feu se propage, dans la même étape, à partir des cellules qui viennent juste de brûler.<br>

## 3. Fin de la simulation
- Lorsqu’il n’y a plus de case en feu, afficher le message : "Fin de simulation, aucune case en feu".

## Conclusion
Cette approche garantit une solution **adaptable**, **modulaire** et **évolutive**.

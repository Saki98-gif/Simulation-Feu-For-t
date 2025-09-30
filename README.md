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
Grâce à un fichier `config.txt` contenant les paramètres essentiels, il est possible de tester rapidement plusieurs scénarios et d’observer le comportement de la simulation **sans modifier le code source principal**.

## Méthodologie de résolution de problème
- Le projet est organisé selon un modèle orienté objet avec trois packages principaux : <br>
  i. Package model/ : représente les entités fondamentales de la simulation,chaque classe a une responsabilité claire. <br>
     - Etat : énumération des états possibles d’une cellule "NORMAL, FEU, ou CENDRES". Elle ne gère que son état.<br>
     - Cellule : un arbre pouvant se trouver dans l’un des états précédents. Elle gère les interactions avec les autres cellules.<br>
     -  Foret : la grille contenant toutes les cellules et gérant la propagation du feu. Elle orchestre le déroulement de la simulation.<br>
 ii. Package simulation/ : contient la classe principale qui lance et contrôle la simulation<br>
    - Simulation : gère la boucle temporelle, l’affichage étape par étape, et l’arrêt lorsque le feu est éteint.<br>
iii. Package utils/ : fournit des paramètres  pour la configuration<br>
   - ConfigReader : lit les paramètres de simulation depuis un fichier de configuration (config.txt) afin de rendre le programme flexible et modulable.<br>
- Identifier les étapes clés : initialisation, propagation, affichage, arrêt.
- Définir les paramètres externes (fichier config.txt) pour garder la flexibilité.
- Le temps est discret : Simulation **étape par étape** de la propagation du feu.
- Utilisation du hasard (classe Random) pour modéliser la probabilité réaliste de propagation.
- Paramètres configurables :  
  - Taille de la forêt (hauteur, largeur)   
  - Probabilité de propagation  (P)
  - Cellules initialement en feu  (positions initiales)
  - Code modulaire et extensible
## Choix architecturaux : 
Cette séparation en packages rend le code plus lisible, facilite la maintenance et permet d’ajouter de nouvelles fonctionnalités sans modifier les parties existantes.
- Package `model/` : avec les classes de données suivantes :
    - `Etat.java` : Enum: NORMAL, FEU, CENDRES 
    - `Cellule.java`: avec un attribut  de type Etat, et les méthodes suivantes : **bruler()** qui change son état en cendres, **EstEnFeu()** qui vérifie si elle est en feu, et **MettreEnFeu()** qui fait passer son état de normal à en feu.
    - `Foret.java` : avec un attribut grille de Cellule (2D), et les méthodes suivantes : **afficher()** qui affiche la grille à chaque étape de la simulation, **propagerFeu(P)** qui calcule la propagation du feu pour chaque cellule en feu, après avoir vérifié l’existence des cellules adjacentes afin de ne pas dépasser les limites de la grille graçe à la fonction **mettreEnFeu**.
- Package `simulation/` : avec la classe principale :
     - `Simulation.java`  l'attribut de type **objet** Forêt, boucle principale de la simulation qui, initialement, lit le fichier de configuration, crée un objet Forêt, place les feux initiaux, affiche la grille, lance la simulation, puis affiche l’évolution jusqu’à la fin (lorsqu’aucune cellule n’est en feu).
- `utils/` : avec la classe des fonctions utilitaires: <br>
   - 'ConfigReader.java ' pour la lecture des fichiers de configuration  `config.txt` / `, Récupérer rapidement les valeurs à l’aide de leurs clés (h, l, P, positions initiales).
- Fichier de configuration des **paramètres initiaux** : config.txt, Stocker dans une structure de données **HashMap**, sous forme de paires **<clé, valeur>**.
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
## Outils utilisés
- **Langage** : Java (JDK 25)  
- **IDE** : Eclipse IDE  
- **Gestion de versions** : Git & GitHub  
- **OS** : Windows 11

## Conclusion
Cette approche garantit une solution **adaptable**, **modulaire** et **évolutive**.

## Dépôt GitHub
Le projet est disponible sur GitHub : [Simulation-Feu-Foret](https://github.com/Saki98-gif/Simulation-Feu-For-t)

package model;
import java.util.Random;

public class Foret {

	//Attributs
	private int hauteur;          // nombre de lignes
	private int largeur;          // nombre de colonnes
	private Cellule[][] grille;   // matrice de cellules
	private Random random = new Random(); // pour la propagation aléatoire

	public Foret(int hauteur, int largeur) {
	    this.hauteur = hauteur;
	    this.largeur = largeur;
	    grille = new Cellule[hauteur][largeur];

	    //Initialiser la grille avec des cellules normales
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            grille[i][j] = new Cellule(); // état NORMAL par défaut
	        }
	    }
	}
	
	// Ajoute getter 
		public int getHauteur() {
		    return this.hauteur;
		}

		public int getLargeur() {
		    return this.largeur;
		}

		// constructeur 
		public Cellule getCellule(int ligne, int colonne) {
		    if (ligne >= 0 && ligne < hauteur && colonne >= 0 && colonne < largeur) {
		        return grille[ligne][colonne];
		    } else {
		        return null; // ou lever une exception si tu préfères
		    }
		}
	
	//Vérifier que la cellule existe dans la grille avant de la mettre en feu, afin de ne pas dépasser les limites de la grille
	public void mettreEnFeu(int ligne, int colonne) {
	    if (ligne >= 0 && ligne < hauteur && colonne >= 0 && colonne < largeur) {
	        grille[ligne][colonne].mettreEnFeu(); //Mettre le feu à une cellule
	    }
	}
	
	//Propagation du feu aux cellules adjacentes	
	public boolean propagation(double probabilite) {
	    boolean presentFeu = false;
	    Cellule[][] nouvelleGrille = new Cellule[hauteur][largeur];

	    // Copier la grille actuelle : éviter que le feu se propage, dans la même étape, à partir des cellules qui viennent juste de brûler
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            nouvelleGrille[i][j] = new Cellule();
	            nouvelleGrille[i][j].setEtat(grille[i][j].getEtat());
	        }
	    }

	    // Parcourir toutes les cellules
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            if (grille[i][j].estEnFeu()) {
	                // La cellule actuellement en feu devient cendres
	                nouvelleGrille[i][j].bruler();

	                // Propagation aux 4 cellules voisines normales
	                if (i > 0 && grille[i-1][j].getEtat() == Etat.NORMAL && random.nextDouble() < probabilite)//Haut
	                    nouvelleGrille[i-1][j].mettreEnFeu();
	                if (i < hauteur - 1 && grille[i+1][j].getEtat() == Etat.NORMAL && random.nextDouble() < probabilite)//Bas
	                    nouvelleGrille[i+1][j].mettreEnFeu();
	                if (j > 0 && grille[i][j-1].getEtat() == Etat.NORMAL && random.nextDouble() < probabilite)//Gauche
	                    nouvelleGrille[i][j-1].mettreEnFeu();
	                if (j < largeur - 1 && grille[i][j+1].getEtat() == Etat.NORMAL && random.nextDouble() < probabilite)//Droite
	                    nouvelleGrille[i][j+1].mettreEnFeu();
	               
	                //2ême méthode avec affichage d’un nombre aléatoire
	                  //double r;
		             // Haut
	                // Haut
	                /*
	                if (i > 0 && grille[i-1][j].getEtat() == Etat.NORMAL) {
	                    r = random.nextDouble();
	                    System.out.println("Cellule (" + i + "," + j + ") → Haut : random=" + r);
	                    if (r < probabilite) nouvelleGrille[i-1][j].mettreEnFeu();
	                }

	                // Bas
	                if (i < hauteur - 1 && grille[i+1][j].getEtat() == Etat.NORMAL) {
	                    r = random.nextDouble();
	                    System.out.println("Cellule (" + i + "," + j + ") → Bas : random=" + r);
	                    if (r < probabilite) nouvelleGrille[i+1][j].mettreEnFeu();
	                }

	                // Gauche
	                if (j > 0 && grille[i][j-1].getEtat() == Etat.NORMAL) {
	                    r = random.nextDouble();
	                    System.out.println("Cellule (" + i + "," + j + ") → Gauche : random=" + r);
	                    if (r < probabilite) nouvelleGrille[i][j-1].mettreEnFeu();
	                }

	                // Droite
	                if (j < largeur - 1 && grille[i][j+1].getEtat() == Etat.NORMAL) {
	                    r = random.nextDouble();
	                    System.out.println("Cellule (" + i + "," + j + ") → Droite : random=" + r);
	                    if (r < probabilite) nouvelleGrille[i][j+1].mettreEnFeu();
	                    
	                }
	                System.out.println("----------------------------\n");
                     */
	            }
	        }
	    }

	    // Vérifier s’il reste du feu dans la nouvelle grille
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            if (nouvelleGrille[i][j].estEnFeu()) {
	                presentFeu = true; //déclarr la Cellule en feu 
	                break;
	            }
	        }
	        if (presentFeu) break; //Condition d’arrêt : aucune cellule en feu 
	    }

	    // Mettre à jour la grille à chaque étape 
	    grille = nouvelleGrille;
	    return presentFeu;
	}

	//Afficher la grille
	public void afficher() {
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            System.out.print(grille[i][j] + " "); //Utiliser toString() de la classe Cellule pour un affichage plus clair
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

	
	
	
	
	
	
	
	
	
	
	


}

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

	    //Initialise la grille avec des cellules normales 
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            grille[i][j] = new Cellule(); // état NORMAL par défaut
	        }
	    }
	}
	
	//Vérifie que la cellule existe dans la grille avant de la mettre en feu.
	public void mettreEnFeu(int ligne, int colonne) {
	    if (ligne >= 0 && ligne < hauteur && colonne >= 0 && colonne < largeur) {
	        grille[ligne][colonne].mettreEnFeu(); //mettre le feu à une cellule
	    }
	}
	
	//propagation du feu
	
	public boolean propagation(double probabilite) {
	    boolean Presentfeu = false;
	    //une nouvelle grille temporaire pour éviter que le feu se propage plusieurs fois dans la même étape
	    Cellule[][] nouvelleGrille = new Cellule[hauteur][largeur];

	    // Copier la grille actuelle
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
	                // La cellule devient cendres
	                nouvelleGrille[i][j].bruler();
	              //  double r;
	             // Haut
		               /* if (i > 0) {
		                    r = random.nextDouble();
		                    System.out.println("Cellule (" + i + "," + j + ") → Haut : random=" + r);
		                    if (r < probabilite) nouvelleGrille[i-1][j].mettreEnFeu();
		                }
		                */
	                // Propagation aux 4 voisins
	               if (i > 0 && random.nextDouble() < probabilite) nouvelleGrille[i-1][j].mettreEnFeu(); // Haut
	                if (i < hauteur - 1 && random.nextDouble() < probabilite) nouvelleGrille[i+1][j].mettreEnFeu();  // Bas
	                if (j > 0 && random.nextDouble() < probabilite) nouvelleGrille[i][j-1].mettreEnFeu();// Gauche
	                if (j < largeur - 1 && random.nextDouble() < probabilite) nouvelleGrille[i][j+1].mettreEnFeu();  // Droite
	            }
	            if (nouvelleGrille[i][j].estEnFeu()) Presentfeu = true;//déclarr la Cellule en feu 
	        }
	    }

	    // Mettre à jour la grille
	    grille = nouvelleGrille;
	    return Presentfeu; // vrai s'il reste du feu
	    
	    
	}
	//afficher la grille
	public void afficher() {
	    for (int i = 0; i < hauteur; i++) {
	        for (int j = 0; j < largeur; j++) {
	            System.out.print(grille[i][j] + " "); // utilise toString() de Cellule
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

	
	
	
	
	
	
	
	
	
	
	


}

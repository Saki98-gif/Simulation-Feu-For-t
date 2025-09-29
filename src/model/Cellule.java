package model;

public class Cellule {
	
	 private Etat etat;
	 
	 public Cellule() { //constructeur 
	        this.etat = Etat.NORMAL; // Par défaut, la cellule est normale
	    }
	 public Etat getEtat() { //getter
	        return etat;
	    }
	 public void setEtat(Etat etat) { //setter
	        this.etat = etat;
	    }
	//Vérifier si la cellule est actuellement en feu.
	 public boolean estEnFeu() { 
		    return etat == Etat.FEU;
		}
	// Transformer une cellule en cendres, c’est-à-dire qu’elle était en feu à l’étape précédente.
	 public void bruler() {
		    this.etat = Etat.CENDRES;
		}
    //Mettre une cellule en feu si elle n’a pas encore brûlé
	 public void mettreEnFeu() {
		    if (etat == Etat.NORMAL) {
		        this.etat = Etat.FEU;
		    }
		}
	 
	 @Override
	 public String toString() {
	     switch (etat) {
	     case NORMAL:  return "\u001B[32m🌲\u001B[0m";        // vert pour arbres
	     case FEU:     return "\u001B[31m🔥\u001B[0m";        // rouge pour feu
	     case CENDRES: return "\u001B[38;5;240m⬛\u001B[0m";  // gris pour cendres
	         default:      return "?";
	     }
	 }

}

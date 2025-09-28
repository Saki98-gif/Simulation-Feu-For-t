package model;

public class Cellule {
	
	 private Etat etat;
	 
	 public Cellule() {
	        this.etat = Etat.NORMAL; // Par défaut, la cellule est normale
	    }
	 public Etat getEtat() {
	        return etat;
	    }
	 public void setEtat(Etat etat) {
	        this.etat = etat;
	    }
	//vérifier si la cellule est actuellement en feu
	 public boolean estEnFeu() { 
		    return etat == Etat.FEU;
		}
	// transformer une cellule en cendres, c’est-à-dire que à l'étape précedente était en feu
	 public void bruler() {
		    this.etat = Etat.CENDRES;
		}
//mettre une cellule en feu si elle n’a pas déjà brûlé.
	 public void mettreEnFeu() {
		    if (etat == Etat.NORMAL) {
		        this.etat = Etat.FEU;
		    }
		}
	 
	 @Override
	 public String toString() {
	     switch (etat) {
	         case NORMAL:  return "\u001B[32m🌲\u001B[0m"; // vert pour arbres
	         case FEU:     return "\u001B[31m🔥\u001B[0m"; // rouge pour feu
	         case CENDRES: return "\u001B[30m⬛\u001B[0m"; // gris pour cendres
	         default:      return "?";
	     }
	 }

}

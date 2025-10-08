package view;

import model.Foret;
import javax.swing.*;
import java.awt.*;

/**
 * Classe ForetPanel : composant graphique responsable de l'affichage d'une forêt.
 * Elle hérite de JPanel: est une classe de Swing,zone rectangulaire (bibliothèque graphique de Java) 
 * et dessine la grille représentant les cellules de la forêt.
 */
public class ForetPanel extends JPanel {

  
    private Foret foret;   // objet Foret contenant l'état de chaque cellule
    private final int cellSize = 20;  // Taille en pixels d'une cellule 

    
    // Constructeur du panneau.
    public ForetPanel(Foret foret) {
        this.foret = foret;

        // Définit la taille du panneau en fonction de la taille de la forêt et de la taille des cellules
        setPreferredSize(new Dimension(foret.getLargeur() * cellSize, foret.getHauteur() * cellSize));
        setBackground(Color.WHITE);  // Définit la couleur d’arrière-plan (blanc)
    }

    // Méthode appelée automatiquement par Swing pour redessiner le composant.
     // @param g : objet Graphics utilisé pour dessiner sur le panneau.
     
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Efface l'ancien contenu avant de redessiner

        // Parcourt toutes les cellules de la forêt
        for (int i = 0; i < foret.getHauteur(); i++) {
            for (int j = 0; j < foret.getLargeur(); j++) {

                // Change la couleur selon l’état de la cellule
                switch (foret.getCellule(i, j).getEtat()) {
                    case NORMAL -> g.setColor(Color.GREEN);  // vert
                    case FEU -> g.setColor(Color.RED);       //rouge
                    case CENDRES -> g.setColor(Color.GRAY);  //gris
                }

                // Dessine le carré rempli représentant la cellule
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                // Dessine les bordures noires autour de chaque cellule
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

  
     // Appelle repaint() pour redessiner la forêt ==> Met à jour l'affichage du panneau.
    public void majAffichage() {
        repaint();
    }

    
     // Setter pour changer la forêt affichée (utile en cas de "reset" ou nouvelle simulation).
    //  @param foret : nouvelle forêt à afficher.
     
    public void setForet(Foret foret) {
        this.foret = foret;
        repaint(); // Redessine immédiatement la nouvelle forêt
    }
}

package view;

import model.Foret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *  FenetreSimulation : représente la fenêtre principale de la simulation de propagation d’un feu de forêt.
 * Elle gère à la fois :
 *  - l'affichage de la forêt (avec ForetPanel)
 *  - les contrôles de simulation (boutons, Curseur)
 */
public class FenetreSimulation extends JFrame {

    // === Composants graphiques ===
    private ForetPanel panel;              // Zone graphique qui affiche la forêt
    private JButton btnStartPause, btnReset, btnAppliquer; // Boutons de contrôle
    private JSlider sliderProba;           // Curseur pour ajuster la probabilité de propagation
    private JTextField txtHauteur, txtLargeur, txtPositions; // Champs de configuration (h,l et feux initiaux)
    private boolean enCours = false;       // Indique si la simulation tourne
    private Thread simulationThread;       // Thread séparé pour exécuter la simulation sans bloquer l’interface

    // === Données du modèle ===
    private Foret foret;                   // Modèle représentant la grille de cellules
    private double probabilite;            // Probabilité de propagation du feu
    private int hauteurInitiale;           // h initiale 
    private int largeurInitiale;           // L initiale 
    private String positionsInitiales;     // Positions des cellules initialement en feu 

    
     //Constructeur de la fenêtre de simulation.
     
    public FenetreSimulation(Foret foret, double probaInitiale, int hauteur, int largeur, String initialFeu) {
        this.foret = foret;
        this.probabilite = probaInitiale;
        this.hauteurInitiale = hauteur;
        this.largeurInitiale = largeur;
        this.positionsInitiales = initialFeu;

        // Configuration de la fenêtre 
        setTitle("Simulation Feu de Forêt");//titre de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //button Fermer la fenêtre
      //définir la manière dont les composants sont organisés à l’intérieur de la foret (generalement 5 zones)
        setLayout(new BorderLayout());

        // Zone graphique 
        panel = new ForetPanel(this.foret); //le paneau de la foret 
        add(panel, BorderLayout.CENTER); // le dessin en centre

        // Panneau inférieur : Contrôles de la simulation
        JPanel controls = new JPanel(new FlowLayout());//crée un panneau (JPanel) qui va contenir plusieurs composants

        btnStartPause = new JButton("▶ Démarrer");//Button Démarrer
        btnStartPause.addActionListener(this::toggleSimulation); // Lance ou met en pause la simulation

        btnReset = new JButton("🔁 Réinitialiser");//Button REST
        btnReset.addActionListener(this::resetSimulation); // Revient à l’état initial

        // Slider pour modifier la probabilité de propagation du feu
        sliderProba = new JSlider(0, 100, (int) (probaInitiale * 100));//Crée un slider de 0 à 100 avec une valeur initiale
        sliderProba.setMajorTickSpacing(20);//Ajoute une graduation tous les 20
        sliderProba.setPaintTicks(true);//Affiche les graduations
        sliderProba.setPaintLabels(true);//Affiche les chiffres sous les graduations
        sliderProba.addChangeListener(e -> probabilite = sliderProba.getValue() / 100.0);//	Met à jour la probabilité quand on bouge le curseur

        controls.add(new JLabel("Probabilité :"));// Ajoute un label Probabilité dans le panneau de contrôle
        controls.add(sliderProba);// Ajoute le slider pour régler la probabilité 
        controls.add(btnStartPause);// Ajoute le bouton qui permet de démarrer ou mettre en pause la simulation
        controls.add(btnReset);// Ajoute le bouton qui permet de réinitialiser la simulation

        add(controls, BorderLayout.SOUTH);// Place le panneau complet "controls" en bas de la fenêtre

        //  Panneau supérieur : Configuration dynamique (H,L et feux initiaux) 
        JPanel configPanel = new JPanel(new FlowLayout());
     // Création des champs texte pour saisir les parametres
        txtHauteur = new JTextField(String.valueOf(hauteurInitiale), 3);//hauteur environ 3 caractères
        txtLargeur = new JTextField(String.valueOf(largeurInitiale), 3);//largeur environ 3 caractères
        txtPositions = new JTextField(positionsInitiales, 10);//positions initiales des feux environ 10 caractères
     
        // Création du bouton "Appliquer" pour valider la nouvelle configuration
        btnAppliquer = new JButton("Appliquer");
     // Ajout d'un écouteur : quand on clique, la méthode appliquerConfiguration() est appelée
        btnAppliquer.addActionListener(e -> appliquerConfiguration());

     // Ajout des composants dans le panneau configPanel avec leur label
        configPanel.add(new JLabel("Hauteur :")); // texte descriptif
        configPanel.add(txtHauteur);//H
        configPanel.add(new JLabel("Largeur :"));
        configPanel.add(txtLargeur);//L
        configPanel.add(new JLabel("Positions feu :"));
        configPanel.add(txtPositions);//positions feu
        configPanel.add(btnAppliquer);//outon pour appliquer la config

     // Placement du panneau de configuration en haut de la fenêtre
        add(configPanel, BorderLayout.NORTH);

        // Finalisation de la fenêtre
        pack(); // Ajuste la taille automatique selon les composants
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true);//fenetre visible à l’écran.
    }

    
   // Applique une nouvelle configuration saisie dans les champs texte :
     
    private void appliquerConfiguration() {
        try {
            int nouvelleHauteur = Integer.parseInt(txtHauteur.getText());
            int nouvelleLargeur = Integer.parseInt(txtLargeur.getText());
            String nouvellesPositions = txtPositions.getText();

            this.hauteurInitiale = nouvelleHauteur;
            this.largeurInitiale = nouvelleLargeur;
            this.positionsInitiales = nouvellesPositions;

            // Nouvelle forêt
            this.foret = new Foret(hauteurInitiale, largeurInitiale);

            // Active les feux initiaux
            String[] positions = positionsInitiales.split(";");
            for (String pos : positions) {
                String[] coords = pos.split(",");
                this.foret.mettreEnFeu(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            }

            // Met à jour l’affichage
            panel.setForet(this.foret);//MAJ le panneau graphique avec la nouvelle forêt
            panel.majAffichage();// MAJ l'affichage pour montrer  les changements
            pack(); //Ajuste automatiquement la taille de la fenêtre en fonction des composants
            JOptionPane.showMessageDialog(this, "✅ Configuration appliquée !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Valeurs invalides !");
        }
    }

    
     //Lance ou met en pause la simulation (appelé quand on clique sur Démarer ou Pause).
     
    private void toggleSimulation(ActionEvent e) {
        if (enCours) {                        // Si la simulation est déjà en cours
            enCours = false;                  // On met la simulation en pause
            btnStartPause.setText("▶ Reprendre"); //  le texte du bouton  indique "Reprendre"
        } else {                              // Si la simulation est arrêtée ou en pause
            enCours = true;                   // On démarre ou reprend la simulation
            btnStartPause.setText("⏸ Pause");    //  le texte du bouton  indique "Pause"
            lancerSimulation();               //  On lance la simulation dans un thread séparé
        }
    }


    
     //Réinitialise la simulation avec les valeurs de départ.
     
    private void resetSimulation(ActionEvent e) {
        enCours = false;                         //  On arrête la simulation si elle était en cours
        btnStartPause.setText("▶ Démarrer");    // On remet le texte du bouton "Démarrer"

        //  Recréation d'une nouvelle forêt avec les dimensions initiales
        this.foret = new Foret(hauteurInitiale, largeurInitiale);

        // Remise en feu des cellules initiales
        String[] positions = positionsInitiales.split(";");  // Sépare les positions par ";"
        for (String pos : positions) {
            String[] coords = pos.split(",");               // Sépare x et y par ","
            this.foret.mettreEnFeu(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        }

        // Mise à jour du panneau graphique
        panel.setForet(this.foret);  // On remplace la forêt affichée par la nouvelle
        panel.majAffichage();        // MAJ l'affichage pour montrer la forêt réinitialisée
    }
    /**
     * Lance la simulation dans un thread séparé pour ne pas bloquer l’interface.
     * À chaque étape :
     *  - la méthode foret.propagation(P) fait évoluer l’état des cellules,
     *  - l’affichage est mis à jour,
     *  - la boucle s’arrête quand le feu est totalement éteint.
     */
    private void lancerSimulation() {
        simulationThread = new Thread(() -> {
            boolean feuActif = true; //TQ il reste des cellles en feu
            int etape = 0;

            while (feuActif && enCours) {
                feuActif = foret.propagation(probabilite); // Prpagaton de feu dans la forêt
                panel.majAffichage();   //Affichage MAJ                    
                System.out.println("Étape " + (++etape));

                try {
                    Thread.sleep(500); // Attendre 0.5 s entre chaque étape
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            // Si le feu est éteint
            if (!feuActif) {
                JOptionPane.showMessageDialog(this, "🔥 Le feu est éteint !");
                enCours = false;
                btnStartPause.setText("▶ Rejouer"); 
            }
        });
        simulationThread.start(); // Démarre le thread
    }
}

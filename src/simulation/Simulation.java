package simulation;
import model.Foret;
import utils.ConfigReader;
import java.util.Map;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1️ Lecture du fichier de configuration
        Map<String, String> config = ConfigReader.lireConfig("config.txt");//chemain actuel racine 
        int hauteur = Integer.parseInt(config.get("hauteur"));
        int largeur = Integer.parseInt(config.get("largeur"));
        double probabilite = Double.parseDouble(config.get("probabilite"));
        String initialFeu = config.get("initialFeu");

        //2️ Création de la forêt
        Foret foret = new Foret(hauteur, largeur);
        
     // 3️ Placer les feux initiaux  "0,2;0,1;2,3"
        String[] positions = initialFeu.split(";"); //diviser la chaîne à chaque ; ["0,2", "0,1", "2,3"]
        for (String pos : positions) {
            String[] coords = pos.split(",");//séparer la position en coordonnées x et y avec , ==>coords[0] = "0" coords[1] = "2"
            int x = Integer.parseInt(coords[0]);// x=0
            int y = Integer.parseInt(coords[1]);//y=2
            foret.mettreEnFeu(x, y); //allumer le feu à la cellule (0,2)
        }
        
     // 4️ Affichage initial
        System.out.println("État initial :");
        foret.afficher();
        
     // 5️ Lancer la simulation
        boolean feuActif = true;
        int etape = 0;
        
        while (feuActif) {
            System.out.println("\nÉtape " + (++etape) + " :");
            feuActif = foret.propagation(probabilite); // Propagation du feu
            foret.afficher();//Affichage à chauqe étape 

            try {
                Thread.sleep(1000); // Pause 1 seconde pour visualiser
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n🔥 Le feu est éteint, simulation terminée !");
	}

}

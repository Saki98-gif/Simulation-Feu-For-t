package simulation;
import model.Foret;
import utils.ConfigReader;
import java.util.Map;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1️ Lecture du fichier de configuration
        Map<String, String> config = ConfigReader.lireConfig("config.txt");//chemain actuel racine 
        int hauteur = Integer.parseInt(config.get("hauteur"));//convertir une chaîne de caractères (String) en un entier (int)
        int largeur = Integer.parseInt(config.get("largeur"));
        double probabilite = Double.parseDouble(config.get("probabilite"));//convertir une chaîne de caractères (String) en un double 
        String initialFeu = config.get("initialFeu");

        //2️ Création d'u objet de la forêt
        Foret foret = new Foret(hauteur, largeur);
        
     // 3️ Placer les feux initiaux  Ex: "0,2;0,1;2,3"
        String[] positions = initialFeu.split(";"); //Diviser la chaîne à chaque `;` ["0,2", "0,1", "2,3"]
        for (String pos : positions) {
            String[] coords = pos.split(",");//Séparer la position en coordonnées x et y avec `,` ==>coords[0] = "0" coords[1] = "2"
            int x = Integer.parseInt(coords[0]);// x=0
            int y = Integer.parseInt(coords[1]);//y=2
            foret.mettreEnFeu(x, y); //Mettre la cellule (0,2) en feu
        }
        
     // 4️ Affichage initial
        System.out.println("État initial :");
        foret.afficher();
        
     // 5️ Lancer la simulation
        boolean feuActif = true; 
        int etape = 0;
        
        while (feuActif) { // Tant qu’il reste des cellules en feu
            System.out.println("\nÉtape " + (++etape) + " :");
            feuActif = foret.propagation(probabilite); // Propagation du feu vers les cellules adjacentes 
            foret.afficher();//Affichage de la grille à chaque étape

            try {
                Thread.sleep(1000); // Pause 1 seconde pour visualiser l’étape
            } catch (InterruptedException e) {  //capturer l’exception lorsqu’un thread est interrompu afin que le programme ne plante pas
                e.printStackTrace(); // Affiche la trace de l’erreur dans la console pour savoir la source de l’exception produite.
            }
        }
        System.out.println("\n **Le feu est éteint, simulation terminée !** ");
	}

}

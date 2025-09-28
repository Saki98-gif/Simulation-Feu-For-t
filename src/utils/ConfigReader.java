package utils;

import java.io.BufferedReader; //pour lire le fichier ligne par ligne
import java.io.FileReader;//pour lire le fichier ligne par ligne
import java.io.IOException; //pour gérer les erreurs lors de la lecture.
import java.util.HashMap; //pour stocker les paramètres sous forme de clés et valeurs.
import java.util.Map;//pour stocker les paramètres sous forme de clés et valeurs.

public class ConfigReader {

    // Cette méthode lit le fichier et retourne une Map avec les paramètres
    public static Map<String, String> lireConfig(String cheminFichier) {
        Map<String, String> config = new HashMap<>(); //Creer une HashMap vide pour stocke data

        //try ==> Assure que le fichier sera automatiquement fermé à la fin, même en cas d'erreur
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {//ouvre le fichier +lecture ligne par ligne 
            String ligne;
            while ((ligne = br.readLine()) != null) { //tant qu'il reste des lignes à lire
                ligne = ligne.trim();//enlève les espaces au début et à la fin.
                if (ligne.isEmpty() || ligne.startsWith("#")) continue; // Ignore lignes vides et commentaires
                String[] parts = ligne.split("=");//découper la ligne en deux parties grâce au symbole =
                if (parts.length == 2) {
                    config.put(parts[0].trim(), parts[1].trim());//La paire clé [0]/valeur[1] est ajoutée à la Map.
                }
            }
        } catch (IOException e) {//Si le fichier n'existe pas ou s'il y a un problème de lecture
            System.out.println("Erreur lors de la lecture du fichier config : " + e.getMessage());
        }

        return config;
    }
}

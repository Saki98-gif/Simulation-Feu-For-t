package utils;

import java.io.BufferedReader; // Pour lire le fichier ligne par ligne
import java.io.FileReader;     // Pour lire le fichier ligne par ligne
import java.io.IOException;    // Pour gérer les erreurs lors de la lecture
import java.util.HashMap;      // Pour stocker les paramètres sous forme de clés et valeurs
import java.util.Map;          // Pour stocker les paramètres sous forme de clés et valeurs

public class ConfigReader {

    // Cette méthode lit le fichier et renvoie une Map <clé, valeur> contenant les paramètres
    public static Map<String, String> lireConfig(String cheminFichier) {
        Map<String, String> config = new HashMap<>(); //Créer une HashMap vide pour stocker les données

        //try → Assure que le fichier sera automatiquement fermé à la fin, même en cas d’erreur
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {//Ouvrir le fichier et le lire ligne par ligne 
            String ligne;
            while ((ligne = br.readLine()) != null) { //Tant qu’il reste des lignes à lire
                ligne = ligne.trim();//Supprimer les espaces au début et à la fin
                if (ligne.isEmpty() || ligne.startsWith("#")) continue; // Ignorer les lignes vides et les commentaires
                String[] parts = ligne.split("=");//Découper la ligne en deux parties à l’aide du symbole =
                if (parts.length == 2) {//vérifier que la ligne lue a bien été correctement découpée en deux parties après le split
                    config.put(parts[0].trim(), parts[1].trim());// La paire clé [0] / valeur [1] est ajoutée à la Map
                }
            }
        } catch (IOException e) {//Si le fichier n’existe pas ou qu’il y a un problème de lecture
            System.out.println("Erreur lors de la lecture du fichier config : " + e.getMessage());
        }

        return config;
    }
}

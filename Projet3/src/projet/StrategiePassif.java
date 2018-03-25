package projet;

import java.util.List;

/**
 * @author DU Qiaoqian XU Haoyang
 * La classe StrategiePassif signifie que le joueur virtuel est passif.
 * <p> le joueur virtuel pose la carte s'il a la carte propre
 */
public class StrategiePassif implements Strategie{
	
	private Carte card;
	
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu) {
		
		System.out.println(" tour de "+j.getNom() + "!");
		int index;
		for (index = 0; index < j.getCarteEnMain().size(); index++){       
            if (j.getCarteEnMain().get(index).peuxJouer(top)) 
                break; 
        }

        if (index == j.getCarteEnMain().size()) {
            System.out.println(j.getNom() + " n'a plus de propre carte! Piocher des cartes...");
            j.piocher(cartePaquet.get(0));
            cartePaquet.remove(0);
            card = top;
        } 
        else {       	
        	System.out.println(j.getNom() + " choisit " + j.getCarteEnMain().get(index) + "!");
            card = j.getCarteEnMain().get(index);
            j.getCarteEnMain().remove(index);
        }
        System.out.println(j.getNom() + " possede " + j.getCarteEnMain().size() + " cartes");
        
        return card;
		
	}

}

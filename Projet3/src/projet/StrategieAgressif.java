package projet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DU Qiaoqian XU Haoyang
 * La classe StrategieAgressif signifie que le joueur virtuel est agressif.
 * <p> le joueur virtuel pose la carte selon le nombre de carte du joueur suivant, s'il poss¨¨de moins de 2 cartes, le joueur a une tendence de poser "A" ou "9"; sinon, le joueur va conserve la carte "A" ou "9"
 * 
 */
public class StrategieAgressif implements Strategie{
			
	private ArrayList<Carte> cartePeutJouer = new ArrayList<Carte>();
	private ArrayList<Integer> numeroPeutJouer = new ArrayList<Integer>();  
	private Carte card;
				
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu) {
		
		System.out.println(" turn de "+j.getNom() + "!");
		int index;
		int x;
		//obtenir le nombre de carte en main du joueur suivant 
		int i = jeu.getJoueur().get(jeu.nextTurn()).getCarteEnMain().size();
		//index est la carte choisie
		for(index = 0; index < j.getCarteEnMain().size(); index++){
			 if (j.getCarteEnMain().get(index).peuxJouer(top))
				 break; 
		}
		//si il n'y a pas de carte propre, piocher
		if (index == j.getCarteEnMain().size()) {	        
            System.out.println(j.getNom() + " n'a plus de propre carte! piocher des cartes...");
            j.piocher(cartePaquet.get(0));
            cartePaquet.remove(0);
            card = top;
        } 
		//sinon, choisir la carte 
		else{
			for(index = 0; index < j.getCarteEnMain().size(); index++){
				if (j.getCarteEnMain().get(index).peuxJouer(top)){
					cartePeutJouer.add(j.getCarteEnMain().get(index));
					numeroPeutJouer.add(index);
				}
				// choisir selon le nombre de carte du joueur suivant	
				for(x = 0; x < numeroPeutJouer.size(); x++){
					if(j.getCarteEnMain().get(numeroPeutJouer.get(x)).getValeur() == "A"||j.getCarteEnMain().get(x).getValeur() == "9"){
						if(i>2){
							continue;
						}
						else{
							System.out.println(j.getNom() + " choisit" + j.getCarteEnMain().get(x) + "!");
				            card = j.getCarteEnMain().get(x);
				            j.getCarteEnMain().remove(x);
				            break;
						}
					}
					else{
						if(i>2){
							System.out.println(j.getNom() + " choisit" + j.getCarteEnMain().get(x) + "!");
				            card = j.getCarteEnMain().get(x);
				            j.getCarteEnMain().remove(x);
				            break;
						}
						else{
							continue;
						}
					}	
				}
				if(x == cartePeutJouer.size()){
					System.out.println(j.getNom() + " n'a plus de propre carte! piocher des cartes...");
		            j.piocher(cartePaquet.get(0));
		            cartePaquet.remove(0);
		            card = top;
				}
			}	
		}					
	return card;
	}		
}




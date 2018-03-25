package projet;

import java.util.List;

/**
 * @author DU Qiaoqian XU Haoyang
 * La classe Strategie utilise le patron strat¨¦gie.
 * <p> danslaquell la seule m¨¦thode est jouer(), qui d¨¦cide comment le joueur virtuel pose la carte
 */
public interface Strategie {
	
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur joueur, Jeu jeu);
}

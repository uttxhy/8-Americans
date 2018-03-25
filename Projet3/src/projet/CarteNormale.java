package projet;

/**
 * @author DuQiaoqian XuHaoyang
 * Cette classe CarteNormale h¨¦rite la classe Carte.
 */
public class CarteNormale extends Carte {
	
	public CarteNormale(String forme,String valeur){
		super(forme,valeur);
	}
	
	/** 
	 * Cette m¨¦thode on joue une carte et indique cette carte n'a pas sp¨¦ciale founction.
	 */
	public void fonctionner(Jeu jeu, int turn) {
		System.out.println("la carte sans fonctionnalite speciale");
	}
}

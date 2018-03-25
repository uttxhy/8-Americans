package projet;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.tools.ToolProvider;

/**
 * @author DuQiaoqian XuHaoyang
 * Cette classe est une abstrate classe de carte. Les classe Carte Normale et Carte sp¨¦ciale h¨¦ritent cette classe.
 * <p>Les cartes ont formes, valeurs et note. La note est utilis¨¦ quand on termine le jeu.
 */

public abstract class Carte {
	
	private String forme;
	private String valeur;
	private int point;
	private String face;
	
	public Carte(String forme,String valeur){
		this.forme = forme;
		this.valeur = valeur;
	}
	
	public Carte() {
		// TODO Auto-generated constructor stub
	}
	
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	/**
	 * montrer la carte.
	 */
	public String toString(){
		face = "[" + this.forme + " " + this.valeur + "]";
		return face; 
	}
	
	/**
	 * @return boolean
	 * Cette m¨¦thode pour v¨¦rifier si on peut jouer la carte qu'on chosisit.
	 */
	public boolean peuxJouer(Carte c)
    {
        if (this.forme == c.forme)
            return true;
        else if (this.valeur == c.valeur)
            return true;
        else if (this.forme=="joker")
        	return true;
        else
        	return false;
    }
	
	/**
	 * @param jeu
	 * Cette m¨¦thode pour les cartes sp¨¦ciales, comment les cartes sp¨¦ciales functionne.
	 */
	public abstract void fonctionner(Jeu jeu, int turn); 
		// TODO Auto-generated method stub
		
}

package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

/**
 * @author DU QiaoQian XU HaoYang
 * La classe Joueur repr¨¦sente les joueur de 8 Am¨¦ricains, incluant les joueurs physiques et les joueurs virtuels
 */
public class Joueur {
	
	private String nom;
	private int id;
	private List<Carte> carteEnMain;
	private int point;
	int input;
	public void setInput(int input) {
		this.input = input;
	}
	int index;
	public Carte card;
	public JPanel pPan = new JPanel();

	public Joueur(String nom,int id){
		this.nom = nom;
		this.id = id;
		this.setPoint(0);
		this.carteEnMain = new ArrayList<Carte>();
	}
	public Joueur() {
		
	}
	
	public void setStrategie(Strategie s) {

	}
	
	public JPanel setpPan() {
		return pPan;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Carte> getCarteEnMain() {
		return carteEnMain;
	}
	public void setCarteEnMain(List<Carte> carteEnMain) {
		this.carteEnMain = carteEnMain;
	}
	
	public String getNom(){
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
    
	public void piocher(Carte carte) {
    	carteEnMain.add(carte);
    }
	
	/**
	 * La m¨¦thode joueur repr¨¦sente comment le joueur joue une carte
	 */
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu){
	    return card;
	}
	
	
}

        	
        	
            

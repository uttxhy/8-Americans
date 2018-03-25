package projet;

import java.util.Random;
import java.util.Scanner;
/**
 * @author DuQiaoqian XuHaoyang
 * Cette classe CarteSpeciale est la variante 2;
 * Les cartes sp¨¦ciales sont
 * A:Le joueur suivant pioche deux carte
 * 7:Sauter le tour du joueur suivant
 * 8:Le joueur peut changer la couleur de la carte
 * 9:Joueur suivant pioche une carte
 * 10:Le joueur peut rejouer.
 * V:Piocher une carte.
 */
public class CarteSpeciale2 extends Carte {
	public CarteSpeciale2(String forme,String valeur){
		super(forme,valeur);
	}
	public void fonctionner(Jeu jeu, int turn) {
		switch (this.getValeur()) {
		case "A": {
			System.out.println("joueur suivant pioche throis cartes!");
			if(jeu.reverseFlag == false) {
				for(int p=0; p<3; p++) {
					jeu.getJoueur().get((turn+1)%jeu.getNbJoueur()).piocher(jeu.getCartePaquet().get(0));
					jeu.getCartePaquet().remove(0);
				}
			}
			else{
				for(int p=0; p<3; p++) {
				if(turn>=1){
					turn--;
				}
				else{
					turn=(jeu.getNbJoueur()-Math.abs(turn))-1;
				}
				
				turn = turn % jeu.getNbJoueur();
				jeu.getJoueur().get(turn).piocher(jeu.getCartePaquet().get(0));
				jeu.getCartePaquet().remove(0);
				}
				
			}
		}break;
		case "8": {
			if(jeu.getJoueur().get(turn) instanceof VueDeJoueurPhysique){
				System.out.println("changer de forme! Entrez une forme, svp");
				for(int j = 0; j<4; j++) {
					VueDeJoueurPhysique.formeButton[j].setVisible(true);
					
				}
				Jeu.waitChoose(1000);
				Jeu.wake = false;
				/*Scanner sc = new Scanner(System.in);
				setForme(sc.nextLine());
				System.out.println("votre choix est:" + getForme());*/
				//´´½¨ÐéÄâ¶¥ÅÆ
				//jeu.topCarte8.setForme(getForme());
				//jeu.topCarte8.setValeur("8");
			}
			else if(jeu.getJoueur().get(turn) instanceof JoueurAI){
				System.out.println("changer de forme!");
				Random randomForme=new Random();
				int noDeForme=randomForme.nextInt(3);
				String[] forme={"carreau","coeur","trefle","pique"};
				jeu.topCarte8.setForme(forme[noDeForme]);
				jeu.topCarte8.setValeur("8");
			}
		}break;
		case "10":
		{
			System.out.println("rejouer!");
			for(int i=0;i<jeu.getJoueur().get(turn).getCarteEnMain().size();i++){
				if(jeu.getJoueur().get(turn).getCarteEnMain().size() == 1&jeu.getJoueur().get(turn).getCarteEnMain().get(i).getValeur()=="10") {
					jeu.compter();
				}
				else{
					if(jeu.getJoueur().get(turn).getCarteEnMain().size() > 1&jeu.getJoueur().get(turn).getCarteEnMain().get(i).getValeur()=="10"){
						jeu.topCarte = jeu.getJoueur().get(turn).jouer(jeu.topCarte, jeu.getCartePaquet(),jeu.getJoueur().get(turn), jeu);
					}							
				}
			}			
		}break;
		case "V": {
			System.out.println("changer le sens de jeu!");
			jeu.reverseFlag = !(jeu.reverseFlag);
		}break;
		case "7": {
			System.out.println("Saute le tour du joueur suivant !");
			jeu.skipFlag = true;
		}break;
		case"9":{
			System.out.println("joueur suivant pioche une carte!");
			if(jeu.reverseFlag == false) {
				for(int p=0; p<1; p++) {
					jeu.getJoueur().get((turn+1)%jeu.getNbJoueur()).piocher(jeu.getCartePaquet().get(0));
					jeu.getCartePaquet().remove(0);
				}
			}
			else{
				for(int p=0; p<1; p++) {
				if(turn>=1){
					turn--;
				}
				else{
					turn=(jeu.getNbJoueur()-Math.abs(turn))-1;
				}
				
				turn = turn % jeu.getNbJoueur();
				jeu.getJoueur().get(turn).piocher(jeu.getCartePaquet().get(0));
				jeu.getCartePaquet().remove(0);
				}
				
			}
		}break;
	}
	}
}
	

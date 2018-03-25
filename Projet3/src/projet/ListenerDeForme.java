package projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author DU QiaoQian XU HaoYang
 * Cette classe est pour entendre le choix de forme de la carte, si le joueur physique pose la carte 8
 */
public class ListenerDeForme implements ActionListener{
	 
	Carte top;
	Joueur j;
	
	public ListenerDeForme(Carte top, Joueur j) {
		this.j=j;
		this.top=top;
	}
		
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i<4; i++) {
			if(e.getSource().equals(VueDeJoueurPhysique.formeButton[i])) {
				Jeu.getJeu().topCarte8.setForme(VueDeJoueurPhysique.formeButton[i].getText());
				break;
			}
		}
		Jeu.wake = true;
		//PlayListener.changerForme = true;	
	}
}

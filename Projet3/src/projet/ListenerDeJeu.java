package projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author DU QiaoQian XU HaoYang
 * Cette classe est pour entendre le choix de la carte, si le joueur physique pose la carte 8
 */
public class ListenerDeJeu implements ActionListener {

	Carte top;
	Joueur j;
	public static boolean changerForme = false;
	
	public ListenerDeJeu(Carte top, Joueur j) {
		this.j=j;
		this.top=top;
	}
	
	/** 
	 * cette m¨¦thode entend le choix de joueur physique
	 */
	public void actionPerformed(ActionEvent e) {

		for(int i=0;i<19;i++) {
			if(e.getSource().equals(VueDeJoueurPhysique.cardsButton[i])) {
				if(e.getActionCommand().equals("[pique 8]")) {
					j.setInput(i);
					/*for(int j = 0; j<4; j++) {
						PlayerPhysique.formeButton[j].setVisible(true);						
					}*/
					/*if(changerForme == true) {
						j.setInput(i);
					}*/
				}
				else if(e.getActionCommand().equals("[carreau 8]")) {
					j.setInput(i);
					/*for(int j = 0; j<4; j++) {
						PlayerPhysique.formeButton[j].setVisible(true);
						
					}*/
					
				}
				else if(e.getActionCommand().equals("[trefle 8]")) {
					j.setInput(i);
					/*for(int j = 0; j<4; j++) {
						PlayerPhysique.formeButton[j].setVisible(true);
						
					}*/
					
				}
				else if(e.getActionCommand().equals("[coeur 8]")) {
					j.setInput(i);
					/*for(int j = 0; j<4; j++) {
						PlayerPhysique.formeButton[j].setVisible(true);
						
					}*/
					
				}
				else {
					j.setInput(i);
				}							
				break;
			}
			Jeu.wake=true;
		}
	}
}

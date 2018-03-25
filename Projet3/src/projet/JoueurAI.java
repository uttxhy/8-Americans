package projet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author DU QiaoQian XU HaoYang
 * Cette classe repr¨¦sente les joueurs virtuels
 */
public class JoueurAI extends Joueur{
	
	private Strategie strategie;
	private JTextField sNumCard = new JTextField("");
	
	public JoueurAI(String nom, int id) {
		super(nom, id);		
	}

	/**
	 * on utilise le patron de strat¨¦gie ici
	 */
	public Strategie getStrategie() {
		return strategie;
	}
	public void setStrategie(Strategie s) {
		this.strategie = s;
	}
	
	/**
	 * on set un Panel pour les joueurs virtuels
	 * @return Jpanel
	 */
	public JPanel setpPan() {
		this.pPan.setLayout(new GridLayout());
		this.pPan.setOpaque(false);
		sNumCard.setText(getCarteEnMain().size()+"");
		sNumCard.setFont(new Font("arial",Font.PLAIN, 14));
		sNumCard.setForeground(Color.WHITE);
		sNumCard.setOpaque(false);
		sNumCard.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		sNumCard.setEditable(false);
		sNumCard.setHorizontalAlignment(JTextField.CENTER);
		
		this.pPan.add(sNumCard);
		return this.pPan;
	}
		
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu){
		Carte c = this.strategie.jouer(top, cartePaquet, this, jeu);
		setpPan();
		return c;
	}
	
	
}
package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


//import joueurs.carteListener;

/**
 * 
 * @author DuQiaoqian
 * @see JoueurPhysique
 */
public class VueDeJoueurPhysique extends Joueur{

	public VueDeJoueurPhysique(String nom, int id) {
		super(nom, id);
	}
	
	private JTextField sCardInHand = new JTextField("");
	private JButton pioche = new JButton("Piocher");
	
	public static JButton[] cardsButton = new JButton[19];
	public JPanel buttons = new JPanel();
	public static JButton[] formeButton = new JButton[4];
	public JPanel formes = new JPanel();
	//private carteListener carteClique = new carteListener();
	private int size;
	private Carte top=Jeu.getJeu().topCarte;
	//ActionEvent e;
	
	private void removeButtons() {
		for(int i=0; i< 19 ; i++) {
			cardsButton[i].setVisible(false);
		}
	}
	
	public void showCarteEnMain() {
		removeButtons();		
		size = getCarteEnMain().size();
		for(int i=0; i< size ; i++) {
			cardsButton[i].setText(this.getCarteEnMain().get(i).toString());
			cardsButton[i].setVisible(true);
			//cardsButton[i].addActionListener(new PlayListener(top,this));
		}	
		cardsButton[size].setText("piocher");
		cardsButton[size+1].setText("quitte");
		cardsButton[size].setVisible(true);
		cardsButton[size+1].setVisible(true);
		//cardsButton[size].addActionListener(new PlayListener(top,this));
		//cardsButton[size+1].addActionListener(new PlayListener(top,this));
	}
	
	public JPanel setpPan() {
		this.pPan.setOpaque(false);
		this.pPan.setLayout(new BorderLayout());
		
		sCardInHand.setLayout(new GridLayout(0,1));
		sCardInHand.setFont(new Font("arial",Font.BOLD, 16));
		sCardInHand.setForeground(Color.WHITE);
		sCardInHand.setOpaque(false);
		sCardInHand.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		sCardInHand.setPreferredSize(new Dimension(150,0));
		sCardInHand.setEditable(false);
		sCardInHand.setHorizontalAlignment(JTextField.CENTER);
	
		this.pPan.add(sCardInHand,BorderLayout.CENTER);
		
		for(int j = 0; j<4; j++) {
			formeButton[j] = new JButton();
		}
		formeButton[0].setText("pique");
		formeButton[1].setText("coeur");
		formeButton[2].setText("carreau");
		formeButton[3].setText("trefle");
		
		for(int i = 0; i<4; i++) {
			formeButton[i].setVisible(false);
			formeButton[i].addActionListener(new ListenerDeForme(top, this));
			formes.add(formeButton[i],BorderLayout.CENTER);
		}
		formes.setOpaque(false);
		this.pPan.add(formes, BorderLayout.SOUTH);
		
		pioche.setVisible(false);
		buttons.add(pioche);
		pioche.addActionListener(new ListenerDeJeu(top,this));
		for(int i=0; i< 19 ; i++) {
			cardsButton[i] = new JButton();
			cardsButton[i].setVisible(false);
			cardsButton[i].addActionListener(new ListenerDeJeu(top,this));
			buttons.add(cardsButton[i]);
		}
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.setPreferredSize(new Dimension(150,70));
		buttons.setOpaque(false);
		this.pPan.add(buttons,BorderLayout.NORTH);
		return this.pPan;
	}
	
	public void afficher() {
			sCardInHand.setText("8 AMERICAINS");
	}
	
/*	public class carteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			
			
		  						
		}
	}*/
	
	public void piocher(Carte carteTop) {
		
		this.getCarteEnMain().add(carteTop);
		//sCardInHand.setText(sCardInHand.getText()+"|"+carteTop.toString()+"| ");
		showCarteEnMain();
	}
		
	
	
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu) {
		
		System.out.println("C'est votre tour! Votre choix est:");
		//显示你的手牌
		for (int i = 0; i < getCarteEnMain().size(); i++){
            System.out.print(String.valueOf(i + 1) + ". " + getCarteEnMain().get(i) + "\n");
        }
        System.out.println(String.valueOf(getCarteEnMain().size() + 1 ) + ". " + "piocher carte" + "\n" + 
                           String.valueOf(getCarteEnMain().size() + 2) + ". " + "Quitter");
        
        showCarteEnMain();
        afficher();
		Jeu.waitChoose(1000);
		Jeu.wake=false;
		/*class PlayListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<19;i++) {
					if(e.getSource().equals(cardsButton[i])) {
						
					}
				}
				
			}
			
		}*/
		//chose.actionPerformed(e, top, cartePaquet, j, jeu);
		
        //输入你的选择
       /* System.out.print("\nEntrer votre choix: ");
        Scanner sc = new Scanner(System.in);
    	input = sc.nextInt()-1;*/
    	
    	if(input == getCarteEnMain().size()){
    		piocher(cartePaquet.get(0));
    		cartePaquet.remove(0);
    	    card = top;
    	    
    	}
    	else if(input == getCarteEnMain().size()+1) {
    		System.out.print("Je quitte");
    		System.exit(0);
    	}
    	//判断是否可出
    	else if(getCarteEnMain().get(input).peuxJouer(top)) {        		
    		card = getCarteEnMain().get(input);
    		getCarteEnMain().remove(input);
    		cartePaquet.remove(0);
    	}
    	else if(!(getCarteEnMain().get(input).peuxJouer(top))) {
    		System.out.println("Invalid choice");
    		card = top;
    	}
    	return card;
	}
	
	public void quitter(){
		System.exit(-1);
	}
}
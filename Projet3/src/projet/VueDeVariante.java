package projet;

import projet.Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author DuQiaoqian XuHaoyang
 * VueDeVareinte c'est le vue du 8 americains,on utilse différents méthodes pour affichier la vue du jeu.
 * Les utilisateurs peuvent entrer le choix du nombre du joueur,le strategie du AI joueur,et les variantes
 */

public class VueDeVariante{
	
	Jeu jeu = Jeu.getJeu();
	static int varianteOb;
	static int nbJoueurOb;
	static int strategieOb;
	public static JFrame frmAmericains;
	public static JFrame frame = new JFrame("8 Americains");
	public static JFrame frmEnd =  new JFrame();
	private JTextField nbJoueur;
	private JTextField txtNombreDeJoueur;
	private JTextField strategie;
	private JTextField txtStrategie;
	private JTextField variante;
	private JTextField txtVariante;
	public static JTextField note=new JTextField();
	private JPanel[] position = new JPanel[4];
	private JPanel[] players = new JPanel[6];
	public static JPanel play = new JPanel();
	private static JPanel carteArea = new JPanel();
	public static JTextField showCarte = new JTextField(""); 
		
	/**
	 * Cette méthode pour qctiver le premier frame.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueDeVariante window = new VueDeVariante();
					window.frmAmericains.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VueDeVariante() {			
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmAmericains = new JFrame();
		JButton Start = new JButton("Start");
		frmAmericains.setTitle("8 americains");
		frmAmericains.setBounds(100, 100, 450, 300);
		frmAmericains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	frmAmericains.getContentPane().setLayout(null);
	  	frmAmericains.setIconImage((new ImageIcon("images/Uno-Game.png")).getImage());
		JPanel variantePanel = new JPanel(new GridLayout(3,2));
		variantePanel.setBounds(10, 65, 424, 79);
		frmAmericains.getContentPane().setBackground(new Color(128, 128, 128));
		frmAmericains.getContentPane().add(variantePanel);
		Start.setBounds(174, 214, 93, 23);
		frmAmericains.getContentPane().add(Start);
		
		nbJoueur = new JTextField("2");
		txtNombreDeJoueur= new JTextField();
		txtNombreDeJoueur.setText("NombreDeJoueur");
		txtNombreDeJoueur.setEditable(false);
		txtNombreDeJoueur.setColumns(14);
		
		nbJoueur.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
		variantePanel.add(txtNombreDeJoueur);
		variantePanel.add(nbJoueur);
		
		nbJoueur.setText("2");
		nbJoueur.setEditable(true);
		nbJoueur.setColumns(3);
		nbJoueur.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
		
		strategie = new JTextField();
		txtStrategie= new JTextField();
		txtStrategie.setText("Strategie 1:Passif 2:Agressif");
		txtStrategie.setEditable(false);
		txtStrategie.setColumns(50);
		strategie.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
	//	txtStrategie.setBounds(100,30, 66, 21);

		variantePanel.add(txtStrategie);
		variantePanel.add(strategie);	
		strategie.setText("1");
		strategie.setEditable(true);
		strategie.setColumns(5);
		strategie.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
	//	strategie.setBounds(166, 30, 6, 21);

		jeu.getJeu().strategie=Integer.parseInt(strategie.getText());
		
		
		variante = new JTextField();
		txtVariante= new JTextField();
		txtVariante.setText("Variante 1:normale 2:Monclar 3:mao");
		txtVariante.setColumns(50);
		txtVariante.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 12));
		txtVariante.setEditable(false);

		variantePanel.add(txtVariante);
		variantePanel.add(variante);
		
		variante.setText("2");
		variante.setEditable(true);
		variante.setColumns(3);
		variante.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
		
		jeu.getJeu().variante=(Integer.parseInt(variante.getText()));
	
		StartListener waitReady = new StartListener();
		Start.addActionListener(waitReady);	
	}
	
	
	/**
	 * Quand l'utilisateur clique le boutton START, le Jeu recevoit les paramètres
	 *
	 */
	private class StartListener implements ActionListener {  
		public void actionPerformed(ActionEvent e) {
							
			jeu.getJeu().setNbJoueur(Integer.parseInt(nbJoueur.getText()));
			jeu.getJeu().strategie=Integer.parseInt(strategie.getText());
			jeu.getJeu().variante=(Integer.parseInt(variante.getText()));
			
			frmAmericains.setVisible(false);
			jeu.initCarte();
			jeu.battre(jeu.getCartePaquet());
			jeu.creerJoueur();
			jeu.distribuer();
			
			setWindow();
			new Thread(new ThreadJeu()).start();	
		}
			
	}
	
	/**
	 * Cette méthode pour créer une vue apparaît quand on joue des cartes
	 */
	public void setWindow() {
		play.removeAll();
		//Color background = new Color(34,139,34);//228B22
		ImageIcon image=new ImageIcon("images/backgroud.png");
		JLayeredPane layeredPane=new JLayeredPane();
		JPanel bg = new JPanel(); //background
		JLabel bgimage = new JLabel(image); //background image
		bg.setBounds(0,-5,image.getIconWidth(),image.getIconHeight());    
		bg.add(bgimage);
		play.setBounds(0, -5, image.getIconWidth(),image.getIconHeight());
		play.setLayout(new BorderLayout(5,5)); //window.setLayout(new GridLayout(3, 5, 5, 5));
		play.setOpaque(false);
		layeredPane.add(bg,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(play,JLayeredPane.MODAL_LAYER);

		frame.setSize(905, 660);	
		frame.setVisible(true);
		frame.setIconImage((new ImageIcon("images/Uno-Game.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(frame.getOwner()); 
		frame.setLayeredPane(layeredPane); 
		frame.setResizable(false);
	
		setCarteArea();
		setPosition(jeu.nbJoueur);
		
	}
	
	/**
	 * Cette méthode pour créer les vues cartes en main du jouer AI.
	 */
	public void setCarteArea() {
		carteArea.removeAll();
		ImageIcon ico=new ImageIcon("images/card&shade.png");
		ico.setImage(ico.getImage().getScaledInstance(ico.getIconWidth()/2,ico.getIconHeight()/2, Image.SCALE_DEFAULT));
		JLabel cardPileImage=new JLabel(ico);
		JPanel sCarte=new JPanel(new BorderLayout());
		
		setplayorborder(carteArea, "Carte Pile"); 
		
		showCarte.setText(jeu.topCarte.toString());
		showCarte.setEditable(false);
		showCarte.setForeground(Color.white); 
		showCarte.setFont(new Font("arial",Font.BOLD, 22)); 
		showCarte.setHorizontalAlignment(JTextField.CENTER);
		showCarte.setOpaque(false);
		showCarte.setBorder(null);
		
		sCarte.setBackground(new Color(190,240,204));
		sCarte.setBorder(new BevelBorder(BevelBorder.RAISED,new Color(170,255,204),Color.gray));
		sCarte.setPreferredSize(new Dimension(ico.getIconWidth()-10,ico.getIconHeight()-5));
		sCarte.add(showCarte,BorderLayout.CENTER);
		
		carteArea.setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
		carteArea.add(cardPileImage);
		carteArea.add(sCarte);
		carteArea.setOpaque(false);
		
		play.add(carteArea,BorderLayout.CENTER);
	}
	
	/**
	 * Cette méthode pour créer la vue du topCarte.
	 */
	public void setTopcard()
	{
		showCarte.setText(jeu.topCarte.toString());
		showCarte.setEditable(false);
		showCarte.setForeground(Color.white); 
		showCarte.setFont(new Font("arial",Font.BOLD, 22)); 
		showCarte.setHorizontalAlignment(JTextField.CENTER);
		showCarte.setOpaque(false);
		showCarte.setBorder(null);
	}
	
	/**
	 * @param np
	 * Cette méthode pour installer les places des joueurs, ça dépend le nombre du joueur.
	 */
	public void setPosition(int np ) {
		//prepaprer les images
		ImageIcon ico1=new ImageIcon("images/cards&shade.png");
		ImageIcon ico2=new ImageIcon("images/cardshorizon&shade.png");
		ico1.setImage(ico1.getImage().getScaledInstance(ico1.getIconWidth()/3,
				ico1.getIconHeight()/3, Image.SCALE_DEFAULT));
		ico2.setImage(ico2.getImage().getScaledInstance(ico2.getIconWidth()/3,
				ico2.getIconHeight()/3, Image.SCALE_DEFAULT));
		
		for (int i=0;i<4;i++) {
			position[i] = new JPanel();
			position[i].setPreferredSize(new Dimension(175,175));
			position[i].setOpaque(false);
		}
		play.add(position[0],BorderLayout.WEST);
		play.add(position[1],BorderLayout.EAST);
		play.add(position[2],BorderLayout.NORTH);
		play.add(position[3],BorderLayout.SOUTH);	
		
		for (int i=0;i<np;i++) {
			players[i] = new JPanel();
			players[i].setOpaque(false);
		}
		
		if (np==2) {  //Joueur 1 virtuelle
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			setplayorborder(players[1], "JoueurAI 1");
			JLabel cardPileImage1=new JLabel(ico2);
			players[1].setPreferredSize(new Dimension(600,160));
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur().get(1).setpPan());
			position[2].add(players[1]);
			
		} else if (np==3) {   //Joueur 2 virtuelle
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "JoueurAI 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur().get(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "JoueurAI 2");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur().get(2).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			position[2].setPreferredSize(new Dimension(175,40));
		} else if (np==4) {    //Joueur 3 et 4 virtuelle
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "JoueurAI 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur().get(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "JoueurAI 3");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur().get(3).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			players[3].setPreferredSize(new Dimension(600,160));
			setplayorborder(players[3], "JoueurAI 2");
			JLabel cardPileImage3=new JLabel(ico2);
			players[3].add(cardPileImage3);
			players[3].add(jeu.getJoueur().get(2).setpPan());
			position[2].add(players[3]);
		} else if (np==5) {
			position[2].setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); 
			
			position[0].setLayout(new BorderLayout());
			setplayorborder(players[1], "JoueurAI 1");
			JLabel cardPileImage1=new JLabel(ico1);
			players[1].add(cardPileImage1);
			players[1].add(jeu.getJoueur().get(1).setpPan(),BorderLayout.SOUTH);
			position[0].add(players[1]);
			
			position[1].setLayout(new BorderLayout());
			setplayorborder(players[2], "JoueurAI 4");
			JLabel cardPileImage2=new JLabel(ico1);
			players[2].add(cardPileImage2);
			players[2].add(jeu.getJoueur().get(4).setpPan(),BorderLayout.SOUTH);
			position[1].add(players[2]);
			
			players[3].setPreferredSize(new Dimension(350,160));
			setplayorborder(players[3], "JoueurAI 2");
			JLabel cardPileImage3=new JLabel(ico2);
			players[3].add(cardPileImage3);
			players[3].add(jeu.getJoueur().get(2).setpPan());
			position[2].add(players[3]);
			
			players[4].setPreferredSize(new Dimension(350,160));
			setplayorborder(players[4], "JoueurAI 3");
			JLabel cardPileImage4=new JLabel(ico2);
			players[4].add(cardPileImage4);
			players[4].add(jeu.getJoueur().get(3).setpPan());
			position[2].add(players[4]);
		}
		
		//joueur physique
		position[3].setLayout(new FlowLayout(FlowLayout.CENTER,10,0)); 
		players[0].setPreferredSize(new Dimension(650,160));
		players[0].setBorder(BorderFactory.createTitledBorder(null,
				"Physique", TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
		players[0].setLayout(new BorderLayout());
		players[0].add(jeu.getJoueur().get(0).setpPan(),BorderLayout.CENTER);
		jeu.getJoueur().get(0).setNom("Physique");
		position[3].add(players[0]);
		
	}
	
	private void setplayorborder(JPanel pan, String name) {
		pan.setBorder(BorderFactory.createTitledBorder(null,
				name, TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, Color.WHITE));		
	}
	
	/**
	 * Cette méthode pour créer la vue du note de joueur gagnant.
	 */
	public static void endJeu(){
		frmAmericains.removeAll();
		frmEnd.setTitle("8 americains");
		frmEnd.setBounds(100, 100, 450, 300);
		frmEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnd.getContentPane().setLayout(null);
		frmEnd.setIconImage((new ImageIcon("images/Uno-Game.png")).getImage());
		JPanel endPanel = new JPanel();
		endPanel.setBounds(10, 65, 424, 79);
		frmEnd.getContentPane().setBackground(new Color(128, 128, 128));
		frmEnd.getContentPane().add(endPanel);
		
		note.setFont(new Font("arial",Font.LAYOUT_LEFT_TO_RIGHT, 16));
		endPanel.add(note);
		//note.setText("JoueurGagnant est"+Jeu.getJeu().joueurgagnant.getNom()+"!");
		note.setColumns(30);
		
	}

}

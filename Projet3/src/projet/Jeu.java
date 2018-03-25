package projet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Observer;
import java.util.Observable;
import java.util.Random; 
import java.util.Scanner;
import java.util.Set;

/**
 * @author DU Qiaoqian XU Haoyang
 * @see Jeu 
 * La classe Jeu est singleton.
 * <p> on commencer le jeu par cette class, la fonction Main() est dans ce class
 */
public class Jeu {
	
	private static Jeu jeu = new Jeu();
	private ArrayList<Carte> cartePaquet = new ArrayList<Carte>();
	private ArrayList<Carte> cartePerdue = new ArrayList<Carte>();
	private ArrayList<Carte> carteEnMain = new ArrayList<Carte>();
	private ArrayList<Joueur> joueur = new ArrayList<Joueur>();
	Joueur joueurgagnant = new Joueur();
	public static int nbJoueur;
	public static int variante;
	int strategie;
	Carte topCarte;
	Carte topCarte8 = new CarteSpeciale("pique", "8");
	boolean reverseFlag = false;
	boolean skipFlag = false;
	public int turn = 0;
	public static boolean wake = false;
	
	public int getNbJoueur() {
		return nbJoueur;
	}
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	/**
	 * Singleton 
	 */
	private Jeu(){};
	
	public static Jeu getJeu(){
		return jeu;
	}
	
	public List<Joueur> getJoueur() {
		return joueur;
	}
	public void setJoueur(ArrayList<Joueur> joueur) {
		this.joueur = joueur;
	}

	public ArrayList<Carte> getCartePaquet() {
		return cartePaquet;
	}
	public void setCartePaquet(ArrayList<Carte> cartePaquet) {
		this.cartePaquet = cartePaquet;
	}
	
	String[] forme={"carreau","coeur","trefle","pique"};
	String[] valeur={"A","2","3","4","5","6","7","8","9","10","V","D","R"};
	
	/**
	 * initializer les cartes
	 */
	public void initCarte() {
		cartePerdue.clear();
		if(variante==1){
			for(int i = 0; i < forme.length; i++) {
				for(int j = 0; j < valeur.length; j++) {
					if(j == 0 || j == 6  || j == 7 || j == 9 || j == 10) {
						getCartePaquet().add(new CarteSpeciale(forme[i],valeur[j]));
					}
					else {
						getCartePaquet().add(new CarteNormale(forme[i],valeur[j]));
					}
					
				}
			}	
		}
		else if(variante==2){
			for(int i = 0; i < forme.length; i++) {
				for(int j = 0; j < valeur.length; j++) {
					if(j == 0 || j == 6 ||j == 7 || j == 8|| j == 9 || j == 10) {
						getCartePaquet().add(new CarteSpeciale2(forme[i],valeur[j]));
					}
					else {
						getCartePaquet().add(new CarteNormale(forme[i],valeur[j]));
					}				
				}
			}	
		}	
		else if(variante==3){
			for(int i = 0; i < forme.length; i++) {
				for(int j = 0; j < valeur.length; j++) {
					if(j == 6 || j == 7 ||j == 9 || j == 10) {
						getCartePaquet().add(new CarteSpeciale3(forme[i],valeur[j]));
					}
					else {
						getCartePaquet().add(new CarteNormale(forme[i],valeur[j]));
					}
				
				}
			}
		}
		int n = 0 ; 
		
		for(Carte carte : getCartePaquet()) { 
			if(n % 13 == 0) { 
				System.out.println("\n"); 
			}
			
			System.out.print(carte.getForme() + carte.getValeur() + " ");
			n++; 
		} 
		System.out.println("\n\n------Cartes sont pr¨ºtes.-------"); 
	} 
	
	/**
	 * battre les cartes, on utilise la m¨¦thode shuffle()
	 */
	public void battre(ArrayList<Carte> carteList) {
			Collections.shuffle(carteList);		
	}
		
	/**
	 * cr¨¦er joueur.
	 * <p> on cr¨¦e joueurvirtuel et on set le strat¨¦gie<br>
	 */
	public void creerJoueur() {
		/*while(true){
				System.out.println("Entrer le nombre de joueur, s'il vous plait.(nombre de joueur entre 2-6)");
				Scanner sc = new Scanner(System.in);
			try{
				nbJoueur = sc.nextInt();
				if(nbJoueur<=6&&nbJoueur>=2){
					break;
				}	
				else{ throw new Exception(); 
				}
		     	}catch(InputMismatchException inputMismatchExp){
				System.out.println("Le nombre n'est pas entier!" );
			}catch(Exception e){
				System.out.println("Vous n'avez pas enrtrez propre, entrez encore une fois!");
			}
		}*/
		nbJoueur=getNbJoueur();
		/*System.out.println("Entrer le nom de joueur, s'il vous plait.");
		Scanner sc1 = new Scanner(System.in);
		String nom = sc1.nextLine();	*/
		String nom = "Joueur Physique";
		getJoueur().add(new VueDeJoueurPhysique(nom, 0));
		
		if(strategie == 1) {
			for(int i=1; i<nbJoueur; i++){
				getJoueur().add(new JoueurAI("Joueur"+i, i));
				getJoueur().get(i).setStrategie(new StrategiePassif());
			}
		}
		
		if(strategie == 2) {
			for(int i=1; i<nbJoueur; i++){
				getJoueur().add(new JoueurAI("Joueur"+i, i));
				getJoueur().get(i).setStrategie(new StrategieAgressif());
			}
		}
		
		for(int i=0; i<nbJoueur; i++){
			System.out.println(getJoueur().get(i).getNom()+" id est:"+getJoueur().get(i).getId());
		}
	}		
	
	/**
	 * distribuer les cartes
	 * <p> on choisir le nombre de joueur, et on distribuer la carte<br>
	 * et puis, on retourne la premi¨¨re carte du paquet	
	 */
	public void distribuer() {
		switch(nbJoueur) {
		case 2:
			for(int j=0;j<nbJoueur;j++){
				System.out.println("Joueur" + j +" est distribu¨¦ ");
				for(int i=0;i<10;i++) {
					getJoueur().get(j).getCarteEnMain().add(getCartePaquet().get(0));
					getCartePaquet().remove(0);
				}
			}
			System.out.println("Votre cartes en main sont:");
			for(int i=0;i<10;i++) {
				System.out.println(getJoueur().get(0).getCarteEnMain().get(i));
			}
			break;
			
		case 3:
			for(int j=0;j<nbJoueur;j++) {
				System.out.println("Joueur" + j +" est distribu¨¦ ");
				for(int i=0;i<8;i++) {
					getJoueur().get(j).getCarteEnMain().add(getCartePaquet().get(0));
					getCartePaquet().remove(0);
				}
			}
			System.out.println("Votre cartes en main sont:");
			for(int i=0;i<8;i++) {
				System.out.println(getJoueur().get(0).getCarteEnMain().get(i));
			}
			break;
			
		default:
			for(int j=0;j<nbJoueur;j++) {
				System.out.println("Joueur" + j +" est distribu¨¦ ");
				for(int i=0;i<6;i++){
					getJoueur().get(j).getCarteEnMain().add(getCartePaquet().get(0));
					getCartePaquet().remove(0);
				}
			}
			System.out.println("Votre cartes en main sont:");
			for(int i=0;i<6;i++) {
				System.out.println(getJoueur().get(0).getCarteEnMain().get(i));
			}
			break;
			
		}
	//retourner la premi¨¨re carte	
	topCarte = getCartePaquet().get(0);
	getCartePaquet().remove(0);
	cartePerdue.add(topCarte);
	}	
	
	/**
	 * choisir variante
	 */
	public  void choisirVariante(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Vous pouriez choisir\nVariante 1:NORMALE\nVariante 2:Monclar\nVariante 3 :Mao\nEntrer votre choix[1/2/3]");
		variante=sc.nextInt();
		System.out.println(variante);
	}
	
	/**
	 * choisir strategie
	 */
	public void choisirStrategie() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Vous pouriez choisir\nStrategie 1:Passif\nStrategie 2:Agressif\nEntrer votre choix[1/2]");
		this.strategie = sc.nextInt();	    
	}
	
	/**
	 * d¨¦cider le joueur suivant.
	 * <p> on d¨¦finie reverseFlag et skipFlag et puis on d¨¦cide qui est le joueur suivant
	 * @return integer
	 */
	public int nextTurn() {
		//
		if(reverseFlag) {
			if(skipFlag) {
				if(turn>=2){
					turn-=2;
				}
				else{
					turn=(nbJoueur-Math.abs(turn))-2;
				}
				
			}
			else{
				if(turn>=1){
					turn--;
				}
				else{
					turn=(nbJoueur-Math.abs(turn))-1;
				}
			}
			turn = Math.abs(turn % nbJoueur);
		}
		else{
			if(skipFlag) {
				turn+=2;
			}
			else{
				turn++;
			}
			turn = turn % nbJoueur;
		}
		return turn;
	}
	
	/**
	 * commencer le jeu
	 */
	public void commencer() {
		
		turn = 0;

		while(true) {
			skipFlag = false;
			//juger la premi¨¨re carte de la carte perdue est 8 ou non ÅÐ¶ÏÆúÅÆ¶Ñ¶¥ÅÆÊÇ·ñÎª8
			if(cartePerdue.get(cartePerdue.size()-1).getValeur() != "8") {
				System.out.println("Le topCarte est:" + topCarte);
			}else {
				System.out.println("Le topCarte est:" + topCarte8);
			}
			
			VueDeVariante vueDeVariante = new VueDeVariante();
			vueDeVariante.showCarte.setText(topCarte.toString());
			//juger la premi¨¨re carte de la carte perdue est 8 ou non ÅÐ¶ÏÆúÅÆ¶Ñ¶¥ÅÆÊÇ·ñÎª8
			if(cartePerdue.get(cartePerdue.size()-1).getValeur() != "8") {
				topCarte = getJoueur().get(turn).jouer(topCarte,getCartePaquet(),getJoueur().get(turn), jeu);
			}else {
				topCarte = getJoueur().get(turn).jouer(topCarte8,getCartePaquet(),getJoueur().get(turn), jeu);
			}
			//si un joueur n'a pas de carteEnMain, terminer	
			if(getJoueur().get(turn).getCarteEnMain().size() == 0){
				break;
			}
			cartePerdue.add(topCarte);
			//juger les deux premi¨¨res cartes de la carte perdue est m¨ºme ou pas ÅÐ¶ÏÆúÅÆ¶ÑÖÐ¶¥²¿Á½ÕÅÅÆÊÇ·ñÏàÍ¬			
			if(cartePerdue.get(cartePerdue.size()-1) != cartePerdue.get(cartePerdue.size()-2)) {
				topCarte.fonctionner(jeu, turn);
			}
			else if(cartePerdue.get(cartePerdue.size()-1) == cartePerdue.get(cartePerdue.size()-2)){
				cartePerdue.remove(cartePerdue.size()-1);
			}
			/*//ÅÐ¶ÏÆúÅÆ¶ÑÖÐ¶¥ÅÆÊÇ·ñÎª8
			if(cartePerdue.get(cartePerdue.size()-1).getValeur() == "8"){
			 	if(cartePerdue.get(cartePerdue.size()-2).getValeur() == "8") {
			 		cartePerdue.remove(cartePerdue.size()-1);
			 	}
			 	else {
			 		topCarte.fonctionner(jeu, turn);			 		
			 	}
				
			}*/
			//System.out.println("Le topCarte est:" + topCarte);
			
			//battre les cartes ÖØÐÂÏ´ÅÆ
			if(jeu.getCartePaquet().size()<2){
				jeu.battre(cartePerdue);
				jeu.getCartePaquet().addAll(cartePerdue);
			}
			
				/*if(carteEnMain.size() == 0) {
					System.out.println("cette partie est temin¨¦e, on compte:");
					break;
				}*/
			    //topCarte.fonctionner(jeu, turn);
			//}
			/*else {
				topCarte = joueur.get(turn).jouer(topCarte,cartePaquet);
				topCarte.fonctionner(jeu, turn);
			}*/
		
			jeu.nextTurn();
			
		}		
	}

	/**
	 * attendre le joueurPhysique faire le choix
	 */
	public static void second(int i) {
		try {
			Thread.sleep(i * 1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void waitChoose(int n) {
		int i = n;
		while(wake == false && i >= 0) {
			second(1);
			i--;
		}
	}
	
	/**
	 * compter le point
	 */
	public void compter(){
		int j=0;
		for(int i=0;i<getJoueur().size();i++){
			if(!getJoueur().get(i).getCarteEnMain().isEmpty()){
			for(j=0;j<getJoueur().get(i).getCarteEnMain().size();j++){
				
				if(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="3"){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 3);
					//joueur.get(i).getPoint();
				}
				else if(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="4"){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 4);
					//joueur.get(i).setPoint(point);
					
				}
				else if(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="5"){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 5);
					//joueur.get(i).setPoint(point);
				}
				else if(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="6"){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 6);
					//joueur.get(i).setPoint(point);
				}
				else if(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="9"){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 9);
					//joueur.get(i).setPoint(point);
				}
				else if((getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="D"||(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="R"))){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 10);
					//joueur.get(i).setPoint(point);
				}
				else if((getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="V"||(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="7"||(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="2")))){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 20);
					//joueur.get(i).setPoint(point);
				}
				else if((getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="A"||(getJoueur().get(i).getCarteEnMain().get(j).getValeur()=="8"))){
					getJoueur().get(i).setPoint(getJoueur().get(i).getPoint() + 50);
					//joueur.get(i).setPoint(point);
					}
				}
			}   
			System.out.println(getJoueur().get(i).getNom()+" a "+getJoueur().get(i).getPoint()+" pionts!");
			
		}
		
		int temp = getJoueur().get(0).getPoint();

		
		for(int k = 1;k < nbJoueur;k++){
			if(getJoueur().get(k).getPoint()<temp){
				temp=getJoueur().get(k).getPoint();
				joueurgagnant=getJoueur().get(k);
				}
		}	
		System.out.println(temp);
		System.out.println("JoueurGagnant est"+joueurgagnant.getNom()+"!");
		VueDeVariante.note.setText("Joueur gagnant est " + joueurgagnant.getNom() + " ! ");
	}
	
	/**
	 * fonction main()
	 */		
	public static void main(String[] args){
		
		System.out.println("------Bienvenue ¨¤ 8 am¨¦ricains!------\n");
		
		jeu=getJeu();
		jeu.choisirVariante();
		jeu.choisirStrategie();
		jeu.initCarte();
		jeu.battre(jeu.getCartePaquet());
		jeu.creerJoueur();
		jeu.distribuer();
		jeu.commencer();
		jeu.compter();
	}
	
}





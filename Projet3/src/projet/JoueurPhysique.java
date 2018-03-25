package projet;

import java.util.List;
import java.util.Scanner;

/**
 * La classe JoueurPhysique représente le joueur physique
 * @author DuQiaoqian
 * @see JoueurPhysique
 */
public class JoueurPhysique extends Joueur{

	public JoueurPhysique(String nom, int id) {
		super(nom, id);
	}
	
	/**
	 * Le joueur physique peut choisir quelle carte il pose.
	 * <p> si la carte choisie s'accorde avec la carte top, la carte est posée
	 */
	public Carte jouer(Carte top, List<Carte> cartePaquet, Joueur j, Jeu jeu) {
		System.out.println("C'est votre tour! Votre choix est:");
		//montrer votre carte en main 显示你的手牌
		for (int i = 0; i < getCarteEnMain().size(); i++){
            System.out.print(String.valueOf(i + 1) + ". " + getCarteEnMain().get(i) + "\n");
        }
        System.out.println(String.valueOf(getCarteEnMain().size() + 1 ) + ". " + "piocher carte" + "\n" + 
                           String.valueOf(getCarteEnMain().size() + 2) + ". " + "Quitter");
        //entrer votre choix 输入你的选择
        System.out.print("\nEntrer votre choix: ");
        Scanner sc = new Scanner(System.in);
    	input = sc.nextInt()-1;
    	
    	if(input == getCarteEnMain().size()){
    		piocher(cartePaquet.get(0));
    		cartePaquet.remove(0);
    	    card = top;    
    	}
    	else if(input == getCarteEnMain().size()+1) {
    		System.out.print("Je quitte");
    		System.exit(0);
    	}
    	//juger si vous pouvez jouer ce carte 判断是否可出
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

package projet;

/**
 * @author DuQiaoqian XuHaoyang
 * Cette classe CarteSpeciale est la variante normale;
 * Les cartes sp¨¦ciales sont
 * 7:Le joueur suivant pioche deux cartes
 * 8:Sauter le tour du joueur suivant
 * 10:Le joueur peut rejouer.
 */
public class CarteSpeciale3 extends Carte{

		public CarteSpeciale3(String forme, String valeur){
			super(forme,valeur);
		}
		@Override
		public void fonctionner(Jeu jeu, int turn) {
			// TODO Auto-generated method stub
			switch(this.getValeur()){
			case "7":{
				System.out.println("joueur suivant pioche deux carte!");
				if(jeu.reverseFlag=false){
					for(int i=0;i<2;i++){
					jeu.getJoueur().get((turn+1)%jeu.getNbJoueur()).piocher(jeu.getCartePaquet().get(0));
					jeu.getCartePaquet().remove(0);
				}	
				}
				else{
					for(int p=0; p<2; p++) {
						if(turn>=1){
							turn--;
						}
						else{
							turn=(jeu.getNbJoueur()-Math.abs(turn))-1;
						}
						
							jeu.getJoueur().get((turn-1)%jeu.getNbJoueur()).piocher(jeu.getCartePaquet().get(0));
							jeu.getCartePaquet().remove(0);
						}
				}
			}
				break;
			case "8":{
				System.out.println("Saute le tour du joueur suivant !");
				jeu.skipFlag = true;
			}
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
			}
		}
	}


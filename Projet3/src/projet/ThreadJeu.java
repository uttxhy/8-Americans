package projet;

/**
 * @author DU Qiaoqian XU Haoyang
 * La classe ThreadJeu cr¨¦e un nouveau thread, qui garantit la fonction parall¨¨le de deux threads
 *
 */
public class ThreadJeu implements Runnable{

	public void run() {
		Jeu.getJeu().commencer();
		VueDeVariante.endJeu();
		VueDeVariante.frmEnd.setVisible(true);
		Jeu.getJeu().compter();
	}
}

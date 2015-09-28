package hu.funyirok.rpg_warrior;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AI {

	public alakzat foe, foe_crack;
	public int elet;

	public int helyzet_x, helyzet_y;

	public Random r = new Random();
	public boolean siker = false;

	public KepJatekter jatek;
	public labrintusgenerator labirintus;

	public AI(KepJatekter j, labrintusgenerator labirintus, Texture foe_T, Texture foe_crack_T, int elet_be) {
		jatek = j;
		this.labirintus = labirintus;
		foe = new alakzat(jatek, foe_T, labirintus.kockameret - 10, labirintus.kockameret - 10, false);
		foe_crack = new alakzat(jatek, foe_crack_T, labirintus.kockameret - 10, labirintus.kockameret - 10, false);
		elet = elet_be;
		spawn();
	}

	public boolean el() {

		if (elet > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void iranyvaltoztataskeresztezodesben(int i) {
		labirintus.felfele = false;
		labirintus.lefele = false;
		labirintus.jobra = false;
		labirintus.balra = false;
		labirintus.elfordulas(jatek.ejaj[i].foe, i);
		if (labirintus.felfele) {
			jatek.ejaj_mozog_y[i] = jatek.mozdul_poz;
			jatek.ejaj_mozog_x[i]=0;
		}
		if (labirintus.lefele) {
			jatek.ejaj_mozog_y[i] = jatek.mozdul_neg;
			jatek.ejaj_mozog_x[i]=0;
		}
		if (labirintus.jobra) {
			jatek.ejaj_mozog_x[i] = jatek.mozdul_poz;
			jatek.ejaj_mozog_y[i]=0;
		}
		if (labirintus.balra) {
			jatek.ejaj_mozog_x[i] = jatek.mozdul_neg;
			jatek.ejaj_mozog_y[i]=0;
		}
		System.out.println("felfele: "+ labirintus.felfele+ "|| lefele: "+ labirintus.lefele+"|| jobra: "+ labirintus.jobra+"|| balra: "+labirintus.balra);
	}

	public void render(SpriteBatch batch) {
		if (el()) {
			if (elet == 1) {
				foe_crack.atHelyez(foe.getX(), foe.getY());
				foe_crack.rajzol(batch);
			} else {
				foe.rajzol(batch);
			}

		}

	}

	public void dispose() {
		foe.dispose();
		foe_crack.dispose();
	}

	public void spawn() {

		do {

			helyzet_x = r.nextInt((int) (labirintus.hossz));
			helyzet_y = r.nextInt((int) (labirintus.le));
			if (labirintus.maze[helyzet_y][helyzet_x].getTexture() == jatek.aSemmi.getTexture() && helyzet_x > 5 && helyzet_y > 5 && helyzet_x < labirintus.hossz - 5 && helyzet_y < labirintus.le - 5) {
				foe.atHelyez(labirintus.maze[helyzet_y][helyzet_x].getX(), labirintus.maze[helyzet_y][helyzet_x].getY());
				siker = true;
			}
		} while (!siker);
	}

}

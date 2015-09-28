package hu.funyirok.rpg_warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class labrintusgenerator {
	public KepJatekter jatek;
	public KepToltes menu;
	public AI ejaj;

	public boolean felfele, lefele, jobra, balra;
	public int kockameret = 0, torles_mennyisege = 0;
	public float x = 0;
	public float y = 0;
	public int oldal = 0, szam;
	public int meret_x = 0;
	public int meret_y = 0;
	public alakzat[][] maze;
	public boolean[][] votma;
	public int count;
	public Texture t1, t2;
	private float w, h;
	public boolean feltoltes = true, kesz = false;
	private static Random rnd = new Random();
	public int le = 0;
	public int hossz = 0;

	public int tombX(float x) {
		return hossz - (int) (x / kockameret) - 1;
	}

	public int tombY(float y) {
		return (int) (y / kockameret);
	}

	public void elfordulas(alakzat f, int ia) {
		felfele = false;
		lefele = false;
		jobra = false;
		balra = false;
		int y = tombY(f.getY()+f.getMagassag()/2);
		int x = tombX(f.getX()+f.getSzelesseg()/2);
		int i = (int)(y);// + f.getMagassag()/2);
		int j = (int)(x);// + f.getSzelesseg()/2);
		
		//if (f.getX()+f.getSzelesseg()/2>x*kockameret+kockameret/2 && f.getX()+f.getSzelesseg()/2<x*kockameret+kockameret-kockameret/2 || f.getY()+f.getMagassag()/2>y*kockameret+kockameret/3 && f.getY()+f.getMagassag()/2<y*kockameret+kockameret-kockameret/3){
		//if (f.getX()>x*kockameret+(kockameret/3) || f.getX()<x*kockameret+(kockameret-kockameret/3) && f.getY()>y*kockameret+(kockameret/3) || f.getY()<y*kockameret+(kockameret-kockameret/3)){
		System.out.println((f.getY()+f.getSzelesseg()/2)+"  "+y+" "+kockameret+" "+(y*kockameret+kockameret/3)+ " "+ (y*kockameret+kockameret-kockameret/3));
		if (f.getX()+f.getSzelesseg()/2 >= ((hossz*kockameret-kockameret*x)-kockameret+(kockameret/3)) && f.getX()+f.getSzelesseg()/2 <= ((hossz*kockameret-kockameret*x)-(kockameret/3))) {
		if (jatek.ejaj_mozog_y[ia] != 0) {
			if (rnd.nextInt(2) == 0) {
				if ( j >= 0 && j + 1 < meret_x && maze[i][j + 1].getTexture() == t2) {
					System.out.println(maze[i][j + 1].getX()+" || "+maze[i][j + 1].getX()+maze[i][j + 1].getW());
					jobra = true;  
				}
			} else {
				if (j >= 0 && j - 1 < meret_x && maze[i][j - 1].getTexture() == t2) {
					balra = true;
					System.out.println(maze[i][j - 1].getX()+" || "+(maze[i][j - 1].getX()+maze[0][0].getSzelesseg()));
				}
			}
		}
		}
		
		if (f.getY()+f.getMagassag()/2 >= y*kockameret+(kockameret/3) && f.getY()+f.getMagassag()/2 <= y*kockameret+(kockameret-kockameret/3)){
		if (jatek.ejaj_mozog_x[ia] != 0) {
			if (rnd.nextInt(2) == 0) {
				if (i >= 0 &&  i + 1 < meret_y  && maze[i + 1][j].getTexture() == t2) {
					felfele = true;
				}
			} else {

				if (i >= 0 &&  i - 1 < meret_y  && maze[i - 1][j].getTexture() == t2) {
					lefele = true;
				}
			}
		}
		}
		/*
		if (jatek.ejaj_mozog_y[ia] < 0) {
			if (rnd.nextInt(2) == 0) {
				if (i >= 0 && j >= 0 && i < meret_y && j + 1 < meret_x && maze[i][j + 1].getTexture() == t2) {
					jobra = true;
				}
			} else {
				if (i >= 0 && j >= 0 && i < meret_y && j - 1 < meret_x && maze[i][j - 1].getTexture() == t2) {
					balra = true;
				}
			}

		}*/

		/*
		if (jatek.ejaj_mozog_x[ia] < 0) {
			if (rnd.nextInt(2) == 0) {
				if (i >= 0 && j >= 0 && i + 1 < meret_y && j < meret_x && maze[i + 1][j].getTexture() == t2) {
					felfele = true;
				}
			} else {

				if (i >= 0 && j >= 0 && i - 1 < meret_y && j < meret_x && maze[i - 1][j].getTexture() == t2) {
					lefele = true;
				}
			}
		}*/
		
		
		// ha felette l�v� t==2 akkor felfel� megy ha alatta akkor lefel� �s �gy
		// tov�bb csak 4-et kell meg n��zni �s csak akkor kell meg h�vni ha a t2
		// k�tep�n van a "foe"

		/*
		 * for (j = x - 1; j <= x + 1; j++) { for (i = y - 1; i <= y + 1; i++) {
		 * if (i >= 0 && j >= 0 && i < meret_y && j < meret_x &&
		 * maze[i][j].getTexture() == t1) {
		 * 
		 * 
		 * 
		 * 
		 * } } }
		 */

	}

	/**
	 * Megadja, hogy az egyes labirintus elemek hol �tk�znek a bemen�
	 * alakzattal. Visszaadott �rt�k a labirintus elemek t�mbje, amelyek
	 * �tk�ztek. Ide ker�lnek az ir�nyok is, �s a labirintus elemek
	 * szempontj�b�l kell n�zni.
	 */
	public ArrayList<alakzat> utkozesvizsgalat(alakzat f) {
		ArrayList<alakzat> lista = new ArrayList<alakzat>();
		boolean ub = false;
		boolean uj = false;
		boolean uf = false;
		boolean ul = false;
		int y = tombX(f.getY());
		int x = tombY(f.getX());
		int i = y;
		int j = x;
		for (j = x - 1; j <= x + 1; j++) {
			for (i = y - 1; i <= y + 1; i++) {
				if (i >= 0 && j >= 0 && i < meret_y && j < meret_x && maze[i][j].getTexture() == t1) {
					if (f.utkozes(maze[i][j])) {
						lista.add(maze[i][j]);
						maze[i][j].utkozesBal = f.utkozesJobb;
						maze[i][j].utkozesJobb = f.utkozesBal;
						maze[i][j].utkozesLent = f.utkozesFent;
						maze[i][j].utkozesFent = f.utkozesLent;
					}
				}
			}
		}
		return lista;
	}

	public labrintusgenerator(KepJatekter j, KepToltes a, Texture fal, Texture ures, float x, float y) {
		jatek = j;
		menu = a;
		t1 = fal;
		t2 = ures;
		w = x;
		h = y;

		meret_x = menu.lab_meret_x;
		meret_y = menu.lab_meret_y;
		torles_mennyisege = menu.torles_mennyisege;
		maze = new alakzat[meret_y][meret_x];
		votma = new boolean[meret_y][meret_x];
		le = (votma.length);
		hossz = (votma[0].length);
		nullaz();
	}

	public void nullaz() {

		for (int i = 0; i < hossz; i++) {
			for (int j = 0; j < le; j++) {
				votma[j][i] = false;
			}
		}
		kezdes(0, 0);
	}

	public void render(SpriteBatch batch) {
		if (jatek.bal || jatek.jobb) {
			for (int j = 0; j < le; j++) {
				for (int i = 0; i < hossz; i++) {
					maze[j][i].rajzol(batch);
				}
			}
		}
		if (jatek.fel || jatek.le) {
			for (int i = 0; i < hossz; i++) {
				for (int j = 0; j < le; j++) {
					maze[j][i].rajzol(batch);
				}
			}
		}
	}

	public void dispose() {
		for (int i = 0; i < hossz; i++) {
			for (int j = 0; j < le; j++) {
				maze[j][i].dispose();
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		dispose();
		super.finalize();
	}

	public void torles() {
		int eggyik = (int) rnd.nextInt(votma.length), masik = (int) rnd.nextInt((votma.length - 1));
		count = 0;
		do {
			do {
				eggyik = (int) rnd.nextInt(votma.length - 1);
				masik = (int) rnd.nextInt((votma[0].length - 1));
				if (eggyik == 0) {
					eggyik = 1;
				}
				if (masik == 0) {
					masik = 1;
				}
				if (votma[eggyik][masik]) {

					if (votma[eggyik + 1][masik] == true && votma[eggyik - 1][masik] == true) {
						if (votma[eggyik][masik + 1] == true || votma[eggyik][masik - 1] == true) {
						} else {
							votma[eggyik][masik] = false;
						}
					}
					if (votma[eggyik][masik + 1] == true && votma[eggyik][masik - 1] == true) {
						if (votma[eggyik + 1][masik] == true || votma[eggyik - 1][masik] == true) {

						} else {
							votma[eggyik][masik] = false;
						}
					}
				}
			} while (!votma[eggyik][masik]);
			count++;
		} while (count < torles_mennyisege);

		toltes();
	}

	public void toltes() {
		// textur�k bet�lt�se t�mbbe
		kockameret = (int) (w / 20.0);
		for (int j = 0; j < le; j++) {
			for (int i = 0; i < hossz; i++) {
				if (votma[j][i]) {
					maze[j][i] = new alakzat(jatek, t1, kockameret + 1, kockameret + 1, false);
					maze[j][i].name = "FAL";
				} else {
					maze[j][i] = new alakzat(jatek, t2, kockameret + 1, kockameret + 1, false);
					maze[j][i].name = "SEMMI";
				}
				maze[j][i].mozgo_alakzat = false;
			}
		}
		for (int j = 0; j < le; j++) {
			for (int i = 0; i < hossz; i++) {
				maze[j][i].atHelyez(i * kockameret, (le - j - 1) * kockameret);

			}
		}

	}

	// itt adja meg honnan induljon a cs�k h�z�sa a labirintus vonalainak
	// eggyik az Y kordin�ta
	// masik az X kordin�ta
	public void kezdes(int eggyik, int masik) {
		// k�ls� fal felt�lt�se
		if (!votma[0][0]) {

			// fent
			int i = 0;
			for (i = 0; i < hossz; i++) {
				votma[0][i] = true;
			}

			// alul
			for (i = 0; i < hossz; i++) {
				votma[le - 1][i] = true;
			}

			// jobb

			for (i = 0; i < le; i++) {
				votma[i][hossz - 1] = true;
			}

			// bal
			for (i = 0; i < le; i++) {
				votma[i][0] = true;
			}
		}
		do {

			oldal++;
			// itt kezdi �jra az oldalak sz�ml�l�s�t
			if (oldal > 4) {
				oldal = 1;
			}

			// fels� sorra azaz X-en ad vissza egy p�ratlan t�mb sz�mot
			szam = 0;
			if (oldal == 1) {
				eggyik = 1;
				do {
					masik = (int) rnd.nextInt((votma.length - 1));
					// System.out.println("oldal 1: " + masik);
				} while (1 == masik % 2);

			}

			// jobb
			if (oldal == 2) {
				masik = (votma.length);
				do {
					eggyik = (int) rnd.nextInt(votma.length);
				} while (1 == eggyik % 2);
			}

			// als�
			if (oldal == 3) {
				eggyik = (votma.length);
				do {
					masik = (int) rnd.nextInt((votma.length - 1));
				} while (1 == masik % 2);
			}

			// bal
			if (oldal == 4) {
				masik = 1;
				do {
					eggyik = (int) rnd.nextInt(votma.length);
				} while (1 == eggyik % 2);
			}

			// fel�lr�l kezd�dik
			if (oldal == 1) {
				for (int i = 0; i < (le - 1); i++) {
					if (i != (le - 1)) {

						if (votma[i + 1][masik] == false) {
							votma[i][masik] = true;
						}
					}
				}
			}
			// jobr�l kezd�dik
			if (oldal == 2) {
				for (int i = (hossz - 1); i > 0; i--) {
					if (i != 1) {
						if (votma[(int) eggyik][i - 1] == false) {
							votma[(int) eggyik][i] = true;
						}
					}

				}
			}
			// alulr�l kezd�dik
			if (oldal == 3) {
				for (int i = (le - 1); i > 0; i--) {
					if (i != 1) {
						if (votma[i - 1][(int) masik] == false) {
							votma[i][(int) masik] = true;
						}
					}

				}
			}
			// balr�l kezd�dik
			if (oldal == 4) {
				for (int i = 0; i < (hossz - 1); i++) {
					if (i != (hossz - 1)) {
						if (votma[((int) eggyik)][i + 1] == false) {
							votma[(int) eggyik][i] = true;
						}
					}
				}

			}

			count++;
		} while (count < 500);
		feltoltes = false;

		torles();

	}

}

//Itt kell kifejteni a k�perny� menet�t, v�lzot�it.
package hu.funyirok.rpg_warrior;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepJatekter extends kepernyo_os_obj {

	// http://sourceforge.net/projects/launch4j/files/launch4j-3/3.1.0-beta1/

	protected Szoveg betuiras = new Szoveg(this);
	alakzat aKilep, aHatter, ejaj_wrecked[], ejaj_wreckeda, aShield, aNext;
	alakzat aFel, aLe, aBal, aJobb, aLo, aNemLo;
	alakzat aLolhello;
	alakzat aFigura, aFigura_fel, aFigura_le, aFigura_jobb, aFigura_bal;
	alakzat aFal, aLaser;
	alakzat aFal_oldal;
	alakzat aSemmi;
	alakzat aEnemy;
	alakzat aBumm, aMenugomb;
	alakzat aStart;

	public Sound bum;
	public Sound crash;
	public Sound lazer;
	public Sound coin;
	public Sound csapodas;
	public Sound gameover;
	public int sebessegegyseg = 6;
	public int sebessegegyseg_enemy = 5;
	public int lepesegyseg = 1;
	public int laserX, laserY;
	public Random r;
	public boolean kesz = false, trigger, jatek_vege = false, loves = false, fal, valtozas = true, off_x = true, off_y = true, ellen_mozog = true, jatszottam = false, fel = true, jobb = false, bal = false, le = false, lepes = false, nyertes = false,
			jatekos_ido_vege = false;
	public int elet = 3, ido, jatek_ido, cooldown_shoot, lehules_ido_shoot = 5, cooldown_invulnerable = 100, lehules_ido_invulnerable = 100;
	public float mozdul_poz = (h / 800), mozdul_neg = -(h / 800), offset_y_prew, offset_x_prew;
	public int db = ablakRef.kepernyotoltes.ai_darab;// <---- DB az az hogy
														// mennyi
														// ellenfelet
	public int db2 = ablakRef.kepernyotoltes.coin_darab; // gener�ljon
	public int jatekok_szama;
	BitmapFont bmpfBetu;

	public KepMenu menu;
	public labrintusgenerator labirintus;
	public KepToltes kepernyotoltes;

	public float ejaj_mozog_x[] = new float[db + 1];
	public float ejaj_mozog_y[] = new float[db + 1];

	public AI ejaj[];
	public coin penz[];

	public int ejaj_elet[] = new int[db + 1];
	public int oles;

	public boolean score_van_e[] = new boolean[db + 1];
	public int score;

	public float maxoffsetx = (labirintus.hossz * labirintus.kockameret);
	public float maxoffsety = (labirintus.le * labirintus.kockameret);

	public KepJatekter(GdxAblak ablak) {
		super(ablak);
	}

	public void jatekmenet_letrehoz() {
		ablakRef.kepernyotoltes.lefutott = true;
		db = ablakRef.kepernyotoltes.ai_darab; // ha meg is van adva
												// deklar�l�sn�l
												// az �rt�ke att�l m�g nem
		db2 = ablakRef.kepernyotoltes.coin_darab; // kapja meg
		oles = 0;
		ejaj_mozog_x = new float[db + 1];
		ejaj_mozog_y = new float[db + 1];
		ejaj_elet = new int[db + 1];
		ejaj = new AI[db + 1];
		penz = new coin[db2 + 1];
		ejaj_wrecked = new alakzat[db + 1];

		aStart = new alakzat(this, "newgame.png", 400, false);
		aNext = new alakzat(this, "nextgame.png", 400, false);

		coin = Gdx.audio.newSound(Gdx.files.internal("coinfelszed.mp3"));
		gameover = Gdx.audio.newSound(Gdx.files.internal("gameover.mp3"));
		lazer = Gdx.audio.newSound(Gdx.files.internal("lazerke.wav"));
		crash = Gdx.audio.newSound(Gdx.files.internal("chrash.wav"));
		bum = Gdx.audio.newSound(Gdx.files.internal("michael_bay.wav"));
		csapodas = Gdx.audio.newSound(Gdx.files.internal("becsapodas.mp3"));

		ejaj_wreckeda = new alakzat(this, "ufoBlue_wreck.png", 20, true);

		aFigura = new alakzat(this, "semmi_uj.png", 20, true);
		aFigura.name = "DALEK";
		aFigura_fel = new alakzat(this, "kari_fel2.png", 15, 20, true);
		aFigura_le = new alakzat(this, "kari_le2.png", 15, 20, true);
		aFigura_jobb = new alakzat(this, "kari_job2.png", 15, 20, true);
		aFigura_bal = new alakzat(this, "kari_bal2.png", 15, 20, true);
		aShield = new alakzat(this, "shield.png", 20, true);

		aLaser = new alakzat(this, "laser.png", 1, false);
		aKilep = new alakzat(this, "vissza.png", 50, false);
		aLolhello = new alakzat(this, "coin.png", 40, false);
		aEnemy = new alakzat(this, "ufoBlue_ujjabb.png", 10, false);
		aFal_oldal = new alakzat(this, "falas.png", 40, false);
		aFal = new alakzat(this, "falas.png", 40, false);
		aSemmi = new alakzat(this, "semmi_uj.png", 40, false);
		aHatter = new alakzat(this, "hatterspace.jpg", 10, false);
		aBumm = new alakzat(this, "bumm.png", 40, false);

		aKilep.offset_engedelyezes = false;
		aHatter.offset_engedelyezes = false;

		aFel = new alakzat(this, "gomb_fel_uj.png", 40, false);
		aLe = new alakzat(this, "gomb_le_uj.png", 40, false);
		aBal = new alakzat(this, "gomb_bal_uj.png", 40, false);
		aJobb = new alakzat(this, "gomb_jobb_uj.png", 40, false);

		aLo = new alakzat(this, "shoot_active.png", 40, false);
		aNemLo = new alakzat(this, "shoot_deactive.png", 40, false);

		aNext.offset_engedelyezes = false;
		aStart.offset_engedelyezes = false;

		aLo.offset_engedelyezes = false;
		aNemLo.offset_engedelyezes = false;

		aFel.offset_engedelyezes = false;
		aLe.offset_engedelyezes = false;
		aJobb.offset_engedelyezes = false;
		aBal.offset_engedelyezes = false;

		bg_r = (float) 0.5; // Be�ll�tom a h�tt�rsz�nt...
		bg_g = (float) 0.5;
		bg_b = (float) 0.8;

		bmpfBetu = new BitmapFont();
		bmpfBetu.setColor(1, 1, 1, 1);

		labirintus = new labrintusgenerator(this, ablakRef.kepernyotoltes, aFal.getTexture(), aSemmi.getTexture(), w, h);

		for (int i = 0; i < db; i++) {
			ejaj[i] = new AI(this, labirintus, aEnemy.getTexture(), ejaj_wreckeda.getTexture(), 2);
		}
		if (dev)
			System.out.println("labrintus k�sz");

		for (int i = 0; i < db2; i++) {
			penz[i] = new coin(this, labirintus, aLolhello.getTexture(), w, h);
		}
		if (dev)
			System.out.println("ellenfelek k�sz");

		aFigura.atHelyez(labirintus.kockameret + 1, labirintus.kockameret + 1);
		aFigura.atHelyez(labirintus.kockameret + 1, labirintus.kockameret + 1);

		offset_y_prew = aFigura.getY() + aFigura.getMagassag() / 2 - (h / 2 + offset_y);
		offset_x_prew = aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w / 2) + offset_x);
		offset_y += offset_y_prew;
		offset_x += offset_x_prew;
		ablakRef.camera.position.x = offset_x;
		ablakRef.camera.position.y = offset_y;
		ablakRef.update(offset_x_prew, offset_y_prew);
	}

	@Override
	public void jatekmenet_atmeretez() {
		super.jatekmenet_atmeretez();

		aHatter.atmeretez((labirintus.hossz * labirintus.kockameret * 2) + (w / 2), (labirintus.le * labirintus.kockameret * 2) + (h / 2));
		aFigura.atmeretez((float) labirintus.kockameret * (float) 0.8, (float) labirintus.kockameret * (float) 0.8);

		aFigura_bal.atmeretez((float) labirintus.kockameret * (float) 0.8, (float) labirintus.kockameret * (float) 0.6);
		aFigura_jobb.atmeretez((float) labirintus.kockameret * (float) 0.8, (float) labirintus.kockameret * (float) 0.6);
		aFigura_le.atmeretez((float) labirintus.kockameret * (float) 0.6, (float) labirintus.kockameret * (float) 0.8);
		aFigura_fel.atmeretez((float) labirintus.kockameret * (float) 0.6, (float) labirintus.kockameret * (float) 0.8);
		aShield.atmeretez((float) labirintus.kockameret * (float) 0.8, (float) labirintus.kockameret * (float) 0.8);

		aEnemy.atmeretez((float) labirintus.kockameret * (float) 0.8, (float) labirintus.kockameret * (float) 0.8);

		aKilep.atHelyez(w - aKilep.getSzelesseg() - labirintus.kockameret / 2, h - aKilep.getMagassag() - labirintus.kockameret / 2);

		aLo.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);
		aNemLo.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);

		aFel.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);
		aLe.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);
		aJobb.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);
		aBal.atmeretez((float) labirintus.kockameret * (float) 1.5, (float) labirintus.kockameret * (float) 1.5);

		aLo.atHelyez(w - labirintus.kockameret / 2 - aLo.getSzelesseg(), labirintus.kockameret / 2);
		aNemLo.atHelyez(w - labirintus.kockameret / 2 - aLo.getSzelesseg(), labirintus.kockameret / 2);

		aFel.atHelyez(labirintus.kockameret / 2 + aFel.getSzelesseg(), labirintus.kockameret / 2 + aFel.getMagassag());
		aLe.atHelyez(labirintus.kockameret / 2 + aLe.getSzelesseg(), labirintus.kockameret / 2);
		aJobb.atHelyez(labirintus.kockameret / 2 + aJobb.getSzelesseg() * 2, labirintus.kockameret / 2);
		aBal.atHelyez(labirintus.kockameret / 2, labirintus.kockameret / 2);

		aNext.atmeretez(w / 4, h / 10);
		aNext.atHelyez(w / 2 - aNext.getW() / 2, h / 2 - aNext.getH() - 100);
		aStart.atmeretez(w / 4, h / 10);
		aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() - 100);

		// offset_y_prew = (aFigura.getY() + aFigura.getMagassag() / 2 - ((h /
		// 2) + offset_y));
		// offset_x_prew = (aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w /
		// 2) + offset_x));

		offset_y_prew = (aFigura.getY() + aFigura.getMagassag() / 2 - ((h / 2) + offset_y));
		offset_x_prew = (aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w / 2) + offset_x));

		offset_y += offset_y_prew;
		offset_x += offset_x_prew;

		ablakRef.camera.position.x = offset_x;
		ablakRef.camera.position.y = offset_y;

		ablakRef.update(offset_x_prew, offset_y_prew);
	}

	private void dalekutkozesvizsgalat() {
		// �tk�z�s k�sz. T�mb�t kellett alkalmazni.
		ArrayList<alakzat> vizsgalateredmeny = labirintus.utkozesvizsgalat(aFigura);
		for (alakzat a : vizsgalateredmeny)
			if (vizsgalateredmeny != null) {

				if (a.utkozesBal) {
					aFigura.atHelyez_x(a.getX() - aFigura.getW() - 2);
				}
				if (a.utkozesJobb) {
					aFigura.atHelyez_x(a.getX() + a.getW() + 2);
				}
				if (a.utkozesFent) {
					aFigura.atHelyez_y(a.getY() + a.getH() + 2);
				}
				if (a.utkozesLent) {
					aFigura.atHelyez_y(a.getY() - aFigura.getH() - 2);
				}
				offset_y_prew = (aFigura.getY() + aFigura.getMagassag() / 2 - ((h / 2) + offset_y));
				offset_x_prew = (aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w / 2) + offset_x));

				offset_y += offset_y_prew;
				offset_x += offset_x_prew;

				ablakRef.camera.position.x = offset_x;
				ablakRef.camera.position.y = offset_y;

				ablakRef.update(offset_x_prew, offset_y_prew);
				// ablakRef.update(offset_x, offset_y);

			}
	}

	public void laser() {
		aLaser.atHelyez(0 - aLaser.getSzelesseg() - h, 0 - aLaser.getMagassag() - w);
		laserX = 0;
		laserY = 0;
	}

	@Override
	public void jatekmenet_szal() {
		if (ablakRef.kepernyoMenu.main_menubol) {
			ablakRef.kepernyoMenu.main_menubol = false;
			offset_y_prew = ((aFigura.getY() + (aFigura.getMagassag() / 2)) - ((h / 2) + offset_y));
			offset_x_prew = (aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w / 2) + offset_x));

			offset_y += offset_y_prew;
			offset_x += offset_x_prew;

			ablakRef.camera.position.x = offset_x;
			ablakRef.camera.position.y = offset_y;

			ablakRef.update(offset_x_prew, offset_y_prew);

		}
		// offset_y_prew = aFigura.getY() + aFigura.getMagassag() / 2 - (h +
		// offset_y);
		// offset_x_prew = aFigura.getX() + aFigura.getSzelesseg() / 2 - ((w /
		// 2) + offset_x);
		if (score == db2) {
			jatek_vege = true;
			nyertes = true;
		}

		ArrayList<alakzat> vizsgalateredmeny_1 = labirintus.utkozesvizsgalat(aLaser);
		for (alakzat a : vizsgalateredmeny_1)
			if (vizsgalateredmeny_1 != null) {
				csapodas.play(0.5f);
				laser();
			}
		// ellenf�l specifikus dolgok gener�l�sa
		if (ellen_mozog) {
			for (int i = 0; i < db; i++) {
				ejaj[i].elet = 2;
				if (getRandomNumber(2) == 0) {
					ejaj_mozog_x[i] = 1;
				} else {
					ejaj_mozog_y[i] = 1;
				}
			}
			for (int i = 0; i < db2; i++) {
				score_van_e[i] = true;
			}
			ellen_mozog = false;
			ablakRef.update();
		}// ellenfelek mozgat�s �rt�k felt�ltve

		for (int i = 0; i < db; i++) {
			if (aFigura.utkozes(ejaj[i].foe)) {
				if (cooldown_invulnerable < jatek_ido) {
					cooldown_invulnerable = jatek_ido + lehules_ido_invulnerable;
					if (ejaj_mozog_y[i] != 0) {
						ejaj_mozog_y[i] = ejaj_mozog_y[i] * (-1);
					}
					if (ejaj_mozog_x[i] != 0) {
						ejaj_mozog_x[i] = ejaj_mozog_x[i] * (-1);
					}
					elet--;
					if (elet == 0) {
						jatekos_ido_vege = true;
						jatek_vege = true;
						gameover.play(0.7f);
					}
				}
			}
			if (aLaser.getX() > 0) {
				if (aLaser.utkozes(ejaj[i].foe)) {
					crash.play(0.7f);
					ejaj[i].elet--;
					laser();
					if (!ejaj[i].el()) {
						oles++;
						bum.play(0.7f);
					}
				}
			}
			if (!ejaj[i].el()) {
				ejaj[i].foe.atHelyez(-w * 2, -h * 2);

				ejaj_mozog_y[i] = 0;
				ejaj_mozog_x[i] = 0;
			}
			if (ejaj[i].el()) {
				ejaj[i].iranyvaltoztataskeresztezodesben(i);
			for (int b = 0; b < sebessegegyseg_enemy; b++) {
				ArrayList<alakzat> vizsgalateredmeny = labirintus.utkozesvizsgalat(ejaj[i].foe);
				for (alakzat a : vizsgalateredmeny) {
					if (a.utkozesBal) {
						ejaj_mozog_x[i] = 0;
						if (getRandomNumber(2) == 0) {
							ejaj_mozog_y[i] = mozdul_poz;
						} else {
							ejaj_mozog_y[i] = mozdul_neg;
						}
						ejaj[i].foe.atHelyez_x(a.getX() - ejaj[i].foe.getW() - 2);
					}
					if (a.utkozesJobb) {
						ejaj_mozog_x[i] = 0;
						if (getRandomNumber(2) == 0) {
							ejaj_mozog_y[i] = mozdul_poz;
						} else {
							ejaj_mozog_y[i] = mozdul_neg;
						}
						ejaj[i].foe.atHelyez_x(a.getX() + a.getW() + 2);
					}

					if (a.utkozesLent) {
						ejaj_mozog_y[i] = 0;
						if (getRandomNumber(2) == 0) {
							ejaj_mozog_x[i] = mozdul_poz;
						} else {
							ejaj_mozog_x[i] = mozdul_neg;
						}
						ejaj[i].foe.atHelyez_y(a.getY() - ejaj[i].foe.getH() - 2);

					}
					if (a.utkozesFent) {
						ejaj_mozog_y[i] = 0;
						if (getRandomNumber(2) == 0) {
							ejaj_mozog_x[i] = mozdul_poz;
						} else {
							ejaj_mozog_x[i] = mozdul_neg;
						}
						ejaj[i].foe.atHelyez_y(a.getY() + a.getH() + 2);
					}
				}

				ejaj[i].foe.atHelyez(ejaj[i].foe.getX() + ejaj_mozog_x[i], ejaj[i].foe.getY() + ejaj_mozog_y[i]);

			}
				
			}
		}

		// coin szed�se
		for (int i = 0; i < db2; i++) {
			if (score_van_e[i]) {
				if (aFigura.utkozes(penz[i].lolhello)) {
					score_van_e[i] = false;
					penz[i].lolhello.atHelyez(0 - penz[0].lolhello.getSzelesseg(), 0 - penz[0].lolhello.getMagassag());
					coin.play(0.7f);
					score++;
				}
			}
		}

		if (!jatek_vege) {
			jatek_ido++;
			if (trigger) {
				cooldown_shoot = jatek_ido + lehules_ido_shoot;
				trigger = false;
			}
		}
		if (!jatek_vege) {
			aLaser.atHelyez(aLaser.getX() + laserX, aLaser.getY() + laserY);
			billentyuzet_kezeles();
			if (lepes) {
				mozdulas_irany();
			}
		}
		if (jatek_vege) {
			aHatter.atHelyez(-10, -10);
			for (int i = 0; i < db; i++) {
				ejaj_mozog_x[i] = 0;
				ejaj_mozog_y[i] = 0;

			}
		}
	}

	public void billentyuzet_kezeles() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			bal = true;
			jobb = false;
			le = false;
			fel = false;
			lepes = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			jobb = true;
			bal = false;
			le = false;
			fel = false;
			lepes = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			fel = true;
			le = false;
			bal = false;
			jobb = false;
			lepes = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			le = true;
			fel = false;
			bal = false;
			jobb = false;
			lepes = true;
		}

	}

	public void loveshelyezes() {
		trigger = true;
		if (loves) {
//			Gdx.input.vibrate(40);
			if (jobb) {
				lazer.play(1.0f);
				laserX = 10;
				aLaser.atHelyez(aFigura.getX() + aFigura_jobb.getSzelesseg(), aFigura.getY() + aFigura_bal.getMagassag() / 4);
				aLaser.atmeretez(30, 5);
			}
			if (bal) {
				lazer.play(1.0f);
				laserX = -10;
				aLaser.atHelyez(aFigura.getX() - 30, aFigura.getY() + aFigura_bal.getMagassag() - aFigura_bal.getMagassag() / 2);
				aLaser.atmeretez(30, 5);
			}
			if (fel) {
				lazer.play(1.0f);
				laserY = 10;
				aLaser.atHelyez(aFigura.getX() + (aFigura_fel.getSzelesseg() - (aFigura_fel.getSzelesseg() / 2)), aFigura.getY() + aFigura_fel.getMagassag());
				aLaser.atmeretez(5, 30);
			}
			if (le) {
				lazer.play(1.0f);
				laserY = -10;
				aLaser.atHelyez(aFigura.getX() + aFigura_le.getMagassag() / 4, aFigura.getY() - 30);
				aLaser.atmeretez(5, 30);
			}
		}
	}

	public boolean tap(float x, float y, int count, int button) {
		if (jatek_vege) {
			if (aNext.benneVaneXY(x, y)) {
				ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
			}
		}
		if (aKilep.benneVaneXY(x, y)) {
			ablakRef.kepernyo_csere(ablakRef.kepernyoMenu);
		}

		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 1) {
			lepes = false;
		}
		return false;
	}

	public boolean keyUp(int keycode) {
		if (keycode == 19 || keycode == 20 || keycode == 21 || keycode == 22) {
			lepes = false;
		}
		return false;
	}

	public boolean keyDown(int keycode) {
		if (keycode == 62)
			if (aLaser.getX() < 0 && aLaser.getY() < 0 && cooldown_shoot < jatek_ido) {
				if (!loves) {
					loves = true;
					loveshelyezes();
				}
				loves = false;
			}
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if (!jatek_vege) {
			if (aFel.benneVaneXY(screenX, screenY)) {
				fel = true;
				le = false;
				bal = false;
				jobb = false;
				lepes = true;
			}

			if (aLe.benneVaneXY(screenX, screenY)) {
				le = true;
				fel = false;
				bal = false;
				jobb = false;
				lepes = true;
			}
			if (aBal.benneVaneXY(screenX, screenY)) {
				bal = true;
				jobb = false;
				le = false;
				fel = false;
				lepes = true;
			}

			if (aJobb.benneVaneXY(screenX, screenY)) {
				jobb = true;
				bal = false;
				le = false;
				fel = false;
				lepes = true;
			}

		}
		return false;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {
		if (!jatek_vege) {
			if (aFel.benneVaneXY(x, y)) {
				fel = true;
				le = false;
				bal = false;
				jobb = false;
				lepes = true;
			}

			if (aLe.benneVaneXY(x, y)) {
				le = true;
				fel = false;
				bal = false;
				jobb = false;
				lepes = true;
			}
			if (aBal.benneVaneXY(x, y)) {
				bal = true;
				jobb = false;
				le = false;
				fel = false;
				lepes = true;
			}

			if (aJobb.benneVaneXY(x, y)) {
				jobb = true;
				bal = false;
				le = false;
				fel = false;
				lepes = true;
			}
			if (aLo.benneVaneXY(x, y)) {
				if (aLaser.getX() < 0 && aLaser.getY() < 0 && cooldown_shoot < jatek_ido) {
					if (!loves) {
						loves = true;
						loveshelyezes();
					}
					loves = false;

				}
			}

		}
		return false;
	}

	public void mozdulas_irany() {
		if (lepes) {
			if (fel) {
				mozdulas(0, (h / 800));
			}
			if (jobb) {
				mozdulas((h / 800), 0);
			}
			if (bal) {
				mozdulas(-(h / 800), 0);
			}
			if (le) {
				mozdulas(0, -(h / 800));
			}

		}

	}

	public void mozdulas(float mozdulX, float mozdulY) {
		for (int i = 0; i < sebessegegyseg; i++) {
			aFigura.mozdul(mozdulX, mozdulY);
			offset_x += mozdulX;
			offset_y += mozdulY;
			ablakRef.update(mozdulX, mozdulY);
			dalekutkozesvizsgalat();
		}

		if (ablakRef.kepernyoMenu.giro) {
			lepes = false;
		}
	}

	@Override
	public void jatekmenet_giroszkop_Jobbra(float mertek) {
		if (ablakRef.kepernyoMenu.giro) {
			jobb = true;
			bal = false;
			le = false;
			fel = false;
			lepes = true;
		}
	}

	@Override
	public void jatekmenet_giroszkop_Balra(float mertek) {
		if (ablakRef.kepernyoMenu.giro) {
			bal = true;
			jobb = false;
			le = false;
			fel = false;
			lepes = true;
		}
	}

	@Override
	public void jatekmenet_giroszkop_Fel(float mertek) {
		if (ablakRef.kepernyoMenu.giro) {
			fel = true;
			le = false;
			bal = false;
			jobb = false;
			lepes = true;
		}
	}

	@Override
	public void jatekmenet_giroszkop_Le(float mertek) {
		if (ablakRef.kepernyoMenu.giro) {
			le = true;
			fel = false;
			bal = false;
			jobb = false;
			lepes = true;
		}
	}

	@Override
	public void jatekmenet_render(SpriteBatch batch) {
		if (!jatek_vege) {
			aHatter.atHelyez((-(offset_x / maxoffsetx) * 400) - (w / 2), (-(offset_y / maxoffsety) * 400) - (h / 2));
		}
		aHatter.rajzol(batch);
		aLaser.rajzol(batch);
		labirintus.render(batch);

		if (db == db2) {
			for (int i = 0; i < db; i++) {
				ejaj[i].render(batch);
				if (score_van_e[i]) {
					penz[i].render(batch);

				}
			}
		} else {
			for (int i = 0; i < db; i++) {
				ejaj[i].render(batch);
			}
			for (int i = 0; i < db2; i++) {
				if (score_van_e[i]) {
					penz[i].render(batch);

				}
			}

		}

		if (fel) {
			aFigura_fel.atHelyez((aFigura.getX() + aFigura.getSzelesseg() / 2) - aFigura_fel.getSzelesseg() / 2, (aFigura.getY() + aFigura.getMagassag() / 2) - aFigura_fel.getMagassag() / 2);
			aFigura_fel.rajzol(batch);

			if (cooldown_invulnerable > jatek_ido) {
				aShield.atHelyez(aFigura.getX(), aFigura.getY());
				aShield.rajzol(batch);
			}
		}
		if (le) {
			aFigura_le.atHelyez((aFigura.getX() + aFigura.getSzelesseg() / 2) - aFigura_le.getSzelesseg() / 2, (aFigura.getY() + aFigura.getMagassag() / 2) - aFigura_le.getMagassag() / 2);
			aFigura_le.rajzol(batch);

			if (cooldown_invulnerable > jatek_ido) {
				aShield.atHelyez(aFigura.getX(), aFigura.getY());
				aShield.rajzol(batch);
			}

		}
		if (jobb) {
			aFigura_jobb.atHelyez((aFigura.getX() + aFigura.getSzelesseg() / 2) - aFigura_jobb.getSzelesseg() / 2, (aFigura.getY() + aFigura.getMagassag() / 2) - aFigura_jobb.getMagassag() / 2);
			aFigura_jobb.rajzol(batch);

			if (cooldown_invulnerable > jatek_ido) {
				aShield.atHelyez(aFigura.getX(), aFigura.getY());
				aShield.rajzol(batch);
			}

		}
		if (bal) {
			aFigura_bal.atHelyez((aFigura.getX() + aFigura.getSzelesseg() / 2) - aFigura_bal.getSzelesseg() / 2, (aFigura.getY() + aFigura.getMagassag() / 2) - aFigura_bal.getMagassag() / 2);
			aFigura_bal.rajzol(batch);

			if (cooldown_invulnerable > jatek_ido) {
				aShield.atHelyez(aFigura.getX(), aFigura.getY());
				aShield.rajzol(batch);
			}
		}

		if (aLaser.getX() < 0 && aLaser.getY() < 0) {
			aLo.rajzol(batch);
		} else {
			aNemLo.rajzol(batch);
		}

		if (!ablakRef.kepernyoMenu.giro) {
			aFel.rajzol(batch);
			aLe.rajzol(batch);
			aJobb.rajzol(batch);
			aBal.rajzol(batch);
		}
		betuiras.render_balra(batch, 50, h - 50, "Score: " + score + " : " + db2, 20, false);
		betuiras.render_balra(batch, 50, h - 100, "kill: " + (oles), 20, false);
		betuiras.render_balra(batch, 50, h - 150, "Lives: " + (elet), 20, false);
		// betuiras.render_balra(batch, 50, h - 200, "Fps: " +
		// (Gdx.graphics.getFramesPerSecond()), 20, false);
		Gdx.graphics.getFramesPerSecond();

		if (jatekos_ido_vege) {
			jatek_vege = true;
			aHatter.rajzol(batch);
			betuiras.render_kozepre(batch, w / 2, h / 2 + 50, "Level: " + ablakRef.kepernyotoltes.szint, 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 + 20, "You are dead!", 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2, "Kills: " + (oles) + " Score: " + score + " Play time: " + jatek_ido / 100 + " sec", 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 - 25, "Session kills: " + (ablakRef.kepernyotoltes.kill_all + oles), 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 - 50, "Session scores: " + (ablakRef.kepernyotoltes.score_all + score), 20, false);
			aStart.rajzol(batch);
		}
		if (nyertes) {
			jatek_vege = true;
			aHatter.rajzol(batch);
			betuiras.render_kozepre(batch, w / 2, h / 2 + 50, "Level: " + ablakRef.kepernyotoltes.szint, 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 + 25, "You are winner!", 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2, "Kills: " + (oles) + " Score: " + score + " jatekido: " + jatek_ido / 100 + " sec", 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 - 25, "Session kills: " + (ablakRef.kepernyotoltes.kill_all + oles), 20, false);
			betuiras.render_kozepre(batch, w / 2, h / 2 - 50, "Session scores: " + (ablakRef.kepernyotoltes.score_all + score), 20, false);
			aNext.rajzol(batch);
		}
		if (!jatek_vege) {
			aKilep.rajzol(batch);
		}
	}

	@Override
	public void jatekmenet_megszuntet() {
		aStart.dispose();
		aNext.dispose();
		//aLaser.dispose();
		aFigura_fel.dispose();
		aFigura_le.dispose();
		aFigura_jobb.dispose();
		aFigura_bal.dispose();
		labirintus.dispose();
		aLolhello.dispose();
		aKilep.dispose();
		aFigura.dispose();
		aFal_oldal.dispose();
		//aFal.dispose();
		aSemmi.dispose();
		for (int i = 0; i < db; i++) {
			aBumm.dispose();
			ejaj[i].dispose();
		}
		aLo.dispose();
		aNemLo.dispose();
		aFel.dispose();
		aLe.dispose();
		aJobb.dispose();
		aBal.dispose();
		betuiras.dispose();
		aHatter.dispose();
		aShield.dispose();
		// finalize();
	}
}

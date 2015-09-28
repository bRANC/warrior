package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Ez az objektum egyetlen köt vagy négyzet alakzat megjelenítéséért,
 * mozgatásáért, tárolásáért és ütközés figyeléséért felelõs.
 */
public class alakzat {

	public String name = "";

	private Texture tt;
	private Sprite ss;
	private boolean kor_e;
	private boolean peldanyositott_textura = false;
	public boolean offset_engedelyezes = true;
	public kepernyo_os_obj kepRef;

	/** Bal alsó sarok */
	public float elozoX1 = Float.NaN;
	/** Bal alsó sarok */
	public float elozoY1 = Float.NaN;

	/** Jobb alsó sarok */
	public float elozoX2 = Float.NaN;
	/** Jobb alsó sarok */
	public float elozoY2 = Float.NaN;

	/** Jobb felsõ sarok */
	public float elozoX3 = Float.NaN;
	/** Jobb felsõ sarok */
	public float elozoY3 = Float.NaN;

	/** Bal felsõ sarok */
	public float elozoX4 = Float.NaN;
	/** Bal felsõ sarok */
	public float elozoY4 = Float.NaN;

	/**
	 * !!!FONTOS!!! Be kell állítani, hogy az alakzat mozog-e vagy csak a
	 * képernyõn áll. A mozgó és álló alakzatok ütközésénél fontos. Ha nem mozog
	 * az alakzat, akkor nincs értelme visszaléptetni. Ekkor az értéke hamis
	 * legyen.
	 */
	public boolean mozgo_alakzat = true;

	/**
	 * Ha az utolsó ütközésvizsgálatnál az alakzat itt megnevezett oldala a
	 * másik alakzathoz ért, akkor igaz, különben hamis. Mindig hamis lesz, ha
	 * nincs ütközés. Ha átlóban ütközik, akkor kettõ is lehet igaz.
	 */
	public boolean utkozesBal = false;
	/**
	 * Ha az utolsó ütközésvizsgálatnál az alakzat itt megnevezett oldala a
	 * másik alakzathoz ért, akkor igaz, különben hamis. Mindig hamis lesz, ha
	 * nincs ütközés. Ha átlóban ütközik, akkor kettõ is lehet igaz.
	 */
	public boolean utkozesJobb = false;
	/**
	 * Ha az utolsó ütközésvizsgálatnál az alakzat itt megnevezett oldala a
	 * másik alakzathoz ért, akkor igaz, különben hamis. Mindig hamis lesz, ha
	 * nincs ütközés. Ha átlóban ütközik, akkor kettõ is lehet igaz.
	 */
	public boolean utkozesFent = false;
	/**
	 * Ha az utolsó ütközésvizsgálatnál az alakzat itt megnevezett oldala a
	 * másik alakzathoz ért, akkor igaz, különben hamis. Mindig hamis lesz, ha
	 * nincs ütközés. Ha átlóban ütközik, akkor kettõ is lehet igaz.
	 */
	public boolean utkozesLent = false;

	/**
	 * Ütközés után megadja, hogy mennyi volt az ütközés szöge. Csak kör és
	 * négyzet ütközése esetén mûködik. Ez kell a kör ütközés irányának
	 * kiszámításához is.
	 */
	public float korUtkozesszogRad = 0;


	/**
	 * Akkor jó péládul ha már olyan texturát akarunk betenni, amit egyszer
	 * betöltöttünk. Ekkor nem kell újra betölteni, pazarolni az erõforrásokat.
	 * Méretarányosan méretez.
	 */
	public alakzat(kepernyo_os_obj kepReferencia, Texture tt, float szelesseg, boolean kor_e) {
		kepRef = kepReferencia;
		this.tt = tt;
		ss = new Sprite(tt);
		atmeretez(szelesseg);
		this.kor_e = kor_e;
	}

	/**
	 * Akkor jó péládul ha már olyan texturát akarunk betenni, amit egyszer
	 * betöltöttünk. Ekkor nem kell újra betölteni, pazarolni az erõforrásokat.
	 * Méretarány nem számít.
	 */
	public alakzat(kepernyo_os_obj kepReferencia, Texture tt, float szelesseg, float magassag, boolean kor_e) {
		kepRef = kepReferencia;
		this.tt = tt;
		ss = new Sprite(tt);
		atmeretez(szelesseg, magassag);
		this.kor_e = kor_e;
	}

	/** Méretarányosan méretez. */
	public alakzat(kepernyo_os_obj kepReferencia, String fajlnev, float szelesseg, boolean kor_e) {
		kepRef = kepReferencia;
		peldanyositott_textura = true;
		tt = new Texture(Gdx.files.internal(fajlnev));
		ss = new Sprite(tt);
		atmeretez(szelesseg);
		this.kor_e = kor_e;
	}

	/** Méretarány nem számít. */
	public alakzat(kepernyo_os_obj kepReferencia, String fajlnev, float szelesseg, float magassag, boolean kor_e) {
		kepRef = kepReferencia;
		peldanyositott_textura = true;
		tt = new Texture(Gdx.files.internal(fajlnev));
		ss = new Sprite(tt);
		atmeretez(szelesseg, magassag);
		this.kor_e = kor_e;
	}

	/**
	 * Ki lehet olvasni a texturát. Akkor jó péládul ha még egy ugyan ilyet
	 * akarunk létrehozni, csak máshol. Ekkor nem kell újra betölteni, pazarolni
	 * az erõforrásokat.
	 */
	public Texture getTexture() {
		return tt;
	}

	/** Kör? */
	public boolean is_kor() {
		return kor_e;
	}

	public boolean utkozes(alakzat masikalakzat) {
		// System.out.println(name + " this: " + getX() + " : " + getY() +
		// "    " + masikalakzat.name + "  Másik: " + masikalakzat.getX() +
		// " : " + masikalakzat.getY() + " off " + masikalakzat.kepRef.offset_x
		// + ":" + masikalakzat.kepRef.offset_y + " offeng" +
		// offset_engedelyezes + masikalakzat.offset_engedelyezes);
		utkozesBal = false;
		utkozesJobb = false;
		utkozesLent = false;
		utkozesFent = false;
		if (kor_e == false && masikalakzat.kor_e == false) { // négyzet
																// négyzettel
			// System.out.println("Négyzet négyzet");
			if (benneVaneXY(masikalakzat.getX(), masikalakzat.getY()) || benneVaneXY(masikalakzat.getX() + masikalakzat.getW(), masikalakzat.getY()) || benneVaneXY(masikalakzat.getX(), masikalakzat.getY() + masikalakzat.getH())
					|| benneVaneXY(masikalakzat.getX() + masikalakzat.getW(), masikalakzat.getY() + masikalakzat.getH()) || masikalakzat.benneVaneXY(getX(), getY()) || masikalakzat.benneVaneXY(getX() + getW(), getY())
					|| masikalakzat.benneVaneXY(getX(), getY() + getH()) || masikalakzat.benneVaneXY(getX() + getW(), getY() + getH())) {
				// System.out.println("TRUE");
				utkozesIranyVizsgalat(masikalakzat);
				return true;
			} else {
				// System.out.println("FALSE");
				return false;
			}
		} else {
			if (kor_e == true && masikalakzat.kor_e == true) {
				if ((Math.pow(((getX() + getSzelesseg() / 2) - (masikalakzat.getX() + masikalakzat.getSzelesseg() / 2)), 2)) + (Math.pow(((getY() + getMagassag() / 2) - (masikalakzat.getY() + masikalakzat.getMagassag() / 2)), 2)) < Math.pow(
						(getSzelesseg() / 2 + masikalakzat.getSzelesseg() / 2), 2)) {
					utkozesIranyVizsgalat(masikalakzat);
					return true;
				} else {
					return false;
				}
			} else { // Minden más esetben kör ütközik négyzettel
						// Megvizsgálom, hogy a kör köré írt négyzet ütközik-e a
						// négyzettel. Ha igen, akkor kell csak a kör pontjait a
						// négyzetben vizsgálni.
				boolean kor_e_ment_sajat = kor_e;
				boolean kor_e_ment_masik = masikalakzat.kor_e;
				kor_e = false;
				masikalakzat.kor_e = false;
				if (benneVaneXY(masikalakzat.getX(), masikalakzat.getY()) || benneVaneXY(masikalakzat.getX() + masikalakzat.getW(), masikalakzat.getY()) || benneVaneXY(masikalakzat.getX(), masikalakzat.getY() + masikalakzat.getH())
						|| benneVaneXY(masikalakzat.getX() + masikalakzat.getW(), masikalakzat.getY() + masikalakzat.getH()) || masikalakzat.benneVaneXY(getX(), getY()) || masikalakzat.benneVaneXY(getX() + getW(), getY())
						|| masikalakzat.benneVaneXY(getX(), getY() + getH()) || masikalakzat.benneVaneXY(getX() + getW(), getY() + getH())) {
					kor_e = kor_e_ment_sajat;
					masikalakzat.kor_e = kor_e_ment_masik;
					alakzat kor;
					alakzat negyzet;
					if (kor_e) // Vajon a másikalakzat vagy ez a kör? Legyen
								// egységes!
					{
						kor = this;
						negyzet = masikalakzat;
					} else {
						kor = masikalakzat;
						negyzet = this;
					}
					// Akkor mehet a kör pontjainak ellenõrzése a négyzetben.
					double i = 0;
					double korKozepX = kor.getX() + kor.getW() / 2;
					double korKozepY = kor.getY() + kor.getH() / 2;
					double korSugar = kor.getW() / 2;
					double korPontokSzama = 1 / (double) korSugar;
					double pi2 = Math.PI * 2;
					korPontokSzama *= 4; // A sebesség és a pontatlanság:)
											// növelése érdekében.
					// System.out.println("---------------------------------------------------------------");
					while (i <= pi2 && negyzet.benneVaneXY((int) (korKozepX + (Math.sin((double) i) * korSugar)), (int) (korKozepY + (int) (Math.cos((double) i) * korSugar))) == false) {
						// System.out.println((int)(korKozepX +
						// (Math.sin((double)i) * korSugar)) + ":" +
						// (int)(korKozepY + (int)(Math.cos((double)i) *
						// korSugar)));
						i += korPontokSzama;
					}
					if (i > pi2) {
						// Ha nincs olyan pontja a körnek, amely a négyzetben
						// van, akkor még esély van rá, hogy a négyzet a körbe
						// került. Ezért nézzük meg, hogy a négyzet 4 pontja
						// belül van-e.
						if (kor.benneVaneXY(negyzet.getX(), negyzet.getY()) || kor.benneVaneXY(negyzet.getX() + negyzet.getW(), negyzet.getY()) || kor.benneVaneXY(negyzet.getX(), negyzet.getY() + negyzet.getH())
								|| kor.benneVaneXY(negyzet.getX() + negyzet.getW(), negyzet.getY() + negyzet.getH())) {
							utkozesIranyVizsgalat(masikalakzat);
							return true;
						} else {
							return false;
						}
					} else {
						korUtkozesszogRad = (float) i;
						utkozesIranyVizsgalat(masikalakzat);
						return true;
					}
				} else {
					kor_e = kor_e_ment_sajat;
					masikalakzat.kor_e = kor_e_ment_masik;
					return false;
				}
			}
		}
	}

	/*
	 * Hibás működés: nem adja így vissza az összes ütközést. //Tömb ütközése
	 * egy elemmel
	 * 
	 * public boolean utkozes(alakzat masikalakzat[],int hossz) { for (int i =
	 * 0; i < hossz; i++) { if (utkozes(masikalakzat[i])) { return true; } }
	 * return false; }
	 * 
	 * //2D-s tömb alakzattal való ütközés
	 * 
	 * public boolean utkozes(alakzat masikalakzat[][],int hossz,int le) { for
	 * (int i = 0; i < hossz; i++) { for (int j = 0; j < le; j++) { if
	 * (utkozes(masikalakzat[i][j])) { return true; } } } return false; }
	 */

	/**
	 * Ellenõrzi, hogy x és y pont az alakzaton belül van-e.
	 *
	 * @param x
	 *            Az x pont
	 * @param y
	 *            Az y pont
	 * @return true, akkor benne van.
	 */
	public boolean benneVaneXY(float x, float y) {
		if (kepRef != null) {
			/*
			 * if (offset_engedelyezes==true) { x=x+kepRef.offset_x;
			 * y=y+kepRef.offset_y; }
			 */
		}
		// IMPLEMENTÁLNI KELL
		if (kor_e) {
			if (Math.sqrt((double) ((getX() + getSzelesseg() / 2 - x) * (getX() + getSzelesseg() / 2 - x) + (getY() + getMagassag() / 2 - y) * (getY() + getMagassag() / 2 - y))) < (double) (getSzelesseg() / 2)) {
				return true;
			}
		} else {
			if (getX() <= x && getX() + getSzelesseg() >= x && getY() <= y && getY() + getMagassag() >= y) {
				return true;
			}
		}
		return false; // Csak azért, hogy lefusson
	}

	/**
	 * A render függvényben kell meghívni, megjeleníti a megfelelõ helyen.
	 *
	 * @param batch
	 */
	public void rajzol(SpriteBatch batch) {

		/*
		 * if (!offset_engedelyezes) { if
		 * (ss.getX()-kepRef.offset_x<kepRef.ablakRef.w &&
		 * ss.getY()-kepRef.offset_y+ss.getHeight()>0 &&
		 * ss.getX()-kepRef.offset_x+ss.getWidth()>0 &&
		 * ss.getY()-kepRef.offset_y<kepRef.ablakRef.h) { batch.draw(tt,
		 * ss.getX()-kepRef.offset_x, ss.getY()-kepRef.offset_y, ss.getWidth(),
		 * ss.getHeight()); } } else { if (ss.getX()<kepRef.ablakRef.w &&
		 * ss.getY()+ss.getHeight()>0 && ss.getX()+ss.getWidth()>0 &&
		 * ss.getY()<kepRef.ablakRef.h) { batch.draw(tt, ss.getX(), ss.getY(),
		 * ss.getWidth(), ss.getHeight()); } }
		 */

		if (!offset_engedelyezes) {
			batch.draw(tt, ss.getX() + kepRef.offset_x - kepRef.ablakRef.w / 2, ss.getY() + kepRef.offset_y - kepRef.ablakRef.h / 2, ss.getWidth(), ss.getHeight());
		} else {
			if (ss.getX() - kepRef.offset_x - kepRef.ablakRef.w / 2 < kepRef.ablakRef.w && ss.getY() - kepRef.offset_y + ss.getHeight() + kepRef.ablakRef.h / 2 > 0 && ss.getX() - kepRef.offset_x + ss.getWidth() + kepRef.ablakRef.h / 2 > 0
					&& ss.getY() - kepRef.offset_y - kepRef.ablakRef.h / 2 < kepRef.ablakRef.h) {
				batch.draw(tt, ss.getX() - kepRef.ablakRef.w / 2, ss.getY() - kepRef.ablakRef.w / 2, ss.getWidth(), ss.getHeight());
			}
		}
		// batch.draw(tt, ss.getX(), ss.getY(), ss.getWidth(), ss.getHeight());
	}

	/**
	 * Az objektumon belül tárolja az utolsó elõtti pozíciót. Minden
	 * méretezéskor, áthelyezéskor végrehajtódik.
	 */
	private void pozicio_ment() {
		elozoX1 = ss.getX();
		elozoY1 = ss.getY();

		elozoX2 = elozoX1 + ss.getWidth();
		elozoY2 = elozoY1;

		elozoX3 = elozoX2;
		elozoY3 = elozoY2 + ss.getHeight();

		elozoX4 = elozoX1;
		elozoY4 = elozoY3;
	}

	/**
	 * Az elõzõ pozícióba lép, ha van ilyen. Ha sikerült, ture értékkel tér
	 * vissza. Csak egy visszalépési pont van.
	 */
	public boolean pozicio_visszalep() {
		if (elozoX1 == Float.NaN) {
			return false;
		} else {
			ss.setPosition(elozoX1, elozoY1);
			ss.setSize(elozoX2 - elozoX1, elozoY3 - elozoY2);
			elozoX1 = Float.NaN;
			elozoY1 = Float.NaN;

			elozoX2 = Float.NaN;
			elozoY2 = Float.NaN;

			elozoX3 = Float.NaN;
			elozoY3 = Float.NaN;

			elozoX4 = Float.NaN;
			elozoY4 = Float.NaN;
			return true;
		}
	}

	/**
	 * Megnézi, hogy az ütközés elõtt a két alakzat merre haladt, és azt, hogy
	 * az összes pontja a másik alakzatnak az aktuális alakzattól merre van.
	 */
	private void utkozesIranyVizsgalat(alakzat masik) {
		if (masik.pozicio_visszalep_vane() && pozicio_visszalep_vane()) {
			if (masik.kor_e == false && kor_e == false)// Négyzet-Négyzet
			{

				utkozesBal = (masik.elozoX3 <= elozoX4 && masik.elozoX4 <= elozoX4 && masik.elozoX3 <= elozoX3 && masik.elozoX4 <= elozoX3);
				utkozesJobb = (masik.elozoX3 >= elozoX4 && masik.elozoX4 >= elozoX4 && masik.elozoX3 >= elozoX3 && masik.elozoX4 >= elozoX3);
				utkozesFent = (masik.elozoY1 >= elozoY4 && masik.elozoY4 >= elozoY4 && masik.elozoY1 >= elozoY1 && masik.elozoY4 >= elozoY1);
				utkozesLent = (masik.elozoY1 <= elozoY4 && masik.elozoY4 <= elozoY4 && masik.elozoY1 <= elozoY1 && masik.elozoY4 <= elozoY1);
			} else {
				if (masik.kor_e == true && kor_e == true) {
					// Kör-kör - Itt a bal, jobb, fent, lent helyett jobb lenne
					// a szögeket használni: korUtkozesszogRad
					// De gyors megoldásnak ezek is jók, ezért implementálom.
					float masik_korKozepX = masik.elozoX1 + (masik.elozoX2 - masik.elozoX1) / 2;
					float masik_korKozepY = masik.elozoY1 + (masik.elozoY3 - masik.elozoY2) / 2;
					float korKozepX = elozoX1 + (elozoX2 - elozoX1) / 2;
					float korKozepY = elozoY1 + (elozoY3 - elozoY2) / 2;
					// System.out.println( + " : " + utkozoY);
					if (korKozepX == masik_korKozepX) // 0-val való osztás
														// elkerülése
					{
						if (korKozepY < masik_korKozepY) {
							utkozesFent = true;
						} else {
							utkozesLent = true;
						}
					} else {
						if (korKozepY == masik_korKozepY) {
							if (korKozepX < masik_korKozepX) {
								utkozesJobb = true;
							} else {
								utkozesBal = true;
							}
						} else {
							float ertek = (float) Math.abs(Math.tanh((korKozepX - masik_korKozepX) / (korKozepY - masik_korKozepY)));
							if (ertek > (float) 0.999) // Az oldalát éri
														// valamennyire:)
							{
								utkozesJobb = (korKozepX < masik_korKozepX);
								utkozesBal = (korKozepX > masik_korKozepX);
							} else {
								if (ertek < (float) 0.16) // A tetejét éri
															// valamennyire:)
								{
									utkozesFent = (korKozepY < masik_korKozepY);
									utkozesLent = (korKozepY > masik_korKozepY);
								} else // Különben átlóban éri
								{
									utkozesJobb = (korKozepX < masik_korKozepX);
									utkozesBal = (korKozepX > masik_korKozepX);
									utkozesFent = (korKozepY < masik_korKozepY);
									utkozesLent = (korKozepY > masik_korKozepY);
								}
							}
							System.out.println(ertek);
						}
					}
				} else {
					alakzat kor;
					alakzat negyzet;
					if (kor_e) {
						kor = this;
						negyzet = masik;
					} else {
						kor = masik;
						negyzet = this;
					}
					/*
					 * Majdnem jó így, csak ha túl nagyot ugrana, akkor vezetne
					 * hibához. float pip4=(float)(Math.PI/4.0);
					 * utkozesFent=(kor.korUtkozesszogRad>=pip4*7 ||
					 * kor.korUtkozesszogRad<=pip4);
					 * utkozesJobb=(kor.korUtkozesszogRad>=pip4 &&
					 * kor.korUtkozesszogRad<=pip4*3);
					 * utkozesLent=(kor.korUtkozesszogRad>=pip4*3 &&
					 * kor.korUtkozesszogRad<=pip4*5);
					 * utkozesBal=(kor.korUtkozesszogRad>=pip4*5 &&
					 * kor.korUtkozesszogRad<=pip4*7);
					 * System.out.println(kor.korUtkozesszogRad);
					 */
					double korKozepX = kor.elozoX1 + (kor.elozoX2 - kor.elozoX1) / 2;
					double korKozepY = kor.elozoY1 + (kor.elozoY3 - kor.elozoY2) / 2;
					double korSugar = (kor.elozoX3 - kor.elozoX2) / 2;
					float utkozoX = (float) (korKozepX + (Math.sin(kor.korUtkozesszogRad) * korSugar));
					float utkozoY = (float) (korKozepY + (int) (Math.cos(kor.korUtkozesszogRad) * korSugar));
					// System.out.println(utkozoX + " : " + utkozoY);
					utkozesBal = (utkozoX > negyzet.elozoX4 && utkozoX > negyzet.elozoX3);
					utkozesJobb = (utkozoX < negyzet.elozoX4 && utkozoX < negyzet.elozoX3);
					utkozesLent = (utkozoY > negyzet.elozoY3 && utkozoY > negyzet.elozoY2);
					utkozesFent = (utkozoY < negyzet.elozoY3 && utkozoY < negyzet.elozoY2);
				}
			}
		}
	}

	/** Megnézi, hogy van-e visszalépési lehetõség. */
	public boolean pozicio_visszalep_vane() {
		return (elozoX1 != Float.NaN);
	}

	/** Beállítja mindkét pozíciót. */
	public void atHelyez(float x, float y) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(x, y);
		} else {
			ss.setPosition(x, y);
			pozicio_ment();
		}
	}

	/** Beállítja a függõleges pozíciót. A vízszintes marad. */
	public void atHelyez_y(float y) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(ss.getX(), y);
		} else {
			ss.setPosition(ss.getX(), y);
			pozicio_ment();
		}
	}

	/** Beállítja a vízszintes pozíciót. A függõleges marad. */
	public void atHelyez_x(float x) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(x, ss.getY());
		} else {
			ss.setPosition(x, ss.getY());
			pozicio_ment();
		}
	}

	/** Mindkét tengelyen elmozdítja el a megadott értékkel */
	public void mozdul(float x, float y) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(ss.getX() + x, ss.getY() + y);
		} else {
			ss.setPosition(ss.getX() + x, ss.getY() + y);
			pozicio_ment();
		}
	}

	/** Vízszintesen mozdítja el a megadott értékkel */
	public void mozdul_y(float y) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(ss.getX(), ss.getY() + y);
		} else {
			ss.setPosition(ss.getX(), ss.getY() + y);
			pozicio_ment();
		}
	}

	/** Függõlegesen mozdítja el a megadott értékkel */
	public void mozdul_x(float x) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setPosition(ss.getX() + x, ss.getY());
		} else {
			ss.setPosition(ss.getX() + x, ss.getY());
			pozicio_ment();
		}
	}

	public void atmeretez(float szelesseg) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setSize(szelesseg, szelesseg / ss.getWidth() * ss.getHeight());
		} else {
			ss.setSize(szelesseg, szelesseg / ss.getWidth() * ss.getHeight());
			pozicio_ment();
		}
	}

	public void atmeretezY(float magassag) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setSize(magassag / ss.getHeight() * ss.getWidth(), magassag);
		} else {
			ss.setSize(magassag / ss.getHeight() * ss.getWidth(), magassag);
			pozicio_ment();
		}
	}

	public void atmeretez(float szelesseg, float magassag) {
		if (mozgo_alakzat) {
			pozicio_ment();
			ss.setSize(szelesseg, magassag);
		} else {
			ss.setSize(szelesseg, magassag);
			pozicio_ment();
		}
	}

	public float getX() {
		return ss.getX();
	}

	public float getY() {
		return ss.getY();
	}

	public float getSzelesseg() {
		return ss.getWidth();
	}

	public float getMagassag() {
		return ss.getHeight();
	}

	public float getW() {
		return ss.getWidth();
	}

	public float getH() {
		return ss.getHeight();
	}

	public void dispose() {
		if (peldanyositott_textura) {			
			tt.dispose();
		}
	}
}

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
	alakzat aKilep, aHatter,  aNext;
	alakzat aStart;

	public Random r;
	public boolean kesz = false,  jatek_vege = false,  nyertes = false, jatekos_ido_vege = false;
														// mennyi
														// ellenfelet
	BitmapFont bmpfBetu;

	public KepMenu menu;
	public KepToltes kepernyotoltes;


	public KepJatekter(GdxAblak ablak) {
		super(ablak);
	}

	public void jatekmenet_letrehoz() {
		ablakRef.kepernyotoltes.lefutott = true;

		aStart = new alakzat(this, "newgame.png", 400, false);
		aNext = new alakzat(this, "nextgame.png", 400, false);

///		csapodas = Gdx.audio.newSound(Gdx.files.internal("becsapodas.mp3")); //a minta kedvéért



		aKilep = new alakzat(this, "vissza.png", 50, false);
		aHatter = new alakzat(this, "hatterspace.jpg", 10, false);

		aKilep.offset_engedelyezes = false;
		aHatter.offset_engedelyezes = false;


		aNext.offset_engedelyezes = false;
		aStart.offset_engedelyezes = false;


		bg_r = (float) 0.5; // Be�ll�tom a h�tt�rsz�nt...
		bg_g = (float) 0.5;
		bg_b = (float) 0.8;

		bmpfBetu = new BitmapFont();
		bmpfBetu.setColor(1, 1, 1, 1);




	}

	@Override
	public void jatekmenet_atmeretez() {
		super.jatekmenet_atmeretez();

		aNext.atmeretez(w / 4, h / 10);
		aNext.atHelyez(w / 2 - aNext.getW() / 2, h / 2 - aNext.getH() - 100);
		aStart.atmeretez(w / 4, h / 10);
		aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() - 100);

	}

	@Override
	public void jatekmenet_szal() {
		if (ablakRef.kepernyoMenu.main_menubol) {
			ablakRef.kepernyoMenu.main_menubol = false;
		}
		}// ellenfelek mozgat�s �rt�k felt�ltve
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
		return false;
	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {


		return false;
	}



	@Override
	public void jatekmenet_render(SpriteBatch batch) {
		if (!jatek_vege) {

		}
		aHatter.rajzol(batch);
		Gdx.graphics.getFramesPerSecond();

		if (jatekos_ido_vege) {
			jatek_vege = true;
			aHatter.rajzol(batch);

			aStart.rajzol(batch);
		}
		if (nyertes) {
			jatek_vege = true;
			aHatter.rajzol(batch);

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
		aKilep.dispose();

		betuiras.dispose();
		aHatter.dispose();

		// finalize();
	}
}

package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepToltes extends kepernyo_os_obj {
	protected Szoveg betuiras = new Szoveg(this);


	alakzat aKilep, aHatter, aResume;
	public float betumeret = h / 40;

	public boolean lefutott;

	public KepToltes(GdxAblak ablak) {
		super(ablak);
	}

	@Override
	public void jatekmenet_letrehoz() {
		
		aHatter = new alakzat(this, "hatterspace.jpg", 10, false);

		aResume = new alakzat(this, "res.png", 400, false);
		aResume.atHelyez(w / 2, h / 2);

		aResume.offset_engedelyezes = false;
		aHatter.offset_engedelyezes = false;
		aHatter.atmeretez(w, h);

	}

	@Override
	public void jatekmenet_atmeretez() {
		super.jatekmenet_atmeretez();
		betumeret = h / 40;
		aResume.atHelyez(w / 2, h / 2);
		aHatter.atmeretez(w, h);
	}

	@Override
	public void jatekmenet_szal() {
		
	}

	@Override
	public void jatekmenet_render(SpriteBatch batch) {
		
		aHatter.rajzol(batch);
		kiertekeles();

			ablakRef.kepernyoJatekter.jatekmenet_megszuntet();			
			ablakRef.kepernyoJatekter=null;
			ablakRef.kepernyoJatekter=new KepJatekter(ablakRef);
			//ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
	}

	@Override
	public void jatekmenet_megszuntet() {
		aResume.dispose();
		aHatter.dispose();
		betuiras.dispose();
	}


	public void kiertekeles() {
		
		if (dev) System.out.println("�rt�kl�s bel�p�s");
		
		
		if (ablakRef.kepernyoJatekter.nyertes) {

		}
		if (ablakRef.kepernyoJatekter.jatekos_ido_vege) {

		}

	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		if (aResume.benneVaneXY(screenX, screenY)) {
			if (!ablakRef.kepernyoJatekter.jatek_vege) {

				ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
			} else {
				kiertekeles();
			}
		}
		return false;

	}

}

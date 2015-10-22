package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepSugo extends kepernyo_os_obj {
	protected Szoveg betuiras = new Szoveg(this);
	alakzat aHatter;
	alakzat aMini;
	alakzat aKilep;
	int betumeret = 1;
	
	public KepMenu menu;
	public KepJatekter jatek;
	
	public KepSugo(GdxAblak ablak) {
		super(ablak);
	}

	@Override
	public void jatekmenet_letrehoz() {

		aKilep = new alakzat(this, "vissza.png", 60, false);
		aMini = new alakzat(this, "valami.png", w, false);
		aHatter = new alakzat(this, "halp_tut.png", 500, false);

		
		
		aMini.offset_engedelyezes = false;
		aHatter.offset_engedelyezes = false;
		aKilep.offset_engedelyezes = false;

	}

	public void jatekmenet_atmeretez() {
		super.jatekmenet_atmeretez();
		float betumeret = h / 40;

		aHatter.atmeretez((float) w - h / 10, (float) h - h / 10);
		aMini.atmeretez((float) w, (float) h);

		aHatter.atHelyez(h / 20, h / 20);
		aKilep.atHelyez(w - aKilep.getSzelesseg(), h - aKilep.getMagassag());


	}
	

	@Override
	public void jatekmenet_szal() {
	}

	@Override
	public void jatekmenet_render(SpriteBatch batch) {
		// itt ir ki
		float betumeret = h / 40;
		aMini.rajzol(batch);
		aHatter.rajzol(batch);
		aKilep.rajzol(batch);

	}

	@Override
	public void jatekmenet_megszuntet() {
		aKilep.dispose();
		aMini.dispose();
		aHatter.dispose();
		betuiras.dispose();

	}

	public boolean tap(float x, float y, int count, int button) {
		if (aKilep.benneVaneXY(x, y)) {
			ablakRef.kepernyo_csere(ablakRef.kepernyoMenu);
		}
		return false;

	}

}

package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepSugo extends kepernyo_os_obj {
	protected Szoveg betuiras = new Szoveg(this);
	alakzat aHatter;
	alakzat aPlayer;
	alakzat aEnamy;
	alakzat aEnamy2;
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

		aKilep = new alakzat(this, "vissza.png", 40, false);
		aMini = new alakzat(this, "valami.png", w, false);
		aHatter = new alakzat(this, "frame.png", 500, false);
		aEnamy = new alakzat(this, "ufoBlue_ujjabb.png", 40, false);
		aPlayer = new alakzat(this, "kari_fel2.png", 40, false);
		aEnamy2 = new alakzat(this, "ufoBlue_wreck.png", 40, false);
		
		
		aMini.offset_engedelyezes = false;
		aHatter.offset_engedelyezes = false;
		aPlayer.offset_engedelyezes = false;
		aEnamy2.offset_engedelyezes = false;
		aKilep.offset_engedelyezes = false;

	}

	public void jatekmenet_atmeretez() {
		super.jatekmenet_atmeretez();
		float betumeret = h / 40;

		aPlayer.atmeretez((float) w / 14, (float) h / 10);
		aHatter.atmeretez((float) w - h / 10, (float) h - h / 10);
		aEnamy2.atmeretez((float) w / 12, (float) h / 10);
		aEnamy.atmeretez((float) w / 12, (float) h / 10);
		aMini.atmeretez((float) w, (float) h);

		aHatter.atHelyez(h / 20, h / 20);
		aKilep.atHelyez(w - aKilep.getSzelesseg(), h - aKilep.getMagassag());
		aEnamy2.atHelyez(w / 2 + w / 7, betumeret * 10);
		aEnamy.atHelyez(w / 5, betumeret * 10);
		aPlayer.atHelyez(w / 5, betumeret * 18);

	}
	

	@Override
	public void jatekmenet_szal() {
	}

	@Override
	public void jatekmenet_render(SpriteBatch batch) {
		ablakRef.update();
		// itt ir ki
		float betumeret = h / 40;
		aMini.rajzol(batch);
		aHatter.rajzol(batch);
		aKilep.rajzol(batch);
		aPlayer.rajzol(batch);
		aEnamy.rajzol(batch);
		aEnamy2.rajzol(batch);

		betuiras.render_kozepre(batch, w / 2, betumeret * 26, "Buttons: Control with the arrow buttons", betumeret, false);
		betuiras.render_kozepre(batch, w / 2, betumeret * (float) 24.5, "Shoot: with the big red button or the left ctrl. ", betumeret, false);
		betuiras.render_kozepre(batch, w / 2, betumeret * 23, "Gyroscope: You can move Dalek with the tilt of the device.", betumeret, false);
		betuiras.render_balra(batch, h / 20 + betumeret * 2, betumeret * 20, "Player: ", betumeret, false);
		betuiras.render_balra(batch, h / 20 + w / 3, betumeret * 20, "You have only 1 chance", betumeret, false);
		betuiras.render_balra(batch, h / 20 + betumeret * 2, betumeret * 12, "Enemy: ", betumeret, false);
		betuiras.render_balra(batch, h / 20 + w / 3, betumeret * 12, "Wounded Enemy: ", betumeret, false);
		betuiras.render_kozepre(batch, w / 2, betumeret * 5, "Needs 2 shots", betumeret, false);

	}

	@Override
	public void jatekmenet_megszuntet() {
		aKilep.dispose();
		aMini.dispose();
		aEnamy2.dispose();
		aEnamy.dispose();
		aPlayer.dispose();
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

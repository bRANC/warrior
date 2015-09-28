package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepToltes extends kepernyo_os_obj {
	protected Szoveg betuiras = new Szoveg(this);

	public int bk_szint = 1 ,bk_lehules_ido, bk_lab_meret_x = 15, bk_lab_meret_y = 15, bk_torles_mennyisege = (int) Math.sqrt(bk_lab_meret_x * bk_lab_meret_y), bk_ai_darab = 1, bk_coin_darab = 1;

	public int score_all, kill_all ,szint = bk_szint, lehules_ido, lab_meret_x = bk_lab_meret_x, lab_meret_y = bk_lab_meret_y, torles_mennyisege = bk_torles_mennyisege, ai_darab = bk_ai_darab, coin_darab = bk_coin_darab;

	alakzat aKilep, aHatter, aResume;
	public float betumeret = h / 40;

	public boolean lefutott;

	public Sound sFaf;

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

		sFaf = Gdx.audio.newSound(Gdx.files.internal("exterminate.mp3"));
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
		betuiras.render_kozepre(batch, w / 2, h / 2 + 60, "Level: " + szint , 20, true);
		betuiras.render_kozepre(batch, w / 2, h / 2, "Please wait...", 20, true);		
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
			lab_meret_x += 4;
			lab_meret_y += 4;
			torles_mennyisege = (int) Math.sqrt(lab_meret_x * lab_meret_y);
			ai_darab += 2;
			coin_darab += 2;
			szint++;
			score_all=ablakRef.kepernyoJatekter.score;
			kill_all=ablakRef.kepernyoJatekter.oles;
		}
		if (ablakRef.kepernyoJatekter.jatekos_ido_vege) {
			lab_meret_x = bk_lab_meret_x;
			lab_meret_y = bk_lab_meret_y;
			torles_mennyisege = (int) Math.sqrt(lab_meret_x * lab_meret_y);
			ai_darab = bk_ai_darab;
			coin_darab = bk_coin_darab;
			szint=bk_szint;
			score_all=0;
			kill_all=0;
		}

	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		if (aResume.benneVaneXY(screenX, screenY)) {
			if (!ablakRef.kepernyoJatekter.jatek_vege) {
				sFaf.play(1.0f);
				ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
			} else {
				kiertekeles();
			}
		}
		return false;

	}

}

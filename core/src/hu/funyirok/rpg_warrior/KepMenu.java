//Itt kell kifejteni a k�perny� menet�t, v�lzot�it.
package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepMenu extends kepernyo_os_obj {
    protected Szoveg betuiras = new Szoveg(this);
    alakzat aStart;
    alakzat aResume;
    alakzat aKilep;
    alakzat aRajz;
    alakzat aHatter;
    alakzat aMini;
    alakzat aHalp;
    public float betumeret = h / 40;
    public boolean main_menubol, jatek_van = false, kattintva = false, jatekbaugras = false;
    public Sound sFaf;


    /**
     * Konstruktor, a param�ter �tad�s miatt kell.
     */
    public KepMenu(GdxAblak ablak) {
        super(ablak);
    }

    /**
     * L�trehoz�skor fut le. Ide kell tenni a textur�k bet�lt�s�t.
     */
    @Override
    public void jatekmenet_letrehoz() {
        aStart = new alakzat(this, "newgame.png", 400, false);
        aResume = new alakzat(this, "res.png", 400, false);
        aHatter = new alakzat(this, "frame.png", 500, false);
        aKilep = new alakzat(this, "exit2.png", 40, false);
        ////aMini = new alakzat(this, "valami.png", 40, false);
        aHalp = new alakzat(this, "help.png", 400, false);

        aStart.offset_engedelyezes = false;
        aResume.offset_engedelyezes = false;
        //aHatter.offset_engedelyezes = false;
        aKilep.offset_engedelyezes = false;
//		aMini.offset_engedelyezes = false;
        aHalp.offset_engedelyezes = false;

        aKilep.offset_engedelyezes = false;
        sFaf = Gdx.audio.newSound(Gdx.files.internal("exterminate.mp3"));
    }

    /**
     * �tm�retez�skor �s a textur�k bet�lt�se ut�n fut le. A m�reteket itt kell
     * be�ll�tani.
     */
    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;

        aHatter.atmeretez(w, h);


        aStart.atmeretez(w / 4, h / 10);
        aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() / 2 + betumeret * 3);

        aResume.atmeretez(w / 4, h / 10);
        aResume.atHelyez(w / 2 - aResume.getW() / 2, h / 2 - aResume.getH() / 2 + betumeret * 3);

//		aMini.atmeretez((float) w, (float) h);

        aHalp.atmeretez(w / 4, h / 10);
        aHalp.atHelyez(w / 2 - aHalp.getW() / 2, h / 2 - aHalp.getH() / 2 - betumeret * 3);

        aKilep.atHelyez(w - aKilep.getSzelesseg() - betumeret, h - aKilep.getMagassag() - betumeret);

    }


    /**
     * Ezt ism�tli a sz�l. Itt kell mozgatni, kattint�st kezelni, stb...
     */
    @Override
    public void jatekmenet_szal() {
    }

    public boolean tap(float x, float y, int count, int button) {

        if (aStart.benneVaneXY(x, y)) {
            System.out.println("aStart");
            kattintva = true;

            if (ablakRef.kepernyoJatekter.jatek_vege) {
                main_menubol = true;
                ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
            } else {
                main_menubol = true;
                System.out.println("képrenyőtöltés");
                ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
            }
        }

        if (aHalp.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.KepernyoSugo);
        }
        if (aKilep.benneVaneXY(x, y)) {

            Gdx.app.exit();
        }
        return false;
    }

    /**
     * Itt j�n l�tre a k�p.
     */
    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        ablakRef.update();
//		aMini.rajzol(batch);
        aHatter.rajzol(batch);

        if (kattintva) {
            if (ablakRef.kepernyoJatekter.jatek_vege) {
                aStart.rajzol(batch);
            } else {
                aResume.rajzol(batch);
            }
        } else {
            aStart.rajzol(batch);
        }
        aKilep.rajzol(batch);
        aHalp.rajzol(batch);
    }

    /**
     * Itt sz�nnek meg a textur�k.
     */
    @Override
    public void jatekmenet_megszuntet() {
        aResume.dispose();
        aStart.dispose();
        //	aMini.dispose();
        aHatter.dispose();
        aKilep.dispose();
        aHalp.dispose();
        betuiras.dispose();
    }

}

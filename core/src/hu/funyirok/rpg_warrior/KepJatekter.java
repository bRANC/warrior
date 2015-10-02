//Itt kell kifejteni a k�perny� menet�t, v�lzot�it.
package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class KepJatekter extends kepernyo_os_obj {

    // http://sourceforge.net/projects/launch4j/files/launch4j-3/3.1.0-beta1/
    public float betumeret = h / 40;
    // protected Szoveg betuiras = new Szoveg(this);
    alakzat aKilep, aHatter, aNextgame;
    alakzat aStart;

    public Random r;
    public boolean kesz = false, jatek_vege = false, nyertes = false, jatekos_ido_vege = false;
    // mennyi
    // ellenfelet
    BitmapFont bmpfBetu;
    public KepMenu menu;
    public KepToltes kepernyotoltes;
    public Hero hos1,hos2,hos3,hos4;
    //public Hero fos;

    public KepJatekter(GdxAblak ablak) {
        super(ablak);
    }

    public void jatekmenet_letrehoz() {
        betumeret = h / 40;

        System.out.println(this + " " + h + " " + w);

        hos1 = new Hero(this, h, w, "jani", 500, 10, 20, 10,0);

        hos2 = new Hero(this, h, w, "Peti", 500, 10, 20, 10,1);
        ablakRef.kepernyotoltes.lefutott = true;

        aStart = new alakzat(this, "newgame.png", 400, false);
        aNextgame = new alakzat(this, "nextgame.png", 400, false);


///		csapodas = Gdx.audio.newSound(Gdx.files.internal("becsapodas.mp3")); //a minta kedvéért


        aKilep = new alakzat(this, "vissza.png", 50, false);
        aKilep.atHelyez(w - aKilep.getW() - betumeret, h - aKilep.getH() - betumeret);
        aHatter = new alakzat(this, "hatterspace.jpg", 10, false);


        bg_r = (float) 0.5; // Be�ll�tom a h�tt�rsz�nt...
        bg_g = (float) 0.5;
        bg_b = (float) 0.8;

        bmpfBetu = new BitmapFont();
        bmpfBetu.setColor(1, 1, 1, 1);
    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;
        aKilep.atHelyez(w - aKilep.getW() - betumeret, h - aKilep.getH() - betumeret);

        aNextgame.atmeretez(w / 4, h / 10);
        aNextgame.atHelyez(w / 2 - aNextgame.getW() / 2, h / 2 - aNextgame.getH() - 100);
        aStart.atmeretez(w / 4, h / 10);
        aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() - 100);

    }

    @Override
    public void jatekmenet_szal() {
        hos1.attack(hos2);
        hos2.HealthOut();
        if (ablakRef.kepernyoMenu.main_menubol) {
            ablakRef.kepernyoMenu.main_menubol = false;
        }
    }

    public boolean tap(float x, float y, int count, int button) {

        if (aKilep.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.kepernyoMenu);
        }

        return false;
    }


    @Override
    public void jatekmenet_render(SpriteBatch batch) {

        aHatter.rajzol(batch);

            hos1.render(batch);
            hos2.render(batch);

        // fos.render(batch);
        Gdx.graphics.getFramesPerSecond();

        if (jatekos_ido_vege) {
            jatek_vege = true;
            aHatter.rajzol(batch);
            aStart.rajzol(batch);
        }
        if (!jatek_vege) {
            aKilep.rajzol(batch);
        }

    }

    @Override
    public void jatekmenet_megszuntet() {
        aStart.dispose();
        aNextgame.dispose();
        aKilep.dispose();

//        betuiras.dispose();
        aHatter.dispose();

        // finalize();
    }
}

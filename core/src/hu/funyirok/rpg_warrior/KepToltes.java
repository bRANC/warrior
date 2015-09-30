package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepToltes extends kepernyo_os_obj {
    protected Szoveg betuiras = new Szoveg(this);


    alakzat aKilep, aHatter, jat2, jat3, jat4, jat5;
    public boolean jat_2 = false, jat_3 = false, jat_4 = false, jat_5 = false, valasztva = false;
    public float betumeret = h / 40;

    public boolean lefutott;

    public KepToltes(GdxAblak ablak) {
        super(ablak);
    }

    @Override
    public void jatekmenet_letrehoz() {
        aKilep= new alakzat(this,"vissza.png",50,false);
        aKilep.atHelyez(w-aKilep.getW()-betumeret,h-aKilep.getH()-betumeret);
        aHatter = new alakzat(this, "frame.png", 10, false);


        jat2 = new alakzat(this, "res.png", 200, false);
        jat2.atmeretez(w / 4, h / 10);
        jat2.atHelyez(w / 2 - jat2.getW() / 2, h / 2 - jat2.getH() / 2 + betumeret * 3);
        jat3 = new alakzat(this, "coin.png", 200, false);
        jat3.atmeretez(w / 4, h / 10);
        jat3.atHelyez(w / 2 - jat3.getW() / 2, h / 2 - jat3.getH() / 2 + betumeret * 3);
        jat4 = new alakzat(this, "ikon.png", 200, false);
        jat4.atmeretez(w / 4, h / 10);
        jat4.atHelyez(w / 2 - jat4.getW() / 2, h / 2 - jat4.getH() / 2 + betumeret * 3);
        jat5 = new alakzat(this, "help.png", 200, false);
        jat5.atmeretez(w / 4, h / 10);
        jat5.atHelyez(w / 2 - jat5.getW() / 2, h / 2 - jat5.getH() / 2 + betumeret * 3);


        aHatter.atmeretez(w, h);
        //aHatter.atHelyez(0,0);
    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;

        aKilep.atHelyez(w-aKilep.getW()-betumeret,h-aKilep.getH()-betumeret);

        jat2.atmeretez(w / 4, h / 10);
        jat2.atHelyez(w / 2 - jat2.getSzelesseg() - betumeret * 2, h / 2 + jat2.getH() / 2 + betumeret * 3);
        jat3.atmeretez(w / 4, h / 10);
        jat3.atHelyez(w / 2 + betumeret * 2, h / 2 + jat3.getH() / 2 + betumeret * 3);
        jat4.atmeretez(w / 4, h / 10);
        jat4.atHelyez(w / 2 - jat4.getSzelesseg() - betumeret * 2, h / 2 - jat4.getH() / 2 - betumeret * 3);
        jat5.atmeretez(w / 4, h / 10);
        jat5.atHelyez(w / 2 + betumeret * 2, h / 2 - jat5.getH() / 2 - betumeret * 3);
        aHatter.atmeretez(w, h);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        aHatter.rajzol(batch);
        jat2.rajzol(batch);
        jat3.rajzol(batch);
        jat4.rajzol(batch);
        jat5.rajzol(batch);
        aKilep.rajzol(batch);
        //kiertekeles();

        if (valasztva) {
            ablakRef.kepernyoMenu.jatekbaugras=true;
            ablakRef.kepernyoJatekter.jatekmenet_megszuntet();
            ablakRef.kepernyoJatekter = null;
            ablakRef.kepernyoJatekter = new KepJatekter(ablakRef);
            ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
        }
    }

    @Override
    public void jatekmenet_megszuntet() {

        jat2.dispose();
        jat3.dispose();
        jat4.dispose();
        jat5.dispose();
        aHatter.dispose();
        aKilep.dispose();
        //betuiras.dispose();
    }

    public boolean tap(float x, float y, int count, int button) {

        if (jat2.benneVaneXY(x, y)) {
            jat_2 = true;
            valasztva = true;
        }
        if (jat3.benneVaneXY(x, y)) {
            jat_3 = true;
            valasztva = true;
        }
        if (jat4.benneVaneXY(x, y)) {
            jat_4 = true;
            valasztva = true;
        }
        if (jat5.benneVaneXY(x, y)) {
            jat_5 = true;
            valasztva = true;
        }
        return false;
    }




	/*public void kiertekeles() {

		if (dev) System.out.println("�rt�kl�s bel�p�s");
		
		
		if (ablakRef.kepernyoJatekter.nyertes) {

		}
		if (ablakRef.kepernyoJatekter.jatekos_ido_vege) {

		}

	}*/


}

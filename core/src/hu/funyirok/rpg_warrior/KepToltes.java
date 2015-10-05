package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepToltes extends kepernyo_os_obj {
    protected Szoveg betuiras = new Szoveg(this);


    alakzat aVissza, aHatter, jat2, jat3, jat4, jat5;
    public boolean jat_2 = false, jat_3 = false, jat_4 = false, jat_5 = false, valasztva = false;
    public float betumeret = h / 40;

    public boolean lefutott;

    public KepToltes(GdxAblak ablak) {
        super(ablak);
    }

    @Override
    public void jatekmenet_letrehoz() {
        aVissza = new alakzat(this,"vissza.png",50,false);
        aVissza.atHelyez(w- aVissza.getW()-betumeret,h- aVissza.getH()-betumeret);
        aHatter = new alakzat(this, "frame.png", 10, false);


        jat2 = new alakzat(this, "player2.png", 200, false);
        jat2.atmeretez(w / 4, h / 10);
        jat2.atHelyez(w / 2 - jat2.getW() / 2, h / 2 - jat2.getH() / 2 + betumeret * 3);
        jat3 = new alakzat(this, "player3.png", 200, false);
        jat3.atmeretez(w / 4, h / 10);
        jat3.atHelyez(w / 2 - jat3.getW() / 2, h / 2 - jat3.getH() / 2 + betumeret * 3);
        jat4 = new alakzat(this, "player4.png", 200, false);
        jat4.atmeretez(w / 4, h / 10);
        jat4.atHelyez(w / 2 - jat4.getW() / 2, h / 2 - jat4.getH() / 2 + betumeret * 3);
        jat5 = new alakzat(this, "player5.png", 200, false);
        jat5.atmeretez(w / 4, h / 10);
        jat5.atHelyez(w / 2 - jat5.getW() / 2, h / 2 - jat5.getH() / 2 + betumeret * 3);


        aHatter.atmeretez(w, h);
        //aHatter.atHelyez(0,0);
    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;

        aVissza.atHelyez(w- aVissza.getW()-betumeret,h- aVissza.getH()-betumeret);

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
        aVissza.rajzol(batch);
        //kiertekeles();

        if (valasztva) {
            ablakRef.kepernyoMenu.jatekbaugras=true;
            ablakRef.kepernyoJatekter.jatekmenet_megszuntet();
            ablakRef.kepernyoJatekter = null;
            ablakRef.kepernyoJatekter = new KepJatekter(ablakRef);
            ablakRef.kepernyo_csere(ablakRef.KepBekeres);
        }
    }

    @Override
    public void jatekmenet_megszuntet() {

        jat2.dispose();
        jat3.dispose();
        jat4.dispose();
        jat5.dispose();
        aHatter.dispose();
        aVissza.dispose();
    }

    public boolean tap(float x, float y, int count, int button) {

        if (aVissza.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.kepernyoMenu);
        }
        if (jat2.benneVaneXY(x, y)) {
            jat_2 = true;
            valasztva = true;
        }
        if (jat3.benneVaneXY(x, y)) {
            jat_2=true;
            jat_3 = true;
            valasztva = true;
        }
        if (jat4.benneVaneXY(x, y)) {
            jat_2=true;
            jat_3 = true;
            jat_4 = true;
            valasztva = true;
        }
        if (jat5.benneVaneXY(x, y)) {
            jat_2=true;
            jat_3 = true;
            jat_4 = true;
            jat_5 = true;
            valasztva = true;
        }
        return false;
    }


}

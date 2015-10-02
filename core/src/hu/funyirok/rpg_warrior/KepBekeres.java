package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }
    public float betumeret = h / 40;
    public String nev = "  ";
    public Szoveg nev_be;
    public  alakzat aVissza,aNext;

    @Override
    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        aNext = new alakzat(this, "Next_feherhat.png", 50, false);
        aNext.atHelyez(betumeret,w-aNext.getSzelesseg()-betumeret);

        aVissza = new alakzat(this,"vissza.png",50,false);
        aVissza.atHelyez(w- aVissza.getW()-betumeret,h- aVissza.getH()-betumeret);
        nev = " ";
        nev_be = new Szoveg(this);
        nev_be.ini_render_balra(h / 2, w / 2, "Nev:" + nev, 20);
        System.out.println(nev);
    }
    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;
        aNext.atHelyez(betumeret,w-aNext.getSzelesseg()-betumeret);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {

        nev_be.render_balra(batch);
        aVissza.rajzol(batch);
        aNext.rajzol(batch);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (aNext.benneVaneXY(x,y)){
            ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
        }
        if (aVissza.benneVaneXY(x,y)){
            ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
            ablakRef.kepernyotoltes.valasztva=false;
            ablakRef.kepernyotoltes.jat_2=false;
            ablakRef.kepernyotoltes.jat_3=false;
            ablakRef.kepernyotoltes.jat_4=false;
            ablakRef.kepernyotoltes.jat_5=false;
        }
        if (nev_be.erintve(x, y)) {
            Gdx.input.setOnscreenKeyboardVisible(true);
        }else{
            Gdx.input.setOnscreenKeyboardVisible(false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println((int) character);
        if (nev.length() > 0) {
            if (character == 8) {
                if (nev.length() > 1) {
                    nev = nev.substring(0, nev.length() - 1);
                }
            } else {
                if (character > 64 && character < 98 || character > 96 && character < 123 || character > 47 && character < 58) {
                    nev += character;
                }
            }

        }
        nev_be.szoveg_valtoztat("nev:" + nev);


        return false;
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}

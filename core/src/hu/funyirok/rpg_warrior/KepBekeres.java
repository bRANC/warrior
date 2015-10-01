package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }

    public String nev = "  ";
    public Szoveg nev_be;

    @Override
    public void jatekmenet_letrehoz() {
        nev = " ";
        nev_be = new Szoveg(this);
        nev_be.ini_render_balra(h / 2, w / 2, "Nev: " + nev, 20);
        System.out.println(nev);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {

        nev_be.render_balra(batch);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (nev_be.erintve(x, y)) {
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // if (character == null)
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
        nev_be.szoveg_valtoztat("nev: " + nev);


        return false;
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}

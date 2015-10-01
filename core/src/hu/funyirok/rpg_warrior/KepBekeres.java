package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by bRANC on 10/1/2015.
 */
public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }

    public String nev = " ";
    protected Szoveg betuiras = new Szoveg(this);

    @Override
    public void jatekmenet_letrehoz() {

    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        betuiras.render_balra(batch, h / 2, w / 2, "Nev: " + nev, 20, false);
    }
    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.input.setOnscreenKeyboardVisible(true);
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
                nev += character;
            }

        }


        return false;
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}

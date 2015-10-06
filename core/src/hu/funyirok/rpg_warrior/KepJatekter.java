//Itt kell kifejteni a k�perny� menet�t, v�lzot�it.
package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class KepJatekter extends kepernyo_os_obj {

    // http://sourceforge.net/projects/launch4j/files/launch4j-3/3.1.0-beta1/
    public float betumeret = h / 40, szovegmeret;
    // protected Szoveg betuiras = new Szoveg(this);
    alakzat aKilep, aHatter, aNextgame;
    alakzat aStart;
    private Random random;
    public Random r;
    public boolean kesz = false, jatek_vege = false, nyertes = false, jatekos_ido_vege = false, tamadot = true, tamadot_1 = true, tamadhatnak = false;
    BitmapFont bmpfBetu;
    public KepMenu menu;
    public KepToltes kepernyotoltes;
    public Hero hos1, hos2, hos3, hos4, hos5, hos_tamad, hos_tamad_prev;
    public int lassitas = 0;
    public Hero[] hosok = new Hero[5];
public int jatekosok=0,elok=0;
    public KepJatekter(GdxAblak ablak) {
        super(ablak);
    }

    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        random = new Random();
        r = new Random();

        if (r.nextBoolean()) {
            hos1 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 0);
            hos2 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 1);
            if (ablakRef.kepernyotoltes.jat_3) {
                hos3 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 2);
            }
            if (ablakRef.kepernyotoltes.jat_4) {
                hos4 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 3);
                if (ablakRef.kepernyotoltes.jat_5) {
                    hos5 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 4);
                }
            }
        } else {
            hos1 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 2);
            hos2 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 4);
            if (ablakRef.kepernyotoltes.jat_3) {
                hos3 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 0);
            }
            if (ablakRef.kepernyotoltes.jat_4) {
                hos4 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 3);
                if (ablakRef.kepernyotoltes.jat_5) {
                    hos5 = new Hero(this, h, w, "  ", 500, 10, 20, 10, 1);
                }
            }
        }

        ablakRef.kepernyotoltes.lefutott = true;

        aStart = new alakzat(this, "newgame.png", 400, false);
        aNextgame = new alakzat(this, "nextgame.png", 400, false);


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
        for (int a=0;a<jatekosok;a++){
            hosok[a].atmeretez(h,w);
        }

        aKilep.atHelyez(w - aKilep.getW() - betumeret, h - aKilep.getH() - betumeret);

        aNextgame.atmeretez(w / 4, h / 10);
        aNextgame.atHelyez(w / 2 - aNextgame.getW() / 2, h / 2 - aNextgame.getH() - 100);
        aStart.atmeretez(w / 4, h / 10);
        aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() - 100);

    }

    @Override
    public void jatekmenet_szal() {

        if (tamadhatnak) {
            int i = 0;
            for (int a=0;a<jatekosok;a++){
                if (hosok[a].dead){
                    i++;
                }
            }
            elok=jatekosok-i;
            if (i == jatekosok-1) {
                tamadhatnak = false;
            }
            if (!jatek_vege) {
                int a;
                int b ;

                do {
                    a = r.nextInt(jatekosok);
                    b = r.nextInt(jatekosok);
                } while (a == b || hosok[a].dead);
                System.out.println(a+" "+b);
                hosok[a].attack(hosok[b]);
            }
        }else{
            for (int a=0;a<jatekosok;a++){
                System.out.print(" " + hosok[a].hp);
            }
            System.out.println(" ");
        }
        if (ablakRef.kepernyoMenu.main_menubol)
        {
            ablakRef.kepernyoMenu.main_menubol = false;
        }

        if (lassitas > 10)

        {
            elso_fut = false;
        } else

        {
            lassitas++;
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
       /* if (ablakRef.KepBekeres.bekerve){
            hos1 = new Hero(this, h, w, ablakRef.KepBekeres.hos1, ablakRef.KepBekeres.hos1_hp, ablakRef.KepBekeres.hos1_mana, ablakRef.KepBekeres.hos1_attack, ablakRef.KepBekeres.hos1_defense, 0);
            hos2 = new Hero(this, h, w, ablakRef.KepBekeres.hos2, ablakRef.KepBekeres.hos2_hp, ablakRef.KepBekeres.hos2_mana, ablakRef.KepBekeres.hos2_attack, ablakRef.KepBekeres.hos2_defense, 1);
            if (ablakRef.kepernyotoltes.jat_3) {
                hos3 = new Hero(this, h, w, ablakRef.KepBekeres.hos3, ablakRef.KepBekeres.hos3_hp, ablakRef.KepBekeres.hos3_mana, ablakRef.KepBekeres.hos3_attack, ablakRef.KepBekeres.hos3_defense, 2);
                if (ablakRef.kepernyotoltes.jat_4) {
                    hos4 = new Hero(this, h, w, ablakRef.KepBekeres.hos4, ablakRef.KepBekeres.hos4_hp, ablakRef.KepBekeres.hos4_mana, ablakRef.KepBekeres.hos4_attack, ablakRef.KepBekeres.hos4_defense, 3);
                    if (ablakRef.kepernyotoltes.jat_5) {
                        hos5 = new Hero(this, h, w, ablakRef.KepBekeres.hos5, ablakRef.KepBekeres.hos5_hp, ablakRef.KepBekeres.hos5_mana, ablakRef.KepBekeres.hos5_attack, ablakRef.KepBekeres.hos5_defense, 4);
                    }
                }
            }
            //a támadáshoz kell
            if (ablakRef.kepernyotoltes.jat_5) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
            }
            if (ablakRef.kepernyotoltes.jat_4) {
                ablakRef.kepernyotoltes.jat_5 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
            }
            if (ablakRef.kepernyotoltes.jat_3) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_5 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
            }
            if (ablakRef.kepernyotoltes.jat_2) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_5 = false;
            }


            tamadhatnak = true;
            ablakRef.KepBekeres.bekerve = false;
        }*/

        if (ablakRef.KepBekeres.bekerve) {
            if (r.nextBoolean()) {
                hos1 = new Hero(this, h, w, "Eggyik", ablakRef.KepBekeres.hos1_hp, ablakRef.KepBekeres.hos1_mana, ablakRef.KepBekeres.hos1_attack, ablakRef.KepBekeres.hos1_defense, 0);
                hos2 = new Hero(this, h, w, "Masik", ablakRef.KepBekeres.hos2_hp, ablakRef.KepBekeres.hos2_mana, ablakRef.KepBekeres.hos2_attack, ablakRef.KepBekeres.hos2_defense, 1);
                if (ablakRef.kepernyotoltes.jat_3) {
                    hos3 = new Hero(this, h, w, "Megegy", ablakRef.KepBekeres.hos3_hp, ablakRef.KepBekeres.hos3_mana, ablakRef.KepBekeres.hos3_attack, ablakRef.KepBekeres.hos3_defense, 2);
                    if (ablakRef.kepernyotoltes.jat_4) {
                        hos4 = new Hero(this, h, w, "calki", ablakRef.KepBekeres.hos4_hp, ablakRef.KepBekeres.hos4_mana, ablakRef.KepBekeres.hos4_attack, ablakRef.KepBekeres.hos4_defense, 3);
                        if (ablakRef.kepernyotoltes.jat_5) {
                            hos5 = new Hero(this, h, w, "ezmiez", ablakRef.KepBekeres.hos5_hp, ablakRef.KepBekeres.hos5_mana, ablakRef.KepBekeres.hos5_attack, ablakRef.KepBekeres.hos5_defense, 4);
                        }
                    }
                }
            } else {
                hos1 = new Hero(this, h, w, "Eggyik", ablakRef.KepBekeres.hos1_hp, ablakRef.KepBekeres.hos1_mana, ablakRef.KepBekeres.hos1_attack, ablakRef.KepBekeres.hos1_defense, 2);
                hos2 = new Hero(this, h, w, "Masik", ablakRef.KepBekeres.hos2_hp, ablakRef.KepBekeres.hos2_mana, ablakRef.KepBekeres.hos2_attack, ablakRef.KepBekeres.hos2_defense, 4);
                if (ablakRef.kepernyotoltes.jat_3) {
                    hos3 = new Hero(this, h, w, "Megegy", ablakRef.KepBekeres.hos3_hp, ablakRef.KepBekeres.hos3_mana, ablakRef.KepBekeres.hos3_attack, ablakRef.KepBekeres.hos3_defense, 3);
                    if (ablakRef.kepernyotoltes.jat_4) {
                        hos4 = new Hero(this, h, w, "calki", ablakRef.KepBekeres.hos4_hp, ablakRef.KepBekeres.hos4_mana, ablakRef.KepBekeres.hos4_attack, ablakRef.KepBekeres.hos4_defense, 1);
                        if (ablakRef.kepernyotoltes.jat_5) {
                            hos5 = new Hero(this, h, w, "ezmiez", ablakRef.KepBekeres.hos5_hp, ablakRef.KepBekeres.hos5_mana, ablakRef.KepBekeres.hos5_attack, ablakRef.KepBekeres.hos5_defense, 0);
                        }
                    }
                }
            }
            hosok[0] = hos1;
            hosok[1] = hos2;
            hosok[2] = hos3;
            hosok[3] = hos4;
            hosok[4] = hos5;
            //a támadáshoz kell
            if (ablakRef.kepernyotoltes.jat_5) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
                jatekosok=5;
            }
            if (ablakRef.kepernyotoltes.jat_4) {
                ablakRef.kepernyotoltes.jat_5 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
                jatekosok=4;
            }
            if (ablakRef.kepernyotoltes.jat_3) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_5 = false;
                ablakRef.kepernyotoltes.jat_2 = false;
                jatekosok=3;
            }
            if (ablakRef.kepernyotoltes.jat_2) {
                ablakRef.kepernyotoltes.jat_4 = false;
                ablakRef.kepernyotoltes.jat_3 = false;
                ablakRef.kepernyotoltes.jat_5 = false;
                jatekosok=2;
            }
            System.out.println(ablakRef.kepernyotoltes.jat_5 + "  " + ablakRef.kepernyotoltes.jat_4 + " " + ablakRef.kepernyotoltes.jat_3 + " " + ablakRef.kepernyotoltes.jat_2);

            tamadhatnak = true;
            ablakRef.KepBekeres.bekerve = false;
        }
        aHatter.rajzol(batch);
        if (ablakRef.kepernyotoltes.jat_5) {
            hos1.render(batch);
            hos2.render(batch);
            hos3.render(batch);
            hos4.render(batch);
            hos5.render(batch);
            hos1.render_querry(batch);
            hos2.render_querry(batch);
            hos3.render_querry(batch);
            hos4.render_querry(batch);
            hos5.render_querry(batch);
        }
        if (ablakRef.kepernyotoltes.jat_4) {
            hos1.render(batch);
            hos2.render(batch);
            hos3.render(batch);
            hos1.render_querry(batch);
            hos2.render_querry(batch);
            hos3.render_querry(batch);
        }
        if (ablakRef.kepernyotoltes.jat_3) {
            hos1.render(batch);
            hos2.render(batch);
            hos3.render(batch);
            hos4.render(batch);
            hos1.render_querry(batch);
            hos2.render_querry(batch);
            hos3.render_querry(batch);
            hos4.render_querry(batch);
        }
        if (ablakRef.kepernyotoltes.jat_2) {
            hos1.render(batch);
            hos2.render(batch);
            hos1.render_querry(batch);
            hos2.render_querry(batch);
        }

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

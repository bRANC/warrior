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

    public Random r;
    public boolean kesz = false, jatek_vege = false, nyertes = false, jatekos_ido_vege = false;
    BitmapFont bmpfBetu;
    public KepMenu menu;
    public KepToltes kepernyotoltes;
    public Hero hos1, hos2, hos3, hos4, hos5;
    public int lassitas = 0;

    public KepJatekter(GdxAblak ablak) {
        super(ablak);
    }

    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;

/*
        hos1 = new Hero(this, h, w, "jani", 500, 10, 20, 10, 0);
        hos2 = new Hero(this, h, w, "Peti", 500, 10, 20, 10, 1);
  */

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
        hos1.atmeretez(h, w);
        hos2.atmeretez(h, w);
        if (ablakRef.kepernyotoltes.jat_3) {
            hos3.atmeretez(h, w);
            if (ablakRef.kepernyotoltes.jat_4) {
                hos4.atmeretez(h, w);
                if (ablakRef.kepernyotoltes.jat_5) {
                    hos5.atmeretez(h, w);
                }
            }
        }
        aKilep.atHelyez(w - aKilep.getW() - betumeret, h - aKilep.getH() - betumeret);

        aNextgame.atmeretez(w / 4, h / 10);
        aNextgame.atHelyez(w / 2 - aNextgame.getW() / 2, h / 2 - aNextgame.getH() - 100);
        aStart.atmeretez(w / 4, h / 10);
        aStart.atHelyez(w / 2 - aStart.getW() / 2, h / 2 - aStart.getH() - 100);

    }

    @Override
    public void jatekmenet_szal() {
        hos1.attack_ki_helyez();
        hos2.defense_ki_helyez();


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


    public void hos_tamad_5(Hero tamado) {
        tamado.attack_ki_helyez();
        double tamads_ertek;
        if (rnd.nextBoolean()) {
            tamads_ertek = tamado.attack(hos2);
            if (tamads_ertek == 0) {
                tamads_ertek = tamado.attack(hos3);
                if (tamads_ertek == 0) {
                    tamads_ertek = tamado.attack(hos4);
                    if (tamads_ertek == 0) {
                        tamads_ertek = tamado.attack(hos5);
                        if (tamads_ertek == 0) {
                            tamado.nyert = true;
                        } else {
                            hos5.defense_ki_helyez();
                        }
                    } else {
                        hos4.defense_ki_helyez();
                    }
                } else {
                    hos3.defense_ki_helyez();
                }
            } else {
                hos2.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            tamads_ertek = tamado.attack(hos5);
            if (tamads_ertek == 0) {
                tamads_ertek = tamado.attack(hos4);
                if (tamads_ertek == 0) {
                    tamads_ertek = tamado.attack(hos2);
                    if (tamads_ertek == 0) {
                        tamads_ertek = tamado.attack(hos3);
                        if (tamads_ertek == 0) {
                            tamado.nyert = true;
                        } else {
                            hos3.defense_ki_helyez();
                        }
                    } else {
                        hos2.defense_ki_helyez();
                    }
                } else {
                    hos4.defense_ki_helyez();
                }
            } else {
                hos5.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            tamads_ertek = tamado.attack(hos4);
            if (tamads_ertek == 0) {
                tamads_ertek = tamado.attack(hos5);
                if (tamads_ertek == 0) {
                    tamads_ertek = tamado.attack(hos3);
                    if (tamads_ertek == 0) {
                        tamads_ertek = tamado.attack(hos2);
                        if (tamads_ertek == 0) {
                            tamado.nyert = true;
                        } else {
                            hos2.defense_ki_helyez();
                        }
                    } else {
                        hos3.defense_ki_helyez();
                    }
                } else {
                    hos5.defense_ki_helyez();
                }
            } else {
                hos4.defense_ki_helyez();
            }
        }
        if (!hos2.dead ) {
            if (hos2.defense_helyez) {

            }
        }
        if (!hos3.dead) {
            if (hos3.defense_helyez) {

            }
        }
        if (!hos4.dead) {
            if (hos4.defense_helyez) {

            }
        }
        if (!hos5.dead) {
            if (hos5.defense_helyez) {

            }
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
            ablakRef.KepBekeres.bekerve = false;
        }*/
        if (ablakRef.KepBekeres.bekerve) {
            hos1 = new Hero(this, h, w, "Eggyik", ablakRef.KepBekeres.hos1_hp, ablakRef.KepBekeres.hos1_mana, ablakRef.KepBekeres.hos1_attack, ablakRef.KepBekeres.hos1_defense, 0);
            hos2 = new Hero(this, h, w, "Masik", ablakRef.KepBekeres.hos2_hp, ablakRef.KepBekeres.hos2_mana, ablakRef.KepBekeres.hos2_attack, ablakRef.KepBekeres.hos2_defense, 1);
            if (ablakRef.kepernyotoltes.jat_3) {
                hos3 = new Hero(this, h, w, "Megegy", ablakRef.KepBekeres.hos3_hp, ablakRef.KepBekeres.hos3_mana, ablakRef.KepBekeres.hos3_attack, ablakRef.KepBekeres.hos3_defense, 2);
                if (ablakRef.kepernyotoltes.jat_4) {
                    hos4 = new Hero(this, h, w, ablakRef.KepBekeres.hos4, ablakRef.KepBekeres.hos4_hp, ablakRef.KepBekeres.hos4_mana, ablakRef.KepBekeres.hos4_attack, ablakRef.KepBekeres.hos4_defense, 3);
                    if (ablakRef.kepernyotoltes.jat_5) {
                        hos5 = new Hero(this, h, w, ablakRef.KepBekeres.hos5, ablakRef.KepBekeres.hos5_hp, ablakRef.KepBekeres.hos5_mana, ablakRef.KepBekeres.hos5_attack, ablakRef.KepBekeres.hos5_defense, 4);
                    }
                }
            }
            ablakRef.KepBekeres.bekerve = false;
        }
        aHatter.rajzol(batch);
        hos1.render(batch);
        hos2.render(batch);
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

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

    public KepJatekter(GdxAblak ablak) {
        super(ablak);
    }

    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        random = new Random();
        r = new Random();
/*
        hos1 = new Hero(this, h, w, "jani", 500, 10, 20, 10, 0);
        hos2 = new Hero(this, h, w, "Peti", 500, 10, 20, 10, 1);
  */
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
        if (!jatek_vege) {
            if (tamadhatnak) {
                if (!tamadot) {
                    hos1.kiteszi_big();
                    hos2.kiteszi_big();
                    hos3.kiteszi_big();
                    hos4.kiteszi_big();
                    hos5.kiteszi_big();
                }
                if (ablakRef.kepernyotoltes.jat_2) {
                    if (tamadot) {
                        hos_tamad = hos_tamad_2(hos1);
                        System.out.println(hos_tamad);
                    } else {
                        if (tamadot_1) {
                            tamadot_1 = false;
                            System.out.println(hos_tamad_prev);
                            hos_tamad_prev = hos_tamad_2(hos_tamad);
                        } else {
                            tamadot_1 = true;
                            System.out.println(hos_tamad);
                            hos_tamad = hos_tamad_2(hos_tamad_prev);
                        }
                    }

                }
                if (ablakRef.kepernyotoltes.jat_3) {
                    if (tamadot) {
                        hos_tamad = hos_tamad_3(hos1);
                    } else {
                        if (tamadot_1) {
                            tamadot_1 = false;
                            hos_tamad_prev = hos_tamad_3(hos_tamad);
                        } else {
                            tamadot_1 = true;
                            hos_tamad = hos_tamad_3(hos_tamad_prev);
                        }
                    }

                }
                if (ablakRef.kepernyotoltes.jat_4) {
                    if (tamadot) {
                        hos_tamad = hos_tamad_4(hos1);
                    } else {
                        if (tamadot_1) {
                            tamadot_1 = false;
                            hos_tamad_prev = hos_tamad_4(hos_tamad);
                        } else {
                            tamadot_1 = true;
                            hos_tamad = hos_tamad_4(hos_tamad_prev);

                        }
                    }

                }
                if (ablakRef.kepernyotoltes.jat_5) {

                    if (tamadot) {
                        hos_tamad = hos_tamad_5(hos1);
                    } else {

                        if (tamadot_1) {
                            tamadot_1 = false;
                            hos_tamad_prev = hos_tamad_5(hos_tamad);
                        } else {
                            tamadot_1 = true;
                            hos_tamad = hos_tamad_5(hos_tamad_prev);
                        }
                    }
                }
            }
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

    public Hero hos_tamad_2(Hero tamado) {
        // System.out.println(tamado);
        tamado.attack_ki_helyez();
        double tamads_ertek = 0;
        boolean tamad = false;
        tamadot = false;
        if (tamado != hos1) {
            tamads_ertek = tamado.attack(hos1);
            hos1.defense_ki_helyez();
            return hos1;
        }
        if (tamado != hos2) {
            tamads_ertek = tamado.attack(hos2);
            hos2.defense_ki_helyez();
            return hos2;
        }
        if (tamads_ertek == 0) {
            tamado.nyert = true;
        }

        return tamado;
    }

    public Hero hos_tamad_3(Hero tamado) {
        return tamado;
    }

    public Hero hos_tamad_4(Hero tamado) {
        return tamado;
    }

    public Hero hos_tamad_5(Hero tamado) {
        tamado.attack_ki_helyez();
        double tamads_ertek = 0;
     /*
        if (rnd.nextBoolean()) {
            System.out.println("1. lehetőség");
            if (tamado != hos3) {
                tamads_ertek = tamado.attack(hos3);
            }
            if (tamads_ertek == 0) {
                if (tamado != hos4) {
                    tamads_ertek = tamado.attack(hos4);
                }
                if (tamads_ertek == 0) {
                    if (tamado != hos1) {
                        tamads_ertek = tamado.attack(hos1);
                    }
                    if (tamads_ertek == 0) {
                        if (tamado != hos2) {
                            tamads_ertek = tamado.attack(hos2);
                        }
                        if (tamads_ertek == 0) {
                            if (tamado != hos5) {
                                tamads_ertek = tamado.attack(hos5);
                            }
                            if (tamads_ertek == 0) {
                                tamado.nyert = true;
                            } else {
                                hos5.defense_ki_helyez();
                            }
                        } else {
                            hos2.defense_ki_helyez();
                        }
                    } else {
                        hos1.defense_ki_helyez();
                    }
                } else {
                    hos4.defense_ki_helyez();
                }
            } else {
                hos3.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            System.out.println("2. lehetőség");
            if (tamado != hos5) {
                tamads_ertek = tamado.attack(hos5);
            }
            if (tamads_ertek == 0) {
                if (tamado != hos4) {
                    tamads_ertek = tamado.attack(hos4);
                }
                if (tamads_ertek == 0) {
                    if (tamado != hos1) {
                        tamads_ertek = tamado.attack(hos1);
                    }
                    if (tamads_ertek == 0) {
                        if (tamado != hos2) {
                            tamads_ertek = tamado.attack(hos2);
                        }
                        if (tamads_ertek == 0) {
                            if (tamado != hos3) {
                                tamads_ertek = tamado.attack(hos3);
                            }
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
                    hos1.defense_ki_helyez();
                }
            } else {
                hos5.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            System.out.println("3. lehetőség");
            if (tamado != hos4) {
                tamads_ertek = tamado.attack(hos4);
            }
            if (tamads_ertek == 0) {
                if (tamado != hos5) {
                    tamads_ertek = tamado.attack(hos5);
                }
                if (tamads_ertek == 0) {
                    if (tamado != hos1) {
                        tamads_ertek = tamado.attack(hos1);
                    }
                    if (tamads_ertek == 0) {
                        if (tamado != hos3) {
                            tamads_ertek = tamado.attack(hos3);
                        }
                        if (tamads_ertek == 0) {
                            if (tamado != hos1) {
                            }
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
                    hos1.defense_ki_helyez();
                }
            } else {
                hos4.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            System.out.println("4. lehetőség");
            if (tamado != hos2) {
                tamads_ertek = tamado.attack(hos2);
            }
            if (tamads_ertek == 0) {
                if (tamado != hos5) {
                    tamads_ertek = tamado.attack(hos5);
                }
                if (tamads_ertek == 0) {
                    if (tamado != hos1) {
                        tamads_ertek = tamado.attack(hos1);
                    }
                    if (tamads_ertek == 0) {
                        if (tamado != hos4) {
                            tamads_ertek = tamado.attack(hos4);
                        }
                        if (tamads_ertek == 0) {
                            if (tamado != hos3) {
                            }
                            tamads_ertek = tamado.attack(hos3);
                            if (tamads_ertek == 0) {
                                tamado.nyert = true;
                            } else {
                                hos3.defense_ki_helyez();
                            }
                        } else {
                            hos4.defense_ki_helyez();
                        }
                    } else {
                        hos1.defense_ki_helyez();
                    }
                } else {
                    hos5.defense_ki_helyez();
                }
            } else {
                hos2.defense_ki_helyez();
            }
        } else if (rnd.nextBoolean()) {
            System.out.println("5. lehetőség");
            if (tamado != hos1) {
                tamads_ertek = tamado.attack(hos1);
            }
            if (tamads_ertek == 0) {
                if (tamado != hos5) {
                    tamads_ertek = tamado.attack(hos5);
                }
                if (tamads_ertek == 0) {
                    if (tamado != hos4) {
                        tamads_ertek = tamado.attack(hos4);
                    }
                    if (tamads_ertek == 0) {
                        if (tamado != hos3) {
                            tamads_ertek = tamado.attack(hos3);
                        }
                        if (tamads_ertek == 0) {
                            if (tamado != hos2) {
                            }
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
                        hos4.defense_ki_helyez();
                    }
                } else {
                    hos5.defense_ki_helyez();
                }
            } else {
                hos1.defense_ki_helyez();
            }
        }*/

        boolean hos1_ved, hos2_ved, hos3_ved, hos4_ved, hos5_ved;
        int random_szam;
        do {
            hos1_ved = false;
            hos2_ved = false;
            hos3_ved = false;
            hos4_ved = false;
            hos5_ved = false;
            random_szam = r.nextInt(60);

            if (r.nextBoolean()) {
                if (tamado != hos1) {
                    System.out.println(hos1.nev);
                    tamads_ertek = tamado.attack(hos1);
                    hos1_ved = true;
                }
            } else if (r.nextBoolean()) {
                if (tamado != hos2) {
                    System.out.println(hos2.nev);
                    tamads_ertek = tamado.attack(hos2);
                    hos2_ved = true;
                }
            } else if (r.nextBoolean()) {
                if (tamado != hos3) {
                    System.out.println(hos3.nev);
                    tamads_ertek = tamado.attack(hos5);
                    hos5_ved = true;
                }

            } else if (r.nextBoolean()) {
                if (tamado != hos4) {
                    System.out.println(hos4.nev);
                    tamads_ertek = tamado.attack(hos4);
                    hos4_ved = true;
                }
            } else if (r.nextBoolean()) {
                if (tamado != hos5) {
                    System.out.println(hos5.nev);
                    tamads_ertek = tamado.attack(hos3);
                    hos3_ved = true;
                }
            }

        } while (tamads_ertek == 0);
        System.out.println(random_szam);
        int i=0;
        if (hos1.dead){
            i++;
        }
        if (hos2.dead){
            i++;
        }
        if (hos3.dead){
            i++;
        }
        if (hos4.dead){
            i++;
        }
        if (i==3){

        }
        tamadot = false;
        if (hos1_ved) {
            hos1.defense_ki_helyez();
            return hos1;
        }

        if (hos2_ved) {
            hos2.defense_ki_helyez();
            return hos2;
        }

        if (hos3_ved) {
            hos3.defense_ki_helyez();
            return hos3;
        }

        if (hos4_ved) {
            hos4.defense_ki_helyez();
            return hos4;
        }

        if (hos5_ved) {
            hos5.defense_ki_helyez();
            return hos5;
        }



        System.out.println("hiba a mátrixban");
        return tamado;
    }

    public boolean getRandomBoolean() {
        return random.nextBoolean();
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

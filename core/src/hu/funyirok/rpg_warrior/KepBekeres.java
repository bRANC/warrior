package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }

    public float betumeret = h / 40, szovegmeret, minimeret;
    public String nev = "  ", hp = "  ", mana = "  ", attack = "  ", defense = "  ";
    public int bevitel_szam = 0;
    public Szoveg nev_be, hp_be, mana_be, attack_be, defense_be, hiba_ki,playerszam_ki;
    public alakzat aVissza, aNext, aHatter, aMini_1, aMini_2, aMini_3, aMini_4, aMini_5;
    public String hos1, hos2, hos3, hos4, hos5;
    public double hos1_hp, hos2_hp, hos3_hp, hos4_hp, hos5_hp;
    public double hos1_mana, hos2_mana, hos3_mana, hos4_mana, hos5_mana;
    public double hos1_attack, hos2_attack, hos3_attack, hos4_attack, hos5_attack;
    public double hos1_defense, hos2_defense, hos3_defense, hos4_defense, hos5_defense;
    public int jatekosok = 0, hos1_kk = 0, hos2_kk = 1, hos3_kk = 2, hos4_kk = 3, hos5_kk = 4;
    public boolean bekerve;
    public String Hibauzenet = " ";
    public boolean hiba = false;

    @Override
    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        minimeret = betumeret * 8;
        aNext = new alakzat(this, "Next.png", 1000, false);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aHatter = new alakzat(this, "frame.png", 10, false);
        aHatter.atmeretez(w, h);
        aVissza = new alakzat(this, "vissza.png", 50, false);

        aMini_1 = new alakzat(this, "card_mini_1.png", 50, false);
        aMini_2 = new alakzat(this, "card_mini_2.png", 50, false);
        aMini_3 = new alakzat(this, "card_mini_3.png", 50, false);
        aMini_4 = new alakzat(this, "card_mini_4.png", 50, false);
        aMini_5 = new alakzat(this, "card_mini_5.png", 50, false);


        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
        nev = " ";
        hp = " ";
        mana = " ";
        attack = " ";
        defense = " ";



        nev_be = new Szoveg(this);
        nev_be.ini_render_balra(0, 0, "Nev:" + nev, szovegmeret);

        hp_be = new Szoveg(this);
        hp_be.ini_render_balra(0, 0, "HP:" + hp + "(10-500)", szovegmeret);

        mana_be = new Szoveg(this);
        mana_be.ini_render_balra(0, 0, "Mana:" + mana + " (0-20)", szovegmeret);

        attack_be = new Szoveg(this);
        attack_be.ini_render_balra(0, 0, "Attack:" + attack + " (1-10)", szovegmeret);

        defense_be = new Szoveg(this);
        defense_be.ini_render_balra(0, 0, "Defense:" + defense + "  (1-10)", szovegmeret);

        hiba_ki = new Szoveg(this);
        hiba_ki.ini_render_balra(0, 0, "Error:" + Hibauzenet, szovegmeret);

        playerszam_ki = new Szoveg(this);
        playerszam_ki.ini_render_balra(0, 0, "Player " + jatekosok + 1, szovegmeret);
        playerszam_ki.szoveg_valtoztat("Player " + (jatekosok + 1));


        aMini_1.atmeretez(minimeret, minimeret);
        aMini_2.atmeretez(minimeret, minimeret);
        aMini_3.atmeretez(minimeret, minimeret);
        aMini_4.atmeretez(minimeret, minimeret);
        aMini_5.atmeretez(minimeret, minimeret);

        aMini_1.atHelyez(betumeret * 8, h / 2);
        aMini_2.atHelyez(aMini_1.getX() + aMini_1.getSzelesseg(), aMini_1.getY());
        aMini_3.atHelyez(aMini_2.getX() + aMini_2.getSzelesseg(), aMini_1.getY());
        aMini_4.atHelyez(aMini_3.getX() + aMini_3.getSzelesseg(), aMini_1.getY());
        aMini_5.atHelyez(aMini_4.getX() + aMini_4.getSzelesseg(), aMini_1.getY());

        nev_be.hely_valtoztat(aMini_3.getX(), h / 2 + betumeret * 12);
        hp_be.hely_valtoztat(aMini_1.getX(), h / 2 + betumeret * 8);
        mana_be.hely_valtoztat(aMini_1.getX(), h / 2);
        attack_be.hely_valtoztat(aMini_5.getX() + aMini_5.getSzelesseg() - attack_be.hatter.getSzelesseg(), h / 2 + betumeret * 8);
        defense_be.hely_valtoztat(aMini_5.getX() + aMini_5.getSzelesseg() - defense_be.hatter.getSzelesseg(), h / 2);
        playerszam_ki.hely_valtoztat(aMini_3.getX(), nev_be.hatter.getX()+nev_be.hatter.getMagassag()*2);

    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        minimeret = betumeret * 8;


        aHatter.atmeretez(w, h);
        aNext.atmeretez(w/15,h/10);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);


        aMini_1.atmeretez(minimeret, minimeret);
        aMini_2.atmeretez(minimeret, minimeret);
        aMini_3.atmeretez(minimeret, minimeret);
        aMini_4.atmeretez(minimeret, minimeret);
        aMini_5.atmeretez(minimeret, minimeret);

        aMini_1.atHelyez(w / 2 - (aMini_1.getSzelesseg() * 5) / 2, h / 2 - aMini_1.getMagassag() - 5);
        aMini_2.atHelyez(aMini_1.getX() + aMini_1.getSzelesseg(), aMini_1.getY());
        aMini_3.atHelyez(aMini_2.getX() + aMini_2.getSzelesseg(), aMini_1.getY());
        aMini_4.atHelyez(aMini_3.getX() + aMini_3.getSzelesseg(), aMini_1.getY());
        aMini_5.atHelyez(aMini_4.getX() + aMini_4.getSzelesseg(), aMini_1.getY());

        nev_be.hely_valtoztat(w / 2 - nev_be.hatter.getSzelesseg() / 2, h / 2 + betumeret * 12);
        hp_be.hely_valtoztat(aMini_1.getX(), h / 2 + betumeret * 8);
        mana_be.hely_valtoztat(aMini_1.getX(), h / 2);
        attack_be.hely_valtoztat(aMini_5.getX() + aMini_5.getSzelesseg() - attack_be.hatter.getSzelesseg(), h / 2 + betumeret * 8);
        defense_be.hely_valtoztat(aMini_5.getX() + aMini_5.getSzelesseg() - defense_be.hatter.getSzelesseg(), h / 2);
        playerszam_ki.hely_valtoztat(w/2-playerszam_ki.hatter.getSzelesseg()/2, h / 2 + betumeret * 16);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        jatekmenet_atmeretez();
        aHatter.rajzol(batch);
        if (!hiba) {
            nev_be.render_balra(batch);
            hp_be.render_balra(batch);
            mana_be.render_balra(batch);
            attack_be.render_balra(batch);
            defense_be.render_balra(batch);
            playerszam_ki.render_balra(batch);

            aMini_1.rajzol(batch);
            aMini_2.rajzol(batch);
            aMini_3.rajzol(batch);
            aMini_4.rajzol(batch);
            aMini_5.rajzol(batch);
        } else {
            hiba_ki.render_balra(batch);
        }



        aVissza.rajzol(batch);
        aNext.rajzol(batch);
    }

    public void next_tap_mentes() {
        if (!hiba) {
            playerszam_ki.szoveg_valtoztat("Player " + (jatekosok + 1));
            bevitel_szam = 0;
            if (ablakRef.kepernyotoltes.jat_2) {
                System.out.println(jatekosok);
                if (jatekosok == 0) {
                    hos1 = nev;
                    hos1_hp = Double.parseDouble(hp);
                    hos1_mana = Double.parseDouble(mana);
                    hos1_attack = Double.parseDouble(attack);
                    hos1_defense = Double.parseDouble(defense);
                } else if (jatekosok == 1) {
                    hos2 = nev;
                    hos2_hp = Double.parseDouble(hp);
                    hos2_mana = Double.parseDouble(mana);
                    hos2_attack = Double.parseDouble(attack);
                    hos2_defense = Double.parseDouble(defense);

                    if (!ablakRef.kepernyotoltes.jat_3) {
                        bekerve = true;
                        ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                    }
                }
            }
            if (ablakRef.kepernyotoltes.jat_3) {
                if (jatekosok == 2) {
                    hos3 = nev;
                    hos3_hp = Double.parseDouble(hp);
                    hos3_mana = Double.parseDouble(mana);
                    hos3_attack = Double.parseDouble(attack);
                    hos3_defense = Double.parseDouble(defense);

                    if (!ablakRef.kepernyotoltes.jat_4) {
                        bekerve = true;
                        ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                    }
                }

            }
            if (ablakRef.kepernyotoltes.jat_4) {
                if (jatekosok == 3) {
                    hos4 = nev;
                    hos4_hp = Double.parseDouble(hp);
                    hos4_mana = Double.parseDouble(mana);
                    hos4_attack = Double.parseDouble(attack);
                    hos4_defense = Double.parseDouble(defense);

                    if (!ablakRef.kepernyotoltes.jat_5) {
                        bekerve = true;
                        ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                    }
                }

            }
            if (ablakRef.kepernyotoltes.jat_5) {
                if (jatekosok == 4) {
                    hos5 = nev;
                    hos5_hp = Double.parseDouble(hp);
                    hos5_mana = Double.parseDouble(mana);
                    hos5_attack = Double.parseDouble(attack);
                    hos5_defense = Double.parseDouble(defense);
                    bekerve = true;
                    ablakRef.kepernyo_csere(ablakRef.kepernyoJatekter);
                }
            }
            jatekosok++;
        } else {
            hiba_ki.szoveg_valtoztat(Hibauzenet);
            hiba_ki.hely_valtoztat(0,h/2-hiba_ki.hatter.getMagassag()/2);
            Hibauzenet = " ";
        }
        /*nev = " ";  // csak hogy ne keljen mindig be írni az harcosok értékét
        hp = " 500";
        mana = " 20";
        attack = " 10";
        defense = " 10";
        */
        nev = " ";
        hp = " ";
        mana = " ";
        attack = " ";
        defense = " ";

        nev_be.szoveg_valtoztat("nev:" + nev);
        hp_be.szoveg_valtoztat("HP:" + hp + " (10-500)");
        mana_be.szoveg_valtoztat("Mana:" + mana + " (0-20)");
        attack_be.szoveg_valtoztat("Attack:" + attack + " (1-10)");
        defense_be.szoveg_valtoztat("Defense:" + defense + " (1-10)");
    }


    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (aNext.benneVaneXY(x, y)) {
            if (hiba) {
                hiba = false;
            } else {
                validalas();
            }
        }
        if (aVissza.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
            ablakRef.kepernyotoltes.valasztva = false;
            ablakRef.kepernyotoltes.jat_2 = false;
            ablakRef.kepernyotoltes.jat_3 = false;
            ablakRef.kepernyotoltes.jat_4 = false;
            ablakRef.kepernyotoltes.jat_5 = false;
        }

        if (aMini_1.benneVaneXY(x, y)) {

            switch (jatekosok) {
                case 0:
                    hos1_kk = 0;
                    break;
                case 1:
                    hos2_kk = 0;
                    break;
                case 2:
                    hos3_kk = 0;
                    break;
                case 3:
                    hos4_kk = 0;
                    break;
                case 4:
                    hos5_kk = 0;
                    break;

            }

        }
        if (aMini_2.benneVaneXY(x, y)) {

            switch (jatekosok) {
                case 0:
                    hos1_kk = 1;
                    break;
                case 1:
                    hos2_kk = 1;
                    break;
                case 2:
                    hos3_kk = 1;
                    break;
                case 3:
                    hos4_kk = 1;
                    break;
                case 4:
                    hos5_kk = 1;
                    break;
            }

        }
        if (aMini_3.benneVaneXY(x, y)) {

            switch (jatekosok) {
                case 0:
                    hos1_kk = 2;
                    break;
                case 1:
                    hos2_kk = 2;
                    break;
                case 2:
                    hos3_kk = 2;
                    break;
                case 3:
                    hos4_kk = 2;
                    break;
                case 4:
                    hos5_kk = 2;
                    break;
            }

        }
        if (aMini_4.benneVaneXY(x, y)) {

            switch (jatekosok) {
                case 0:
                    hos1_kk = 3;
                    break;
                case 1:
                    hos2_kk = 3
                    ;
                    break;
                case 2:
                    hos3_kk = 3;
                    break;
                case 3:
                    hos4_kk = 3;
                    break;
                case 4:
                    hos5_kk = 3;
                    break;
            }

        }
        if (aMini_5.benneVaneXY(x, y)) {


            switch (jatekosok) {
                case 0:
                    hos1_kk = 4;
                    break;
                case 1:
                    hos2_kk = 4;
                    break;
                case 2:
                    hos3_kk = 4;
                    break;
                case 3:
                    hos4_kk = 4;
                    break;
                case 4:
                    hos5_kk = 4;
                    break;
            }

        }
        if (nev_be.erintve(x, y)) {
            bevitel_szam = 0;
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        if (hp_be.erintve(x, y)) {
            bevitel_szam = 1;
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        if (mana_be.erintve(x, y)) {
            bevitel_szam = 2;
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        if (attack_be.erintve(x, y)) {
            bevitel_szam = 3;
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        if (defense_be.erintve(x, y)) {
            bevitel_szam = 4;
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("karakter: " + (int) character);
        if (!hiba) {
            if (/*character == 8 ||*/ character == 9 || character == 13) {//androidon a 8-as az enter gépen viszont a backspace
                bevitel_szam++;
                System.out.println("bekérés: " + bevitel_szam);
            }
            if (bevitel_szam == 0) {
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
            }
            if (bevitel_szam == 1) {
                if (hp.length() > 0) {
                    if (character == 8) {
                        if (hp.length() > 1) {
                            hp = hp.substring(0, hp.length() - 1);
                        }
                    } else {
                        if (character > 47 && character < 58) {
                            hp += character;
                        }
                    }
                }
                hp_be.szoveg_valtoztat("HP:" + hp + " (10-500)");
            }
            if (bevitel_szam == 2) {
                if (mana.length() > 0) {
                    if (character == 8) {
                        if (mana.length() > 1) {
                            mana = mana.substring(0, mana.length() - 1);
                        }
                    } else {
                        if (character > 47 && character < 58) {
                            mana += character;
                        }
                    }
                }
                mana_be.szoveg_valtoztat("Mana:" + mana + " (0-20)");
            }

            if (bevitel_szam == 3) {
                if (attack.length() > 0) {
                    if (character == 8) {
                        if (attack.length() > 1) {
                            attack = attack.substring(0, attack.length() - 1);
                        }
                    } else {
                        if (character > 47 && character < 58) {
                            attack += character;
                        }
                    }
                }
                attack_be.szoveg_valtoztat("Attack:" + attack + " (1-10)");
            }
            if (bevitel_szam == 4) {
                if (defense.length() > 0) {
                    if (character == 8) {
                        if (defense.length() > 1) {
                            defense = defense.substring(0, defense.length() - 1);
                        }
                    } else {
                        if (character > 47 && character < 58) {
                            defense += character;
                        }
                    }
                }
                defense_be.szoveg_valtoztat("Defense:" + defense + " (1-10)");
            }
            if (bevitel_szam >= 5) {
                validalas();
            }
        }
        return false;
    }

    public void validalas() {
        try {
            if (Integer.parseInt(hp.trim()) >= 10 && Integer.parseInt(hp.trim()) <= 500) {
            } else {
                Hibauzenet += "HP, ";
                hiba = true;
            }
            if (Integer.parseInt(mana.trim()) >= 0 && Integer.parseInt(mana.trim()) <= 20) {
            } else {
                Hibauzenet += "Mana, ";
                hiba = true;
            }
            if (Integer.parseInt(attack.trim()) >= 1 && Integer.parseInt(attack.trim()) <= 10) {
            } else {
                Hibauzenet += "Attack, ";
                hiba = true;
            }
            if (Integer.parseInt(defense.trim()) >= 1 && Integer.parseInt(defense.trim()) <= 10) {
            } else {
                Hibauzenet += "Defense, ";
                hiba = true;
            }
        } catch (Exception e) {
            Hibauzenet += "Empty ";
            hiba = true;
        }
        if (!hiba) {
            Hibauzenet = " ";
        } else {
            Hibauzenet += "is out of expection, Please retry.";
            hiba_ki.szoveg_valtoztat(Hibauzenet);
        }

        next_tap_mentes();
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}

package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KepBekeres extends kepernyo_os_obj {
    public KepBekeres(GdxAblak ablak) {
        super(ablak);
    }

    public float betumeret = h / 40, szovegmeret;
    public String nev = "  ", hp = "  ", mana = "  ", attack = "  ", defense = "  ";
    public int bevitel_szam = 0;
    public Szoveg nev_be, hp_be, mana_be, attack_be, defense_be, hiba_ki;
    public alakzat aVissza, aNext, aHatter;
    public String hos1, hos2, hos3, hos4, hos5;
    public double hos1_hp, hos2_hp, hos3_hp, hos4_hp, hos5_hp;
    public double hos1_mana, hos2_mana, hos3_mana, hos4_mana, hos5_mana;
    public double hos1_attack, hos2_attack, hos3_attack, hos4_attack, hos5_attack;
    public double hos1_defense, hos2_defense, hos3_defense, hos4_defense, hos5_defense;
    public int jatekosok = 0;
    public boolean bekerve;
    public String Hibauzenet = " ";
    public boolean hiba = false;

    @Override
    public void jatekmenet_letrehoz() {
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        aNext = new alakzat(this, "Next.png", 100, false);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aHatter = new alakzat(this, "frame.png", 10, false);
        aHatter.atmeretez(w, h);
        aVissza = new alakzat(this, "vissza.png", 50, false);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
        nev = " ";
        hp = " ";
        mana = " ";
        attack = " ";
        defense = " ";

        nev_be = new Szoveg(this);
        nev_be.ini_render_balra(w / 2 - betumeret * 8, h / 2 + betumeret * 12, "Nev:" + nev, szovegmeret);

        hp_be = new Szoveg(this);
        hp_be.ini_render_balra(nev_be.x, h / 2 + betumeret *8 , "HP:" + hp + "(10-500)", szovegmeret);

        mana_be = new Szoveg(this);
        mana_be.ini_render_balra(nev_be.x, h / 2, "Mana:" + mana + " (0-20)", szovegmeret);

        attack_be = new Szoveg(this);
        attack_be.ini_render_balra(nev_be.x, h / 2 - betumeret * 4, "Attack:" + attack + " (1-10)", szovegmeret);

        defense_be = new Szoveg(this);
        defense_be.ini_render_balra(nev_be.x, h / 2 - betumeret * 8, "Defense:" + defense + "  (1-10)", szovegmeret);

        hiba_ki = new Szoveg(this);
        hiba_ki.ini_render_balra(betumeret / 2, h / 2, "Error:" + Hibauzenet, szovegmeret);
    }

    @Override
    public void jatekmenet_atmeretez() {
        super.jatekmenet_atmeretez();
        nev_be.hely_valtoztat(w / 2 - betumeret * 4, h / 2 + betumeret * 4);
        hp_be.hely_valtoztat(nev_be.x, h / 2);
        betumeret = h / 40;
        szovegmeret = betumeret * 2 - 5;
        aHatter.atmeretez(w, h);
        aNext.atHelyez(w - aNext.getSzelesseg() - betumeret, betumeret);
        aVissza.atHelyez(w - aVissza.getW() - betumeret, h - aVissza.getH() - betumeret);
    }

    @Override
    public void jatekmenet_szal() {

    }

    @Override
    public void jatekmenet_render(SpriteBatch batch) {
        aHatter.rajzol(batch);
        if (!hiba) {
            nev_be.render_balra(batch);
            hp_be.render_balra(batch);
            mana_be.render_balra(batch);
            attack_be.render_balra(batch);
            defense_be.render_balra(batch);
        } else {
            hiba_ki.render_balra(batch);
        }
        aVissza.rajzol(batch);
        aNext.rajzol(batch);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (aNext.benneVaneXY(x, y)) {
            if (!hiba) {
                bevitel_szam = 0;
                if (ablakRef.kepernyotoltes.jat_2) {
                    System.out.println(jatekosok);
                    if (jatekosok == 0) {
                        hos1 = nev;
                        hos1_hp = Double.parseDouble(hp);
                        hos1_mana = Double.parseDouble(mana);
                        hos1_attack = Double.parseDouble(attack);
                        hos1_defense = Double.parseDouble(defense);

                        attack = " ";
                        defense = " ";
                        nev = " ";
                        hp = " ";
                        mana = " ";
                    } else if (jatekosok == 1) {
                        hos2 = nev;
                        hos2_hp = Double.parseDouble(hp);
                        hos2_mana = Double.parseDouble(mana);
                        hos2_attack = Double.parseDouble(attack);
                        hos2_defense = Double.parseDouble(defense);

                        attack = " ";
                        defense = " ";
                        nev = " ";
                        hp = " ";
                        mana = " ";
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

                        attack = " ";
                        defense = " ";
                        nev = " ";
                        hp = " ";
                        mana = " ";
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

                        attack = " ";
                        defense = " ";
                        nev = " ";
                        hp = " ";
                        mana = " ";
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

                        attack = " ";
                        defense = " ";
                        nev = " ";
                        hp = " ";
                        mana = " ";
                    }
                }
                jatekosok++;
            } else {
                Hibauzenet = " ";
                hiba = false;
                hiba_ki.szoveg_valtoztat(Hibauzenet);
                bevitel_szam = 0;
                nev = " ";
                hp = " ";
                mana = " ";
                attack = " ";
                defense = " ";
            }
            nev_be.szoveg_valtoztat("nev:" + nev);
            hp_be.szoveg_valtoztat("HP:" + hp + " (10-500)");
            mana_be.szoveg_valtoztat("Mana:" + mana + " (0-20)");
            attack_be.szoveg_valtoztat("Attack:" + attack + " (1-10)");
            defense_be.szoveg_valtoztat("Defense:" + defense + " (1-10)");
        }
        if (aVissza.benneVaneXY(x, y)) {
            ablakRef.kepernyo_csere(ablakRef.kepernyotoltes);
            ablakRef.kepernyotoltes.valasztva = false;
            ablakRef.kepernyotoltes.jat_2 = false;
            ablakRef.kepernyotoltes.jat_3 = false;
            ablakRef.kepernyotoltes.jat_4 = false;
            ablakRef.kepernyotoltes.jat_5 = false;
        }
        if (nev_be.erintve(x, y)) {
            bevitel_szam = 0;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {
            //Gdx.input.setOnscreenKeyboardVisible(false);
        }
        if (hp_be.erintve(x, y)) {
            bevitel_szam = 1;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {
            //Gdx.input.setOnscreenKeyboardVisible(false);
        }
        if (mana_be.erintve(x, y)) {
            bevitel_szam = 2;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {

        }
        if (attack_be.erintve(x, y)) {
            bevitel_szam = 3;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {

        }
        if (defense_be.erintve(x, y)) {
            bevitel_szam = 4;
            Gdx.input.setOnscreenKeyboardVisible(true);
        } else {
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("karakter: " + (int) character);
        if (!hiba) {
            System.out.println((int) character);
            if (character == 8 || character == 9 || character == 13) {
                bevitel_szam++;
                System.out.println(bevitel_szam);
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
            if (bevitel_szam == 5) {
                validalas();
            }
        }
        return false;
    }

    public void validalas() {
        try {
            if (Integer.parseInt(hp.trim()) >= 10 && Integer.parseInt(hp.trim()) <= 500) {
                System.out.println(hp);
                System.out.println("jó lesz a hp");
            } else {
                Hibauzenet += "HP, ";
                hiba = true;
            }
            if (Integer.parseInt(mana.trim()) >= 0 && Integer.parseInt(mana.trim()) <= 20) {
                System.out.println(mana);
                System.out.println("jó lesz a mana");
            } else {
                Hibauzenet += "Mana, ";
                hiba = true;
            }
            if (Integer.parseInt(attack.trim()) >= 1 && Integer.parseInt(attack.trim()) <= 10) {
                System.out.println(attack);
                System.out.println("jó lesz az attack");
            } else {
                Hibauzenet += "Attack, ";
                hiba = true;
            }
            if (Integer.parseInt(defense.trim()) >= 1 && Integer.parseInt(defense.trim()) <= 10) {
                System.out.println(defense);
                System.out.println("jó lesz a defense");
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
            Hibauzenet += "is out of expection";
            hiba_ki.szoveg_valtoztat(Hibauzenet);
            bevitel_szam = 0;
        }
    }

    @Override
    public void jatekmenet_megszuntet() {

    }

}

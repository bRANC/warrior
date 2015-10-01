package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Szoveg {
    public kepernyo_os_obj jatek;
    public alakzat betuk[] = new alakzat[39];//abcdefghijklmnopqrstxyz0123456789.,:
    public alakzat rajzolni[] = new alakzat[100];
    public int betu_index_hivatkozas[];
    public int betuk_szama = 0;
    public alakzat hatter;


    public SpriteBatch batch;
    public float x;
    public float y;
    public String szoveg;
    public float meretPX;
    public boolean offset_engedelyezes = false;


    //public labrintusgenerator labirintus;

    public Szoveg(kepernyo_os_obj j) {
        jatek = j;
        hatter = new alakzat(jatek, "semmi_uj.png", 400, false);
        for (int i = 0; i < 26; i++) {
            betuk[i] = new alakzat(jatek, "Karakterek/letter" + (char) (i + 65) + ".png", 20, false);
            betuk[i].mozgo_alakzat = false;
            //System.out.println((char)(i+65));
        }
        for (int i = 26; i < 36; i++) {
            betuk[i] = new alakzat(jatek, "Karakterek/number" + (i - 26) + ".png", 20, false);
            betuk[i].mozgo_alakzat = false;
            //System.out.println((i-26));
        }
        betuk[36] = new alakzat(jatek, "Karakterek/pont.png", 20, false);
        betuk[36].mozgo_alakzat = false;
        betuk[37] = new alakzat(jatek, "Karakterek/vesszo.png", 20, false);
        betuk[37].mozgo_alakzat = false;
        betuk[38] = new alakzat(jatek, "Karakterek/kettospont.png", 20, false);
        betuk[38].mozgo_alakzat = false;

    }

    public int get_betu_index(char c) {
        int index = -1;
        if (c >= 65 && c <= 90) {
            index = c - 65;
        }
        if (c >= 48 && c <= 57) {
            index = c - 22;
        }
        if (c == 46) index = 36;
        if (c == 44) index = 37;
        if (c == 58) index = 38;
        return index;
    }

    public void ini_render_balra(float x_, float y_, String szoveg_, float meretPX_) {
        x = x_;
        y = y_;
        szoveg = szoveg_;
        meretPX = meretPX_;
    }

    public void ini_render_jobbra(float x_, float y_, String szoveg_, float meretPX_) {
        x = x_;
        y = y_;
        szoveg = szoveg_;
        meretPX = meretPX_;
    }

    public void szoveg_hely_valtoztat(float x_, float y_, String szoveg_) {
        x = x_;
        y = y_;
        szoveg = szoveg_;
    }

    public void szoveg_valtoztat(String szoveg_) {
        szoveg = szoveg_;
    }

    public void hely_valtoztat(float x_, float y_) {
        x = x_;
        y = y_;
    }

    public void render_balra(SpriteBatch batch) {

        szoveg = szoveg.toUpperCase();
        int index = -1;
        float start = x;
        betuk_szama = szoveg.length() - 1;
        for (int i = 0; i < szoveg.length(); i++) {
            index = get_betu_index(szoveg.charAt(i));
            if (index >= 0) {
                betuk[index].atmeretezY(meretPX);
                betuk[index].atHelyez(start, y);
                betuk[index].offset_engedelyezes = offset_engedelyezes;

                hatter.atHelyez(x, y);
                hatter.atmeretez(start + meretPX - x, meretPX);
                hatter.rajzol(batch);

                betuk[index].rajzol(batch);
                start = start + betuk[index].getW();
            } else {
                start = start + meretPX / 2;
            }
        }
    }

    public void render_jobbra(SpriteBatch batch, float x, float y, String szoveg, float meretPX, boolean offset_engedelyezes) {
        szoveg = szoveg.toUpperCase();
        int index = -1;
        float start = x;
        for (int i = szoveg.length() - 1; i >= 0; i--) {
            index = get_betu_index(szoveg.charAt(i));
            if (index >= 0) {
                betuk[index].atmeretezY(meretPX);
                betuk[index].atHelyez(start - betuk[index].getW(), y);
                betuk[index].offset_engedelyezes = offset_engedelyezes;

                hatter.atHelyez(x, y);
                hatter.atmeretez(betuk[index].getX() + betuk[index].getSzelesseg() - x, meretPX);
                hatter.rajzol(batch);

                betuk[index].rajzol(batch);
                start = start - betuk[index].getW();
            } else {
                start = start - meretPX / 2;
            }
        }

        hatter.atHelyez(x, y);

    }

    public boolean erintve(float x__, float y__) {
        if (hatter.benneVaneXY(x__, y__)) {
            return true;
        } else {
            return false;
        }

    }

    public void render_kozepre(SpriteBatch batch, float x, float y, String szoveg, float meretPX, boolean offset_engedelyezes) {
        szoveg = szoveg.toUpperCase();
        int index = -1;
        float start = 0;
        for (int i = 0; i < szoveg.length(); i++) {
            index = get_betu_index(szoveg.charAt(i));
            if (index >= 0) {
                betuk[index].atmeretezY(meretPX);
                betuk[index].atHelyez(start, y);
                betuk[index].rajzol(batch);
                start = start + betuk[index].getW();
            } else {
                start = start + meretPX / 2;
            }
        }


        start = x - start / 2;
        for (int i = 0; i < szoveg.length(); i++) {
            index = get_betu_index(szoveg.charAt(i));
            if (index >= 0) {
                betuk[index].atmeretezY(meretPX);
                betuk[index].atHelyez(start, y);
                betuk[index].offset_engedelyezes = offset_engedelyezes;
                betuk[index].rajzol(batch);
                start = start + betuk[index].getW();
            } else {
                start = start + meretPX / 2;
            }
        }
        hatter = new alakzat(jatek, "semmi_uj.png", 400, false);
        hatter.atHelyez(x, y);
        hatter.atmeretez(rajzolni[betuk_szama].getX() + rajzolni[betuk_szama].getSzelesseg() - rajzolni[0].getX(), meretPX);
    }


    public void dispose() {
        for (int i = 0; i < betuk.length; i++) {
            betuk[i].dispose();
        }
    }
}

package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Szoveg {
	public kepernyo_os_obj jatek;
	public alakzat betuk[] = new alakzat[39];//abcdefghijklmnopqrstxyz0123456789.,:
	public int betu_index_hivatkozas[];

	//public labrintusgenerator labirintus;

	public Szoveg(kepernyo_os_obj j) {
		jatek=j;
		for(int i=0; i<26; i++)
		{
			betuk[i] = new alakzat(jatek, "Karakterek/letter"+(char)(i+65)+".png", 20, false);
			betuk[i].mozgo_alakzat=false;
			//System.out.println((char)(i+65));
		}
		for(int i=26; i<36; i++)
		{
			betuk[i] = new alakzat(jatek, "Karakterek/number"+(i-26)+".png", 20, false);
			betuk[i].mozgo_alakzat=false;
			//System.out.println((i-26));
		}
		betuk[36] = new alakzat(jatek, "Karakterek/pont.png", 20, false);
		betuk[36].mozgo_alakzat=false;
		betuk[37] = new alakzat(jatek, "Karakterek/vesszo.png", 20, false);
		betuk[37].mozgo_alakzat=false;
		betuk[38] = new alakzat(jatek, "Karakterek/kettospont.png", 20, false);
		betuk[38].mozgo_alakzat=false;
		
	}

	public int get_betu_index(char c)
	{
		int index=-1;
		if (c>=65 && c<=90)
		{
			index=c-65;
		}
		if (c>=48 && c<=57)
		{
			index=c-22;
		}
		if (c==46) index=36;
		if (c==44) index=37;
		if (c==58) index=38;
		return index;
	}
	
	public void render_balra(SpriteBatch batch, float x, float y, String szoveg, float meretPX, boolean offset_engedelyezes) {
		szoveg=szoveg.toUpperCase();
		int index=-1;
		float start=x;
		for (int i = 0; i < szoveg.length(); i++) {
			index=get_betu_index(szoveg.charAt(i));
			if (index>=0)
			{
				betuk[index].atmeretezY(meretPX);
				betuk[index].atHelyez(start, y);
				betuk[index].offset_engedelyezes=offset_engedelyezes;
				betuk[index].rajzol(batch);
				start=start+betuk[index].getW();
			}
			else
			{
				start=start+meretPX/2;
			}
		}
	}

	public void render_jobbra(SpriteBatch batch, float x, float y, String szoveg, float meretPX, boolean offset_engedelyezes) {
		szoveg=szoveg.toUpperCase();
		int index=-1;
		float start=x;
		for (int i = szoveg.length()-1; i >= 0; i--) {
			index=get_betu_index(szoveg.charAt(i));
			if (index>=0)
			{
				betuk[index].atmeretezY(meretPX);
				betuk[index].atHelyez(start-betuk[index].getW(), y);
				betuk[index].offset_engedelyezes=offset_engedelyezes;
				betuk[index].rajzol(batch);
				start=start-betuk[index].getW();
			}
			else
			{
				start=start-meretPX/2;
			}
		}
	}	
	

	public void render_kozepre(SpriteBatch batch, float x, float y, String szoveg, float meretPX, boolean offset_engedelyezes) {
		szoveg=szoveg.toUpperCase();
		int index=-1;
		float start=0;
		for (int i = 0; i < szoveg.length(); i++) {
			index=get_betu_index(szoveg.charAt(i));			
			if (index>=0)
			{
				betuk[index].atmeretezY(meretPX);
				betuk[index].atHelyez(start, y);
//				betuk[index].rajzol(batch);				
				start=start+betuk[index].getW();
			}
			else
			{
				start=start+meretPX/2;
			}
		}
		
			
		start=x-start/2;
		for (int i = 0; i < szoveg.length(); i++) {
			index=get_betu_index(szoveg.charAt(i));
			if (index>=0)
			{
				betuk[index].atmeretezY(meretPX);
				betuk[index].atHelyez(start, y);
				betuk[index].offset_engedelyezes=offset_engedelyezes;
				betuk[index].rajzol(batch);
				start=start+betuk[index].getW();
			}
			else
			{
				start=start+meretPX/2;
			}
		}		
	}	
	
	public void dispose() {
		for (int i = 0; i < betuk.length; i++) {
			betuk[i].dispose();
		}
	}
/*
	public void felbontas() {
		String criteria = "";
		String[] strarr = felbontani.split(criteria);

		for (int i = 0; i < strarr.length; i++) {
			System.out.println(strarr[i]);
		}

	}
*/
}

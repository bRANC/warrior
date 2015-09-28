package hu.funyirok.rpg_warrior;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class coin {
	private float w, h;
	public alakzat lolhello;

	public int helyzet_x, helyzet_y;

	public Random r = new Random();
	public boolean siker = false;

	public KepJatekter jatek;
	public labrintusgenerator labirintus;

	public coin(KepJatekter j, labrintusgenerator labirintus, Texture lolhello_T, float x, float y) {

		jatek = j;
		this.labirintus = labirintus;
		w = x;
		h = y;
		lolhello = new alakzat(jatek, lolhello_T, w / 20, w / 20, false);
		spawn();
	}

	public void render(SpriteBatch batch) {
		lolhello.rajzol(batch);
	}

	public void dispose() {
		lolhello.dispose();
	}

	public void spawn() {

		do {
			helyzet_x = r.nextInt((int) (labirintus.hossz));
			helyzet_y = r.nextInt((int) (labirintus.le));
			if (labirintus.maze[helyzet_y][helyzet_x].getTexture() == jatek.aSemmi.getTexture()) {
				lolhello.atHelyez(labirintus.maze[helyzet_y][helyzet_x].getX(), labirintus.maze[helyzet_y][helyzet_x].getY());
				siker = true;
			}
		} while (!siker);

	}

}

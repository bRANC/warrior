package hu.funyirok.rpg_warrior;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class kepernyo_os_obj implements Runnable {
	public GdxAblak ablakRef; // Csak a k�perny� lecser�l�s�hez haszn�lhat�! Ha
								// p�ld�ul a men�ben v�lasztunk valamit, akkor
								// egy m�sik ablakk�prre lehet ugrani.
	public float offset_x = 0;
	public float offset_y = 0;
	protected float w, h;
	private float giro_tureshatar = (float) 1.5;
	private float giro_x = 0;
	private float giro_y = 0;
	private float giro_z = 0;

	public boolean dev = false;

	private static Random rnd = new Random();
	private boolean jatekmenet_halad = false;
	public boolean fut = true;

	/** H�tt�rsz�n */
	public float bg_r = 0;
	/** H�tt�rsz�n */
	public float bg_g = 0;
	/** H�tt�rsz�n */
	public float bg_b = 0;

	/** Kezdeti �rt�kek, p�ld�nyos�t�sok, text�r�k bet�lt�se */
	public abstract void jatekmenet_letrehoz();

	/** A j�t�kmenet folyamatosan fut� sz�la */
	public abstract void jatekmenet_szal();

	/** A k�perny� renderel�s�hez sz�ks�ges dolgok */
	public abstract void jatekmenet_render(SpriteBatch batch);

	/** A j�t�kt�r megsz�ntet�s�hez sz�ks�ges dolgok */
	public abstract void jatekmenet_megszuntet();

	/** A j�t�kt�r �tm�retez�s�hez sz�ks�ges dolgokat kell idepakolni */
	public void jatekmenet_atmeretez() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		ablakRef.update();
		
	}

	/**
	 * Akkor fut le, ha jobbra d�l a k�sz�l�k. Ha nincs giroszk�p, akkor nem fut
	 * le.
	 */
	public void jatekmenet_giroszkop_Jobbra(float mertek) {
	}

	/**
	 * Akkor fut le, ha jobbra d�l a k�sz�l�k. Ha nincs giroszk�p, akkor nem fut
	 * le.
	 */
	public void jatekmenet_giroszkop_Balra(float mertek) {
	}

	/**
	 * Akkor fut le, ha jobbra d�l a k�sz�l�k. Ha nincs giroszk�p, akkor nem fut
	 * le.
	 */
	public void jatekmenet_giroszkop_Fel(float mertek) {
	}

	/**
	 * Akkor fut le, ha jobbra d�l a k�sz�l�k. Ha nincs giroszk�p, akkor nem fut
	 * le.
	 */
	public void jatekmenet_giroszkop_Le(float mertek) {
	}

	public kepernyo_os_obj(GdxAblak ablak) {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		ablakRef = ablak;
		jatekmenet_letrehoz();
		jatekmenet_atmeretez();
	}

	public boolean getRandomBoolean() {
		return rnd.nextBoolean();
	}



	@Override
	public final void run() {
		while (fut) {
			if (jatekmenet_halad)
				jatekmenet_szal();
			try {
				Thread.sleep(200); // lelas�tja a sz�lat hogy k�nnyebben ki lehessen olvasni az �rt�keket
				//Thread.sleep(17); // 60 fps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fut = true;
	}

	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	public boolean scrolled(int amount) {

		return false;
	}

	public boolean tap(float x, float y, int count, int button) {

		return false;
	}

	public boolean longPress(float x, float y) {

		return false;
	}

	public boolean fling(float velocityX, float velocityY, int button) {

		return false;
	}

	public boolean pan(float x, float y, float deltaX, float deltaY) {

		return false;
	}

	public boolean panStop(float x, float y, int pointer, int button) {

		return false;
	}

	public boolean zoom(float initialDistance, float distance) {

		return false;
	}

	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

		return false;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {

		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	public final float getGiroX() {
		return giro_x;
	}

	public final float getGiroY() {
		return giro_y;
	}

	public final float getGiroZ() {
		return giro_z;
	}

	public final void resetGiro() {
		giro_x = 0;
		giro_y = 0;
		giro_z = 0;
	}

	public final void giroszkop(float x, float y, float z) {
		giro_x = x;
		giro_y = y;
		giro_z = z;
		switch (Gdx.input.getRotation()) {
		case 90:
			if (Math.abs(x) > giro_tureshatar) {
				if (x > 0) {
					jatekmenet_giroszkop_Le(-x);
				} else {
					jatekmenet_giroszkop_Fel(-x);
				}
			}
			if (Math.abs(y) > giro_tureshatar) {
				if (y > 0) {
					jatekmenet_giroszkop_Jobbra(y);
				} else {
					jatekmenet_giroszkop_Balra(y);
				}
			}
			break;
		case 0:
			if (Math.abs(x) > giro_tureshatar) {
				if (x > 0) {
					jatekmenet_giroszkop_Jobbra(-x);
				} else {
					jatekmenet_giroszkop_Balra(-x);
				}
			}
			if (Math.abs(y) > giro_tureshatar) {
				if (y > 0) {
					jatekmenet_giroszkop_Le(-y);
				} else {
					jatekmenet_giroszkop_Fel(-y);
				}
			}
			break;
		case 180:
			if (Math.abs(x) > giro_tureshatar) {
				if (x > 0) {
					jatekmenet_giroszkop_Jobbra(x);
				} else {
					jatekmenet_giroszkop_Balra(x);
				}
			}
			if (Math.abs(y) > giro_tureshatar) {
				if (y > 0) {
					jatekmenet_giroszkop_Fel(y);
				} else {
					jatekmenet_giroszkop_Le(y);
				}
			}
			break;
		case 270:
			if (Math.abs(x) > giro_tureshatar) {
				if (x > 0) {
					jatekmenet_giroszkop_Fel(x);
				} else {
					jatekmenet_giroszkop_Le(x);
				}
			}
			if (Math.abs(y) > giro_tureshatar) {
				if (y > 0) {
					jatekmenet_giroszkop_Balra(-y);
				} else {
					jatekmenet_giroszkop_Jobbra(-y);
				}
			}
			break;
		}
	}

	public final void giroszkop_setTurehatar(float ertek) {
		giro_tureshatar = ertek;
	}

	/** A sz�l fut, de nem megy a j�t�kmenet el�re. */
	public void szuneteltet() {
		jatekmenet_halad = false;
	}

	/** A sz�l fut, �s a j�t�kmenet is megy. */
	public void folytat() {
		jatekmenet_halad = true;
	}

	/** Le�ll�tja a sz�lat. Ett�l nem sz�nik meg, csak lev�lasztja a sz�lr�l. */
	public void leallit() {
		fut = false;
	}

	@Override
	public String toString() {
		return "kepernyo_os_objektum";
	}

	@Override
	protected void finalize() throws Throwable {
		jatekmenet_megszuntet();
		super.finalize();
	}

}

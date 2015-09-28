package hu.funyirok.rpg_warrior;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class GdxAblak implements ApplicationListener, GestureListener, InputProcessor {
	public float w, h;
	private SpriteBatch batch;
	private float X[] = new float[5], Y[] = new float[5], Z;
	public OrthographicCamera camera = new OrthographicCamera();
	private boolean giroszkop_vane = false;

	public int a = 0;

	public alakzat aCursor;

	public boolean dev = false, dev_hover = false;

	public kepernyo_os_obj kepernyoFuto;
	public KepMenu kepernyoMenu;
	public KepToltes kepernyotoltes;
	public KepJatekter kepernyoJatekter;
	public KepSugo KepernyoSugo;

	 public Thread kepernyoThread;

	public void kepernyo_csere(kepernyo_os_obj kepernyoUj) {
		
		if (kepernyoFuto != null) {
			kepernyoFuto.szuneteltet();
			kepernyoFuto.leallit();
		}
		kepernyoThread = null;
		kepernyoFuto = kepernyoUj;
		kepernyoFuto.folytat();
		kepernyoThread = new Thread(kepernyoFuto);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		kepernyoThread.start();
	}
	

	@Override
	public void create() {
		Gdx.graphics.setVSync(true);
		// Gdx.input.setCursorCatched(true); //ezzel el lehet kapni az egeret �s
		// a j�t�kban tartani+"l�thatatlann�" tenni hover-el pedig textur�t
		// tenni a hely�re.
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera.setToOrtho(false, 0, 0);
		batch = new SpriteBatch();
		camera.update();
		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(this);
		im.addProcessor(gd);
		im.addProcessor(this);
		Gdx.input.setInputProcessor(im);

		giroszkop_vane = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

		aCursor = new alakzat(kepernyoFuto, "eger.png", 40, false);
		aCursor.offset_engedelyezes = false;

		kepernyotoltes = new KepToltes(this); // P�ld�nyos�tom a k�perny�ket.
		kepernyoMenu = new KepMenu(this);
		kepernyoJatekter = new KepJatekter(this);
		KepernyoSugo = new KepSugo(this);

		kepernyo_csere(kepernyoMenu); // Men�vel kezd�nk

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		kepernyotoltes.jatekmenet_atmeretez();
		kepernyoMenu.jatekmenet_atmeretez();
		kepernyoJatekter.jatekmenet_atmeretez();
		KepernyoSugo.jatekmenet_atmeretez();

	}

	public void update(float mozdulX, float mozdulY) {
		camera.update();
		// System.out.println(mozdulX+" || "+mozdulY);
		// System.out.println(aCursor.getX()+" poz "+ aCursor.getY());
		aCursor.atHelyez(aCursor.getX() + mozdulX, aCursor.getY() + mozdulY);
		// System.out.println(aCursor);
		// aCursor.mozdul(mozdulX, mozdulY);
	}

	public void update() {
		camera.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(kepernyoFuto.bg_r, kepernyoFuto.bg_g, kepernyoFuto.bg_b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		camera.rotate(1, 0, 0, 0);

		camera.position.x = kepernyoFuto.offset_x;
		camera.position.y = kepernyoFuto.offset_y;
		// camera.position.z = ;

		batch.begin();

		kepernyoFuto.jatekmenet_render(batch);

		// aCursor.rajzol(batch);
		batch.end();

		// camera.update();

		if (giroszkop_vane) {
			kepernyoFuto.giroszkop(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ());
		}
	}

	@Override
	public void pause() {
		kepernyoFuto.szuneteltet();
	}

	@Override
	public void resume() {
		kepernyoFuto.folytat();
	}

	@Override
	public void dispose() {
		batch.dispose();
		try {
			kepernyoMenu.finalize(); // A teljes ablakokon kereszt�l sz�ntetek
										// meg mindent.
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			kepernyoJatekter.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			KepernyoSugo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			kepernyotoltes.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		if (dev)
			System.out.println(keycode + " lenyomva ");
		kepernyoFuto.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (dev)
			System.out.println(keycode + " felengedve ");
		kepernyoFuto.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (dev)
			System.out.println(character + " sz�veg ");
		kepernyoFuto.keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (dev)
			System.out.println(screenX + "touchDragged " + (h - screenY));
		kepernyoFuto.touchDragged(screenX, ((int) h - screenY), pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		aCursor.atHelyez(((float) screenX - aCursor.getSzelesseg() / 4) + kepernyoFuto.offset_x, (h - (float) screenY) - aCursor.getMagassag() + kepernyoFuto.offset_y);

		if (dev_hover)
			System.out.println(screenX + " mousehover " + (h - screenY));
		kepernyoFuto.mouseMoved(screenX, ((int) h - screenY));
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (dev)
			System.out.println(amount + " amount ");
		kepernyoFuto.scrolled(amount);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (dev)
			System.out.println(x + " tap " + (h - y));
		kepernyoFuto.tap(x, h - y, count, button);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		System.out.println(x + " longPress " + (h - y));
		kepernyoFuto.longPress(x, (h - y));
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (dev)
			System.out.println(velocityX + " fling " + velocityY);
		kepernyoFuto.fling(velocityX, velocityY, button);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if (dev)
			System.out.println(x + " pan " + (h - y));
		kepernyoFuto.pan(x, (h - y), deltaX, deltaY);
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		if (dev)
			System.out.println(x + " panStop " + (h - y));
		kepernyoFuto.panStop(x, (h - y), pointer, button);
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		if (dev)
			System.out.println(initialDistance + " zoom " + distance);
		kepernyoFuto.zoom(initialDistance, distance);
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		if (dev)
			System.out.println(initialPointer1 + " pinch " + initialPointer2);
		kepernyoFuto.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (dev)
			System.out.println(x + " touchDown gesture " + (h - y));
		kepernyoFuto.touchDown(x, (h - y), pointer, button);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (dev)
			System.out.println(screenX + " touch down input process " + (h - screenY));
		kepernyoFuto.touchDown(screenX, (h - screenY), pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (dev)
			System.out.println(screenX + "touchUp input process " + screenY);
		kepernyoFuto.touchUp(screenX, screenY, pointer, button);
		return false;
	}

}

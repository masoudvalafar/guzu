import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Guzu {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final long NANO_TO_MILI = 1000000;
	private static final int NUM_ENTITIES = 100;
	private static final long SLEEP_TIME = 20;
	private static final int NUM_FORCE_SPOTS = 2;
	
	private boolean showRightMenu = false; 

	public static void main(String[] argv) throws InterruptedException {
		Guzu guzu = new Guzu();
		guzu.start();
	}

	private void start() throws InterruptedException {
		
		// create force field
		//Force2D f = new ConstantForce();
		//Force2D f = new RandomForce();
		//Force2D forceField = new CentralizedField(WIDTH, HEIGHT);
		Force2D forceField = new ForceSpottedField(WIDTH, HEIGHT, NUM_FORCE_SPOTS);
		
		// create ObjectManager
		EntityManager objectManager = new EntityManager();
		objectManager.setNumberEntities(NUM_ENTITIES);
		objectManager.initialize(WIDTH, HEIGHT, System.nanoTime() / NANO_TO_MILI);
		
		// create display
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
				
		// loop: update, plot
		while (!Display.isCloseRequested()) {
			Thread.sleep(SLEEP_TIME);
			
			// updating and plotting entities
			objectManager.update(forceField, System.nanoTime() / NANO_TO_MILI);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			objectManager.draw();
			
			// updating forcefield
			forceField.draw();
			
			// mouse action
			Coordinate2D mouseCoordinate = pollMouseInput();
			if (showRightMenu) {
				GraphicsHelper.drawQuad(mouseCoordinate);
			}
			
			Display.update();
		}
		
		// destroying display
		Display.destroy();
	}

	private Coordinate2D pollMouseInput() {

		if (!showRightMenu) {
			if (Mouse.isButtonDown(0)) {
				showRightMenu = true;
				double x = Mouse.getX();
				double y = Mouse.getY();
				System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
				return new Coordinate2D(x, y);
			}
		} else {
			showRightMenu = false;
		}
		
		return null;
	}
	
}

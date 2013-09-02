

import org.lwjgl.opengl.GL11;

public class GraphicsHelper {

	public static void plotPixel(int x, int y) {
		// just one pixel
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2i(x, y);
		GL11.glVertex2i(x, y + 1);
		GL11.glEnd();
	}
	
	public static void plotLine(Coordinate2D c1, Coordinate2D c2) {
		plotLine(c1.getX(), c1.getY(), c2.getX(), c2.getY());
	}

	public static void plotLine(double x1, double y1, double x2, double y2) {
		
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2d(x1, y1);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
	}

	public static void drawQuad(Coordinate2D c) {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(c.getX() - 5, c.getY() - 5);
		GL11.glVertex2d(c.getX() - 5, c.getY() + 5);
		GL11.glVertex2d(c.getX() + 5, c.getY() + 5);
		GL11.glVertex2d(c.getX() + 5, c.getY() - 5);
		GL11.glEnd();
		
		
	}

}

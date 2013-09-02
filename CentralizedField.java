import java.util.Random;

import org.lwjgl.opengl.GL11;


public class CentralizedField implements Force2D {

	Random random = new Random();
	private static final double HATE_DISTANCE = 0;
	private static final double CONSTANT_FORCE = 100000.0;
	Coordinate2D forcePoint = new Coordinate2D();
	
	public CentralizedField(int width, int height) {
		forcePoint.setX(random.nextInt(width));
		forcePoint.setY(random.nextInt(height));
	}

	@Override
	public double getForceX(Coordinate2D c) {
		return getForce(c).getX();
	}

	@Override
	public double getForceY(Coordinate2D c) {
		return getForce(c).getY();
	}

	@Override
	public Coordinate2D getForce(Coordinate2D c) {
		Coordinate2D force = new Coordinate2D();
		
		double distance = ArithmaticHelper.getDistance(c, forcePoint);
		double forceMagnitude = CONSTANT_FORCE / distance; 
		
		if (distance > HATE_DISTANCE){
			force.setX(-1 * forceMagnitude * ArithmaticHelper.cosine(c, forcePoint));
			force.setY(-1 * forceMagnitude * ArithmaticHelper.sine(c, forcePoint));
		} else {
			force.setX(forceMagnitude * ArithmaticHelper.cosine(c, forcePoint));
			force.setY(forceMagnitude * ArithmaticHelper.sine(c, forcePoint));
		}
		
		return force;
	}

	@Override
	public void draw() {

		GL11.glColor3d(1.0d, 0d, 0d);
		GraphicsHelper.drawQuad(forcePoint);
	}

}

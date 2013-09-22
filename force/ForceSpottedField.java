package force;
import java.util.ArrayList;
import java.util.Random;

import main.ArithmaticHelper;
import main.GraphicsHelper;

import org.lwjgl.opengl.GL11;

import entity.Coordinate2D;


public class ForceSpottedField implements Force2D {

	private static final double HATE_DISTANCE = 50;
	private static final double CONSTANT_FORCE = 100000.0;
	
	Random random = new Random();
	ArrayList<Coordinate2D> forcePoints = new ArrayList<Coordinate2D>();
	
	public ForceSpottedField(int width, int height, int numForceSpots) {
		for (int i = 0; i < numForceSpots; i ++) {
			Coordinate2D forcePoint = new Coordinate2D(random.nextInt(width), random.nextInt(height));
			forcePoints.add(forcePoint);
		}
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
		
		for (Coordinate2D f: forcePoints){
			double distance = ArithmaticHelper.getDistance(c, f);
			double forceMagnitude = CONSTANT_FORCE / distance; 
			
			if (distance > HATE_DISTANCE){
				force.setX(-1 * forceMagnitude * ArithmaticHelper.cosine(c, f) + force.getX());
				force.setY(-1 * forceMagnitude * ArithmaticHelper.sine(c, f) + force.getY());
			} else {
				force.setX(forceMagnitude * ArithmaticHelper.cosine(c, f) + force.getX());
				force.setY(forceMagnitude * ArithmaticHelper.sine(c, f) + force.getY());
			}
		}
		
		return force;
	}

	@Override
	public void draw() {
		for(Coordinate2D f: forcePoints) {
			GL11.glColor3d(1.0d, 0d, 0d);
			GraphicsHelper.drawQuad(f);
		}

	}

}

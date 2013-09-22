package force;
import input.MouseInputStatus;
import main.ArithmaticHelper;
import main.GraphicsHelper;

import org.lwjgl.opengl.GL11;

import entity.Coordinate2D;


public class MouseField implements Force2D {

	private MouseInputStatus mouseInputStatus;
	
	private static final double CONSTANT_FORCE = 100000.0;
	private static final double HATE_DISTANCE = 0;

	public MouseField(MouseInputStatus mouseInputStatus) {
		this.mouseInputStatus = mouseInputStatus;
	}

	@Override
	public double getForceX(Coordinate2D c) {
		return getForce(c) == null ? 0 : getForce(c).getX();
	}

	@Override
	public double getForceY(Coordinate2D c) {
		
		return getForce(c) == null ? 0 : getForce(c).getY();
	}

	@Override
	public Coordinate2D getForce(Coordinate2D c) {
		
		//System.out.println(mouseInputStatus.getLeftButtonStatus());
		
		if (mouseInputStatus.getLeftButtonStatus()) {
			return mouseForceField(c);
		}
		else {
			return null;
		}
	}

	private Coordinate2D mouseForceField(Coordinate2D c) {
		Coordinate2D force = new Coordinate2D();
		
		double distance = ArithmaticHelper.getDistance(c, mouseInputStatus.getCoordinate());
		double forceMagnitude = CONSTANT_FORCE / distance; 
		
		if (distance > HATE_DISTANCE){
			force.setX(-1 * forceMagnitude * ArithmaticHelper.cosine(c, mouseInputStatus.getCoordinate()) + force.getX());
			force.setY(-1 * forceMagnitude * ArithmaticHelper.sine(c, mouseInputStatus.getCoordinate()) + force.getY());
		} else {
			force.setX(forceMagnitude * ArithmaticHelper.cosine(c, mouseInputStatus.getCoordinate()) + force.getX());
			force.setY(forceMagnitude * ArithmaticHelper.sine(c, mouseInputStatus.getCoordinate()) + force.getY());
		}
		
		return force;
	}

	@Override
	public void draw() {
		GL11.glColor3d(1.0d, 0d, 0d);
		GraphicsHelper.drawQuad(mouseInputStatus.getCoordinate());
	}

}

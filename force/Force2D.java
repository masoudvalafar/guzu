package force;

import entity.Coordinate2D;


public interface Force2D {
	
	public double getForceX(Coordinate2D c);
	public double getForceY(Coordinate2D c);
	public Coordinate2D getForce(Coordinate2D c);
	public void draw();
}

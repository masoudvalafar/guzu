package force;
import java.util.Random;

import entity.Coordinate2D;


public class RandomForce implements Force2D {

	Random random = new Random();
	
	@Override
	public double getForceX(Coordinate2D c) {
		
		return random.nextDouble() * 200 - 100;
	}

	@Override
	public double getForceY(Coordinate2D c) {
		
		return random.nextDouble() * 200 - 100;
	}

	@Override
	public Coordinate2D getForce(Coordinate2D c) {
		return new Coordinate2D(getForceX(c), getForceY(c));
	}

	@Override
	public void draw() {
	}

}

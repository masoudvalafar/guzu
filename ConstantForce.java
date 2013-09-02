public class ConstantForce implements Force2D {

	Coordinate2D force = new Coordinate2D();

	public ConstantForce(){
		force.setX(20.0);
		force.setY(50.0);
	}

	@Override
	public double getForceX(Coordinate2D c) {
		return force.getX();
	}

	@Override
	public double getForceY(Coordinate2D c) {
		return force.getY();
	}

	@Override
	public Coordinate2D getForce(Coordinate2D c) {
		return force;
	}

	@Override
	public void draw() {
	}
	

}

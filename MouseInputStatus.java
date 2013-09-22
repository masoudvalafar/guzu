

public class MouseInputStatus {

	Coordinate2D mouseCoordinate = new Coordinate2D();
	private boolean leftButtonDown;
	private boolean rightButtonDown;
	
	public void setMouseCoordinate(double x, double y) {
		
		mouseCoordinate.setX(x);
		mouseCoordinate.setY(y);
		
		leftButtonDown = false;
		rightButtonDown = false;
	}

	public void setLeftButtonStatus(boolean buttonDown) {
		
		leftButtonDown = buttonDown;
		
	}
	
	public boolean getLeftButtonStatus() {
		return leftButtonDown;
	}
	
	public void setRighttButtonStatus(Boolean buttonDown) {
		
		rightButtonDown = buttonDown;
	}
	
	public boolean getRightButtonStatus() {
		return rightButtonDown;
	}

	public Coordinate2D getCoordinate() {
		return mouseCoordinate;
	}

}

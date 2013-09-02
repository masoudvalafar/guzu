
public class ArithmaticHelper {

	public static double getDistance(Coordinate2D c1, Coordinate2D c2) {
		double distance = Math.sqrt( Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2));
		return distance;
	}

	public static double cosine(Coordinate2D c1, Coordinate2D c2) {
		double distance = getDistance(c1, c2);
		return (c1.getX() - c2.getX()) / distance;
	}

	public static double sine(Coordinate2D c1, Coordinate2D c2) {
		double distance = getDistance(c1, c2);
		return (c1.getY() - c2.getY()) / distance;
	}

}

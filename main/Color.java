package main;
public class Color{
	
	private double red;
	private double green;
	private double blue;

	public Color(double r, double g, double b){
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	public double getRed() {
		return red;
	}

	public void setRed(double red) {
		this.red = red;
	}

	public double getGreen() {
		return green;
	}

	public void setGreen(double green) {
		this.green = green;
	}

	public double getBlue() {
		return blue;
	}

	public void setBlue(double blue) {
		this.blue = blue;
	}

}

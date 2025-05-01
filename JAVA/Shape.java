import java.util.Scanner;
abstract class Shape {
	double sides[];
	double perimeter,area;
	Shape(double sides[]) {
		this.sides = sides;
		perimeter=0;
		area=0;
	}
	abstract void calculatePerimeter();
 	abstract void calculateArea();
	void displayDetails() {
		for(int i=0; i<sides.length; i++) {
			System.out.println("Side "+(i+1)+": "+sides[i]);
		}
		System.out.println("Area: "+area+"\nPerimeter: "+perimeter);
	}
}
class Triangle extends Shape{
	Triangle(double s1, double s2, double s3) {
		super(new double[]{s1,s2,s3});
	}
	void calculatePerimeter () {
		perimeter = sides[0]+sides[1]+sides[2];
	}
	void calculateArea() {
		double s = perimeter/2;
		area = Math.sqrt(s*(s-sides[0])*(s-sides[1])*(s-sides[2]));
	}
}

class UseShape {
	public static void main(String args[]) {
		Triangle t = new Triangle(15,20,30);
		t.calculatePerimeter();
		t.calculateArea();
		t.displayDetails();
	}
}

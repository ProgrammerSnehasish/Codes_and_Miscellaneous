import java.io.*;
import java.util.Scanner;
class Main {
	public static void main(String [] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter radius for the circle: ");
	float radius = scanner.nextFloat();
        Circle circle = new Circle(radius);
	System.out.print("Enter height for the rectangle: ");
	float height = scanner.nextFloat();
	System.out.print("Enter length for the rectangle: ");
	float length = scanner.nextFloat();
        Rectangle rectangle = new Rectangle(height, length);
        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());
    }
}
abstract class Shape{
    abstract double calculateArea();
    abstract double calculatePerimeter();
}
class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
class Rectangle extends Shape {
    private double length;
    private double width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    double calculateArea() {
        return length * width;
    }
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

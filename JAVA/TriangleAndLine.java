//Draw Triangle and Line.
import java.io.*;
import java.util.Scanner;
class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        Triangle triangle = new Triangle();
        LineDraw lineDraw = new LineDraw();

        System.out.println("Drawing a triangle:");
        System.out.print("Enter range to draw the triangle: ");
        int range_triangle = scanner.nextInt();
        triangle.draw(range_triangle);

        System.out.println("Drawing a line:");
        System.out.print("Enter range to draw the line: ");
        int range_line = scanner.nextInt();
        lineDraw.draw(range_line);
    }
}
abstract class Drawing extends Main{
    abstract void draw(int range);
}
class Triangle extends Drawing {
    void draw(int range_triangle) {
        int i = 0;
        int j = 0;
        for (i = 0; i < range_triangle; i++) {
            for (j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
class LineDraw extends Drawing {
    void draw(int range_line) {
        int i = 0;
        for (i = 0; i < range_line; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

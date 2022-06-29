package training;

import java.util.Scanner;

public class QuadraticEquation {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    // Реални числа за  тест:  а=1,  b=3, c=-4

    System.out.println("Lets solve the quadratic formula ax(2)+bx+c");
    System.out.println("Enter the value of a: ");
    double a = scanner.nextDouble();

    System.out.println("Enter the value of b: ");
    double b = scanner.nextDouble();

    System.out.println("Enter the value of c: ");
    double c = scanner.nextDouble();

    double D = (b * b) - (4 * a * c);
    // System.out.println(D);

    if (a == 0) {
      double x0;
      x0 = -c / b;
      System.out.println("This equation has only one root and it is");
      System.out.println("X= " + x0);
    } else {
      if (D < 0) {
        System.out.println("Sorry, but this equation has no real roots to solve...");
      } else {

        double x1;
        double x2;

        x1 = (-(b) + (Math.sqrt(D))) / (2 * a);
        // System.out.println(x1);

        x2 = (-(b) - (Math.sqrt(D))) / (2 * a);
        // System.out.println(x2);

        System.out.println("The roots of the equation are:");
        System.out.println("X1= " + x1);
        System.out.println("X2= " + x2);
      }
    }

  }
}

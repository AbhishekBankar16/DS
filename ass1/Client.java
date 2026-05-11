import java.rmi.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            String serverURL = "rmi://localhost/Server";

            // Lookup remote object
            ServerIntf serverIntf =
                    (ServerIntf) Naming.lookup(serverURL);

            // Input
            System.out.print("Enter First Number: ");
            double num1 = sc.nextDouble();

            System.out.print("Enter Second Number: ");
            double num2 = sc.nextDouble();

            // Display input
            System.out.println("First Number Is: " + num1);
            System.out.println("Second Number Is: " + num2);

            // Operations
            System.out.println("\n------ Results ------");

            System.out.println("Addition Is: "
                    + serverIntf.Addition(num1, num2));

            System.out.println("Subtraction Is: "
                    + serverIntf.Subtraction(num1, num2));

            System.out.println("Multiplication Is: "
                    + serverIntf.Multiplication(num1, num2));

            System.out.println("Division Is: "
                    + serverIntf.Division(num1, num2));

        } catch (Exception e) {

            System.out.println(
                    "Exception Occurred At Client: "
                            + e.getMessage());
        }

        sc.close();
    }
}

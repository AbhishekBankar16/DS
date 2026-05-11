import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject
        implements ServerIntf {

    // Constructor
    public ServerImpl() throws RemoteException {
        super();
    }

    // Addition
    public double Addition(double num1, double num2)
            throws RemoteException {

        return num1 + num2;
    }

    // Subtraction
    public double Subtraction(double num1, double num2)
            throws RemoteException {

        return num1 - num2;
    }

    // Multiplication
    public double Multiplication(double num1, double num2)
            throws RemoteException {

        return num1 * num2;
    }

    // Division
    public double Division(double num1, double num2)
            throws RemoteException {

        if (num2 == 0) {

            System.out.println("Cannot divide by zero!");
            return 0;
        }

        return num1 / num2;
    }
}

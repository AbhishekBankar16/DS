import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class ReverseClient {
    public static void main(String args[]) {
        try {
            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            // Resolve server object
            Reverse reverseObj = ReverseHelper.narrow(
                    ncRef.resolve_str("ReverseService"));

            String input = "Distributed System";

            // Invoke remote method
            String result = reverseObj.reverse_string(input);

            System.out.println("Original String: " + input);
            System.out.println("Reversed String: " + result);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

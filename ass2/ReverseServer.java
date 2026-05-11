import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class ReverseServer {
    public static void main(String args[]) {
        try {
            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get reference to RootPOA and activate POAManager
            POA rootpoa = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Create servant
            ReverseImpl reverseImpl = new ReverseImpl();

            // Get object reference from servant
            org.omg.CORBA.Object ref =
                    rootpoa.servant_to_reference(reverseImpl);

            Reverse href = ReverseHelper.narrow(ref);

            // Get naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            // Bind object reference in naming
            NameComponent path[] =
                    ncRef.to_name("ReverseService");

            ncRef.rebind(path, href);

            System.out.println("Reverse Server ready...");

            // Wait for client calls
            orb.run();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

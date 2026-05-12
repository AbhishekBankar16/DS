import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

class A extends CalcPOA {

    public float add(float a,float b){ return a+b; }

    public float sub(float a,float b){ return a-b; }

    public float mul(float a,float b){ return a*b; }

    public float div(float a,float b){ return a/b; }
}

public class Server {

    public static void main(String args[]) throws Exception {

        ORB orb = ORB.init(args,null);

        POA root = POAHelper.narrow(
        orb.resolve_initial_references("RootPOA"));

        root.the_POAManager().activate();

        A obj = new A();

        org.omg.CORBA.Object ref =
        root.servant_to_reference(obj);

        Calc href = CalcHelper.narrow(ref);

        NamingContextExt nc =
        NamingContextExtHelper.narrow(
        orb.resolve_initial_references("NameService"));

        nc.rebind(nc.to_name("ABC"),href);

        System.out.println("Server Started");

        orb.run();
    }
}
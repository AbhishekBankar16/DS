import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Client {

    public static void main(String args[]) throws Exception {

        ORB orb = ORB.init(args,null);

        NamingContextExt nc =
        NamingContextExtHelper.narrow(
        orb.resolve_initial_references("NameService"));

        Calc obj =
        CalcHelper.narrow(nc.resolve_str("ABC"));

        System.out.println(obj.add(10,5));
        System.out.println(obj.sub(10,5));
        System.out.println(obj.mul(10,5));
        System.out.println(obj.div(10,5));
    }
}
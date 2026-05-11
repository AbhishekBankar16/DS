import java.rmi.*;

public class Server {

    public static void main(String[] args) {

        try {

            // Create object
            ServerImpl serverImpl = new ServerImpl();

            // Bind object
            Naming.rebind("rmi://localhost/Server", serverImpl);

            System.out.println("Server Started...");

        } catch (Exception e) {

            System.out.println(
                    "Exception Occurred At Server: "
                            + e.getMessage());
        }
    }
}

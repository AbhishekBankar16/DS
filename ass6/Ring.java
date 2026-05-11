import java.util.*;

public class Ring {

    int max_processes;
    int coordinator;
    boolean processes[];
    ArrayList<Integer> pid;

    // Constructor
    public Ring(int max) {

        coordinator = max;
        max_processes = max;

        pid = new ArrayList<Integer>();
        processes = new boolean[max];

        System.out.println("Creating processes...");

        for (int i = 0; i < max; i++) {

            processes[i] = true;
            System.out.println("P" + (i + 1) + " created.");
        }

        System.out.println("P" + coordinator + " is the coordinator");
    }

    // Display process status
    void displayProcesses() {

        System.out.println("\nProcess Status:");

        for (int i = 0; i < max_processes; i++) {

            if (processes[i]) {
                System.out.println("P" + (i + 1) + " is UP");
            } else {
                System.out.println("P" + (i + 1) + " is DOWN");
            }
        }

        if (processes[coordinator - 1]) {
            System.out.println("Coordinator is P" + coordinator);
        } else {
            System.out.println("Coordinator is DOWN. Run election again.");
        }
    }

    // UP process
    void upProcess(int process_id) {

        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {

            processes[process_id - 1] = true;
            System.out.println("Process P" + process_id + " is now UP.");

        } else {

            System.out.println("Process P" + process_id + " is already UP.");
        }
    }

    // DOWN process
    void downProcess(int process_id) {

        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {

            System.out.println("Process P" + process_id + " is already DOWN.");

        } else {

            processes[process_id - 1] = false;
            System.out.println("Process P" + process_id + " is DOWN.");

            if (coordinator == process_id) {
                System.out.println("Coordinator is DOWN. Run election again.");
            }
        }
    }

    // Display ArrayList
    void displayArrayList(ArrayList<Integer> pid) {

        System.out.print("[ ");

        for (Integer x : pid) {
            System.out.print(x + " ");
        }

        System.out.println("]");
    }

    // Ring Election Algorithm
    void initElection(int process_id) {

        if (process_id < 1 || process_id > max_processes) {
            System.out.println("Invalid process ID.");
            return;
        }

        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is DOWN.");
            return;
        }

        pid.clear();

        int temp = process_id - 1;

        System.out.println("\nElection initiated by P" + process_id);

        do {

            if (processes[temp]) {

                pid.add(temp + 1);

                System.out.print("Process P" + (temp + 1)
                        + " sending the following list: ");

                displayArrayList(pid);
            }

            temp = (temp + 1) % max_processes;

        } while (temp != (process_id - 1));

        coordinator = Collections.max(pid);

        System.out.println("\nProcess P" + coordinator
                + " is elected as the new coordinator.");

        pid.clear();
    }

    // Main Method
    public static void main(String args[]) {

        Ring ring = null;

        int max_processes;
        int process_id;
        int choice;

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Ring Algorithm =====");

            System.out.println("1. Create Processes");
            System.out.println("2. Display Processes");
            System.out.println("3. Up a Process");
            System.out.println("4. Down a Process");
            System.out.println("5. Run Election Algorithm");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter total number of processes: ");
                    max_processes = sc.nextInt();

                    if (max_processes <= 0) {
                        System.out.println("Invalid number of processes.");
                        break;
                    }

                    ring = new Ring(max_processes);
                    break;

                case 2:

                    if (ring == null) {
                        System.out.println("Please create processes first.");
                        break;
                    }

                    ring.displayProcesses();
                    break;

                case 3:

                    if (ring == null) {
                        System.out.println("Please create processes first.");
                        break;
                    }

                    System.out.print("Enter process number to UP: ");
                    process_id = sc.nextInt();

                    ring.upProcess(process_id);
                    break;

                case 4:

                    if (ring == null) {
                        System.out.println("Please create processes first.");
                        break;
                    }

                    System.out.print("Enter process number to DOWN: ");
                    process_id = sc.nextInt();

                    ring.downProcess(process_id);
                    break;

                case 5:

                    if (ring == null) {
                        System.out.println("Please create processes first.");
                        break;
                    }

                    System.out.print("Enter process to initiate election: ");
                    process_id = sc.nextInt();

                    ring.initElection(process_id);
                    break;

                case 6:

                    System.out.println("Program terminated.");
                    sc.close();
                    System.exit(0);
                    break;

                default:

                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
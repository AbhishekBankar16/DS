#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[]) {

    int rank, size;

    int num[20];

    // Initialize MPI
    MPI_Init(&argc, &argv);

    // Get process rank
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    // Get total number of processes
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // Initialize array
    for (int i = 0; i < 20; i++) {

        num[i] = i + 1;
    }

    // MASTER PROCESS
    if (rank == 0) {

        int s[4];

        printf("Distribution at rank %d\n", rank);

        // Send 5 elements to each process
        for (int i = 1; i < 4; i++) {

            MPI_Send(&num[i * 5],
                     5,
                     MPI_INT,
                     i,
                     1,
                     MPI_COMM_WORLD);
        }

        // Local sum for rank 0
        int local_sum = 0;

        for (int i = 0; i < 5; i++) {

            local_sum += num[i];
        }

        // Receive local sums
        for (int i = 1; i < 4; i++) {

            MPI_Recv(&s[i],
                     1,
                     MPI_INT,
                     i,
                     1,
                     MPI_COMM_WORLD,
                     MPI_STATUS_IGNORE);
        }

        printf("Local sum at rank %d is %d\n",
               rank,
               local_sum);

        int final_sum = local_sum;

        // Add all local sums
        for (int i = 1; i < 4; i++) {

            final_sum += s[i];
        }

        printf("Final sum = %d\n", final_sum);

    }

    // WORKER PROCESSES
    else {

        int k[5];

        // Receive 5 numbers
        MPI_Recv(k,
                 5,
                 MPI_INT,
                 0,
                 1,
                 MPI_COMM_WORLD,
                 MPI_STATUS_IGNORE);

        int local_sum = 0;

        // Calculate local sum
        for (int i = 0; i < 5; i++) {

            local_sum += k[i];
        }

        printf("Local sum at rank %d is %d\n",
               rank,
               local_sum);

        // Send local sum to master
        MPI_Send(&local_sum,
                 1,
                 MPI_INT,
                 0,
                 1,
                 MPI_COMM_WORLD);
    }

    // Finalize MPI
    MPI_Finalize();

    return 0;
}





//sudo apt update
//sudo apt install mpich -y
//sudo apt update
//sudo apt install mpich libmpich-dev -y
//mpicc Sum_mpi.c -o sum
//mpiexec -n 4 ./sum

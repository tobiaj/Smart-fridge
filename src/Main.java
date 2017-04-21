import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tobiaj on 2017-04-20.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        LinkedBlockingQueue<Jobs> jobs = new LinkedBlockingQueue<>();
        Worker worker = new Worker("Worker", jobs);
        worker.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in ));
        while (true) {
            System.out.println("What would you like to do \n" +
                    "1: Scan in \n" +
                    "2: Scan out \n");
            System.out.println("Enter here: ");
            int choice = Integer.parseInt(bufferedReader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Scanning in");
                    break;

                case 2:
                    System.out.println("Scannin out");
                    break;

                    default:
                        System.out.println("Try again");
            }

        }
    }
}

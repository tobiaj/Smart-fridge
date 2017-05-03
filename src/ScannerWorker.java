
import Database.DatabaseHandler;
import Database.DatabaseInterface;
import sun.awt.image.ImageWatched;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tobiaj on 2017-04-20.
 */


public class ScannerWorker {

    private String workername;
    private Integer numberOfWorker;
    private LinkedBlockingQueue<Jobs> jobs;

    public ScannerWorker(String name, LinkedBlockingQueue<Jobs> scannerJobs, DatabaseHandler databaseHandler, int numberOfWorkers) {
        this.jobs = scannerJobs;
        this.workername = name;
        this.numberOfWorker = numberOfWorkers;

        for (int i = 0; i < numberOfWorker ; i++) {

            Worker worker = new Worker("Worker - " + i, jobs, databaseHandler);
            worker.start();
        }

    }

    public class Worker extends Thread {

        private Thread thread;
        private String name;
        private DatabaseHandler databaseHandler;
        private LinkedBlockingQueue<Jobs> barcodes;

        public Worker(String name, LinkedBlockingQueue<Jobs> barcodes, DatabaseHandler databaseHandler) {
            this.name = name;
            this.barcodes = barcodes;
            this.databaseHandler = databaseHandler;

        }


        public void start() {
            System.out.println("Starting thread: " + name);
            if (thread == null) {
                thread = new Thread(this, name);
                thread.start();
            }
        }

        @Override
        public void run() {

            try {
                waitForJobs();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();

        }

        private void waitForJobs() throws InterruptedException {
            Jobs job = jobs.take();

            if (job.getNumberOfScan().equals(1)) {
                System.out.println(workername + " took job with number " + job.getNumberOfScan() + " and sending barcode " + job.getArticelNumber());

                databaseHandler.add(job.getArticelNumber());

            } else if (job.getNumberOfScan().equals(2)) {
                System.out.println(workername + " took job with number " + job.getNumberOfScan() + " and sending barcode " + job.getArticelNumber());


                databaseHandler.remove(job.getArticelNumber());

            } else {
                System.out.println("No matching input");
            }

        }
    }
}
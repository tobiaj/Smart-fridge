import Database.DatabaseHandler;
import Database.DatabaseInterface;
import com.sun.corba.se.spi.orbutil.fsm.InputImpl;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by tobiaj on 2017-04-20.
 */
public class Main {


            public static void main(String[] args) throws IOException {
                ServerSocket serverSocket = null;


                LinkedBlockingQueue<Jobs> scannerJobs = new LinkedBlockingQueue<>(15);
                DatabaseHandler databaseHandler = new DatabaseHandler();
                int numberOfWorkers = 3;
                ScannerWorker worker = new ScannerWorker("Scanner Worker", scannerJobs, databaseHandler, numberOfWorkers);

                try {
                    serverSocket = new ServerSocket(4444);
                    serverSocket.setSoTimeout(1600000);

                    System.out.println(" Waiting a client ... ");
                    Socket socket = serverSocket.accept();
                    int i = 0;
                    DataOutputStream out = new DataOutputStream(
                            socket.getOutputStream());

                    String result = "";

                    while (true) {

                        System.out.println("Connected");
                        DataInputStream in = new DataInputStream(
                                socket.getInputStream());

                        result = in.readUTF();
                        System.out.println("Result " + result);

                        String[] split = result.split(",");
                        String inOrOut = split[0];
                        int barcode = Integer.parseInt(split[1]);


                        int choice = Integer.parseInt(inOrOut);
                        Jobs job_i;

                        switch (choice) {
                            case 1:
                                System.out.println("Scanning in");
                                    job_i = new Jobs(1, barcode);
                                    scannerJobs.add(job_i);

                                break;

                            case 2:
                                System.out.println("Scanning out");
                                    job_i = new Jobs(2, barcode);
                                    scannerJobs.add(job_i);

                                break;

                            default:
                                System.out.println("Try again");
                        }

                    }

                } catch (IOException e) {
                    System.err.println(" Eroare IO \n" + e);


                } finally {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

        }
/*
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
                    for (int i = 0; i < 3; i++) {
                        Jobs job_i = new Jobs(1, 123+i);
                        scannerJobs.add(job_i);

                    }
                    break;

                case 2:
                    System.out.println("Scanning out");
                    for (int i = 0; i < 3; i++) {
                        Jobs job_i = new Jobs(2, 123+i);
                        scannerJobs.add(job_i);

                    }
                    break;

                    default:
                        System.out.println("Try again");
            }

        }
    }*/


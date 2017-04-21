import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tobiaj on 2017-04-20.
 */
public class Worker extends Thread {

    private Thread thread;
    private String name;
    private LinkedBlockingQueue<Jobs> jobs;


    public Worker(String name, LinkedBlockingQueue<Jobs> jobs){
        this.jobs = jobs;
        this.name = name;
    }

    public void start() {
        System.out.println("Starting thread: " + name);
        if (thread == null ){
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


    }
}

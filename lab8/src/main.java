import java.io.IOException;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) throws IOException {
        URLPool pool = new URLPool(new URLDepthPair(args[0],0),Integer.parseInt(args[1]));
        LinkedList<Thread> threadList = new LinkedList<>();

        int countThread = Integer.parseInt(args[2]);

        for (int i=0;i<countThread;i++){
            CrawlerTask crawlerTask = new CrawlerTask(pool);
            threadList.add(new Thread(crawlerTask));
            threadList.getLast().start();
        }

        while (pool.getWaitThreads()!=countThread){}

        LinkedList<URLDepthPair> site=pool.getSite();

        for(URLDepthPair url: site){
            System.out.println(url);
        }

        for(Thread thread: threadList){
            thread.stop();
        }
    }
}

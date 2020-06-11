import java.io.IOException;

public class CrawlerTask implements Runnable {

    private URLPool urlPool;
    private URLDepthPair urlDepthPair;
    private Crawler crawler;

    public CrawlerTask(URLPool pool) {
        urlPool = pool;
    }

    public void run() {
        while (true) {
            urlDepthPair = urlPool.getURL();
            try {
                crawler=new Crawler(urlDepthPair);
                urlPool.addListURL(crawler.start());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

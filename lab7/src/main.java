import java.io.IOException;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) throws IOException {
        LinkedList<URLDepthPair> site = new LinkedList<>();
        LinkedList<URLDepthPair> newSite = new LinkedList<>();

        int fdepth=Integer.parseInt(args[1]);
        newSite.add(new URLDepthPair(args[0],0));

        while(newSite.size()!=0){
            Crawler crawler = new Crawler(newSite.getFirst());
            LinkedList<URLDepthPair> getSites = crawler.start();
            if (newSite.getFirst().getDepth()<fdepth)
                newSite.addAll(getSites);

            else
                site.addAll(getSites);
            site.add(newSite.removeFirst());
        }

        for(URLDepthPair url: site){
            System.out.println(url);
        }
    }
}

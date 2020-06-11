import java.util.LinkedList;

public class URLPool {
    private LinkedList<URLDepthPair> addSite = new LinkedList<>();
    private LinkedList<URLDepthPair> noAddSite = new LinkedList<>();

    private int finalDepth;

    private int waitThreads = 0;

    URLPool(URLDepthPair url, int depth){
        finalDepth=depth;
        noAddSite = new LinkedList<URLDepthPair>();
        addSite = new LinkedList<URLDepthPair>();
        noAddSite.add(url);
    }

    public synchronized int getWaitThreads() {
        return waitThreads;
    }

    public synchronized URLDepthPair getURL(){
        if (noAddSite.size() == 0) {
            try {
                waitThreads++;
                this.wait();
            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        addSite.add(noAddSite.getFirst());
        return noAddSite.removeFirst();
    }

    public synchronized void addListURL(LinkedList<URLDepthPair> URLs){

        if (URLs.size()!=0){
            if (URLs.getFirst().getDepth()>=finalDepth){
                addSite.addAll(URLs);
            }
            else{
                noAddSite.addAll(URLs);
                for (int countSite=URLs.size(); countSite!=0 && waitThreads!=0;countSite-- , waitThreads--){
                    this.notify();
                }
            }
        }
    }

    public LinkedList<URLDepthPair> getSite(){
        return addSite;
    }
}

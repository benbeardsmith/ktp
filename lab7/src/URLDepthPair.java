import java.net.*;

public class URLDepthPair {
    private URL url;
    private int depth;

    URLDepthPair(String url, int depth){
        try {
            this.url = new URL(url);
            this.depth = depth;
        }
        catch (MalformedURLException e){
                System.err.println("MalformedURLException: " + e.getMessage());
        }
    }

    public boolean isURL(){
        return true;
    }

    public String getUrl() {
        return url.getHost();
    }

    public int getDepth() {
        return depth;
    }

    public String toString(){
        return "|"+depth+"| "+url;
    }
}

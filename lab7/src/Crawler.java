import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    URLDepthPair urlDepthPair;
    LinkedList<URLDepthPair> siteList;
    MySocket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Pattern regHTTP;

    Crawler (URLDepthPair urlDepthPair)  {
        this.urlDepthPair=urlDepthPair;
    }

    LinkedList<URLDepthPair> start() throws IOException{
        try {
            socket = new MySocket(urlDepthPair.getUrl(),80);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            siteList = new LinkedList<URLDepthPair>();
            regHTTP = Pattern.compile("(http:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");
        }
        catch (Exception exc){
            System.out.println(exc);
            return new LinkedList<URLDepthPair>();
        }

        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: "+urlDepthPair.getUrl()+":80");
        printWriter.println("Connection: Close");
        printWriter.println();

        try{
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                while(line.contains("<a")){
                    while (line.indexOf(">", line.indexOf("<a"))==-1) line+=bufferedReader.readLine();

                    String http = line.substring(line.indexOf("<a"),line.indexOf(">", line.indexOf("<a")));
                    if (http.contains("http://")){
                        Matcher matcher = regHTTP.matcher(http);
                        String url="";
                        if (matcher.find()) {
                            url = matcher.group();
                        }
                        else return new LinkedList<URLDepthPair>();
                        siteList.add(new URLDepthPair(url,urlDepthPair.getDepth()+1));
                    }
                    line=line.replace(http,"");
                }
            }
        }
        catch (IOException except){
            System.out.println(except);
        }
        socket.close();

        return siteList;
    }

}



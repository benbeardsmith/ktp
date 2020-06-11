import java.io.*;
import java.net.*;
public class MySocket {
    Socket socket;

    MySocket(String host,int port) throws IOException {
        socket = new java.net.Socket(host,port);
        setSoTimeout(5000);
    }

    void setSoTimeout(int timeout)throws IOException {
        socket.setSoTimeout(timeout);
    }

    InputStream getInputStream()throws IOException {
        return socket.getInputStream();
    }

    OutputStream getOutputStream()throws IOException{
        return socket.getOutputStream();
    }

    void close() throws IOException{
        socket.close();
    }
}

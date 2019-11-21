import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int listenPort;

    public Server(int listenPort) {
        this.listenPort = listenPort;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(listenPort);
            Socket clientSocket = serverSocket.accept();
            long startTime = System.currentTimeMillis();
            byte data[] = new byte[1000];
            long received = 0, n = 0;
            InputStream in = clientSocket.getInputStream();
            while ((n = in.read(data)) != -1) received += n;
            long time = System.currentTimeMillis() - startTime;
            serverSocket.close();
            clientSocket.close();
            double rate = received * 8.0 / (time * 1000);
            System.out.println("received=" + received / 1000 + "KB rate=" + String.format("%.3f", rate) + "Mbps");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

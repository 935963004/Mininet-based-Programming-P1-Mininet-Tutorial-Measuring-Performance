package jwb;

import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private String serverHostname;
    private int serverPort, time;

    public Client(String serverHostname, int serverPort, int time) {
        this.serverHostname = serverHostname;
        this.serverPort = serverPort;
        this.time = time;
    }

    public void run() {
        try {
            Socket socket = new Socket(serverHostname, serverPort);
            byte data[] = new byte[1000];
            long sent = 0, startTime = System.currentTimeMillis();
            OutputStream out = socket.getOutputStream();
            while (System.currentTimeMillis() - startTime <= time * 1000) {
                out.write(data);
                out.flush();
                sent++;
            }
            socket.close();
            double rate = sent * 8.0 / (1000 * time);
            System.out.println("sent=" + sent + "KB rate=" + String.format("%.3f", rate) + "Mbps");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

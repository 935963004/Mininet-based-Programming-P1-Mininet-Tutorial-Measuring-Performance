public class Iperfer {

    public static void main(String[] args) {
        int length = args.length;
        boolean isClient = false;
        if (args[0].equals("-c")) {
            isClient = true;
            if (length != 7) {
                System.err.println("Error: missing or additional arguments");
                System.exit(-1);
            }
        }
        else if (args[0].equals("-s")) {
            isClient = false;
            if (length != 3) {
                System.err.println("Error: missing or additional arguments");
                System.exit(-1);
            }
        }
        else {
            System.err.println("Error: no such mode, please use \"-c\" or \"-s\"");
            System.exit(-1);
        }

        if (isClient) {
            if (!args[1].equals("-h")) {
                System.err.println("Error: the second option should be \"-h\"");
                System.exit(-1);
            }
            String serverHostname = args[2];
            if (!args[3].equals("-p")) {
                System.err.println("Error: the third option should be \"-p\"");
                System.exit(-1);
            }
            int serverPort = Integer.parseInt(args[4]);
            if (serverPort < 1024 || serverPort > 65535) {
                System.err.println("Error: port number must be in the range 1024 to 65535");
                System.exit(-1);
            }
            if (!args[5].equals("-t")) {
                System.err.println("Error: the fourth option should be \"-t\"");
                System.exit(-1);
            }
            int time = Integer.parseInt(args[6]);
            new Client(serverHostname, serverPort, time).run();
        }
        else {
            if (!args[1].equals("-p")) {
                System.err.println("Error: the second option should be \"-p\"");
                System.exit(-1);
            }
            int listenPort = Integer.parseInt(args[2]);
            if (listenPort < 1024 || listenPort > 65535) {
                System.err.println("Error: port number must be in the range 1024 to 65535");
                System.exit(-1);
            }
            new Server(listenPort).run();
        }
    }
}

import java.io.IOException;
import java.net.*;

public class EchoClient {

    private static final int BUFSIZE = 508;
    private static final int TIMEOUT = 2000;

    public static void main(String[] args) throws IOException {

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        byte[] data = args[2].getBytes();


        //Socket wird an anonymen Port gebunden
        DatagramSocket socket = new DatagramSocket();

        //Maximaler TIMOUT in Milliksenkunden
        socket.setSoTimeout(TIMEOUT);

        //Paket an Server senden
        InetAddress addr = InetAddress.getByName(host);
        DatagramPacket paketOut = new DatagramPacket(data, data.length, addr, port);
        socket.send(paketOut);

        //Antwort Paket empfangen
        DatagramPacket paketIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
        socket.receive(paketIn);

        String received = new String(paketIn.getData(), 0, paketIn.getLength());
        System.out.println("Received: " + received);

        socket.close();
    }


}

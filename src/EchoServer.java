import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

    private static final int BUFSIZE = 508;

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);

        //UDP-Socket an den Port binden
        DatagramSocket socket = new DatagramSocket(port);

        //Pakte zum Empfangen bzw. Senden
        DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
        DatagramPacket packetOut = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

        System.out.println("Server gestartet...");


        while (true) {
            //Paket empfangen
            socket.receive(packetIn);
            System.out.println("Received: " + packetIn.getLength() + " bytes");

            //Daten und Länge im Antwortpaket speichern
            packetOut.setData(packetIn.getData());
            packetOut.setLength(packetIn.getLength());


            //Zieladresse und Zielport im Antwortpaket setzen

            packetOut.setAddress(packetIn.getAddress());
            packetOut.setPort(packetIn.getPort());

            //Antwort senden
            socket.send(packetOut);
        }

    }




}

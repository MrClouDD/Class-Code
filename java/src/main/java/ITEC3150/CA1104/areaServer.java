package ITEC3150.CA1104;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class areaServer {
    public static void main(String[] args) {
        int port = 8000;
        String host = "localhost";

        DataInputStream in;
        DataOutputStream out;
        ServerSocket server;
        Socket socket;

        try {
            server = new ServerSocket(port);
            socket = server.accept();

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            int length = in.readInt();
            int width = in.readInt();

            int area = length * width;

            out.writeInt(area);

            server.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Exception occured" + e.getLocalizedMessage());
        }
    }
}

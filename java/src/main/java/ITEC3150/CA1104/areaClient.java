package ITEC3150.CA1104;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class areaClient {
    public static void main(String[] args) {
        int port = 8000;
        String host = "localhost";

        DataInputStream in;
        DataOutputStream out;
        Socket socket;

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Scanner kbd = new Scanner(System.in);
            System.out.println("Which 2 numbers do you want to use? Enter with spaces in between");
            int length = kbd.nextInt();
            int width = kbd.nextInt();

            out.writeInt(length);
            out.writeInt(width);

            int area = in.readInt();

            System.out.println("The area is " + area + " units");
            socket.close();

        } catch (Exception e) {

        }
    }
}

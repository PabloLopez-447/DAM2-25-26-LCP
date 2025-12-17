package UD2.Ejercicios.stock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", Config.PUERTO);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in)
        ) {
            System.out.println(in.readUTF());

            while (true) {
                System.out.print("> ");
                String cmd = sc.nextLine();
                out.writeUTF(cmd);

                String resp = in.readUTF();
                System.out.println(resp);

                if (cmd.equalsIgnoreCase(Config.CMD_SALIR))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Conexión cerrada");
        }
    }
}


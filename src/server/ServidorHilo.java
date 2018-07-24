package server;

import resources.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.Arrays;

public class ServidorHilo implements Runnable {

    private static FileManager manager;
    private Socket socket = null;

    public ServidorHilo(Socket socket) {
//        super("ServidorHilo");
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());) {
            manager = new FileManager();
//            manager = FileManager.getInstance();
            String fromClient, respuesta;
            respuesta = uso();
            outputStream.writeObject(respuesta);
//            System.out.println(super.getName() + ":" + super.getId() + " envio " + respuesta);
            //hilo corre mientras el cliente envie mensajes

            while ((fromClient = (String) inputStream.readObject()) != null) {
//                System.out.println(super.getName() + ":" + super.getId() + " recibio " + fromClient);
                //si el cliente termina la conexion
                if (fromClient.equalsIgnoreCase("q")) {
                    outputStream.writeObject("Terminando...");
//                    System.out.println(super.getName() + ":" + super.getId() + " ha terminado");
                    break;
                } else if (fromClient.equalsIgnoreCase("ls")) {
                    String[] files = manager.getFilesNames();
                    outputStream.writeObject(files);
                } else {
                    try {
                        byte[] contenido = manager.getFile(fromClient);
                        outputStream.writeObject(contenido);

                    } catch (FileNotFoundException e) {
                        outputStream.writeObject("Ese archivo no existe");
                    }
                }
                //
                //System.out.println(super.getName() + ":" + super.getId() + " envio " + contenido);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String uso() {
        String out = "Escribe el nombre completo del archivo para ver su contenido\n";
        out += "Escribe 'q' para salir\n";
        out += "Escribe 'ls' para ver los archivos\n";
        out += Arrays.toString(manager.getFilesNames());
        out += "\n";
        return out;
    }
}

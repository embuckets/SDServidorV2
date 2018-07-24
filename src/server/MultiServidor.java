package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServidor implements Runnable {

//    private ArrayList<ServidorHilo> myThreads;
    private int portNumber = 1111;

    public MultiServidor() {
//        myThreads = new ArrayList<ServidorHilo>();
//        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        boolean listening = true;
//        System.out.println("Escribe 'stop' para detener el servidor");
//        System.out.println("Escribe 'list' para ver la lista de hilos");
//        new KeyboardListener(this).start();
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            //new KeyboardListener().start();
            while (listening) {
                Socket newSocket = serverSocket.accept();
                ServidorHilo hilo = new ServidorHilo(newSocket);
                (new Thread(hilo)).start();
//                myThreads.add(hilo);
//                hilo.start();
                //new ServidorHilo(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("No se puede conectar al puerto " + portNumber);
        }
    }
//
//    public void detener() {
//        System.exit(0);
//    }
//
//    public String listThreads() {
//        String threads = "";
//        for (ServidorHilo thread : myThreads) {
//            threads += thread.getName() + ":" + thread.getId() + ":" + thread.getState() + "\n";
//        }
//        return threads;
//    }
}

//class KeyboardListener extends Thread {
//
//    private MultiServidor servidor;
//
//    public KeyboardListener(MultiServidor servidor) {
//        this.servidor = servidor;
//    }
//
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        String line;
//        while ((line = scanner.nextLine()) != null) {
//            if (line.equalsIgnoreCase("stop")) {
//                servidor.detener();
//                break;
//            } else if ((line.equalsIgnoreCase("list"))) {
//                System.out.println(servidor.listThreads());
//            }
//        }
//    }
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import server.MultiServidor;
import server.ServidorRMI;
import server.ServidorRPC;

/**
 *
 * @author emilio
 */
public class Main {

    public static void main(String[] args) {
        (new Thread(new MultiServidor())).start();
        (new Thread(new ServidorRPC())).start();
        (new Thread(new ServidorRMI())).start();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import resources.FileManagerInterface;
import resources.FileManagerRMI;

import java.net.MalformedURLException;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emilio
 */
public class ServidorRMI implements Runnable {

    @Override
    public void run() {
        try {

//            FileManagerInterface fileManager = (FileManagerInterface) UnicastRemoteObject.exportObject(new FileManagerRMI(), 0);
            FileManagerInterface fileManager = new FileManagerRMI();
            Registry registry = LocateRegistry.createRegistry(1099);
//            System.setProperty("java.rmi.server.hostname","127.0.0.1");
//            UnicastRemoteObject.exportObject(calc, 1099);
            Naming.bind("rmi://localhost:1099/fileservice", fileManager);
//            registry.bind("rmi://localhost:1099/filemanager", fileManager);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

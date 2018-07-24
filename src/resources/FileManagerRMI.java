/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emilio
 */
public class FileManagerRMI extends UnicastRemoteObject implements FileManagerInterface {

    private static String PATH = "etc/";

    public FileManagerRMI() throws RemoteException {
        super();
    }
    
    

    @Override
    public String[] getFilesNames() throws RemoteException {
        List<String> results = new ArrayList<String>();
        File directory = new File(PATH);
        File[] fileNames = directory.listFiles();
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].isFile()) {
                results.add(fileNames[i].getName());
            }
        }
        return results.toArray(new String[results.size()]);
    }

    @Override
    public byte[] getFile(String name) throws RemoteException {
        byte[] filebytes = null;
        String path = PATH + name;
        FileInputStream fileInput = null;
        BufferedInputStream bufferedInput = null;
        try {
            File file = new File(path);
            fileInput = new FileInputStream(file);
            bufferedInput = new BufferedInputStream(fileInput);
            filebytes = new byte[(int) file.length()];
            bufferedInput.read(filebytes, 0, filebytes.length);

        } catch (FileNotFoundException e) {
//            throw e;
            return null;

        } catch (IOException ex) {
//            throw ex;
            return null;
        }
        return filebytes;
    }

}

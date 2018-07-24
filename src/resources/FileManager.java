package resources;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private String pathName; //ruta relativa directorio de archivos
    private static FileManager instance; //singleton
    private static String PATH = "etc/";

    public FileManager() {
        this.pathName = PATH;
    }

//    public static FileManager getInstance() {
//        if (instance == null) {
//            instance = new FileManager(PATH);
//        }
//        return instance;
//    }
    public String[] getFilesNames() {
        List<String> results = new ArrayList<String>();
        File directory = new File(pathName);
        File[] fileNames = directory.listFiles();
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].isFile()) {
                results.add(fileNames[i].getName());
            }
        }
        return results.toArray(new String[results.size()]);
    }

    //throws FileNotFoundException, IOException
    public byte[] getFile(String name) {
        byte[] filebytes = null;
        String path = pathName + name;
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
//        finally {
//            if (bufferedInput != null) {
//                bufferedInput.close();
//            }
//            if (fileInput != null) {
//                fileInput.close();
//            }
//        }
        return filebytes;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}

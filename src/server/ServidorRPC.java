/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import resources.FileManager;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
/**
 *
 * @author emilio
 */
public class ServidorRPC implements Runnable {
    private static int port = 2222;

    @Override
    public void run() {
        try {
            WebServer webServer = new WebServer(port);
            XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
            
            PropertyHandlerMapping propertyMapper = new PropertyHandlerMapping();
            propertyMapper.addHandler("FileManager", FileManager.class);
            
            xmlRpcServer.setHandlerMapping(propertyMapper);
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
            serverConfig.setEnabledForExceptions(true);
            serverConfig.setEnabledForExtensions(true);
            serverConfig.setContentLengthOptional(false);
            
            webServer.start();
        } catch (XmlRpcException ex) {
            Logger.getLogger(ServidorRPC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorRPC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.app.taller2.arep.webapps;

import edu.escuelaing.app.taller2.arep.HttpServer;
import edu.escuelaing.app.taller2.arep.services.*;
import java.io.IOException;

/**
 *
 * @author cv100
 */
public class App {

    public static void main(String[] args) throws IOException {
        
        HttpServer server = HttpServer.getInstance();
        server.addService("/index.html", new RestHtmlService());
        server.addService("/app.js", new RestJsService());
        server.addService("/styles.css", new RestCssService());
        server.addService("/imagen.jpg", new RestJpgService());
        server.addService("/page.html", new RestPageService());
        server.addService("/404", new RestNotFoundService());
        server.main(args);
    }
}

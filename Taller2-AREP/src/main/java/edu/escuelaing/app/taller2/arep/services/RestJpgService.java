/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.app.taller2.arep.services;

import edu.escuelaing.app.taller2.arep.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 *
 * @author cv100
 */
public class RestJpgService implements RestService {
    
    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getResponse() throws IOException {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/jpg\r\n"
                + "\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/imagen.jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;
    }



}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.app.taller2.arep.services;

/**
 *
 * @author cv100
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RestPageService implements RestService {
    
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 \r\n"
               + "Content-Type: text/html \r\n"
               + "\r\n";
    }

    @Override
    public String getResponse() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/page.html"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }
}

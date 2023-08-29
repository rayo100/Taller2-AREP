/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.app.taller2.arep.services;

import java.io.IOException;
/**
 *
 * @author cv100
 */

public interface RestService {

    /**
     * Method that gets the header of the file
     * @return Header in String
     */
    public String getHeader();

    /**
     * Method that gets the Body of the file
     * @return Body in String
     */
    public String getResponse() throws IOException;

}

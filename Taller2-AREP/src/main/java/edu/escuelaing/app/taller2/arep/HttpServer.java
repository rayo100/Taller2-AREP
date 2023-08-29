/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.app.taller2.arep;

/**
 *
 * @author cv100
 */
import edu.escuelaing.app.taller2.arep.services.RestService;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class HttpServer {
    
    private static  HttpServer _instance = new HttpServer();
    
    private static Map<String, RestService> services = new HashMap<>();
    
    private OutputStream outputStream = null;

    public void main(String[] args) throws IOException {

        String urlTitle = "";
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
            String inputLine, outputLine, path = "/simple";
            Boolean firstLine = true;
            outputStream = clientSocket.getOutputStream();

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if(inputLine.contains("hello?name=")){
                    String[] res = inputLine.split("name=");
                    urlTitle = (res[1].split("HTTP")[0]).replace(" ", "");
                }
                if (firstLine) {
                    path = inputLine.split(" ")[1];
                    firstLine = false;
                }

                if (!in.ready()) {
                    break;
                }
            }
            if (path.startsWith("/apps/")) {
                outputLine = executeService(path.substring(5));
            }else if(!urlTitle.equals("")){
                String response = HttpConnection.requestTitle(urlTitle);
                outputLine ="HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 1 \"> \n " + createTable(response)
                        + "</table>";
            }else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + getIndexResponse();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
    
    /**
     * Method that creates a table in String(JSON) format
     * @param response String movie information
     * @return String(JSON)
     */
    private static String createTable(String response){
        HashMap<String,String> dictionary = new HashMap<String, String>();
        JSONArray jsonArray = new JSONArray(response);
        for (int i=0; i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            for (String key: object.keySet()) {
                dictionary.put(key.toString(), object.get(key).toString());
            }
        }
        String table = "<tr> \n";
        for (String keys: dictionary.keySet()){
            String value = dictionary.get(keys);
            table += "<td>" + keys + "</td>\n";
            table += "<td>" + value + "</td>\n";
            table += "<tr> \n";
        }
        return table;
    }

    /**
     * Method createv view html
     * @return view html
     */
    public static String getIndexResponse(){
        return "<!DOCTYPE html>\n" 
                +"<html>\n" 
                +"    <head>\n"
                +"        <center>\n"
                +"            <title>Search Films</title>\n" 
                +"            <meta charset=\"UTF-8\">\n" 
                +"            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                +"        </center>\n"
                +"    </head>\n" 
                +"    <body>\n" 
                +"        <h1>Search Movies</h1>\n"
                +"        <form action=\"/hello\">\n"
                +"            <label for=\"name\">Name:</label><br>\n" 
                +"            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" 
                +"            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" 
                +"        </form> \n" 
                +"        <div id=\"getrespmsg\"></div>\n" 
                +"\n" 
                +"        <script>\n" 
                +"            function loadGetMsg() {\n" 
                +"                let nameVar = document.getElementById(\"name\").value;\n" 
                +"                if (nameVar) {\n" 
                +"                   console.log(\"Nombre \" + nameVar)\n" 
                +"                   const xhttp = new XMLHttpRequest();\n"
                +"                   xhttp.onload = function() {\n" 
                +"                       document.getElementById(\"getrespmsg\").innerHTML =\n" 
                +"                       this.responseText;\n" 
                +"                   }\n" 
                +"                   xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" 
                +"                   xhttp.send();\n" 
                +"                };\n" 
                +"            }\n" 
                +"        </script>\n" 
                +"\n" 
                +"    </body>\n" 
                +"</html>";
    }
    
    /**
     * Method that returns the server instance
     * @return Instance Server
     */
    public static HttpServer getInstance() {
        return _instance;
    }

    /**
     * Method that extracts the header and the body of the services
     * @param serviceName Service name
     * @return String service
     */
    public static String executeService(String serviceName) throws IOException {
        String body, header;
        if (services.containsKey(serviceName) ) {
            RestService rs = services.get(serviceName);
            header = rs.getHeader();
            body = rs.getResponse();
        } else {
            RestService rs = services.get("/404");
            header = rs.getHeader();
            body = rs.getResponse();
        }

        return header + body;
    }

    /**
     * Method to add services
     * @param key String key
     * @param service String value
     */
    public void addService(String key, RestService service) {
        services.put(key, service);
    }

    /**
     * Method that gets the OutputStream
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }
    
}
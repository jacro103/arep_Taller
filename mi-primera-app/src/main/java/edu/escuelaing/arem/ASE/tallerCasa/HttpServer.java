package edu.escuelaing.arem.ASE.tallerCasa;

import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class HttpServer {
    
    private static HTTPMovie apiConection = new HTTPMovie();
    private static ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<>();
    
    private static HashMap<String,Object> Data = new HashMap<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean fLine = true;
            String uriS = "";
            while ((inputLine = in.readLine()) != null) {
                
                System.out.println("Received: " + inputLine);
                if (fLine) {
                    fLine = false;
                    uriS = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (uriS.startsWith("/movie?")) {
                outputLine = getMovie(uriS);
            }else if(uriS.startsWith("/movie?name=Close") || uriS.startsWith("/moviepost?name=Close")){
                running = false;
                outputLine = getIndexResponse();
            }
            else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
            
    public static String getMovie(String uri) {
        String name = nameFixer(uri.split("=")[1]);
        String message;
        String httpList;
        if (cache.containsKey(name.toUpperCase())){
            message = cache.get(name.toUpperCase());
            movieSetter(message);
            httpList = info();
            return "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:  text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + " <head>\n"
                    + "     <meta charset=\"UTF-8\">\n"
                    + "     <title>List</title> \n"
                    + " </head>\n"
                    + " <body>\n"
                    + "     " + httpList
                    + " </body>\n"
                    +"</html>";
        }else{
            apiConection.movieSet(name);
            try {
                apiConection.run();
                message = apiConection.getMessage();
                movieSetter(message);
                httpList = info();
                cache.put(name.toUpperCase(),message);
                return  "HTTP/1.1 200 OK\r\n"
                        + "Content-Type:  text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + " <head>\n"
                        + "     <meta charset=\"UTF-8\">\n"
                        + "     <title>List</title> \n"
                        + " </head>\n"
                        + " <body>\n"
                        + "     " + httpList
                        + " </body>\n"
                        +"</html>";
            }catch (IOException x){
                x.printStackTrace();
            }
        }
        return "Error 404";
    }   
    
        /**
     * This method will extract the values of the dictionary where is located the json message
     * @return the part in html listing the data from the movie that is wnat to be show
     */
    public static String info(){
        String title = (String) Data.get("Title");
        String year = (String) Data.get("Year");
        String genre = (String) Data.get("Genre");
        String director = (String) Data.get("Director");
        String sinopsis = (String) Data.get("Plot");
        return "<ul>\n"
                +"  <li> Title: " + title + "</li>\n"
                +"  <li> Year: " + year + "</li>\n"
                +"  <li> Genre: " + genre + "</li>\n"
                +"  <li> Director: " + director + "</li>\n"
                + " <li> Sinopsis: " + sinopsis + "</li>\n"
                +"</ul>\n";
    }
            
    public static String getIndexResponse() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Movie Search </title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "       <style>\n" +
                "           body{\n" +
                "           label, input[type=\"text\"],input[type=\"button\"]{\n" +
                "           }"+
                "       </style>" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>MOVIE SEARCH </h1>\n" +
                "        <form action=\"/movie\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"Guardians of the galaxy\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/movie?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "        </script>\n" +
                "    </body>\n" +
                "</html>";
    }
    
     public static String nameFixer(String uriName){
        return uriName.replace("%20", " ");
    }

    /**
     * This method have the mission of mapping a json intro a Hashmap
     * @param jsonString Should be the String version of the JSON
     */
    public static void movieSetter(String jsonString){
        try{
            JSONObject jsonObj = new JSONObject(jsonString);
            Iterator<String> keys = jsonObj.keys();
            while (keys.hasNext()){
                String key = keys.next();
                Object value = jsonObj.get(key);
                Data.put(key,value);
            }
        }catch (Exception x){
            x.printStackTrace();
        }
    }

    public static HashMap<String, Object> getMovieData() {
        return Data;
    }



}


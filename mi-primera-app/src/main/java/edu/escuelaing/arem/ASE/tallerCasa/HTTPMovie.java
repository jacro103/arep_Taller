package edu.escuelaing.arem.ASE.tallerCasa;

/**
 *
 * @author josea
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
public class HTTPMovie {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "c19ff813"; //needed for the api to work
    private static final String GET_URL = "http://www.omdbapi.com/";

    private static  String movieName = "Guardians of the galaxy";

    private static String responseString = "Fire" ;

    public HTTPMovie(){
        movieName = "Guardians of the galaxy";
    }
    public static void main(String[] args) throws IOException {
        URL obj = new URL(fullUrlBuilder());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        System.out.println(fullUrlBuilder());

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            responseString = response.toString();
            System.out.println(responseString);

        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
    }

    public static void execute() throws IOException{
        URL obj = new URL(fullUrlBuilder());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            responseString = response.toString();
        }
    }

    /**
     * This method is in charge o
     * @return
     */
    public static String fullUrlBuilder(){
        return GET_URL + "?t=" + movieName + "&apikey=" + API_KEY;
    }

    /**
     *
     * @param name
     * @return the encode of the movie name this is just to make sure that the url at least in te name part will be okay
     *This method is built just to make a dinamyc way of using the API, changing the name of the movie i want to search,
     * encoding the name of the movie doesnt mean tht will work but at least is a way to having right
     */
    public static void movieNameSetter(String name) {
        movieName = name;
        String movieNameUrl = null;
        try {
            movieNameUrl = URLEncoder.encode(movieName, "UTF-8");
        } catch (UnsupportedEncodingException x) {
            x.printStackTrace();
        }
        movieName = movieNameUrl;
    }

    public String getMovieName(){
        return movieName;
    }

    /**
     *
     * @return the json casted to string
     */
    public static String getMessage(){
        return responseString.toString();
    }

}

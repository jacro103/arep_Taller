package edu.escuelaing.arem.ASE.tallerCasa;

import java.io.IOException;

public class main {
       public static void main(String[] args) {
        try {
            HttpServer.startSever();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

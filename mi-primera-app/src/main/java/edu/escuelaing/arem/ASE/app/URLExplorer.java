/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.arem.ASE.app;

import java.net.URL;


/**
 *
 * @author jose.correa-r
 */
public class URLExplorer {
    public static void main(String[] args) throws Exception {
        URL myurl = new URL("http://www.google.com/");
        System.out.print("host:" + myurl.getHost());
        System.out.print("Authority:" + myurl.getAuthority());
        System.out.print("Path:" + myurl.getPath());
        System.out.print("Protocol:" + myurl.getProtocol());
        System.out.print("Port:" + myurl.getPort());
        System.out.print("Query:" + myurl.getQuery());
        System.out.print("Ref.:" + myurl.getRef());
        System.out.print("filet:" + myurl.getFile());
    }


    
}

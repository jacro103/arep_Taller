/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.arem.ASE.tallerCasa;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPApiMovieTest {

    @Test
    public void ShouldFormatTheName(){
        HTTPMovie httpApiConection = new HTTPMovie();
        httpApiConection.movieNameSetter("Guardians of the galaxy");
        String name = httpApiConection.getMovieName();
        assertEquals("Guardians+of+the+galaxy",name);
    }
     @Test
    public void ShouldMkeTheRightUrl(){
        HTTPMovie httpApiConection = new HTTPMovie();
        httpApiConection.movieNameSetter("star" + " " + "wars");
        String url = httpApiConection.fullUrlBuilder();
        assertEquals("http://www.omdbapi.com/?t=star+wars&apikey=c19ff813", url);
    }
}

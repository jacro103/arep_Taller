/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.escuelaing.arem.ASE.app;



/**
 *
 * @author josea
 */
import edu.escuelaing.arem.ASE.tallerCasa.HTTPMovie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPApiMovieTest {

        @Test
    public void ShouldMkeTheRightUrl(){
        HTTPMovie httpApiConection = new HTTPMovie();
        httpApiConection.movieNameSetter("Fast" + " " + "and" + " " + "Furious");
        String url = httpApiConection.fullUrlBuilder();
        assertEquals("http://www.omdbapi.com/?t=Fast+and+Furious&apikey=c19ff813", url);
    }

}

package edu.escuelaing;

import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonObject;

public class Cache {
        private ConcurrentHashMap<String, JsonObject> movieCache;
    private static Cache cache = null;



    public Cache(){
        movieCache = new ConcurrentHashMap<String,JsonObject>();
    }

 
    public static Cache getInstance(){
        if(cache == null){
            cache = new Cache();
        }

        return cache;
    }


    public JsonObject getMovie(String name){
        return movieCache.get(name);
    }

    public boolean movieInCache(String name){
        return movieCache.containsKey(name);
    }

    public void addMovieToCache(String name, JsonObject movieInfo){
        movieCache.putIfAbsent(name, movieInfo);
    }
}

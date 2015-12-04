package com.first.android.moviedatabase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cse on 29-11-2015.
 */
public class Movie {
    String title,year,release,runtime,genre,director,plot,poster,rating;

    Movie(String JsonMovie)throws JSONException{
        JSONObject movie=new JSONObject(JsonMovie);
        title=movie.getString("Title");
        year=movie.getString("Year");
        release=movie.getString("Released");
        runtime=movie.getString("Runtime");
        genre=movie.getString("Genre");
        director=movie.getString("Director");
        plot=movie.getString("Plot");
        poster=movie.getString("Poster");
        rating=movie.getString("imdbRating");
    }

    public String getTitle(){
        return title;
    }
    public String getYear(){
        return year;
    }
    public String getRelease(){
        return release;
    }
    public String getRuntime(){
        return runtime;
    }
    public String getGenre(){
        return genre;
    }
    public String getDirector(){
        return director;
    }
    public String getPlot(){
        return plot;
    }
    public String getPoster(){
        return poster;
    }
    public String getRating(){
        return rating;
    }
}

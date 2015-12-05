package com.first.android.moviedatabase;

import org.json.JSONException;
import org.json.JSONObject;


public class Movie {
    String title,release,runtime,genre,director,plot,poster,rating,votes;

    Movie(String JsonMovie)throws JSONException{
        JSONObject movie=new JSONObject(JsonMovie);
        title=movie.getString("Title");
        release=movie.getString("Released");
        runtime=movie.getString("Runtime");
        genre=movie.getString("Genre");
        director=movie.getString("Director");
        plot=movie.getString("Plot");
        poster=movie.getString("Poster");
        rating=movie.getString("imdbRating");
        votes=movie.getString("imdbVotes");
    }

    public String getTitle(){
        return title;
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
    public String getVotes(){return votes;}
}

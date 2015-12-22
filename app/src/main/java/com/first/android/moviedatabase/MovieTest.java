package com.first.android.moviedatabase;

/**
 * Created by cse on 30-11-2015.
 */
public class MovieTest{
    int poster;
    String title;
    MovieTest(int poster,String title){
        this.poster=poster;
        this.title=title;
    }

    public int getPoster(){
        return poster;
    }

    public String getTitle(){
        return title;
    }
}

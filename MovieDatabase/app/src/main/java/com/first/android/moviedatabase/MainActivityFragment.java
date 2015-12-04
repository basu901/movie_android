package com.first.android.moviedatabase;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String host="http://www.omdbapi.com/?t=";
    String details="&y=&plot=short&r=json";
    String[] urls = {host+"frozen+"+details,
            host+"Terminator+2"+details,
            host+"Avengers+"+details,
            host+"Dark+Knight+"+details,
            host+"Despicable+me+2"+details};
    ArrayList<MovieTest> myMovies=new ArrayList<>();


    public MainActivityFragment() {
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
       // FetchMovie fetchMovie=new FetchMovie();
       // fetchMovie.execute();
        populateMovie();
        populateGridView();

    }

    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
        inflater.inflate(R.menu.moviefragment, menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            //FetchMovie fetchmovie= new FetchMovie();
            //fetchmovie.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateMovie(){
        myMovies.add(new MovieTest(R.drawable.dark_knight_png,"The Dark Knight"));
        myMovies.add(new MovieTest(R.drawable.frozen_png,"Frozen"));
        myMovies.add(new MovieTest(R.drawable.terminator2_png,"Termiantor 2:Judgement Day"));
        myMovies.add(new MovieTest(R.drawable.megamind,"Megamind"));
        myMovies.add(new MovieTest(R.drawable.hot_fuzz_png,"Hot Fuzz"));
        myMovies.add(new MovieTest(R.drawable.despicable_me_2,"Despicable Me 2"));

    }

    public void populateGridView(){
        ArrayAdapter<MovieTest> adapter=new MyMovieTestAdapter();
        GridView gridView=(GridView)getActivity().findViewById(R.id.list_grid);
        gridView.setAdapter(adapter);
    }

    private class MyMovieTestAdapter extends ArrayAdapter<MovieTest>{
        private Context mContext;
        private ArrayList<MovieTest> movieData;

        public MyMovieTestAdapter(){
            super(getActivity(),R.layout.pop,myMovies);
            this.mContext=getActivity();
            this.movieData=myMovies;
        }
        public View getView(int position,View convertView,ViewGroup parent){
            View item=convertView;
            ImageView imageView;
            if(item==null){
                item=getActivity().getLayoutInflater().inflate(R.layout.pop,parent,false);
                MovieTest currentMovie=movieData.get(position);
                imageView=(ImageView)item.findViewById(R.id.pop_image);
                imageView.setLayoutParams(new GridView.LayoutParams(150,150));
                Picasso.with(mContext).load(currentMovie.getPoster()).into(imageView);
            }

            return item;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //String[] data={"something","something else","something","something else","something","something else"};
       /* Iterator<Movie> itr=myMovies.iterator();
        while(itr.hasNext()){
            Movie element=itr.next();
            Log.v(MainActivityFragment.class.getSimpleName(),"Title:"+element.getTitle());
        }
        ArrayAdapter<Movie> adapter=new MovieAdapter(getActivity(),R.layout.pop,myMovies);
        GridView gridView=(GridView)rootView.findViewById(R.id.list_grid);
        gridView.setAdapter(adapter);*/
        //List<String> dataShow =new ArrayList<String>(Arrays.asList(data));
        //ArrayAdapter<String> mdataAdapter =new ArrayAdapter<String>(getActivity(),R.layout.pop,R.id.pop_image,dataShow);
        //GridView listview=(GridView)rootView.findViewById(R.id.list_grid);
        //listview.setAdapter(mdataAdapter);
        return rootView;

    }


   /* public class MovieAdapter extends ArrayAdapter<Movie>{
        String LOG_TAG=MainActivityFragment.class.getSimpleName();
        Context mcontext;
        ArrayList<Movie> movie;
        public MovieAdapter(Context context,int ImageViewResourceId,ArrayList<Movie> movie){
            super(context,ImageViewResourceId);
            mcontext=context;
            this.movie=movie;
        }

        public View getView(int position,View convertView,ViewGroup parent){
            View view=convertView;
            Bitmap bitmap;
            if(view==null){
                view=getActivity().getLayoutInflater().inflate(R.layout.pop,parent,false);

            }
            Movie currentMovie=movie.get(position);
            ImageView imageView=(ImageView)view.findViewById(R.id.pop_image);
            DownloadImage downloadImage=new DownloadImage();
            downloadImage.execute(currentMovie.getPoster());
            bitmap=downloadImage.onPost();
            imageView.setImageBitmap(bitmap);
            Log.v(LOG_TAG, "The movie is: " + currentMovie.getTitle());
            Log.v(LOG_TAG,"Bitmap is: "+bitmap);

            return view;
        }
    }

    public class DownloadImage extends AsyncTask<String,Void,Void>{
        Bitmap bitmap;

        protected Void doInBackground(String...url) {
            /*String urlDisplay=url[0];
            Bitmap im_pos=null;
            try{
                InputStream in=new java.net.URL(urlDisplay).openStream();
                im_pos= BitmapFactory.decodeStream(in);
            }catch (Exception e){
                Log.e("Error obtaining image",e.getMessage());
                e.printStackTrace();
            }
            return im_pos;
        }*/
        /*    try {
                URL url_image = new URL(url[0]);
                HttpURLConnection connection = (HttpURLConnection) url_image.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public Bitmap onPost() {
            return bitmap;
        }
    }

    public class FetchMovie extends AsyncTask<String,Void,Void>{
        private final String LOG_TAG=MainActivityFragment.class.getSimpleName();


        public String getMovieData(String dataJSON)throws JSONException{
            JSONObject data=new JSONObject(dataJSON);
            String title=data.getString("Title");
            String year=data.getString("Year");
            String release=data.getString("Released");
            String runtime=data.getString("Runtime");
            String genre=data.getString("Genre");
            String director=data.getString("Director");
            String plot=data.getString("Plot");
            String poster=data.getString("Poster");
            String rating=data.getString("imdbRating");
            Log.v(LOG_TAG,"Title: "+title+"\nYear: "+year
            +"\nRelease: "+release+"\nRuntime: "+runtime+"\nGenre: "+genre
            +"\nDirector: "+director+"\nPlot: "+plot+"\nPoster: "+poster+"\nRating is:"+rating);
            return rating;
        }

        protected Void doInBackground(String...params){
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;

            ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

                for(int i=0;i<urls.length;i++)
                {

                        try
                        {
                            URL movieUrl = new URL(urls[i]);
                            urlConnection = (HttpURLConnection) movieUrl.openConnection();
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();
                            InputStream inputStream = urlConnection.getInputStream();
                            StringBuffer buffer = new StringBuffer();
                            if (inputStream == null) {
                                return null;
                            }

                            reader = new BufferedReader(new InputStreamReader(inputStream));
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                buffer.append(line + "\n");
                            }
                            if (buffer.length() == 0) {
                                return null;
                            }
                            movieJsonStr = buffer.toString();
                            Log.v(LOG_TAG, "Movie Info:" + movieJsonStr);

                        } catch (IOException e) {
                            Log.e("MainActivityFragment", "Error in network", e);
                            return null;
                        } finally{
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (final IOException e) {
                                Log.e("MainActivityFragment", "Error closing stream", e);
                            }
                        }/*
                        try {
                            getMovieData(movieJsonStr);
                            ;
                        } catch (JSONException e) {
                            Log.e(LOG_TAG, e.getMessage(), e);
                            e.printStackTrace();
                        }*/

                  /*  }
                    try{
                        myMovies.add(new Movie(movieJsonStr));
                    }catch(JSONException e){
                        Log.e(LOG_TAG,"Unable to convert to JSON Object\n");
                    }

                }
                return null;
            }
            else {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "Sorry! No access to the internet!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        return null;
        }

    }*/
}

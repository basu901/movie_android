package com.first.android.moviedatabase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String host="http://www.omdbapi.com/?t=";
    String details="&y=&plot=short&r=json";
    String[] urls = {host+"Frozen+"+details,
            host+"Megamind"+details,
            host+"The+Avengers+"+details,
            host+"The+Dark+Knight+"+details,
            host+"Despicable+me"+details,
            host+"The+Constant+Gardener"+details,
            host+"Blood+Diamond"+details,
            host+"12+Angry+Men"+details,
            host+"300"+details,
            host+"Up"+details,
            host+"The+Lion+King"+details,
            host+"Carnage"+details};
    ArrayList<Movie> myMovies=new ArrayList<>();


    public MainActivityFragment() {
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


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


    public void onStart(){
        super.onStart();
        updateMovie();
    }

    public void updateMovie(){
        FetchMovie fetchMovie=new FetchMovie();
        fetchMovie.execute();
    }

    public void populateGridView(){
        ArrayAdapter<Movie> adapter=new MyMovieTestAdapter();
        GridView gridView=(GridView)getActivity().findViewById(R.id.list_grid);
        gridView.setAdapter(adapter);
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyMovieTestAdapter testAdapter=(MyMovieTestAdapter)parent.getAdapter();
                Movie currentMovie=(Movie)testAdapter.getItem(position);
                Intent detail=new Intent(getActivity(),DetailActivity.class);
                Bundle extras=new Bundle();
                extras.putString("director",currentMovie.getDirector());
                extras.putString("released",currentMovie.getRelease());
                extras.putString("genre",currentMovie.getGenre());
                extras.putString("runtime",currentMovie.getRuntime());
                extras.putString("plot",currentMovie.getPlot());
                detail.putExtras(extras);
                startActivity(detail);
            }
        });
    }

    private class MyMovieTestAdapter extends ArrayAdapter<Movie>{
        private Context mContext;
        private ArrayList<Movie> movieData;

        public MyMovieTestAdapter(){
            super(getActivity(),R.layout.pop,myMovies);
            this.mContext=getActivity();
            this.movieData=myMovies;
        }
        public View getView(int position,View convertView,ViewGroup parent) {
            View item;
            ImageView imageView;
            if (convertView == null) {
                item = getActivity().getLayoutInflater().inflate(R.layout.pop, parent, false);
                final Movie currentMovie = movieData.get(position);
                imageView = (ImageView) item.findViewById(R.id.pop_image);
                //imageView.setLayoutParams(new GridView.LayoutParams(150,150));
                Picasso.with(mContext).load(currentMovie.getPoster()).into(imageView);
                return item;
            } else {
                return convertView;
            }
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


    public class FetchMovie extends AsyncTask<String,Void,Void> {
        private final String LOG_TAG=MainActivityFragment.class.getSimpleName();

        protected Void doInBackground(String...params){
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;

            ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

                for (int i = 0; i < urls.length; i++) {

                    try {
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
                        try {
                            myMovies.add(new Movie(movieJsonStr));
                            Log.v("Fragment class","Loaded Movie");
                        } catch (JSONException e) {
                            Log.e("LOG_TAG", "Error in fetching JSON Movie Object", e);
                        }

                    } catch (IOException e) {
                        Log.e("MainActivityFragment", "Error in network", e);
                        return null;
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (final IOException e) {
                                Log.e("MainActivityFragment", "Error closing stream", e);
                            }
                        }
                    }
                }
                Iterator<Movie> itr=myMovies.iterator();
                while(itr.hasNext()){
                    Movie element=itr.next();
                    Log.v("Fragment Movie:",element.getPoster());
                }
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

        protected void onPostExecute(Void result){
            populateGridView();
            Log.v("Fetch Movie class:","Finished Executing AsyncTask");
        }
    }

}

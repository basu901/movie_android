package com.first.android.moviedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent=getActivity().getIntent();
        if(intent!=null){
            Bundle extras=intent.getExtras();
            String director=extras.getString("director");
            String released=extras.getString("released");
            String genre=extras.getString("genre");
            String runtime=extras.getString("runtime");
            String plot=extras.getString("plot");
            ((TextView)rootView.findViewById(R.id.director_value)).setText(director);
            ((TextView)rootView.findViewById(R.id.released_value)).setText(released);
            ((TextView)rootView.findViewById(R.id.genre_value)).setText(genre);
            ((TextView)rootView.findViewById(R.id.runtime_value)).setText(runtime);
            ((TextView)rootView.findViewById(R.id.plot_value)).setText(plot);
        }
        return rootView;
    }
}

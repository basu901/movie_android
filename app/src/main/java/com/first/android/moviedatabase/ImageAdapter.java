package com.first.android.moviedatabase;

/**
 * Created by cse on 23-11-2015.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * Created by cse on 23-11-2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c){
        mContext=c;
    }

    public int getCount(){
        return Icon.length;
    }

    public Integer getItem(int position){
        //return null;
        return Icon[position];
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        View view;
        ImageView imageView;
       if (convertView==null) {
            imageView=new ImageView(mContext);
            /*//FrameLayout frame=(FrameLayout)findViewById(R.id.fragment);
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager=(WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            switch(metrics.densityDpi){
                case DisplayMetrics.DENSITY_LOW:imageView.setLayoutParams(new GridView.LayoutParams());
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:imageView.setLayoutParams(new GridView.LayoutParams(lowVal,lowVal));
                    break;
                case DisplayMetrics.DENSITY_HIGH:imageView.setLayoutParams(new GridView.LayoutParams(lowVal,lowVal));
                    break;
                default:imageView.setLayoutParams(new GridView.LayoutParams());
            }*/
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(0, 0, 0, 0);
        }
        else
            imageView=(ImageView)convertView;

        imageView.setImageResource(Icon[position]);
        return imageView;
            /*view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main, null);
            imageView = (ImageView) view.findViewById(R.id.image_for_grid);
            imageView.setImageResource(getItem(position));
            return view;*/
    }

    private Integer[] Icon={
            R.drawable.batman,
            R.drawable.mask40,
            R.drawable.batman,
            R.drawable.mask40,
            R.drawable.batman,
            R.drawable.mask40,
            R.drawable.batman,
            R.drawable.mask40

    };
}

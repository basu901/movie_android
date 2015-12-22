package com.first.android.moviedatabase;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cse on 23-12-2015.
 */
public class EvenImageView extends ImageView{
    public EvenImageView(Context context) {
        super(context);
    }

    public EvenImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EvenImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}


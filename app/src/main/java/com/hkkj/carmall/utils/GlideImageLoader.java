package com.hkkj.carmall.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.hkkj.carmall.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
    public GlideImageLoader(){
    }
    public GlideImageLoader(ImageView.ScaleType scaleType){
        this.scaleType = scaleType;
    }
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(scaleType);
        RequestOptions options = new RequestOptions()
                .error(R.drawable.black_background);
        Glide.with(context).load(path)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }
}

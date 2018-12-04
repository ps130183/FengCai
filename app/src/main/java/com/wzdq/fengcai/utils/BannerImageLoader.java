package com.wzdq.fengcai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.ps.glidelib.GlideImageLoader;
import com.ps.glidelib.GlideImageView;
import com.youth.banner.loader.DefaultImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by PengSong on 18/11/29.
 */

public abstract class BannerImageLoader implements ImageLoaderInterface<GlideImageView> {

    @Override
    public GlideImageView createImageView(Context context) {

        GlideImageView imageView = new GlideImageView(context);
        return imageView;
    }
}

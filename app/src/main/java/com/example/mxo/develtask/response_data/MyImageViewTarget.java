package com.example.mxo.develtask.response_data;


import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

public class MyImageViewTarget extends ImageViewTarget<Drawable> {

    public MyImageViewTarget(ImageView view) {
        super(view);
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link ImageView#setImageDrawable(Drawable)}.
     *
     * @param resource {@inheritDoc}
     */
    @Override
    protected void setResource(@Nullable Drawable resource) {
        view.setBackground(resource);
    }

    /**
     * Returns the current {@link Drawable} being displayed in the view using
     * {@link ImageView#getDrawable()}.
     */
    @Nullable
    @Override
    public Drawable getCurrentDrawable() {
        return view.getBackground();
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link ImageView#setImageDrawable(Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);

        view.setBackground(placeholder);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);

        view.setBackground(errorDrawable);
    }
}

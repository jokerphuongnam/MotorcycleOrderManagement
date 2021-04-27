package com.example.motorcycleordermanagement.binding;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageBinding {
    @BindingAdapter("path_image")
    public static void setImageByPath(ImageView imageView, String path) {
        if (path == null || path.isEmpty()) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(path);
            imageView.setImageURI(uri);
        }
    }
}

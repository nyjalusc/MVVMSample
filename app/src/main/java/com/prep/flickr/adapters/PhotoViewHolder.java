package com.prep.flickr.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prep.flickr.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView image;
    private PhotoListener photoListener;

    public PhotoViewHolder(@NonNull View itemView, PhotoListener photoListener) {
        super(itemView);

        image = itemView.findViewById(R.id.photo_image);
        this.photoListener = photoListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        photoListener.onPhotoClicked(getAdapterPosition());
    }
}

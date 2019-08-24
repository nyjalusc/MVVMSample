package com.prep.flickr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prep.flickr.R;
import com.prep.flickr.models.FlickrPhoto;

import java.util.List;

// TODO: Use the new RecylerView.ListAdapter for better performance. DiffUtil implementation becomes very easy.
public class PhotoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FlickrPhoto> mPhotos;
    private PhotoListener mPhotoListener;

    public PhotoRecyclerAdapter(PhotoListener mPhotoListener) {
        this.mPhotoListener = mPhotoListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photo_list_item, parent, false);
        return new PhotoViewHolder(view, mPhotoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FlickrPhoto item = mPhotos.get(position);

        if (holder instanceof PhotoViewHolder) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(item.getUrl())
                    .into(((PhotoViewHolder) holder).image);
        }
    }

    public void setPhotos(List<FlickrPhoto> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    @Nullable
    public FlickrPhoto getSelectedItem(int position) {
        if (mPhotos != null) {
            return mPhotos.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (mPhotos != null) {
            return mPhotos.size();
        }
        return 0;
    }
}

package com.example.colin.gwent_android_v2;

/**
 * Created by Colin on 7/16/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RecyclerViewImageAdapter extends RecyclerView.Adapter<RecyclerViewImageAdapter.ViewHolder> {
    private Bitmap[] images;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerViewImageAdapter(Context context, Bitmap[] imageArray) {
        this.mInflater = LayoutInflater.from(context);
        images = imageArray;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the button in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap image = images[position];
        holder.imageView.setImageBitmap(image);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return images.length;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

        // convenience method for getting data at click position
        public Bitmap getItem(int id) {
            return images[id];
        }

        // allows clicks events to be caught
        public void setClickListener(RecyclerViewImageAdapter.ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

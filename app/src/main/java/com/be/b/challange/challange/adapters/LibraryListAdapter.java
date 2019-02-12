package com.be.b.challange.challange.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.be.b.challange.R;
import com.be.b.challange.challange.MyAdapterListener;
import com.be.b.challange.challange.models.LibraryItemModel;
import com.be.b.challange.challange.ui.fragments.SoundsFragment;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.ViewHolder> {

    ArrayList<LibraryItemModel> mValues;

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public MyAdapterListener onClickListener;

    public LibraryListAdapter(Context context, ArrayList<LibraryItemModel> data, MyAdapterListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mValues = data;
        onClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_library_item, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.get().load(mValues.get(position).getImageURL()).into(holder.libraryListItemImageView);
        holder.libraryItemTitle.setText(mValues.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView libraryListItemImageView;
        TextView libraryItemTitle;

        ViewHolder(View itemView) {
            super(itemView);
            libraryListItemImageView = itemView.findViewById(R.id.libraryItemImage);
            libraryItemTitle=itemView.findViewById(R.id.libraryItemName);

            libraryItemTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.iconImageViewOnClick(v, getAdapterPosition());

                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            LibraryItemModel libraryItemModel = mValues.get( getAdapterPosition());
            Fragment myFragment;

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            if(libraryItemModel.getTitle().equals(new String("Bird Sounds"))){
                myFragment = new SoundsFragment("Kuş");
            }
            else if(libraryItemModel.getTitle().equals(new String("Piano Sounds"))){
                myFragment= new SoundsFragment("Piyano");
            }
            else{
                myFragment = new SoundsFragment("Doğa");
            }
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, myFragment).addToBackStack(null).commit();
        }
    }

    public LibraryItemModel getItem(int id) {
        return mValues.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
package com.be.b.challange.challange.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.be.b.challange.R;
import com.be.b.challange.challange.MyAdapterListener;
import com.be.b.challange.challange.models.LibraryItemModel;
import com.be.b.challange.challange.models.SoundItemModel;
import com.be.b.challange.challange.ui.activities.MainActivity;
import com.be.b.challange.challange.ui.fragments.MyFavoritesFragment;
import com.be.b.challange.challange.ui.fragments.SoundsFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import static android.media.ToneGenerator.MAX_VOLUME;

public class SoundsListAdapter extends RecyclerView.Adapter<SoundsListAdapter.ViewHolder> {

    ArrayList<SoundItemModel> data;
    private LayoutInflater mInflater;
    private SoundsListAdapter.ItemClickListener mClickListener;
    public MyAdapterListener onClickListener;
    boolean isSoundFragment;

    public SoundsListAdapter(Context context, MyAdapterListener listener,boolean isSoundsFragment) {
        this.mInflater = LayoutInflater.from(context);
        onClickListener = listener;
        this.isSoundFragment=isSoundsFragment;
        if(isSoundsFragment){
            data= MainActivity.sounds;
        }
        else{
            data=MainActivity.favoriteSounds;
        }
    }

    @Override
    public SoundsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_sounds_item, parent, false);
        return new SoundsListAdapter.ViewHolder(view);
    }
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final SoundsListAdapter.ViewHolder holder, final int position) {
        holder.favoriteToggleButton.setImageResource(data.get(position).getDrawableHeartId());
        holder.playPauseToggleButton.setImageResource(data.get(position).getDrawablePlayPauseId());
        holder.soundName.setText(data.get(position).getSoundName());
        holder.seekBar.setProgress(data.get(position).getSeekBarProgressInt());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView favoriteToggleButton;
        ImageView playPauseToggleButton;
        SeekBar seekBar;
        TextView soundName;

        ViewHolder(View itemView) {
            super(itemView);
            favoriteToggleButton = itemView.findViewById(R.id.favToggleButton);
            playPauseToggleButton = itemView.findViewById(R.id.playPauseToggleButton);
            seekBar = itemView.findViewById(R.id.seekBar);
            seekBar.setMax(100);
            soundName = itemView.findViewById(R.id.soundName);



            playPauseToggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.iconImageViewOnClick(v, getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    if(data.get(getAdapterPosition()).getDrawablePlayPauseId()==R.drawable.play_ic){
                        data.get(getAdapterPosition()).startMusic();
                        data.get(getAdapterPosition()).setDrawablePlayPauseId(R.drawable.pause_ic);
                    }
                    else{
                        data.get(getAdapterPosition()).setDrawablePlayPauseId(R.drawable.play_ic);
                        data.get(getAdapterPosition()).stopMusic();
                    }

                }
            });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar bar) {

                }
                public void onStartTrackingTouch(SeekBar bar) {
                }
                public void onProgressChanged(SeekBar bar, int paramInt, boolean paramBoolean) {
                    if(paramBoolean){
                        float volume = (float) (1 - (Math.log(MAX_VOLUME - paramInt) / Math.log(MAX_VOLUME)));
                        data.get(getAdapterPosition()).setSoundVolume(volume);
                        data.get(getAdapterPosition()).setSeekBarProgressInt(paramInt);
                    }

                }
            });

            favoriteToggleButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    notifyItemChanged(getAdapterPosition());
                    if(isSoundFragment){
                        if(data.get(getAdapterPosition()).getDrawableHeartId()==R.drawable.fav_empty_ic){
                            data.get(getAdapterPosition()).setDrawableHeartId(R.drawable.heart_filled_ic);
                            data.get(getAdapterPosition()).setFavorite(true);
                            MainActivity.favoriteSounds.add(data.get(getAdapterPosition()));
                        }
                        else{
                            data.get(getAdapterPosition()).setDrawableHeartId(R.drawable.fav_empty_ic);
                            data.get(getAdapterPosition()).setFavorite(false);
                            MainActivity.favoriteSounds.remove(data.get(getAdapterPosition()));
                        }
                    }
                    else{
                        data.get(getAdapterPosition()).setDrawableHeartId(R.drawable.fav_empty_ic);
                        data.get(getAdapterPosition()).setFavorite(false);
                        MainActivity.sounds.get(getAdapterPosition()).setFavorite(false);
                        data.remove(getAdapterPosition());

                    }
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public SoundItemModel getItem(int id) {
        return data.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(SoundsListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
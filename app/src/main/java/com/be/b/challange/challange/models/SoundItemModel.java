package com.be.b.challange.challange.models;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import com.be.b.challange.R;

import java.io.IOException;
import java.io.Serializable;

public class SoundItemModel implements Serializable {
    String soundName;
    String soundFileName;
    MediaPlayer mediaPlayer;
    Context context;
    int drawableHeartId;
    int drawablePlayPauseId;
    int seekBarProgressInt;
    boolean isFavorite;
    int seekBarId;
    float soundVolume;
    AssetFileDescriptor afd;


    public SoundItemModel(){

    }

    public SoundItemModel(String soundName, String soundFileName, Context context, int soundVolume){
        this.soundName = soundName;
        this.soundFileName=soundFileName;
        this.context=context;
        seekBarProgressInt=100;
        this.isFavorite=false;
        this.soundVolume=soundVolume;
        this.mediaPlayer = new MediaPlayer();
        try {
            afd = this.context.getAssets().openFd(soundFileName);
            this.mediaPlayer.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(),  afd.getLength() );
            this.mediaPlayer.prepare();
            this.mediaPlayer.setVolume(soundVolume,soundVolume);
        } catch (IOException e) {

        }

        this.drawableHeartId =(R.drawable.fav_empty_ic);
        this.drawablePlayPauseId = (R.drawable.play_ic);
        this.seekBarId = R.id.seekBar;
    }

    public void startMusic(){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();

        }
    }
    public void stopMusic(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getDrawableHeartId() {
        return drawableHeartId;
    }

    public int getDrawablePlayPauseId() {
        return drawablePlayPauseId;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setDrawableHeartId(int drawableHeartId) {
        this.drawableHeartId = drawableHeartId;
    }

    public void setDrawablePlayPauseId(int drawablePlayPauseId) {
        this.drawablePlayPauseId = drawablePlayPauseId;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
        if(mediaPlayer!=null) {
            mediaPlayer.setVolume(soundVolume, soundVolume);
        }
    }

    public int getSeekBarProgressInt() {
        return seekBarProgressInt;
    }

    public void setSeekBarProgressInt(int seekBarProgressInt) {
        this.seekBarProgressInt = seekBarProgressInt;
    }

}

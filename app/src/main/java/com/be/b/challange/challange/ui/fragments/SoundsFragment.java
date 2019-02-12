package com.be.b.challange.challange.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.be.b.challange.R;
import com.be.b.challange.challange.MyAdapterListener;
import com.be.b.challange.challange.adapters.SoundsListAdapter;
import com.be.b.challange.challange.ui.activities.MainActivity;


@SuppressLint("ValidFragment")
public class SoundsFragment  extends android.support.v4.app.Fragment implements SoundsListAdapter.ItemClickListener, MyAdapterListener {
    SoundsListAdapter adapter;
    String soundType;

    @SuppressLint("ValidFragment")
    public SoundsFragment(String soundType){
        this.soundType=soundType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sounds, container, false);

        if(soundType.equals(new String("Kuş"))){
            MainActivity.sounds= MainActivity.birdSoundList;
        }
        else if(soundType.equals(new String("Piyano"))){
            MainActivity.sounds= MainActivity.pianoSoundList;

        }
        else if(soundType.equals(new String("Doğa"))){
            MainActivity.sounds= MainActivity.natureSoundList;

        }
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.soundList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new SoundsListAdapter(this.getActivity(), this,true);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void iconImageViewOnClick(View v, int position) {
    }
    @Override
    public void onItemClick(View view, int position) {
    }
}
package com.be.b.challange.challange.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.be.b.challange.R;
import com.be.b.challange.challange.MyAdapterListener;
import com.be.b.challange.challange.adapters.LibraryListAdapter;
import com.be.b.challange.challange.models.LibraryItemModel;
import com.be.b.challange.challange.ui.activities.MainActivity;

import java.util.ArrayList;

public class LibraryFragment extends android.support.v4.app.Fragment implements LibraryListAdapter.ItemClickListener, MyAdapterListener {

    LibraryListAdapter adapter;

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_library, container, false);
        ArrayList<LibraryItemModel> libraryListImages= new ArrayList();

        libraryListImages= MainActivity.libraryCategoryList;

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.libraryList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new LibraryListAdapter(this.getActivity(), libraryListImages, this);
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
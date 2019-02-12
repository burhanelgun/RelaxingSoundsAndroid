package com.be.b.challange.challange.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.be.b.challange.R;
import com.be.b.challange.challange.models.LibraryItemModel;
import com.be.b.challange.challange.models.SoundItemModel;
import com.be.b.challange.challange.ui.fragments.LibraryFragment;
import com.be.b.challange.challange.ui.fragments.MyFavoritesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public static ArrayList<SoundItemModel> birdSoundList;
    public static ArrayList<SoundItemModel> natureSoundList;
    public static ArrayList<SoundItemModel> pianoSoundList;
    public static ArrayList<LibraryItemModel> libraryCategoryList;
    public static ArrayList<SoundItemModel> favoriteSounds=new ArrayList<>();
    public static ArrayList<SoundItemModel> sounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        birdSoundList = getSoundsList(readJSON("library_birds.html"));
        natureSoundList = getSoundsList(readJSON("library_nature_sounds.html"));
        pianoSoundList=getSoundsList(readJSON("library_piano_sounds.html"));
        libraryCategoryList=getLibraryCategoriesList(readJSON("library_category_json.html"));

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = MyFavoritesFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = LibraryFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, LibraryFragment.newInstance());
        transaction.commit();
    }

    public String readJSON(String filename){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename)));
            String mLine;
            StringBuilder allLines=new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                allLines.append(mLine);
            }
            return allLines.toString();
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    ArrayList<SoundItemModel> getSoundsList(String strJson){
        ArrayList<SoundItemModel> soundItemModels=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJson);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String soundTitle = jsonObject1.optString("title");
                String soundSong = jsonObject1.optString("song");
                soundItemModels.add(new SoundItemModel(soundTitle,soundSong,getApplicationContext(),100));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return soundItemModels;
    }

    ArrayList<LibraryItemModel> getLibraryCategoriesList(String strJson){
        ArrayList<LibraryItemModel> libraryItemModels=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJson);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                int id = jsonObject1.optInt("id");
                String categoryTitle = jsonObject1.optString("title");
                String categoryImage = jsonObject1.optString("bgImage");
                libraryItemModels.add(new LibraryItemModel(categoryImage,categoryTitle));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return libraryItemModels;
    }
}

package com.example.a16443_if633_bl_uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.PluralRules;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class menu1 extends AppCompatActivity {

        private static final int REQUEST_PERMISSION = 99;

        ArrayList<Song> songArrayList;
        ListView lvsong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        
        lvsong = findViewById(R.id.lvsong);
        songArrayList = new ArrayList<>();
        SongAdapter songAdapter;
        songAdapter = new SongAdapter(this, songArrayList);
        lvsong.setAdapter(songAdapter);
        for (int i=1; i<=10; i++)
        songArrayList.add( new Song ("title 1" +i, "Artist 1"+i));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION );
            return;
        }else{
            getSong();

            lvsong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Song song = songArrayList.get(position);
                    Intent openMusicPlayer = new Intent(menu1.this, music.class);
                    openMusicPlayer.putExtra("title", song.getTitle());
                    openMusicPlayer.putExtra("artist", song.getArtist());
                    startActivity(openMusicPlayer);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSong();
            }
        }
    }

    private void getSong(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor songCursor = contentResolver.query(songUri,null,null,null,null);
        if (songCursor != null && ((Cursor) songCursor).moveToFirst()){
            int indexTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int indexArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do {
                String title = songCursor.getString(indexTitle);
                String artist = songCursor.getString(indexArtist);
                songArrayList.add(new Song(title,artist));
            }while (songCursor.moveToNext());
        }


    }
    






}
package com.example.a16443_if633_bl_uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {


    public SongAdapter(@NonNull Context context,  @NonNull List<Song> objects) {
        super(context, 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, null);

        TextView title = convertView.findViewById(R.id.title);
        TextView artist = convertView.findViewById(R.id.artist);


        Song song = getItem(position);
        artist.setText(song.getArtist());
        title.setText(song.getTitle());

        return convertView;

    }
}

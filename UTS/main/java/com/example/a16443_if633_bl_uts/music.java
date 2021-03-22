package com.example.a16443_if633_bl_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class music extends AppCompatActivity {
   TextView playerpos, playerduration, title, artist;
   SeekBar seekBar;
   ImageView play, rew, pause, next;

   MediaPlayer mediaPlayer;
   Handler handler = new Handler();
   Runnable runnable;

   @Override
    protected void onCreate(Bundle savedInstanceState){
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_music);

   Song song = (Song) getIntent().getSerializableExtra("song");

   playerpos = findViewById(R.id.playerpos);
   playerduration = findViewById(R.id.playerduration);
   seekBar = findViewById(R.id.seekbar);
   rew = findViewById(R.id.rew);
   play = findViewById(R.id.play);
   pause = findViewById(R.id.pause);
   next = findViewById(R.id.next);
   title = findViewById(R.id.title);
      artist = findViewById(R.id.artist);

      title.setText(song.getTitle());
      artist.setText(song.getArtist());
   mediaPlayer = MediaPlayer.create(this,R.raw.music);
      try {
         mediaPlayer.setDataSource(song.getTitle());
      } catch (IOException e) {
         e.printStackTrace();
      }

      runnable = new Runnable() {
      @Override
      public void run() {
         seekBar.setProgress(mediaPlayer.getCurrentPosition());
         handler.postDelayed(this, 500);

      }
   };

   int duration = mediaPlayer.getDuration();
   String sDuration = convertFormat(duration);
   playerduration.setText(sDuration);



   play.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         play.setVisibility(View.GONE);
         pause.setVisibility(View.VISIBLE);
         mediaPlayer.start();
         seekBar.setMax(mediaPlayer.getDuration());
         handler.postDelayed(runnable, 0);
      }
   });

   pause.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
         pause.setVisibility(View.GONE);
         play.setVisibility(View.VISIBLE);
         mediaPlayer.pause();
         handler.removeCallbacks(runnable);
      }
   });
      next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            int currentPossition = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (mediaPlayer.isPlaying() && duration != currentPossition){
               currentPossition = currentPossition + 5000;
               playerpos.setText(convertFormat(currentPossition));
               mediaPlayer.seekTo(currentPossition);
            }
         }
      });

      rew.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (mediaPlayer.isPlaying() && currentPosition > 5000) {

               currentPosition = currentPosition - 5000;
               playerpos.setText(convertFormat(currentPosition));
               mediaPlayer.seekTo(currentPosition);
            }
         }
      });

      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser){
               mediaPlayer.seekTo(progress);
            }
            playerpos.setText(convertFormat(mediaPlayer.getCurrentPosition()));
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
      });

      mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
         @Override
         public void onCompletion(MediaPlayer mp) {
            pause.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
            mediaPlayer.seekTo(0);
         }
      });
}


   @SuppressLint("DefaultLocale")
   private String convertFormat(int duration) {
      return String.format("%02d:%02d"
              ,TimeUnit.MILLISECONDS.toMinutes(duration)
              ,TimeUnit.MILLISECONDS.toSeconds(duration)-
              TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
   }
   }
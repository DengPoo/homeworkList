package com.example.tiktok;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.tiktok.player.VideoPlayerIJK;

import java.io.File;
import java.util.Locale;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class FullActivity extends AppCompatActivity {
    private final static int REQUEST_CAMERA = 123;
    private DataResponse data = new DataResponse();
    private VideoInfo info = new VideoInfo();
    private File imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        VideoPlayerIJK player = findViewById(R.id.full);
        ImageView plus = findViewById(R.id.plus);
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView likecount = findViewById(R.id.likecount);
        TextView collectioncount = findViewById(R.id.collectioncount);
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        TextView text3 = findViewById(R.id.text3);
        TextView text4 = findViewById(R.id.text4);


        Intent intent = this.getIntent();
        data._id = intent.getStringExtra("_id");
        data.feedurl = intent.getStringExtra("feedurl");
        data.nickname = intent.getStringExtra("nickname");
        data.description = intent.getStringExtra("description");
        data.likecount = intent.getLongExtra("likecount", 0);
        data.avatar = intent.getStringExtra("avatar");
        info.duration = intent.getLongExtra("duration", 0);
        info.width = intent.getIntExtra("width", 0);
        info.height = intent.getIntExtra("height", 0);
        Log.d("wecan:", data.toString());

        TextPaint tp1 = text1.getPaint();
        tp1.setFakeBoldText(true);
        text1.setText("首页");
        TextPaint tp2 = text2.getPaint();
        tp2.setFakeBoldText(true);
        text2.setText("同城");
        TextPaint tp3 = text3.getPaint();
        tp3.setFakeBoldText(true);
        text3.setText("消息");
        TextPaint tp4 = text4.getPaint();
        tp4.setFakeBoldText(true);
        text4.setText("我的");

        String nameBuffer = "@ " + data.nickname;
        TextPaint tp = name.getPaint();
        tp.setFakeBoldText(true);
        name.setText(nameBuffer);
        description.setText(data.description);

        likecount.setText(String.valueOf(data.likecount));
        collectioncount.setText(String.valueOf(data.likecount / 10));

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        player.setVideoPath(data.feedurl);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying())
                    player.pause();
                else player.start();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(FullActivity.this, Manifest.permission.CAMERA);
                if (permission == PackageManager.PERMISSION_GRANTED)
                    openSystemCamera();
                else {
                    Toast toast = Toast.makeText(FullActivity.this, "请求允许权限", Toast.LENGTH_SHORT);
                    toast.show();
                    ActivityCompat.requestPermissions(FullActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA);
                }
            }
        });
    }

    private void openSystemCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }
}

package com.bytedance.videoplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import com.bytedance.videoplayer.player.VideoPlayerIJK;
import com.bytedance.videoplayer.player.VideoPlayerListener;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CAMERA = 123;
    private VideoPlayerIJK ijkPlayer;
    private SeekBar sb;
    private ImageView plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        setContentView(R.layout.activity_main);
        ijkPlayer = findViewById(R.id.bilibili);
        sb = findViewById(R.id.seekBar);
        plus = findViewById(R.id.plus);

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        VideoPlayerListener listener = new VideoPlayerListener();
        ijkPlayer.setListener(listener);
        ijkPlayer.setVideoPath(getVideoPath());
        ijkPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ijkPlayer.isPlaying())
                    ijkPlayer.pause();
                else ijkPlayer.start();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                if (permission == PackageManager.PERMISSION_GRANTED)
                    openSystemCamera();
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "请求允许权限", Toast.LENGTH_SHORT);
                    toast.show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA);
                }
//                Intent intent = new Intent(MainActivity.this,AnotherActivity.class);
//                MainActivity.this.startActivity(intent);
//                finish();
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                long length = ijkPlayer.getDuration();
                ijkPlayer.seekTo(length / 100 * i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void openSystemCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    private String getVideoPath() {
        return "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4";
    }

    private void reFresh() {
        float length = ijkPlayer.getDuration();
        float position = ijkPlayer.getCurrentPosition();
        int part = (int) (position / length * 100);
        Log.d("wecan:", String.valueOf(part));
        //sb.setProgress(part);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ijkPlayer.isPlaying()) {
            ijkPlayer.stop();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}

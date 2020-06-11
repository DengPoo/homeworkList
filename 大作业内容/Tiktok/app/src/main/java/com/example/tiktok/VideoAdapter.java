package com.example.tiktok;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktok.player.VideoPlayerIJK;
import com.example.tiktok.player.VideoPlayerListener;

import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<DataResponse> mdata;
    private Context mcontext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View videoView;
        TextView textView;
        TextView length;
        ImageView allin;
        ImageView refresh;
        VideoPlayerIJK player;
        VideoPlayerListener listener;
//        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            videoView = view;
            textView = view.findViewById(R.id.nickname);
            length = view.findViewById(R.id.length);
//            imageView = view.findViewById(R.id.image);
            allin = view.findViewById(R.id.allin);
            refresh = view.findViewById(R.id.refresh);
            player = view.findViewById(R.id.bilibili);
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        }
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        DataResponse data = mdata.get(position);
//        Log.d("wecan:", String.valueOf(position));
        String name = "@ " + data.nickname;
        TextPaint tp0 = holder.textView.getPaint();
        tp0.setFakeBoldText(true);
        holder.textView.setText(name);

        holder.player.setVideoPath(data.feedurl);
        holder.player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.player.isPlaying())
                    holder.player.pause();
                else holder.player.start();
            }
        });

        holder.listener = new VideoPlayerListener();
        holder.player.setListener(holder.listener);

        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.player.seekTo(0);
                String time = String.valueOf(holder.listener.info.duration / 1000) + "s";
                TextPaint tp = holder.length.getPaint();
                tp.setFakeBoldText(true);
                holder.length.setText(time);
            }
        });

        holder.allin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mcontext, FullActivity.class);
                it.putExtra("_id", data._id);
                it.putExtra("feedurl", data.feedurl);
                it.putExtra("nickname", data.nickname);
                it.putExtra("description", data.description);
                it.putExtra("likecount", data.likecount);
                it.putExtra("avatar", data.avatar);
                it.putExtra("duration", holder.listener.info.duration);
                it.putExtra("width", holder.listener.info.width);
                it.putExtra("height", holder.listener.info.height);
                mcontext.startActivity(it);
            }
        });

//        Glide.with(holder.imageView.getContext())
//                .load(data.avatar)
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mdata == null ? 0 : mdata.size();
    }

    public VideoAdapter(Context context, List<DataResponse> dataList) {
        mdata = dataList;
        mcontext = context;
    }
}

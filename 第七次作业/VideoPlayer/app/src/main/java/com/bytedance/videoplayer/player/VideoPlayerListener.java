package com.bytedance.videoplayer.player;

import com.bytedance.videoplayer.VideoInfo;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoPlayerListener implements IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnSeekCompleteListener {

    public VideoInfo info;

    public VideoPlayerListener() {
        info = new VideoInfo();
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        System.out.println(i);
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        info.duration = iMediaPlayer.getDuration();
        info.width = iMediaPlayer.getVideoWidth();
        info.height = iMediaPlayer.getVideoHeight();
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}

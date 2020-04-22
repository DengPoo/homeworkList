package com.example.chapter3.homework;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private List<String> list = new ArrayList<>();
    private RecyclerView rv;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_placeholder, container, false);
        initRecycleView();
        Log.d("test", "this");
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        animationView = getView().findViewById(R.id.lottie_view);
        animationView.playAnimation();
        rv.setAlpha((float) 0);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.animate()
                        .alpha(0f)
                        .setDuration(800)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationView.setVisibility(View.GONE);
                            }
                        });

                rv.animate()
                        .alpha(1f)
                        .setDuration(800);
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            }
        }, 1000);
    }

    private void initRecycleView() {
        rv = v.findViewById(R.id.RV);
        initData();

        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(LayoutManager);
        MyAdapter mAdapter = new MyAdapter(list);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity()).build());
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("friend" + i);
        }
    }
}

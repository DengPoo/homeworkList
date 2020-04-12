package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private int num = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_2);

        View v=findViewById(R.id.scrollview);
        TextView txv;
        txv = (TextView) findViewById(R.id.textView);
        txv.setText("num:"+getAllChildViewCount(v));
    }

    //递归统计——如果统计到的View属于ViewGroup则调用递归
    public int getAllChildViewCount(View view) {
        int count = 0;
        if (view == null) return 0;
        if (view instanceof ViewGroup) {
            count++;
            for (int i = 0; i < (((ViewGroup) view).getChildCount()); i++) {
                View temp = ((ViewGroup) view).getChildAt(i);
                if (temp instanceof ViewGroup) count += getAllChildViewCount(temp);
                else count++;
            }
        }
        return count;
    }
}

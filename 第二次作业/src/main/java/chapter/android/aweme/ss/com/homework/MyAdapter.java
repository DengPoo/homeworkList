package chapter.android.aweme.ss.com.homework;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import chapter.android.aweme.ss.com.homework.model.Message;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Message> mDataset;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    public MyAdapter(List<Message> messages) {
        mDataset = messages;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.im_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (Objects.equals(mDataset.get(position).getIcon(), "TYPE_ROBOT")){
            holder.m_iv_avatar.setImageResource(R.drawable.session_robot);
        }
        else if(Objects.equals(mDataset.get(position).getIcon(), "TYPE_STRANGER")){
            holder.m_iv_avatar.setImageResource(R.drawable.session_stranger);
        }
        else if(Objects.equals(mDataset.get(position).getIcon(), "TYPE_SYSTEM")){
            holder.m_iv_avatar.setImageResource(R.drawable.session_system_notice);
        }
        else if(Objects.equals(mDataset.get(position).getIcon(), "TYPE_GAME")){
            holder.m_iv_avatar.setImageResource(R.drawable.icon_micro_game_comment);
        }

        if (mDataset.get(position).isOfficial() == true) {
            holder.m_robot_notice.setVisibility(View.VISIBLE);
        }
        holder.m_tv_title.setText(mDataset.get(position).getTitle());
        holder.m_tv_description.setText(mDataset.get(position).getDescription());
        holder.m_tv_time.setText(mDataset.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        /*ImageView mImage;
        TextView mTitleView;
        TextView mContentView;*/
        ImageView m_iv_avatar, m_robot_notice;
        TextView m_tv_title, m_tv_description, m_tv_time;


        public MyViewHolder(View itemRecyclerView) {
            super(itemRecyclerView);
            m_iv_avatar = itemRecyclerView.findViewById(R.id.iv_avatar);
            m_robot_notice = itemRecyclerView.findViewById(R.id.robot_notice);
            m_tv_title = itemRecyclerView.findViewById(R.id.tv_title);
            m_tv_description = itemRecyclerView.findViewById(R.id.tv_description);
            m_tv_time = itemRecyclerView.findViewById(R.id.tv_time);

            /*mImage = itemRecyclerView.findViewById(R.id.tv_image);
            mTitleView = (TextView)itemRecyclerView.findViewById(R.id.tv_item_number);
            mContentView = (TextView)itemRecyclerView.findViewById(R.id.tv_view_holder_instance);*/
        }
    }

    public static class test {
    }
}

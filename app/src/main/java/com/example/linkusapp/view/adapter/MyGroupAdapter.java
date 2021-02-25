package com.example.linkusapp.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkusapp.R;
import com.example.linkusapp.model.vo.Board;
import com.example.linkusapp.model.vo.LeaderGroup;
import com.example.linkusapp.viewModel.ManageJoinViewModel;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.MyGroupViewHolder> implements View.OnClickListener {

    private List<LeaderGroup> items;
    private SlidingUpPanelLayout slidingView;
    private ManageJoinViewModel viewModel;
    private GroupNameListener mListener = null;
    public MyGroupAdapter(List<LeaderGroup> items, SlidingUpPanelLayout slidingView, ManageJoinViewModel viewModel) {
        this.items = items;
        this.slidingView = slidingView;
        this.viewModel = viewModel;
    }

    public class MyGroupViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout groupLayout;
        private TextView groupName,joinReqCount;

        public MyGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupLayout = itemView.findViewById(R.id.group_layout);
            groupName = itemView.findViewById(R.id.my_group_name);
            joinReqCount = itemView.findViewById(R.id.request_cnt);
        }
    }

    public void updateItem(List<LeaderGroup> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_group,parent,false);
        return new MyGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGroupViewHolder holder, int position) {
            LeaderGroup leaderGroup = items.get(position);
            holder.groupName.setText(leaderGroup.getgName());
            holder.joinReqCount.setText(String.valueOf(leaderGroup.getReqCount()));
            holder.groupLayout.setTag(position);
            holder.groupLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onClick(View v) {
        String gName = items.get((Integer) v.getTag()).getgName();
        viewModel.getReqUser(gName);
        if(mListener!=null){
            mListener.returnGname(gName);
        }
    }

    public interface GroupNameListener{
        void returnGname(String gName);
    }
    public void setGnameListener(GroupNameListener listener){
        this.mListener = listener;
    }
}

package com.example.linkusapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkusapp.R;
import com.example.linkusapp.model.vo.Board;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private List<Board> boardList;
    private Context context;
    public BoardAdapter(List<Board> boardList, Context context){
        this.boardList = boardList;
        this.context = context;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        if (holder instanceof BoardAdapter.BoardViewHolder) {
            holder.onBind(position);
        }
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }


    public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        int position;
        private CardView cardView;
        private TextView gPart;
        private TextView gArea;
        private TextView gReader;
        private TextView gName;
        private TextView gPurpose;
        private TextView gJoinMethod;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.board_item);
            gPart = itemView.findViewById(R.id.g_part);
            gArea = itemView.findViewById(R.id.g_area);
            gReader = itemView.findViewById(R.id.g_reader);
            gName = itemView.findViewById(R.id.g_name);
            gPurpose = itemView.findViewById(R.id.g_purpose);
            gJoinMethod = itemView.findViewById(R.id.g_join_method);
        }

        public void onBind(int position) {
            Log.d("seq", "onBind: ");
            this.position = position;
            gPart.setText(boardList.get(position).getgPart());
            gArea.setText(boardList.get(position).getgArea());
            gReader.setText(boardList.get(position).getgReader());
            gName.setText(boardList.get(position).getgName());
            gPurpose.setText(boardList.get(position).getgPurpose());
            gJoinMethod.setText(boardList.get(position).getgJoinMethod());
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context.getApplicationContext(),));
        }
    }
}
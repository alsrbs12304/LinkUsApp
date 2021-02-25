package com.example.linkusapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.linkusapp.R;
import com.example.linkusapp.model.vo.Board;
import com.example.linkusapp.model.vo.Comment;
import com.example.linkusapp.view.adapter.CommentAdapter;
import com.example.linkusapp.viewModel.BoardViewModel;
import com.example.linkusapp.viewModel.CommentViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterMainGroupActivity extends AppCompatActivity {

    private ImageButton backBtn,settingBtn,sendCommentBtn;
    private TextView leaderTv,partTv,periodTv,noticeTv,groupNameTv;
    private RecyclerView commentRv;
    private CommentViewModel viewModel;
    private EditText commentEt;
    private CheckedTextView checkedSecret;
    /*비밀 댓글 여부 변수*/
    private boolean isSecret = false;
    /*댓글이 쓰이는 게시판 명*/
    private String gName;
    /*댓글 작성자*/
    private String writer;
    /*commentlist*/
    private List<Comment> commentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_main_group);

        backBtn = (ImageButton) findViewById(R.id.back_btn);
        sendCommentBtn =(ImageButton) findViewById(R.id.comment_send_button);
        settingBtn = (ImageButton) findViewById(R.id.setting_btn);
        leaderTv = (TextView) findViewById(R.id.leader_tv);
        partTv = (TextView) findViewById(R.id.part_tv);
        periodTv = (TextView) findViewById(R.id.period_tv);
        noticeTv =(TextView) findViewById(R.id.notice_tv);
        groupNameTv = (TextView) findViewById(R.id.group_name_tv);
        commentRv = (RecyclerView) findViewById(R.id.comment_rv);
        checkedSecret = (CheckedTextView) findViewById(R.id.chk_secret_write);
        commentEt = (EditText) findViewById(R.id.comment_edittext);

        viewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        Intent intent = getIntent();
        Board board = (Board)intent.getSerializableExtra("board2");
        gName = board.getgName();
        writer = viewModel.getUserInfoFromShared().getUserNickname();
        groupNameTv.setText(gName);
        leaderTv.setText("리더 : "+board.getgReader());
        partTv.setText("분야 : "+board.getgPart());
        periodTv.setText("기간 : "+board.getgStartDate()+" ~ "+board.getgEndDate());
        noticeTv.setText(board.getgPurpose());

        CommentAdapter commentAdapter = new CommentAdapter(commentList);
        commentRv.setAdapter(commentAdapter);
        commentRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        viewModel.getComment(gName);
        viewModel.getCommentRsLD.observe(this,commentInfo -> {
            if(commentInfo.getCode()==200){
                Snackbar.make(findViewById(R.id.enter_main_group_activity),"댓글을 불러왔습니다.",Snackbar.LENGTH_SHORT).show();
                commentAdapter.updateItem(commentInfo.getJsonArray());
            }else if(commentInfo.getCode()==204){
                Snackbar.make(findViewById(R.id.enter_main_group_activity),"댓글이 없습니다.",Snackbar.LENGTH_SHORT).show();
            }else {
                Snackbar.make(findViewById(R.id.enter_main_group_activity),"오류",Snackbar.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*비밀댓글 여부*/
        checkedSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckedTextView) view).isChecked()){
                    ((CheckedTextView) view).setChecked(false);
                    isSecret = false;
                }else {
                    ((CheckedTextView) view).setChecked(true);
                    isSecret =true;
                }
            }
        });
        sendCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = commentEt.getText().toString();
                if(comment.trim().equals(""))
                {
                    Snackbar.make(findViewById(R.id.enter_main_group_activity),"댓글을 입력해 주세요.",Snackbar.LENGTH_SHORT).show();
                }
                viewModel.insertComment(gName,writer,comment,isSecret);
            }
        });
        viewModel.insertCommentRsLD.observe(this,code ->{
            if(code.equals("200")){
                Snackbar.make(findViewById(R.id.enter_main_group_activity),"댓글 등록 완료",Snackbar.LENGTH_SHORT).show();
            }else{
                Snackbar.make(findViewById(R.id.enter_main_group_activity),"댓글 등록 실패",Snackbar.LENGTH_SHORT).show();
            }
        } );
    }
}
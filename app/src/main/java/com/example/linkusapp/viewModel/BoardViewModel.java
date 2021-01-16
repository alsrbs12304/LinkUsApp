package com.example.linkusapp.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.linkusapp.model.vo.AddressInfo;
import com.example.linkusapp.model.vo.BoardInfo;
import com.example.linkusapp.model.vo.BoardPartInfo;
import com.example.linkusapp.model.vo.BoardSearchInfo;
import com.example.linkusapp.model.vo.UserInfo;
import com.example.linkusapp.repository.RetrofitClient;
import com.example.linkusapp.repository.ServiceApi;
import com.example.linkusapp.util.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardViewModel extends AndroidViewModel {
    ServiceApi service;
    private SharedPreference prefs;

    public MutableLiveData<BoardInfo> boardRsLD = new MutableLiveData<BoardInfo>();
    public MutableLiveData<BoardPartInfo> boardPartRsLD = new MutableLiveData<BoardPartInfo>();
    public MutableLiveData<BoardSearchInfo> boardSearchRsLD = new MutableLiveData<BoardSearchInfo>();
    public MutableLiveData<BoardInfo> userGroupRsLD = new MutableLiveData<BoardInfo>();
    public MutableLiveData<BoardInfo> allAddressRsLD = new MutableLiveData<BoardInfo>();
    public MutableLiveData<BoardInfo> optionBoardRsLD = new MutableLiveData<BoardInfo>();


    public BoardViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient(application).create(ServiceApi.class);
        prefs = new SharedPreference(application);
    }

    public void getAllBoard(){
        service.getAllBoard().enqueue(new Callback<BoardInfo>() {
            @Override
            public void onResponse(Call<BoardInfo> call, Response<BoardInfo> response) {
                BoardInfo result = response.body();
                boardRsLD.postValue(result);
            }

            @Override
            public void onFailure(Call<BoardInfo> call, Throwable t) {

            }
        });
    }

    public void getPartBoard(String gPart){
        service.getPartBoard(gPart).enqueue(new Callback<BoardPartInfo>() {
            @Override
            public void onResponse(Call<BoardPartInfo> call, Response<BoardPartInfo> response) {
                BoardPartInfo result = response.body();
                Log.d("onResponse: ",result.toString());
                boardPartRsLD.postValue(result);
            }
            @Override
            public void onFailure(Call<BoardPartInfo> call, Throwable t) {

            }
        });
    }

    public void getSearchBoard(String keyword){
        service.getSearchBoard(keyword).enqueue(new Callback<BoardSearchInfo>(){

            @Override
            public void onResponse(Call<BoardSearchInfo> call, Response<BoardSearchInfo> response) {
                BoardSearchInfo result = response.body();
                boardSearchRsLD.postValue(result);
            }

            @Override
            public void onFailure(Call<BoardSearchInfo> call, Throwable t) {

            }
        });
    }
    public void userBoardAll(String userNickname){
        service.userBoardAll(userNickname).enqueue(new Callback<BoardInfo>() {
            @Override
            public void onResponse(Call<BoardInfo> call, Response<BoardInfo> response) {
                BoardInfo result = response.body();
                userGroupRsLD.postValue(result);
            }

            @Override
            public void onFailure(Call<BoardInfo> call, Throwable t) {

            }
        });
    }
    public void allAddress(){
        service.allAddress().enqueue(new Callback<BoardInfo>() {
            @Override
            public void onResponse(Call<BoardInfo> call, Response<BoardInfo> response) {
                BoardInfo result = response.body();
                allAddressRsLD.postValue(result);
            }

            @Override
            public void onFailure(Call<BoardInfo> call, Throwable t) {

            }
        });
    }
    public void optionBoard(String gPart,String address){
        service.optionBoard(gPart,address).enqueue(new Callback<BoardInfo>() {
            @Override
            public void onResponse(Call<BoardInfo> call, Response<BoardInfo> response) {
                BoardInfo result = response.body();
                optionBoardRsLD.postValue(result);
            }

            @Override
            public void onFailure(Call<BoardInfo> call, Throwable t) {

            }
        });
    }
}
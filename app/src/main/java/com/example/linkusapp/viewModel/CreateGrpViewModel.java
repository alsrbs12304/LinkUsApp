package com.example.linkusapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.linkusapp.model.vo.GroupMember;
import com.example.linkusapp.model.vo.User;
import com.example.linkusapp.model.vo.UserInfo;
import com.example.linkusapp.model.vo.UsersInfo;
import com.example.linkusapp.repository.RetrofitClient;
import com.example.linkusapp.repository.ServiceApi;
import com.example.linkusapp.util.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGrpViewModel extends BaseViewModel {
//    private ServiceApi service;
//    private SharedPreference prefs;

    public MutableLiveData<UserInfo> userLiveData = new MutableLiveData<UserInfo>();
    public MutableLiveData<String> createGroupRes = new MutableLiveData<>();
    public MutableLiveData<UsersInfo> memberCount = new MutableLiveData<>();
    public MutableLiveData<String> joinGroupRes = new MutableLiveData<>();
    public MutableLiveData<String> insertReqRes = new MutableLiveData<>();
    public MutableLiveData<String> chkGnameRes = new MutableLiveData<>();

    public CreateGrpViewModel(@NonNull Application application) {
        super(application);
//        this.service = RetrofitClient.getClient(application).create(ServiceApi.class);
//        this.prefs = new SharedPreference(application);
    }
    public void getUserInfo(){
        service.getUserInfo(prefs.getLoginMethod()).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo result = response.body();
                userLiveData.postValue(result);
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    public void chkGname(String gName){
        service.chkGroupName(gName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                chkGnameRes.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public void createGroup(String gName, String gExplanation, String gPart, String gPurpose, String gMemberLimit, String gStartDate, String gEndDate, String gJoinMethod){
        service.createGroup(gName,gExplanation,gPart,gPurpose,gMemberLimit,gStartDate,gEndDate,gJoinMethod,prefs.getLoginMethod()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                createGroupRes.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void getGroupMember(String gName){
        service.getGroupMember(gName).enqueue(new Callback<UsersInfo>() {
            @Override
            public void onResponse(Call<UsersInfo> call, Response<UsersInfo> response) {
                UsersInfo result = response.body();
                memberCount.postValue(result);
            }

            @Override
            public void onFailure(Call<UsersInfo> call, Throwable t) {

            }
        });
    }

    public void joinGroup(String gName, String gMemberId, String gMemberNick){
        service.joinGroup(gName,gMemberId,gMemberNick).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                joinGroupRes.postValue(result);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void requestJoin(String nickname,String userNick,String userAge, String userGender, String address ){
        service.requestJoin(nickname,userNick,userAge,userGender,address).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void insertRequest(String gName, String userNick){
        service.insertRequest(gName,userNick).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                insertReqRes.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }



}

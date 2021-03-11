package com.example.linkusapp.view.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkusapp.R;
import com.example.linkusapp.databinding.ItemAddressBinding;
import com.example.linkusapp.databinding.ItemMyChatRoomBinding;
import com.example.linkusapp.model.vo.UserAddress;
import com.example.linkusapp.viewModel.MyPageViewModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{
    private List<UserAddress> mDataset;
    private Activity getActivity;
    private MyPageViewModel viewModel;
    private String nickname;
    private AddressAdapter thisObject = this;

    /**/
    public class AddressViewHolder extends RecyclerView.ViewHolder {
        private ItemAddressBinding binding;

        public AddressViewHolder(@NonNull ItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setAdapter(thisObject);
        }

        void bind(UserAddress userAddress, int position) {
            binding.setAddress(userAddress);
            binding.setPosition(position);
        }
    }

    public void updateItem(List<UserAddress> items) {
        mDataset = items;
        notifyDataSetChanged();
    }

    public AddressAdapter(List<UserAddress> addressList, Activity getActivity, MyPageViewModel viewModel, String nickname) {
        mDataset = addressList;
        this.getActivity = getActivity;
        this.nickname = nickname;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAddressBinding binding = ItemAddressBinding.inflate(inflater, parent, false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        UserAddress address = mDataset.get(position);
        holder.bind(address, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void itemClickEvent(int position) {
        viewModel.updateAddress(nickname, mDataset.get(position).getAddress());
    }

    public void removeClickEvent(int position) {
        viewModel.removeAddress(mDataset.get(position).getAddress());
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
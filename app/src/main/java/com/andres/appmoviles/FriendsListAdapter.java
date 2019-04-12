package com.andres.appmoviles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andres.appmoviles.model.Friend;

import java.util.ArrayList;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.CustomViewHolder> {

    ArrayList<Friend> data;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;

        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public void showAllFriends(ArrayList<Friend> allFriends) {
        for (int i = 0; i < allFriends.size(); i++) {
            if (!data.contains(allFriends.get(i))) data.add(allFriends.get(i));
        }
        notifyDataSetChanged();
    }

    public FriendsListAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        ((TextView) holder.root.findViewById(R.id.item_list_name)).setText(data.get(position).getName());
        ((TextView) holder.root.findViewById(R.id.item_list_phone)).setText(data.get(position).getPhone());
        holder.root.findViewById(R.id.item_list_call_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Friend friend);
    }

    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}



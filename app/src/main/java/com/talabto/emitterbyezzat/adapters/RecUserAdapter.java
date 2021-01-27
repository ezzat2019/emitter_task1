package com.talabto.emitterbyezzat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talabto.emitterbyezzat.R;
import com.talabto.emitterbyezzat.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RecUserAdapter extends RecyclerView.Adapter<RecUserAdapter.UserVH> {
    public OnUserItemClick onUserItemClick;
    private List<UserModel>userModels=new ArrayList<>();

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
        notifyDataSetChanged();
    }

    public void setOnUserItemClick(OnUserItemClick onUserItemClick) {
        this.onUserItemClick = onUserItemClick;
    }

    public interface  OnUserItemClick{
        void onClick(int pos);
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new UserVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        holder.bindData(userModels.get(position));

    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    class  UserVH extends RecyclerView.ViewHolder{
        private TextView txt_user_name,txt_user_email;


        public UserVH(@NonNull View itemView) {
            super(itemView);
            init(itemView);

            actions(itemView);
        }
        void bindData(UserModel userModel)
        {
            txt_user_name.setText(userModel.getName());
            txt_user_email.setText(userModel.getEmail());
        }

        private void actions(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserItemClick.onClick(getAdapterPosition());
                }
            });
        }

        private void init(View v) {
            txt_user_email=v.findViewById(R.id.txt_user_email);
            txt_user_name=v.findViewById(R.id.txt_user_name);
        }
    }
}

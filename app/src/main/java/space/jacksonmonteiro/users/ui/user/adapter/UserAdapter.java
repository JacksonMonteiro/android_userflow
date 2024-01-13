package space.jacksonmonteiro.users.ui.user.adapter;
/*
Created By Jackson Monteiro on 11/01/2024
*/


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.jacksonmonteiro.users.R;
import space.jacksonmonteiro.users.models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);

        holder.tvName.setText(currentUser.getNome());
        holder.tvUsername.setText(currentUser.getUsername());
        holder.tvCpfCnpj.setText(currentUser.getCpfCnpj());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvUsername;
        private TextView tvCpfCnpj;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.nameValue);
            tvUsername = itemView.findViewById(R.id.usernameValue);
            tvCpfCnpj = itemView.findViewById(R.id.cpfCnpjValue);
        }
    }
}

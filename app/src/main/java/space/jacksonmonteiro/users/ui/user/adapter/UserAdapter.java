package space.jacksonmonteiro.users.ui.user.adapter;
/*
Created By Jackson Monteiro on 11/01/2024
*/


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.jacksonmonteiro.users.R;
import space.jacksonmonteiro.users.listeners.OnUserClickListener;
import space.jacksonmonteiro.users.models.User;
import space.jacksonmonteiro.users.utils.ImageUtil;
import space.jacksonmonteiro.users.utils.MaskUtil;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> users = new ArrayList<>();
    private OnUserClickListener listener;

    public UserAdapter(OnUserClickListener listener) {
        this.listener = listener;
    }

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

        if (currentUser.getCpfCnpj().length() == 11) {
            holder.tvCpfCnpj.setText(MaskUtil.maskCpf(currentUser.getCpfCnpj()));
            holder.tvCpfCnpjLabel.setText(R.string.cpf_label);
        } else {
            holder.tvCpfCnpjLabel.setText(R.string.cnpj_label);
            holder.tvCpfCnpj.setText(MaskUtil.maskCnpj(currentUser.getCpfCnpj()));
        }

        holder.profile.setImageBitmap(ImageUtil.convertBase64ToBitmap(currentUser.getFoto()));

        holder.edit.setOnClickListener(v -> {
            listener.gotoEdit(currentUser.getId());
        });

        holder.delete.setOnClickListener(v -> {
            listener.gotoDelete(currentUser.getId());
        });
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
        private TextView tvCpfCnpjLabel;
        private TextView tvCpfCnpj;
        private ImageView profile, edit, delete;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.nameValue);
            tvUsername = itemView.findViewById(R.id.usernameValue);
            tvCpfCnpj = itemView.findViewById(R.id.cpfCnpjValue);
            tvCpfCnpjLabel = itemView.findViewById(R.id.cpfCnpjLabel);
            profile = itemView.findViewById(R.id.profileImageItem);
            edit = itemView.findViewById(R.id.btnGotoEdit);
            delete = itemView.findViewById(R.id.btnGotoDelete);
        }
    }
}

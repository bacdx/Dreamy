package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.Comment;
import com.example.dreamy.R;
import com.squareup.picasso.Picasso;




import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;

    private ArrayList<com.example.dreamy.Model.Comment> list =new ArrayList<>();

    public CommentAdapter(Context context, ArrayList<Comment> list) {
        this.context = context;
        this.list = list;
    }
    public CommentAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment= list.get(position);
        byte[] bytes=Base64.decode(comment.getUriAvatar(),Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        holder.imgAvatar.setImageBitmap(bitmap);
        holder.tvName.setText(comment.getName());
        holder.tvComment.setText(comment.getComment());
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvName,tvComment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.avatar_item_comment);
            tvName=itemView.findViewById(R.id.tvName_itemComment);
            tvComment=itemView.findViewById(R.id.tvComment_itemComment);
        }
    }
}

package kg.geektrch.newapp38.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektrch.newapp38.databinding.ItemNewsBinding;
import kg.geektrch.newapp38.models.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> list = new ArrayList<>();
    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public void addItem(NewsModel newsModel) {
        list.add(0,newsModel);
        notifyItemInserted(list.indexOf(newsModel));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void removeItem(int position){
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public NewsModel getItem(int pos){
        return list.get(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;
        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(NewsModel newsModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClick.onLongClick(getAdapterPosition());
                    return true;
                }
            });
            if (getAdapterPosition() % 2 == 0){
                binding.textTitle.setBackgroundColor(Color.GRAY);
            }else {
                binding.textTitle.setBackgroundColor(Color.WHITE);
            }
            binding.textTitle.setText(newsModel.getTitle());
        }
    }
    interface OnClick {
        void onLongClick(int newsModel);
        void onClick(int newsModel);
    }
}
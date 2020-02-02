package com.example.bookaddict.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaddict.Model.Item;
import com.example.bookaddict.Model.VolumeInfo;
import com.example.bookaddict.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterSearchResults extends RecyclerView.Adapter<AdapterSearchResults.ItemBookViewHolder> {
    List<Item> items = new ArrayList<>();

    OnBookClickListener onRecyclerItemClickListener;
    Context context;

    public interface OnBookClickListener {
        void onClick(Item currItem);
    }

    public AdapterSearchResults() {

    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public AdapterSearchResults(Context context, OnBookClickListener listener) {
        this.context = context;
        this.onRecyclerItemClickListener = listener;
    }

    @NonNull
    @Override
    public ItemBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ItemBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBookViewHolder holder, int position) {
        Item item = items.get(position);
        VolumeInfo volumeInfo = item.getVolumeInfo();
        holder.txtv_title.setText(volumeInfo.getTitle());
        String[] authors = volumeInfo.getAuthors();
        StringBuilder sb = new StringBuilder();
        for (int i = 0,len = authors.length; i < len; i++) {
            if(i != len - 1){
                sb.append(authors[i] +", ");
            }else {
                sb.append(authors[i]+ ".");
            }
        }
        holder.txtv_author.setText(sb.toString());
        holder.txtv_page_count.setText(String.valueOf(volumeInfo.getPageCount()));
//        holder.txtv_review.setText(volumeInfo.get);

    }

    public void addBooks(List<Item> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public class ItemBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtv_title) TextView txtv_title;
        @BindView(R.id.txtv_subtitle) TextView txtv_subtitle;
        @BindView(R.id.txtv_author) TextView txtv_author;
        @BindView(R.id.txtv_page_count) TextView txtv_page_count;
        @BindView(R.id.txtv_review) TextView txtv_review;
        @BindView(R.id.imgv_small_thumbnail) ImageView imgv_small_thumbnail;

        public ItemBookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerItemClickListener.onClick(items.get(getAdapterPosition()));
        }
    }
}

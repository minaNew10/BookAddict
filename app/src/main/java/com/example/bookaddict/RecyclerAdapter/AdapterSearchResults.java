package com.example.bookaddict.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaddict.Model.Item;
import com.example.bookaddict.Model.VolumeInfo;
import com.example.bookaddict.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterSearchResults extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Item> items = new ArrayList<>();
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    // flag for footer ProgressBar (i.e. last item of list)
    private boolean isLoadingAdded = false;



    OnBookClickListener onRecyclerItemClickListener;
    Context context;

    private String errorMsg;

    public interface OnBookClickListener {
        void onClick(Item currItem);
    }

    public AdapterSearchResults() {

    }

    public List<Item> getItems() {
        return items;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case ITEM:
                viewHolder = getViewHolder(parent,inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress,parent,false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_book, parent, false);
        viewHolder = new ItemBookViewHolder(v1);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                ItemBookViewHolder itemBookViewHolder = (ItemBookViewHolder) holder;
                VolumeInfo volumeInfo = item.getVolumeInfo();
                itemBookViewHolder.txtv_title.setText(volumeInfo.getTitle());
                String[] authors = volumeInfo.getAuthors();
                StringBuilder sb = new StringBuilder();
                for (int i = 0, len = authors.length; i < len; i++) {
                    if (i != len - 1) {
                        sb.append(authors[i] + ", ");
                    } else {
                        sb.append(authors[i] + ".");
                    }
                }
                itemBookViewHolder.txtv_author.setText(sb.toString());
                itemBookViewHolder.txtv_page_count.setText(String.valueOf(volumeInfo.getPageCount()));
    //        holder.txtv_review.setText(volumeInfo.get);
                break;
            case LOADING:
                break;
        }

    }

    public void addBooks(List<Item> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addBook(Item book) {
        items.add(book);
        notifyItemInserted(items.size() - 1);
    }

    public void remove(Item book) {
        int position = items.indexOf(book);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));

        }
    }
    public boolean isEmpty() {
        return getItemCount() == 0;
    }
    public void addLoadingFooter() {
        isLoadingAdded = true;
        items.add(new Item());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = items.size() - 1;
        Item item = getItem(position);
        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Item getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == items.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
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
    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
              }

        @Override
        public void onClick(View view) {

        }
    }


}

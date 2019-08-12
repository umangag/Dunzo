package com.umang.dunzo;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umang.dunzo.model.SearchDTO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchListViewHolder> {

    private ArrayList<SearchDTO.Items> items;
    private CardClickListener cardClickListener;

    public SearchResultsAdapter(CardClickListener contactListener) {
        items = new ArrayList<>();
        this.cardClickListener = contactListener;
    }

    public void updateData(ArrayList<SearchDTO.Items> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchResultsAdapter.SearchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new SearchListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsAdapter.SearchListViewHolder searchListViewHolder, int i) {
        searchListViewHolder.bindWithData(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class SearchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgView)
        ImageView imgView;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtLink)
        TextView txtLink;

        @BindView(R.id.cvCard)
        CardView cvCard;


        public SearchListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cvCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.cvCard) {
                cardClickListener.onCardClicked(getAdapterPosition());
            }
        }

        public void bindWithData(SearchDTO.Items items) {

            if (items.pagemap == null) {
                cvCard.setVisibility(View.GONE);
                return;
            }
            cvCard.setVisibility(View.VISIBLE);

            if (items.pagemap.getCse_thumbnail() != null && !items.pagemap.getCse_thumbnail().isEmpty()) {
                Glide.with(imgView.getContext())
                        .load(items.pagemap.getCse_thumbnail().get(0).getSrc())
                        .into(imgView);
            }else {
                Glide.with(imgView.getContext())
                        .load(R.drawable.ic_accessibility_black_24dp)
                        .into(imgView);
            }

            txtTitle.setText(items.getTitle());

            if (items.pagemap.getMetatags() != null) {
                txtLink.setText(!TextUtils.isEmpty(items.pagemap.getMetatags().get(0).getTitle()) ? items.pagemap.getMetatags().get(0).getTitle() : items.pagemap.getMetatags().get(0).getTwitterTitle());
            } else {
                txtLink.setText(items.getTitle());
            }
        }
    }

    public interface CardClickListener {

        void onCardClicked(int position);

    }

}
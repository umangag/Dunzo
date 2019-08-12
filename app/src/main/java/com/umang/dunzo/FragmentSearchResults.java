package com.umang.dunzo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umang.dunzo.model.SearchDTO;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class FragmentSearchResults  extends Fragment implements SearchResultsAdapter.CardClickListener {

    @BindView(R.id.searchCards)
    RecyclerView searchCards;

    SearchResultsAdapter searchResultsAdapter;

    SearchDTO searchDTO;

    public static FragmentSearchResults getFragment(SearchDTO searchDTO) {
        FragmentSearchResults fragmentSearchResults = new FragmentSearchResults();
        Bundle bundle = new Bundle();
        bundle.putParcelable("searchDTO", searchDTO);
        fragmentSearchResults.setArguments(bundle);
        return fragmentSearchResults;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        searchDTO = getArguments().getParcelable("searchDTO");

        searchResultsAdapter = new SearchResultsAdapter(this);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        searchCards.setLayoutManager(linearVertical);
        searchCards.setAdapter(searchResultsAdapter);
        searchResultsAdapter.updateData(searchDTO.getItems());
    }

    @Override
    public void onCardClicked(int position) {
        ((MainActivity) Objects.requireNonNull(this.getActivity())).handleViews(Constant.SEARCH_DETAILS_SCREEN, position);
    }
}


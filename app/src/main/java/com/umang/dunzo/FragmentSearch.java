package com.umang.dunzo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class FragmentSearch extends Fragment {


    @BindView(R.id.etSearch)
    EditText searchText;

    OnSearchClicked onSearchClicked;

    public static FragmentSearch getFragment() {
        return new FragmentSearch();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnClick({R.id.card_search})
    public void onClick(View view) {

        if (view.getId() == R.id.card_search) {

            if (TextUtils.isEmpty(searchText.getText().toString())) {
                searchText.setError("Search can't be empty");
                return;
            }

            try {
                ((OnSearchClicked) getActivity()).searchClicked(searchText.getText().toString().trim());
            } catch (ClassCastException cce) {
//HAndle catch
            }
        }

    }

    interface OnSearchClicked {
        void searchClicked(String queryString);
    }
}

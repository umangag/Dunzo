package com.umang.dunzo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.umang.dunzo.model.SearchDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ApiResponse, FragmentSearch.OnSearchClicked {

    @BindView(R.id.flLayout)
    FrameLayout flLayout;

    private ApiManager apiManager;

    public SearchDTO searchDTO;
    public SearchDTO.Items clickedItem;

    SearchFlowAdapter searchFlowAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this);
        searchFlowAdapter = new SearchFlowPagerAdapter();
        searchFlowAdapter.showNext();
    }

    public void handleViews(String searchScreen, int pos) {

        if (Constant.SEARCH_DETAILS_SCREEN.equalsIgnoreCase(searchScreen)) {
            clickedItem = searchDTO.getItems().get(pos);
        }
        searchFlowAdapter.showNext();
    }


    @Override
    public void searchClicked(String queryString) {

        if (isNetworkAvailable(MainActivity.this)) {
            showProgressDialog("Loading", false);
            apiManager.searchQuery(queryString);
        } else {
            Toast.makeText(MainActivity.this, "No Network Available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void apiCallback(SearchDTO searchDTO) {
        hideProgressDialog();
        this.searchDTO = searchDTO;
        System.out.println("MainActivity.apiCallback::  " + searchDTO.getItems().get(0).getLink());
        searchFlowAdapter.showNext();
    }


    public class SearchFlowPagerAdapter extends SearchFlowAdapter {
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return FragmentSearch.getFragment();
                case 1:
                    return FragmentSearchResults.getFragment(searchDTO);
                case 2:
                    return FragmentSearchDetails.getFragment(clickedItem);
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    public abstract class SearchFlowAdapter {
        private int current = -1;

        public abstract Fragment getItem(int position);

        public boolean showNext() {
            if (current < getCount() - 1) {
                current++;
                Fragment item = getItem(current);
                getSupportFragmentManager().beginTransaction().replace(R.id.flLayout, item).commitAllowingStateLoss();
                return true;
            }
            return false;
        }

        public boolean showPrevious() {
            if (current > 0) {
                current--;
                Fragment item = getItem(current);
                getSupportFragmentManager().beginTransaction().replace(R.id.flLayout, item).commitAllowingStateLoss();
                return true;
            }
            return false;
        }

        public abstract int getCount();

        public int getCurrent() {
            return current;
        }
    }

    private void handelBack() {
        if (searchFlowAdapter == null || searchFlowAdapter.getCurrent() <= 0) {
            super.onBackPressed();
        } else {
            boolean isPreviousShown = searchFlowAdapter.showPrevious();
            if (!isPreviousShown) {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onBackPressed() {
        handelBack();
    }
}
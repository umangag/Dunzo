package com.umang.dunzo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umang.dunzo.model.SearchDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class FragmentSearchDetails extends Fragment {

    @BindView(R.id.imgView)
    ImageView imgIcon;

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtDetails)
    TextView txtDetails;

    private SearchDTO.Items items;

    public static FragmentSearchDetails getFragment(SearchDTO.Items searchDTO) {
        FragmentSearchDetails fragmentSearchDetails = new FragmentSearchDetails();
        Bundle bundle = new Bundle();
        bundle.putParcelable("searchDTO", searchDTO);
        fragmentSearchDetails.setArguments(bundle);
        return fragmentSearchDetails;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_details, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        items = getArguments().getParcelable("searchDTO");
        bindUI();
    }

    private void bindUI() {

        if (items.pagemap == null) {
            return;
        }

        if (items.pagemap.getCse_thumbnail() != null && !items.pagemap.getCse_thumbnail().isEmpty()) {
            Glide.with(imgIcon.getContext())
                    .load(items.pagemap.getCse_thumbnail().get(0).getSrc())
                    .centerCrop()
                    .into(imgIcon);
        } else {
            Glide.with(imgIcon.getContext())
                    .load(R.drawable.ic_accessibility_black_24dp)
                    .fitCenter()
                    .into(imgIcon);
        }

        txtTitle.setText(items.getTitle());

        if (items.pagemap.getMetatags() != null) {
            txtDetails.setText(!TextUtils.isEmpty(items.pagemap.getMetatags().get(0).getDecription()) ? items.pagemap.getMetatags().get(0).getDecription() : items.pagemap.getMetatags().get(0).getTwitterdecription());
        } else {
            txtDetails.setText("No Description Available");
        }

    }
}

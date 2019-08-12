package com.umang.dunzo.Network;

import com.umang.dunzo.model.SearchDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by umangagarwal on 12,August,2019
 */
public interface APIModule {

    @GET
    Call<SearchDTO> getSearchDetails(@Url String url);
}

package com.umang.dunzo;

import com.umang.dunzo.Network.RestService;
import com.umang.dunzo.model.SearchDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class ApiManager {

    private ApiResponse apiResponse;


    public ApiManager(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    void searchQuery(String queryString) {

        Call<SearchDTO> searchDetails = RestService.getRestClient().getSearchDetails("https://www.googleapis.com/customsearch/v1?q=" + queryString + "&cx=011476162607576381860:ra4vmliv9ti&key=AIzaSyDCrJXMvp_BxQdLMzh0FcDjQqM_nyv7pt8");

        searchDetails.enqueue(new Callback<SearchDTO>() {
            @Override
            public void onResponse(Call<SearchDTO> call, Response<SearchDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("ApiManager.onResponse::: "+response.body().getItems().size());
                    apiResponse.apiCallback(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchDTO> call, Throwable t) {
                System.out.println("ApiManager.onFailure::: "+t.getMessage());
            }
        });
    }
}

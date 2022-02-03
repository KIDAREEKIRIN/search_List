package com.personal.search_list;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getcontacts.php")
    Call<ArrayList<DutyName>> getContact(
            @Query("key") String keyword
    );
}

package space.jacksonmonteiro.users.services;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import space.jacksonmonteiro.users.models.User;

public interface API {
    @POST("Desafio/rest/desafioRest/")
    Call<String> sendUser(@Body User request);
}

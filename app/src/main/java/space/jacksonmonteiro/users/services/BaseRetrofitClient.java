package space.jacksonmonteiro.users.services;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitClient {
    public static <S> S createService(Class<S> serviceClass, Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient.Builder client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.avaty.com.br/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();

        return retrofit.create(serviceClass);
    }
}

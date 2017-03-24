package net.buildbox.sample.retrofit_sample.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import net.buildbox.sample.retrofit_sample.model.ConnpassEvent;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static Observable<ConnpassEvent> getEvent(long eventId) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

        Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ConnpassApi.API_END_POINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        return retrofit.create(ConnpassApi.class)
            .getEvent(eventId);
    }
}

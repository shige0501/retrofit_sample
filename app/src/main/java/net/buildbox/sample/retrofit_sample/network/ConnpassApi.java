package net.buildbox.sample.retrofit_sample.network;

import net.buildbox.sample.retrofit_sample.model.ConnpassEvent;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConnpassApi {
    String API_END_POINT = "https://connpass.com/api/v1/";

    @GET("event/")
    Observable<ConnpassEvent> getEvent(
        @Query("event_id") long eventId
    );
}

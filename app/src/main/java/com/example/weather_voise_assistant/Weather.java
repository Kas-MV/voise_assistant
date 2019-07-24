package com.example.weather_voise_assistant;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Weather {
    public static class Condition {
        @SerializedName("text")
        public String text;
    }

    public static class Forecast{
        @SerializedName("temp_c")
        public Float temperature;

        @SerializedName("condition")
        public Condition condition;
    }

    public static class ApiResult{
        @SerializedName("current")
        public Forecast current;
    }

    public interface WeatherService {
        @GET("/v1/current.json?key=7f85a9f1a484475c803162814191504")
        Call<ApiResult>getCurrentWeather(@Query("q") String city, @Query("lang") String lang);
    }
}

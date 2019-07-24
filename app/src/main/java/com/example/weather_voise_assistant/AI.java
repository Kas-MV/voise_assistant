package com.example.weather_voise_assistant;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Consumer;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
    public static void getAnswer (String user_question, final Consumer<String> callback){

        Map<String, String> database = new HashMap(){{
            put("привет", "Здрасте");
            put("как дела", "Дела норм");
            put("чем занимаешься", "Отвечаю на дурацкие вопросы");
            put("как тебя зовут", "Меня зовут Маргарита!");
            put("кто тебя создал", "Меня создал Марат");
        }};
        user_question = user_question.toLowerCase();

        final List<String> answers = new ArrayList<>();

        for (String database_questoin : database.keySet()){
            if (user_question.contains(database_questoin)){
                answers.add(database.get(database_questoin));
            }
        }

        Pattern cityPattern = Pattern.compile("какая погода в городе (\\p{L}+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = cityPattern.matcher(user_question);
        if (matcher.find()){
            String cityName = matcher.group(1);
            Weather.getWeather(cityName, new Consumer<String>() {
                @Override
                public void accept(String weather_forecast) {
                    answers.add(weather_forecast);
                    callback.accept(TextUtils.join(", " , answers));

                }
            });
            //answers.add("Не знаю я какая там погода у вас в городе " + cityName); //Заглушка
        }

        if (answers.isEmpty()){
            answers.add("OK");
        }
        callback.accept(TextUtils.join(", " , answers));


    }
}

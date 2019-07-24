package com.example.weather_voise_assistant;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
    public static String getAnswer (String user_question){

        Map<String, String> database = new HashMap(){{
            put("привет", "Здрасте");
            put("как дела", "Дела норм");
            put("чем занимаешься", "Отвечаю на дурацкие вопросы");
            put("как тебя зовут", "Меня зовут Маргарита!");
            put("кто тебя создал", "Меня создал Марат");
        }};
        user_question = user_question.toLowerCase();

        List<String> answers = new ArrayList<>();

        for (String database_questoin : database.keySet()){
            if (user_question.contains(database_questoin)){
                answers.add(database.get(database_questoin));
            }
        }

        if (answers.isEmpty()){
            answers.add("OK");
        }
        return TextUtils.join(", " , answers);


    }
}

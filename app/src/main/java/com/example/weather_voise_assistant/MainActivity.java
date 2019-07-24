package com.example.weather_voise_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected Button sendButton;
    protected EditText questionField;
    protected TextView chatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionField = findViewById(R.id.questionField);
        chatWindow = findViewById(R.id.chatWindow);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickSendButton();
            }
        });
    }

    protected void onClickSendButton(){
        String text = questionField.getText().toString();
        String answer = "Ок, понял";

        chatWindow.append(">>" + text + "\n");
        chatWindow.append("<<" + answer + "\n");

        questionField.setText("");

    }

}

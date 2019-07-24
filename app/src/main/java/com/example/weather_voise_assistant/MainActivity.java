package com.example.weather_voise_assistant;

import android.speech.tts.TextToSpeech;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected Button sendButton;
    protected EditText questionField;
    protected RecyclerView chatMessageList;

    protected TextToSpeech tts;

    protected MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionField = findViewById(R.id.questionField);
        chatMessageList = findViewById(R.id.chatMessageList);

        adapter = new MessageListAdapter();
        chatMessageList.setLayoutManager(new LinearLayoutManager(this));
        chatMessageList.setAdapter(adapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onClickSendButton();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(new Locale("ru"));

                }
            }
        });



    }

    protected void onClickSendButton(){
        String text = questionField.getText().toString();
        AI.getAnswer(text, new Consumer<String>() {
            @Override
            public void accept(String answer) {
               // chatWindow.append("<<" + answer + "\n");
                adapter.messageList.add(new Message(answer, false));
                tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null, null);
                adapter.notifyDataSetChanged();
                int lastMessageIndex = adapter.messageList.size() - 1;
                chatMessageList.scrollToPosition(lastMessageIndex);
            }
        });

        //chatWindow.append(">>" + text + "\n");
        adapter.messageList.add(new Message(text, true));
        adapter.notifyDataSetChanged();
        questionField.setText("");

    }

}

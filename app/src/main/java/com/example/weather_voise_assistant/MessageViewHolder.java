package com.example.weather_voise_assistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static android.support.v7.widget.RecyclerView.*;

public class MessageViewHolder extends ViewHolder {
    protected TextView messageText;
    protected TextView messageTime;

    public MessageViewHolder(View item){
        super(item);
        messageText = item.findViewById(R.id.messageTextView);
        messageTime = item.findViewById(R.id.messageDateView);
    }

    public void bind(Message message){
        messageText.setText(message.text);
        DateFormat fmt = new SimpleDateFormat("HH:mm");
        messageTime.setText(fmt.format(message.date));
    }
}

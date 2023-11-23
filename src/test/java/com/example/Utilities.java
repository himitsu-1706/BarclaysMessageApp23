package com.example;

import com.example.entities.Message;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Utilities {
    public static ArrayList<Message> createMessageList()  {
        ArrayList<Message> messages = new ArrayList<>();
        Message message1 = new Message("First test message");
        Message message2 = new Message("Second test message");
        Message message3 = new Message("Third test message");
        Message message4 = new Message("Fourth test message");
        messages.add(message1);
        messages.add(message3);
        messages.add(message2);
        messages.add(message4);
        return messages;
    }

    public static <T> long getSize(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).count();
    }


}

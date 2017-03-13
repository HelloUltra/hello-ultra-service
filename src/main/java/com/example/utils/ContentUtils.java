package com.example.utils;

import com.example.model.Content;

import java.util.List;

/**
 * Created by YG-MAC on 2017. 3. 11..
 */
public class ContentUtils {
    public static <T extends Content> String convertListToMessage(List<T> contents){
        StringBuilder stringBuilder = new StringBuilder();
        contents.stream().forEach(content -> stringBuilder.append(content.toShortString()).append("\n"));
        return stringBuilder.toString();
    }

    public static <T extends Content> String convertToMessage(T content){
        return content.toDetailString();
    }
}

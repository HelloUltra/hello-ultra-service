package com.example.functions;

import com.example.MessageDispatcher;
import com.example.annotations.Command;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by YG_king on 2017-03-06.
 */
public class Function {
    @Autowired
    private MessageDispatcher messageDispatcher;

    @PostConstruct
    public void init(){
        Arrays.asList(this.getClass().getMethods()).stream()
                .filter(method -> method.isAnnotationPresent(Command.class))
                .forEach(method -> messageDispatcher.put(this, method));
    }
}

package com.example.functions;

import com.example.annotations.Command;
import com.example.dispatcher.MessageDispatcher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by YG_king on 2017-03-06.
 */
public class Function {
    @Autowired
    private MessageDispatcher messageDispatcher;

    @Autowired
    private BeanFactory beanFactory;

    @PostConstruct
    public void init(){
        Arrays.stream(this.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(Command.class))
                .forEach(method -> messageDispatcher.commanderPut(this, method, beanFactory));
    }
}

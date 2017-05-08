package com.alexside.client.gui;

import javafx.application.Application;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Alex on 08.05.2017.
 */
public abstract class AbstractJavaFxApp extends Application {
    protected static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {}

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApp> clazz, String[] args) {
        AbstractJavaFxApp.savedArgs = args;
        Application.launch(clazz, args);
    }
}

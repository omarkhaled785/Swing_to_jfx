package com.example;

import com.example.Thegui.Table;

import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Table table = new Table();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}

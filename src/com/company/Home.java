package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class Home {
    Home(){

    }

    public Scene scene;

    public Stage getFeneter() {
        return feneter;
    }

    public void setFeneter(Stage window) {
        this.feneter = feneter;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage feneter;

    public void getMenu(boolean admin){

        TabPane tabPane = new TabPane();// create TabPane.

        Tab cars = new Tab("Car"); // create car Tab.
        cars.setContent(new Car().getNode( this,admin));// adding node to the tab.
        cars.setClosable(false); // create a closable tab.

        Tab motorcycle = new Tab("Motorcycle");
        motorcycle.setContent(new Motorcycle().getNode(this,admin));
        motorcycle.setClosable(false); // create a closable tab.


        tabPane.getTabs().addAll(cars, motorcycle); // add item to the tabPane.
        tabPane.setStyle("-fx-background-color: floralwhite");

        scene = new Scene(tabPane, 900, 700);
        feneter = new Stage();
        feneter.setScene(scene);
        feneter.setResizable(false);
        feneter.setTitle("                                              WELCOME TO ONLINE REGISTRATION SYSTEM");
        feneter.show();



    }
}

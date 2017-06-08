package com.company;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class PasswordReset {
    PasswordReset(){

    }
    public Scene getScene(Main login){

        Label title = new Label("FORGOT PASSWORD!?");

        Label userLabel = new Label("USERNAME: ");
        TextField userText= new TextField();

        Label pincodeLabel = new Label("PINCODE: ");
        PasswordField pincode= new PasswordField();

        Label newPassLabel= new Label("NEW PASSWORD: ");
        PasswordField passwordField= new PasswordField();

        Button done =  new Button("   DONE   ");
        done.setOnAction((ActionEvent event1) -> {
            if(login.getLoad(userText.getText(),pincode.getText(),true)){//if password reset  is valid then set to login page
                login.signUpO.getWrite(login,userText.getText(),passwordField.getText(),pincode.getText(),true);
                login.feneter.setScene(login.getScene());
            }
        });
        Button goBack = new Button("   Go Back   ");
        goBack.setOnAction(event -> {
            login.feneter.setScene(login.getScene());//set forgot password scene to login scene
        });
        GridPane gridPane= new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setConstraints(userLabel,0,0);
        GridPane.setConstraints(userText,1,0);
        GridPane.setConstraints(pincodeLabel,0,1);//add userLabel to 1 no Row and 0 no Colum
        GridPane.setConstraints(pincode,1,1);
        GridPane.setConstraints(newPassLabel,0,2);
        GridPane.setConstraints(passwordField,1,2);
        GridPane.setConstraints(done,2,5);
        GridPane.setConstraints(goBack,0,5);
        gridPane.getChildren().addAll(userLabel,userText,pincodeLabel,pincode,newPassLabel,passwordField,goBack,done);
        gridPane.setHalignment(userLabel, HPos.RIGHT);
        gridPane.setHalignment(pincodeLabel, HPos.RIGHT);
        gridPane.setHalignment(newPassLabel, HPos.RIGHT);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(30,30,30,30));
        vBox.getChildren().addAll(title,gridPane);
        vBox.setStyle("-fx-background-color:#99ffb9;");

        return new Scene(vBox,500,450);
    }
}

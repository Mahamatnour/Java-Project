package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class SignUp {
    public SignUp(){

    }

    public Scene getScene(Main login){
        Label title= new Label("Register New Admin");
        Label uLabel=  new Label("User Name");
        TextField uText =  new TextField();
        uText.setMaxWidth(200);
        Label notification= new Label();
        Label notification2= new Label();
        Label passLabel = new Label("Password");
        PasswordField pass= new PasswordField();
        pass.setMaxWidth(200);
        HBox hbox2=new HBox();//creates a Horizontal Node
        hbox2.getChildren().addAll(uText,notification);
        hbox2.setSpacing(20);
        Label confirmPassLabel= new Label("Confirm Password");
        PasswordField confirmPass= new PasswordField();
        confirmPass.setMaxWidth(200);

        Label pincodeLabel= new Label("PIN , which can be used to reset password");
        PasswordField pincode = new PasswordField();
        pincode.setMaxWidth(200);
        HBox hBox3= new HBox();
        hBox3.setSpacing(150);
        hBox3.getChildren().addAll(pincodeLabel,notification2);


        Button register = new Button("Register");
        register.setMinSize(100,30);
        register.setOnAction(event1 -> {
            if(!uText.getText().isEmpty() && !pass.getText().isEmpty() && !confirmPass.getText().isEmpty() && !pincode.getText().isEmpty()
                    && pass.getText().equals(confirmPass.getText()) && !pass.getText().contains(" ")&& !uText.getText().contains(" ") && !pincode.getText().contains(" ")
                    ){/*
                    Check whether sign Up requirement met or not
                    such as password can't have space
                    or filled can't be empty
      */


                if(getWrite(login,uText.getText(),pass.getText(),pincode.getText(),false))//check wether the username alreadt exist or not
                    login.feneter.setScene(login.getScene());
                else{
                    notification.setText("USERNAME ALREADY EXIST !");
                }
            }
            if(!pass.getText().equals(confirmPass.getText())){//checks password matched with confirm password
                confirmPassLabel.setText("PASSWORD DOESN'T MATCH");
            }

            if(pass.getText().contains(" ")||uText.getText().contains(" ") || pincode.getText().contains(" ")){//check space in password
                notification2.setText("INPUT CAN'T HAVE SPACE" );
            }
            if(uText.getText().isEmpty() || pass.getText().isEmpty() || confirmPass.getText().isEmpty() || pincode.getText().isEmpty()//check wether all field are filled or not
                    ){
                notification2.setText("SOMETHING IS MISSING");
            }

        });

        Button goBack = new Button("Go Back");
        goBack.setOnAction(event -> {
            login.feneter.setScene(login.getScene());
        });

        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(goBack,register);
        hBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(30,30,30,30));
        vbox.setMargin(title,new Insets(20,0,10,170));
        vbox.setMargin(register,new Insets(0,0,10,270));

        vbox.getChildren().addAll(title,uLabel,hbox2,passLabel,pass,confirmPassLabel,confirmPass,hBox3,pincode,hBox);

        vbox.setStyle("-fx-background-color:#99ffb9;");
        return  new Scene(vbox,500,450);//creates scene of size 500X450
    }
    public boolean getWrite(Main login,String username,String password,String pincode,boolean update){
        boolean exist=true;
        String fileName ="code.txt";
        boolean write=true;

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter contactWriter = new BufferedWriter(fileWriter);
            for(int i=0;i<login.numberOfCredential;i++){
                String currentLine=(login.credential[i][0]+" "+login.credential[i][1]+" "+login.credential[i][2]+" ").toLowerCase();
                if((login.credential[i][0]).equals(username.toLowerCase())){
                    if(update){
                        write=false;
                        contactWriter.append((username+" "+password+" "+pincode+" ").toLowerCase());
                    }
                    else{
                        //        confirm user name exist
                        exist=  false;
                    }
                }
                else {

                    contactWriter.append(currentLine.toLowerCase());contactWriter.newLine();
                    //   write the  line don't need to  modify
                }
            }

            if (write){
                contactWriter.append((username.toLowerCase()+" "+password+" "+pincode+" ").toLowerCase());
                //   write new entry to file
            }
            contactWriter.newLine();//move to the new line
            contactWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }
        return exist;
    }

}

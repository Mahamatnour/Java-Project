package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

import static javafx.application.Application.launch;

public class Main extends Application {

    public int numberOfCredential=0;
    public String[][] credential= new String[20][3];
    public Stage getFeneter = new Stage();
    public SignUp signUpO= new SignUp();

    public Stage feneter;

    public SignUp getSignUpO() {
        return signUpO;
    }

    public void setSignUpO(SignUp signUpO) {
        this.signUpO = signUpO;
    }


    public Stage getGetFeneter() {
        return feneter;
    }

    public void setFeneter(Stage feneter) {
        this.feneter = feneter;
    }

    public String[][] getCredential() {
        return credential;
    }

    public void setCredential(String[][] credential) {
        this.credential = credential;
    }

    public int getNumberOfCredential() {
        return numberOfCredential;
    }

    public void setNumberOfCredential(int numberOfCredential) {
        this.numberOfCredential = numberOfCredential;
    }

    public Scene getScene(){

        Label title = new Label(" Login ");
        title.setFont(Font.font(30));

        Label uLabel= new Label("User Name");
        TextField uText= new TextField();

        Label pLabel=  new Label("Password");
        PasswordField pText= new PasswordField();

        Button signIn= new Button("Sign In");
        signIn.setPrefWidth(370);
        signIn.setOnAction((ActionEvent event) -> {
            if(getLoad(uText.getText(),pText.getText(),false)) {//check sign In is valid or not

                feneter.close();
                new Home().getMenu(true);
            }
            //window.close();
            //new Home().getMenu(true);
        });


       Image logo = new Image(new File("logo.png").toURI().toString());
       //Image logo= new Image("logo.png");
        ImageView ivLogo= new ImageView(logo);//setting image to ImageView
        ivLogo.setSmooth(true);
        ivLogo.setCache(true);
        ivLogo.setPreserveRatio(true);
        ivLogo.setFitWidth(150);

        HBox hbox= new HBox();
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.TOP_LEFT);
        Button forgotPass = new Button("Forgot Password");
        forgotPass.setOnAction((ActionEvent e) ->{
            feneter.setScene(new PasswordReset().getScene(this));//open forPassword window
        });

        Button signUp= new Button("Register New");
        signUp.setPrefWidth(105);
        signUp.setOnAction(e->{
            getLoad("","",false);
            feneter.setScene(signUpO.getScene(this));
        });

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10,30,30,30));
        vBox.getChildren().addAll(uLabel,uText,pLabel,pText,signIn,hbox,signUp,forgotPass);
        vBox.setMargin(signUp,new Insets(0,0,0,75));
        vBox.setAlignment(Pos.TOP_LEFT);

        HBox hBox1= new HBox();
        hBox1.getChildren().addAll(ivLogo,vBox);
        hBox1.setAlignment(Pos.CENTER_LEFT);

        VBox finish = new VBox();//creates a vertical Node
        finish.setSpacing(20);
        finish.setPadding(new Insets(30,30,30,30));
        finish.getChildren().addAll(title,hBox1);
        finish.setAlignment(Pos.CENTER);
        finish.setStyle("-fx-background-color: cornsilk;");
        return new Scene(finish,500,450);
    }

    public boolean getLoad(String user,String pass,boolean forgot){
        String fileName = "code.txt";
        String line = null;
        //System.out.println("BUFFER called");
        numberOfCredential=0;//set back total number of user to 0 avoid  conflict
        try {
            FileReader fileReader = new FileReader(fileName);//creates a fileReder object to read from file

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                for(int i=0,j=0;i<line.length();i++){
                    String temp="";

                    if(line.charAt(i)==' '){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        credential[numberOfCredential][j]=temp;
                        j++;

                    }
                }
                if(forgot && credential[numberOfCredential][2].equals(pass)){//check pincode is right or wrong
                    return  true;
                }
                else if(credential[numberOfCredential][0].equals(user) && credential[numberOfCredential][1].equals(pass))
                    return true;
                numberOfCredential++;


            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        return false;

    }

    public static void main(String [] args){

        launch(args);
    }
    public void start(Stage primary) throws IOException{


        feneter= primary;
        feneter.setScene(getScene());
        feneter.setResizable(false);
        feneter.show();
    }
}

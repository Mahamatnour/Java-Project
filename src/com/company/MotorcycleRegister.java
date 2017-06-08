package com.company;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class MotorcycleRegister {

    public boolean yearB, allFilled = true;
    public String[] details = new String[6];
    public int field = 6;

    public int getField() {
        return field;
    }
    public void setField(int field) {
        this.field = field;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    public boolean isAllFilled() {
        return allFilled;
    }

    public void setAllFilled(boolean allFilled) {
        this.allFilled = allFilled;
    }

    public boolean isYearB() {
        return yearB;
    }

    public void setYearB(boolean yearB) {
        this.yearB = yearB;
    }

    public Scene getScene(Home menu, Motorcycle Motorcycle, boolean editable) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(30);//set Horizontal Allignment to be 30pixel
        gridPane.setVgap(15);


        Label bookName = new Label("Title");
        TextField bookNameT = new TextField();
        GridPane.setConstraints(bookName, 0, 1);
        GridPane.setConstraints(bookNameT, 0, 2);

        bookNameT.setOnMouseClicked(event1 -> {//make the red box to black on mouse click
            allFilled=true;
            bookNameT.setStyle("-fx-border-color:black");
        });

        Label authorName = new Label("Author Name");
        GridPane.setConstraints(authorName, 0, 3);
        TextField authorNameT = new TextField();
        GridPane.setConstraints(authorNameT, 0, 4);
        authorNameT.setOnMouseClicked(event1 -> {
            allFilled=true;
            authorNameT.setStyle("-fx-border-color:black");
        });

        Label publisher = new Label("Publisher");
        GridPane.setConstraints(publisher, 1, 3);
        TextField publisherT = new TextField();
        GridPane.setConstraints(publisherT, 1, 4);
        publisherT.setMinWidth(250);
        publisherT.setOnMouseClicked(event1 -> {
            allFilled=true;
            publisherT.setStyle("-fx-border-color:black");
        });

        Label copy = new Label("Available Copy");
        TextField copyT = new TextField();
        GridPane.setConstraints(copy, 1, 6);
        GridPane.setConstraints(copyT, 1, 7);
        copyT.setOnMouseClicked(event1 -> {
            allFilled=true;
            copyT.setStyle("-fx-border-color:black");
        });

        Label isbn = new Label("ISBN");
        TextField isbnT = new TextField();
        GridPane.setConstraints(isbn, 0, 6);
        GridPane.setConstraints(isbnT, 0, 7);
        isbnT.setOnMouseClicked(event1 -> {
            allFilled=true;
            isbnT.setStyle("-fx-border-color:black");
        });

        Label publishYear = new Label("Publish Year");

        ComboBox<String> year = new ComboBox<>();//creates combox for year
        year.setPromptText("Year");//set prompt value to be "Year"
        for (int i = 1900; i < 2017; i++)
            year.getItems().add(""+i+"");//adding Items to year
        year.setMaxHeight(150);
        year.setOnAction(event1 -> {
            allFilled=true;
            year.setStyle("-fx-border-color: black");yearB=true;
        });
        GridPane.setConstraints(year,0,8);

        Button save = new Button("Save");
        save.setMinSize(120,40);
        gridPane.setHalignment(save,HPos.LEFT);//set horizontal allignment to be Left
        GridPane.setConstraints(save, 2, 13);
        Button cancel= new Button(" Cancel ");
        gridPane.setHalignment(cancel,HPos.RIGHT);
        cancel.setMinSize(120,40);
        GridPane.setConstraints(cancel, 1, 13);
        //adding items to gridpane
        gridPane.getChildren().addAll(bookName, bookNameT, authorName,authorNameT, publisher,publisherT, isbn, isbnT, copy,copyT,year, cancel,save);
        cancel.setOnAction(event1 -> menu.feneter.setScene(menu.scene));
        //handle save button clicked
        save.setOnAction(event -> {

            if(!bookName.getText().isEmpty()){//checking whether empty or not
                details[0]=bookNameT.getText();
            }
            else {
                bookNameT.setStyle("-fx-border-color:Red");
                allFilled = false;
            }
            if(!authorNameT.getText().isEmpty())
                details[1]=authorNameT.getText();
            else {
                authorNameT.setStyle("-fx-border-color:Red");
                allFilled=false;
            }
            if(!publisherT.getText().isEmpty())
                details[2]=publisherT.getText();
            else {
                allFilled=false;
                publisherT.setStyle("-fx-border-color:Red");
            }

            if(!copyT.getText().isEmpty())
                details[3]=copyT.getText();
            else{
                allFilled=false;
                copyT.setStyle("-fx-border-color:Red");
            }

            if(!isbnT.getText().isEmpty())
                details[4]=isbnT.getText();
            else{
                allFilled=false;
                isbnT.setStyle("-fx-border-color:Red");
            }

            if(year.getSelectionModel().getSelectedIndex()!=-1)//cheking whether year value selected or not
                details[5]=year.getValue();
            else{
                allFilled=false;
                year.setStyle("-fx-border-color:Red");
            }

            if(allFilled){
                if(editable){
                    update(Motorcycle,false,true);
                }
                else
                    Motorcycle.addEntry.update(Motorcycle,false,false);
                Motorcycle.borderPane.setLeft(new MotorcycleTab().getLeft(Motorcycle));
                menu.feneter.setScene(menu.scene);
            }

        });
        if(editable){//check update is asked or not
            int index= Motorcycle.searchEntry.index;
            String details[][]= Motorcycle.searchEntry.store;
            bookNameT.setText(details[index][0]);
            authorNameT.setText(details[index][1]);
            publisherT.setText(details[index][2]);
            copyT.setText(details[index][3]);
            isbnT.setText(details[index][4]);
            year.setValue(details[index][5]);

        }
        gridPane.setStyle("-fx-background-color:#99ffb9;");//setting background color
        return new Scene(gridPane, 800, 600);
    }

    public void update(Motorcycle menu, boolean delete, boolean update){
        String fileName ="motorcycle.txt";

        try {

            String tobeOut="";//the data is going to be written in file
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter detailsWriter = new BufferedWriter(fileWriter);
            for(int i=0;i<field;i++){
                tobeOut+=(details[i]+"|");
            }
            String temp="";

            for(int i=0;i<field;i++)
                temp+=menu.searchEntry.store[menu.searchEntry.index][i]+"|";//set the index value that is  selected from left panel

            for(int i=0;i<menu.searchEntry.count;i++){
                String currentLine="";
                for(int j=0;j<field;j++){
                    currentLine+=menu.searchEntry.store[i][j]+"|";//the line that is read by compiler now
                }


                if(currentLine.equals(temp)){

                    if(!delete && !update){
                        detailsWriter.append(currentLine);detailsWriter.newLine();
                        // System.out.println("Inside "+ currentLine);
                    }
                    if(update){//check whether to update
                        detailsWriter.append(tobeOut);detailsWriter.newLine();
                        // System.out.println("Update "+tobeOut);
                    }
                }
                else {

                    detailsWriter.append(currentLine);detailsWriter.newLine();//write the data which should be unchanged
                    // System.out.println("Pass y "+currentLine);
                }
            }
            if (!update && !delete){ //add new entry when update and delete is false
                detailsWriter.append(tobeOut);
                // System.out.println("ADD new "+tobeOut);
                detailsWriter.newLine();
            }
            // System.out.println("\n");
            detailsWriter.close();//closing the files
            menu.searchEntry.getLoad();
            menu.borderPane.setLeft(menu.searchEntry.getLeft(menu));
            menu.borderPane.setRight(menu.searchEntry.getRight());
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }


    }
}

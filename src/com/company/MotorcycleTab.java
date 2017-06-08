package com.company;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class MotorcycleTab {

    public int count=0, index=0;
    public String[][] store= new String[1000][6];//string that store the details of book entry
    public String selected="";//string that store the currently selected item
    public Motorcycle menu;
    public TextField searchT= new TextField();

    MotorcycleTab(){getLoad();}
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String[][] getStore() {
        return store;
    }

    public void setStore(String[][] store) {
        this.store = store;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public Motorcycle getMenu() {
        return menu;
    }

    public void setMenu(Motorcycle menu) {
        this.menu = menu;
    }

    public TextField getSearchT() {
        return searchT;
    }

    public void setSearchT(TextField searchT) {
        this.searchT = searchT;
    }

    public VBox getLeft(Motorcycle temp){

        menu=temp;
        getLoad();//read date from file and store it in store string
        searchT.setPromptText("search by name");
        searchT.setStyle("-fx-font: 15px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%); -fx-stroke: black; -fx-stroke-width: 1");
        searchT.setAlignment(Pos.CENTER);
        searchT.setOnKeyReleased(event -> {
            //System.out.println(searchT.getText());
            handleSearch(searchT.getText());
        });
        ObservableList<String> names = FXCollections.observableArrayList();

        for(int i=0;i<count;i++){
            names.add(store[i][0]+"\n"+store[i][1]);//adding list item to  observable list
        }
        ListView<String> listView = new ListView<>(names);
        listView.setStyle("-fx-font-size:15;");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    //  System.out.println(new_val);
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.borderPane.setRight(getRight());

                });
        VBox vbox= new VBox();
        vbox.getChildren().addAll(searchT,listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.setMinWidth(200);
        vbox.setMinHeight(300);
        vbox.setPadding(new Insets(30,30,30,30));
        vbox.setStyle("-fx-background-color:  #99ffb9;-fx-border-color:white;-fx-border-width: 4;");
        return vbox;
    }
    public int getIndex(){
        int i;
        for( i=0;i<count;i++){
            if((store[i][0]+"\n"+store[i][1]).equals(selected)){
                index=i;
                menu.searchEntry.index=i;
                //System.out.println("Pressed Index--- "+i);
                break;
            }
        }
        return i;
    }

    public StackPane getRight() {
        StackPane stackPane = new StackPane();
        stackPane.setMinWidth(400);
        stackPane.setMinHeight(300);
        //stackPane.setStyle("-fx-border-color: black;-fx-border-width: 2;-fx-border-style: solid;");
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setPadding(new Insets(30, 30, 30, 30));

        GridPane details = new GridPane();
        Label firstName = new Label("First Name \n" + store[index][0]);
        firstName.setFont(javafx.scene.text.Font.font(15));
        firstName.setMinHeight(40);
        Label lastName = new Label("Last Name \n" + store[index][1]);
        lastName.setFont(javafx.scene.text.Font.font(15));
        lastName.setMinHeight(40);
        Label carsType = new Label("Car's Type \n" + store[index][2]);
        carsType.setFont(javafx.scene.text.Font.font(15));
        Label levelOfStudy = new Label("Level \n" + store[index][2]);
        levelOfStudy.setFont(javafx.scene.text.Font.font(15));
        Label carYear = new Label("Car Year \n" + store[index][3]);
        carYear.setFont(javafx.scene.text.Font.font(15));
        carYear.setMinHeight(40);
        Label bareCode = new Label("Bar Code No \n" + store[index][4]);
        bareCode.setFont(javafx.scene.text.Font.font(15));
        bareCode.setMinHeight(40);
        Label matricNo = new Label("Matric No  \n" + store[index][5]);
        matricNo.setFont(javafx.scene.text.Font.font(15));
        matricNo.setMinHeight(40);
        details.add(firstName, 0, 5);
        details.add(lastName, 0, 7);
        details.add(levelOfStudy, 0, 13);
        details.add(carsType, 0, 9);
        details.add(carYear, 0, 11);
        details.add(bareCode, 1, 11);
        details.add(matricNo, 0, 13);
        details.setAlignment(Pos.CENTER_LEFT);
        details.setHgap(20);
        details.setVgap(10);
        details.setMinWidth(150);
        stackPane.getChildren().add(details);
        stackPane.setStyle("-fx-background-color:  white;-fx-border-color:#99ffb9;-fx-border-width: 4;");
        return stackPane;

    }

    public void getLoad(){
        String fileName = "book.txt";
        String line = null;
        count=0;
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                for(int i=0,j=0;i<line.length();i++){
                    String temp="";
                    if(line.charAt(i)=='|'){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        store[count][j]=temp;
                        j++;

                    }
                }
                count++;
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

    }
    public void handleSearch(String query){
        ObservableList<String> names = FXCollections.observableArrayList();


        for(int i=0;i<count;i++){
            String match=store[i][0]+"\n"+store[i][1];

            if(match.toLowerCase().contains(query.toLowerCase())){//check whether the entered query matched with list item
                names.add(match);
            }
        }
        ListView<String>listView = new ListView<>(names);
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.borderPane.setRight(getRight());

                });
        listView.setStyle("-fx-font-size:15;");
        VBox vbox= new VBox();
        vbox.getChildren().addAll(searchT,listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.setMinWidth(200);
        vbox.setMinHeight(300);
        vbox.setPadding(new Insets(30,30,30,30));
        menu.borderPane.setLeft(vbox);
    }

}

package com.company;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by mahamatnouralimai on 24/04/2017.
 */
public class Motorcycle {
    public BorderPane borderPane;
    public CarTab searchEntry = new CarTab();

    public MotorcycleRegister getAddEntry() {
        return addEntry;
    }

    public void setAddEntry(MotorcycleRegister addEntry) {
        this.addEntry = addEntry;
    }

    public CarTab getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(CarTab searchEntry) {
        this.searchEntry = searchEntry;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Node getNode;

    public MotorcycleRegister addEntry = new MotorcycleRegister();

    public BorderPane getNode(Home home, boolean admin) {
        borderPane = new BorderPane();
        borderPane.setLeft(borderPane.getLeft());
        borderPane.setRight(borderPane.getRight());

        Button add = new Button("  ADD NEW  ");
        add.setOnAction(event -> {

            home.feneter.setScene(addEntry.getScene(home, this, false));
        });
        Button update= new Button("  UPDATE   ");
        update.setOnAction(event -> {
            borderPane.setLeft(borderPane.getLeft());//will load the index first
            home.feneter.setScene(new MotorcycleRegister().getScene(home,this,true));//set the update scene


        });
        Button delete= new Button("  DELETE ");
        delete.setOnAction(event -> {
            addEntry.update(this,true,false);//delete and updating the index first
            borderPane.setLeft(borderPane.getLeft());//load  the left panel again
        });
        HBox hBox= new HBox();
        hBox.setMinHeight(150);
        hBox.setMinWidth(650);
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(32,32,32,32));
        hBox.setStyle("-fx-background-color: ghostwhite;-fx-border-color:burlywood;-fx-border-width: 6;");
        Button logout= new Button("  SignOff  ");
        logout.setOnAction((ActionEvent event) -> {
            home.feneter.close();
            Main log= new Main();
            log.feneter.setScene(log.getScene());
            log.feneter.setResizable(false);//Make the window not resizable
            log.feneter.show();
        });
        hBox.getChildren().add(logout);
        if (admin)//if admin true then delete , update and add button will be added
            hBox.getChildren().addAll(delete,update,add);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        borderPane.setTop(hBox);
        borderPane.setAlignment(borderPane,Pos.TOP_LEFT);
        borderPane.setPadding(new Insets(32,32,32,32));
        return borderPane;
    }
}

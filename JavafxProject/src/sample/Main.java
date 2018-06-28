package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {
    public static ResultSet resSet;
    public static Statement statmt;
    private static final String STANDARD_BUTTON_STYLE = "-fx-padding: 5 22 5 22; -fx-border-color: #e2e2e2;-fx-border-width: 2; -fx-background-radius: 0; -fx-background-color: #1d1d1d; -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 11pt; -fx-text-fill: #d8d8d8; -fx-background-insets: 0 0 0 0, 0, 1, 2;";
    private static final String HOVERED_BUTTON_STYLE  = "-fx-padding: 5 22 5 22; -fx-border-color: #e2e2e2;-fx-border-width: 2; -fx-background-radius: 0; -fx-background-color: #3a3a3a; -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 11pt; -fx-text-fill: #d8d8d8; -fx-background-insets: 0 0 0 0, 0, 1, 2;";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label auth = new Label("Authorization");

        GridPane grid3 = new GridPane();
        grid3.setMinSize(20,20);
        grid3.setVgap(5);
        grid3.setHgap(5);

        grid3.add(auth, 12, 8);


        auth.setStyle("-fx-font-size:30px;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: white;-fx-opacity: 1;");


        TextField textField = new TextField();
        TextField textField2 = new TextField ();
        Label name = new Label("User name: ");
        name.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 0.6;");

        Label password = new Label("Password: ");
        password.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 0.6;");

        Button sign = new Button("Sign in");
        changeBackgroundOnHoverUsingEvents(sign);
        sign.setStyle("-fx-padding: 5 22 5 22; -fx-border-color: #e2e2e2;-fx-border-width: 2; -fx-background-radius: 0; -fx-background-color: #1d1d1d; -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 11pt; -fx-text-fill: #d8d8d8; -fx-background-insets: 0 0 0 0, 0, 1, 2;");
        sign.setOnAction(event -> {
            String textlogin = textField.getText();
            String textpass = textField2.getText();
            if(!textlogin.equals("") & !textpass.equals(""))
                try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader("D:\\IdeaProjects\\JavafxProject\\src\\sample\\movies.json"));
                JSONArray arr = (JSONArray) obj;
                    if(textlogin.equals("admin") & textpass.equals("admin")){
//                        Object ob = parser.parse(arr.get(0).toString());
//                        JSONObject objectt = (JSONObject) ob;
//                        Object obh = parser.parse(arr.get(1).toString());
//                        JSONObject objectth = (JSONObject) obh;
                            VBox boxvb = new VBox();
                            HBox tophbox = new HBox();


                            Button update = new Button("Update");
                            Button delete = new Button("Delete");

                            GridPane top = new GridPane();
                            top.setMinSize(20, 20);
                            top.setVgap(5);
                            top.setHgap(5);
                            top.add(update, 20, 0);
                            top.add(delete, 25, 0);
                        TextField textf = new TextField();
                        TextField textf1 = new TextField();
                        TextField textf2 = new TextField();
                        TextField textf3 = new TextField();

                        GridPane griddd = new GridPane();
                        griddd.setMinSize(20, 20);
                        griddd.setVgap(5);
                        griddd.setHgap(5);
                        griddd.add(textf, 5, 0);
                        griddd.add(textf1, 5, 5);
                        griddd.add(textf2, 5, 10);
                        griddd.add(textf3, 5, 15);

                        final ListView<User> list = new ListView<>(conn.sh());
                        delete.setOnAction(event1 -> {
                            User item11 = list.getSelectionModel().getSelectedItem();
                            String selected = item11.getName();
                            try {
                                conn.Delete(selected);
                                list.setItems(conn.sh());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                        update.setOnAction(event1 -> {
                            try {
                                User item11 = list.getSelectionModel().getSelectedItem();
                                String selected = item11.getName();
                                conn.Update(textf.textProperty(), textf1.textProperty(), textf2.textProperty(), textf3.textProperty(), selected);
                                list.setItems(conn.sh());
                            }catch (ClassNotFoundException e){
                                e.printStackTrace();
                            }catch(SQLException e){
                                e.printStackTrace();
                            }

                        });


                            HBox listh = new HBox();
                            listh.getChildren().addAll(list,griddd);

                            tophbox.getChildren().add(top);
//                        HBox bhbox = new HBox();
//                        HBox bhbox1 = new HBox();
//                        Label newlbl = new Label(objectt.get("name").toString());
//                        Label newlbl1 = new Label(objectth.get("name").toString());
//                        Button finalbtn = new Button("Delete");
//                        Button finalbtn2 = new Button("Update");
//                        Button finalbtn3 = new Button("Delete");
//                        Button finalbtn4 = new Button("Update");
//                        finalbtn.setOnAction(event1 -> {
//                            objectt.remove("name");
//                            objectt.remove("pass");
//                            objectt.remove("surname");
//                            objectt.remove("age");
//                            System.out.println(objectt + "Object deleted!");
//                        });
//                        finalbtn3.setOnAction(event1 -> {
//                            objectth.remove("name");
//                            objectth.remove("pass");
//                            objectth.remove("age");
//                            objectth.remove("surname");
//                            System.out.println(objectth + "Object deleted!");
//                        });
//
//                        GridPane gridnew = new GridPane();
//                        gridnew.setMinSize(20, 20);
//                        gridnew.setVgap(5);
//                        gridnew.setHgap(5);
//                        gridnew.add(newlbl,20,5);
//                        gridnew.add(finalbtn,25,5);
//                        gridnew.add(finalbtn2,30,5);
//                        bhbox.getChildren().add(gridnew);
//
//                        GridPane gridnew1 = new GridPane();
//                        gridnew1.setMinSize(20, 20);
//                        gridnew1.setVgap(5);
//                        gridnew1.setHgap(5);
//                        gridnew1.add(newlbl1,20,5);
//                        gridnew1.add(finalbtn3,25,5);
//                        gridnew1.add(finalbtn4,30,5);
//                        bhbox1.getChildren().add(gridnew1);
//
//
//                        boxvb.getChildren().addAll(bhbox,bhbox1);
                            boxvb.getChildren().addAll(tophbox, listh);

                            Stage stage = new Stage();
                            stage.setTitle("My New Stage Title");
                            stage.setScene(new Scene(boxvb, 450, 450));
                            stage.show();

                    }else {
                        for (int i = 0; i < arr.size(); i++) {
                            Object ob = parser.parse(arr.get(i).toString());
                            JSONObject objectt = (JSONObject) ob;
                            if (objectt.get("name").toString().equals(textlogin) & objectt.get("pass").toString().equals(textpass)) {
                                JSONParser parser1 = new JSONParser();
                                URL yahoo = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=fe9cbd02f074b52856ea6b50952d22c4");
                                URLConnection yc = yahoo.openConnection();
                                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                                String inputLine;
                                JSONObject main1 = null;
                                JSONArray weth1 = null;
                                while ((inputLine = in.readLine()) != null) {
                                    JSONObject jsonbject = (JSONObject) parser1.parse(inputLine);
                                    main1 = (JSONObject) jsonbject.get("main");
                                    weth1 = (JSONArray) jsonbject.get("weather");
                                    System.out.println(jsonbject);
                                }
                                in.close();

                                Object smthob = parser.parse(weth1.get(0).toString());
                                JSONObject forphoto = (JSONObject) smthob;
                                JSONObject formain = (JSONObject) main1;

                                Label gorod = new Label("London,UK");
                                gorod.setStyle("-fx-font-size:25px;-fx-font-family: \"Segoe UI Semibold\";-fx-opacity: 1;");

                                Label thew = new Label("The weather is: ");
                                Label tempIs = new Label("Temperature is: ");
                                Label humidity = new Label("Humidity: ");
                                Label humi = new Label();
                                humi.setText(formain.get("humidity").toString());
                                Label pressure = new Label("Pressure: ");
                                Label pres = new Label();
                                pres.setText(formain.get("pressure").toString());

                                HBox photofor = new HBox();
                                double temp = Double.parseDouble(formain.get("temp").toString());
                                double tempC = (temp - 32) * (5 / 9);
                                Label tempCC = new Label();
                                tempCC.setText(String.valueOf(temp)+" in Fahrenheit");

                                final ImageView selectedImage = new ImageView();
                                Image image1 = new Image(Main.class.getResourceAsStream(forphoto.get("icon").toString() + ".png"));

                                selectedImage.setImage(image1);

                                HBox gor = new HBox();
                                GridPane gridd2 = new GridPane();
                                gridd2.setMinSize(20, 20);
                                gridd2.setVgap(5);
                                gridd2.setHgap(5);

                                gridd2.add(gorod, 30, 5);
                                gor.getChildren().add(gridd2);

                                GridPane gridd1 = new GridPane();
                                gridd1.setMinSize(20, 20);
                                gridd1.setVgap(5);
                                gridd1.setHgap(5);

                                gridd1.add(thew, 20, 5);
                                gridd1.add(selectedImage, 25, 5);
                                gridd1.add(tempIs, 20, 10);
                                gridd1.add(tempCC, 25, 10);
                                gridd1.add(humidity, 20, 15);
                                gridd1.add(humi, 25, 15);
                                gridd1.add(pressure, 20, 20);
                                gridd1.add(pres, 25, 20);
                                photofor.getChildren().add(gridd1);

                                HBox hbbox = new HBox();
                                Label weather = new Label("WEATHER");
                                weather.setStyle("-fx-font-size:30px;-fx-font-family: \"Segoe UI Semibold\";-fx-opacity: 1;");

                                GridPane gridd = new GridPane();
                                gridd.setMinSize(20, 20);
                                gridd.setVgap(5);
                                gridd.setHgap(5);

                                gridd.add(weather, 30, 15);
                                hbbox.getChildren().add(gridd);

                                VBox newvboxx = new VBox();
                                newvboxx.getChildren().addAll(hbbox, gor, photofor);

                                Stage stage = new Stage();
                                stage.setTitle("My New Stage Title");
                                stage.setScene(new Scene(newvboxx, 450, 450));
                                stage.show();
                                break;
                            } else if (!objectt.get("name").toString().equals(textlogin) | !objectt.get("pass").toString().equals(textpass)) {
                                JOptionPane.showMessageDialog(null, "Your login or password is not correct!");
                                break;
                            }

                        }
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
            else{
                JOptionPane.showMessageDialog(null, "You forgot to write login or password");
            }
        });

        Button reg = new Button("Register");
        changeBackgroundOnHoverUsingEvents(reg);
        reg.setStyle("-fx-padding: 5 22 5 22; -fx-border-color: #e2e2e2;-fx-border-width: 2; -fx-background-radius: 0; -fx-background-color: #1d1d1d; -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 11pt; -fx-text-fill: #d8d8d8; -fx-background-insets: 0 0 0 0, 0, 1, 2;");
        reg.setOnAction(event -> {
            VBox newvbox1 = new VBox();
            HBox newhbox1 = new HBox();
            Label authh = new Label("Registration");
            authh.setStyle("-fx-font-size:30px;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: white;-fx-opacity: 1;");
            GridPane grid4 = new GridPane();
            grid4.setMinSize(20,20);
            grid4.setVgap(5);
            grid4.setHgap(5);

            grid4.add(authh, 30, 15);
            newhbox1.getChildren().add(grid4);

            HBox newhbox2 = new HBox();
            TextField textreg = new TextField();
            TextField textreg1 = new TextField();
            TextField textreg2 = new TextField();
            TextField textreg3 = new TextField();

            Label lblreg = new Label("Login: ");
            lblreg.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 1;");
            Label lblreg1 = new Label("Surname");
            lblreg1.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 1");
            Label lblreg2 = new Label("Password: ");
            lblreg2.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 1;");
            Label lblreg3 = new Label("Age: ");
            lblreg3.setStyle("-fx-font-size: 11pt; -fx-font-family: \"Segoe UI Semibold\"; -fx-text-fill: white; -fx-opacity: 1;");

            GridPane grid5 = new GridPane();
            grid5.setMinSize(20,20);
            grid5.setVgap(5);
            grid5.setHgap(5);

            grid5.add(lblreg, 20, 10);
            grid5.add(lblreg1, 20, 20);
            grid5.add(lblreg2, 20, 30);
            grid5.add(lblreg3, 20, 40);
            grid5.add(textreg, 30, 10);
            grid5.add(textreg1, 30, 20);
            grid5.add(textreg2, 30, 30);
            grid5.add(textreg3, 30, 40);
            newhbox2.getChildren().add(grid5);

            HBox newhbox3 = new HBox();
            Button mainRegistr = new Button("Continue");

            GridPane grid6 = new GridPane();
            grid6.setMinSize(20,20);
            grid6.setVgap(5);
            grid6.setHgap(5);

            grid6.add(mainRegistr, 38, 10);
            newhbox3.getChildren().add(grid6);

            newvbox1.getChildren().addAll(newhbox1,newhbox2,newhbox3);

            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(newvbox1, 450, 600));
            String image = Main.class.getResource("back2.jpg").toExternalForm();
            newvbox1.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-repeat: stretch;");
            stage.show();
            mainRegistr.setOnAction(event1 -> {

                String nametext = textreg.getText();
                String surtext = textreg1.getText();
                String passtext = textreg2.getText();
                String agetext = textreg3.getText();
                try {
//                    conn.Conn();

                    conn.WriteDB(nametext, surtext, agetext, passtext);

                    conn.ReadDB();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }



//                JSONObject obj = new JSONObject();
//                obj.put("name", nametext);
//                obj.put("surname",surtext);
//                obj.put("pass", passtext);
//                obj.put("age", Integer.parseInt(agetext));

//                try {
//                    JSONParser parser1 = new JSONParser();
//                    Object object = parser1.parse(new FileReader("D:\\IdeaProjects\\JavafxProject\\src\\sample\\movies.json"));
//                    JSONArray arr1 = (JSONArray) object;
//
////                    arr1.put(obj.toJSONString());
//                    object.put();
//                    System.out.println(arr1);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                try (FileWriter file = new FileWriter("D:\\IdeaProjects\\JavafxProject\\src\\sample\\movies.json")) {
//                    JSONArray arrau = new JSONArray();
//                    arrau.add(obj);
//                    System.out.println(arrau);
//                    file.write(arrau.toJSONString());
//                    System.out.println("Successfully Copied JSON Object to File...");
//                    System.out.println("\nJSON Object: " + obj);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                stage.close();
            });
        });

        GridPane grid2 = new GridPane();
        grid2.setMinSize(200,200);
        grid2.setVgap(5);
        grid2.setHgap(5);

        grid2.add(reg, 8, 0);
        grid2.add(sign, 12, 0);

        GridPane grid = new GridPane();
        grid.setMinSize(200,200);
        grid.setVgap(5);
        grid.setHgap(5);

        grid.add(name,5,15);
        grid.add(password,5,20);
        grid.add(textField, 10,15);
        grid.add(textField2, 10,20);
        hbox.getChildren().add(grid2);


//        grid.add(sign,8,25);
//        grid.add(reg,13,25);
        vbox.getChildren().add(grid3);
        vbox.getChildren().add(grid);
        vbox.getChildren().add(hbox);
        hbox.setHgrow(reg, Priority.ALWAYS);
        hbox.setPadding(new Insets(5,5,5,5));
        primaryStage.setScene(new Scene(vbox, 300, 275));

        String image = Main.class.getResource("back.jpg").toExternalForm();
        vbox.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        primaryStage.show();
    }
    public static class User{
        public String login,password,name,surname;
        public User(String name, String surname, String login, String password){
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.password = password;
        }
        public String toString(){
            return name + " " + surname + " " + login + " " + password;
        }

        public String getName() {
            return name;
        }
    }
//    public ObservableList<User> Add() throws ClassNotFoundException, SQLException{
//        ArrayList<User> users = new ArrayList<>();
//        conn.Conn();
//
//        resSet = statmt.executeQuery("SELECT * FROM users");
//
//
//        while(resSet.next()){
//            User user = new User(resSet.getString("name"), resSet.getString("surname"),resSet.getString("login"), resSet.getString("password"));
//            users.add(user);
//        }
//        ObservableList list1 = FXCollections.observableArrayList();
//        Iterator list2 = users.iterator();
//
//        while(list2.hasNext()){
//            User city = (User) list2.next();
//            list1.add(city);
//        }
//        return list1;
//    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        conn.Conn();
        conn.CreateDB();
        launch(args);
        conn.CloseDB();
    }
    public void changeBackgroundOnHoverUsingEvents(final Node node) {
        node.setStyle(STANDARD_BUTTON_STYLE);
        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle(HOVERED_BUTTON_STYLE);
            }
        });
        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle(STANDARD_BUTTON_STYLE);
            }
        });
    }
}

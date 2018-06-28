/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // —------Connection------
    public static void Conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:test.db");

        System.out.println("Connected to db!");
    }

    // —------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'surname' text, 'login' text, 'password' text);");

        System.out.println("Table created or exists");
    }

    public static void WriteDB(String name, String surname, String login, String password) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `users`('name', 'surname', 'login', 'password') VALUES (?,?,?,?)");
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, login);
        pstmt.setString(4, password);
        pstmt.executeUpdate();

        System.out.println("Inserted into table");
    }

    public static void Delete(String name) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE name = ?");
        pstmt.setString(1, name);
        pstmt.executeUpdate();

        System.out.println("Deleted from table");
    }

    public static void Update (StringProperty name, StringProperty surname, StringProperty login, StringProperty password, String name2) throws ClassNotFoundException, SQLException{
        PreparedStatement pstmt = conn.prepareStatement(" UPDATE users SET name = ?, surname=?, login=?, password=? WHERE name = ? ");
        pstmt.setString(1, String.valueOf(name));
        pstmt.setString(2, String.valueOf(surname));
        pstmt.setString(3, String.valueOf(login));
        pstmt.setString(4, String.valueOf(password));
        pstmt.setString(5, name2);

        pstmt.executeUpdate();

        System.out.println("Inserted into table");

    }
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {

        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String name = resSet.getString("name");
            String surname = resSet.getString("surname");
            String login = resSet.getString("login");
            String password = resSet.getString("password");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "surname = " + surname );
            System.out.println( "login = " + login );
            System.out.println( "password = " + password );

        }

        System.out.println("Table shown");
    }
    public static ObservableList<Main.User> sh() throws ClassNotFoundException, SQLException, NullPointerException{
        ArrayList<Main.User> ussers = new ArrayList<>();
//        Conn();


        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next()){
            Main.User user = new Main.User(resSet.getString("name"), resSet.getString("surname"),resSet.getString("login"), resSet.getString("password"));
            ussers.add(user);
        }
        ObservableList list1 = FXCollections.observableArrayList();
        Iterator list2 = ussers.iterator();

        while(list2.hasNext()){
            Main.User city = (Main.User) list2.next();
            list1.add(city);
        }
        return list1;
    }

    public static boolean CheckDB(String login, String password) throws ClassNotFoundException, SQLException{
        resSet=statmt.executeQuery("SELECT login, password FROM users WHERE login='" +login+"' AND password='"+password+"' ");
        if (resSet.next()){
            String login1 = resSet.getString("login");
            String password1= resSet.getString("password");
            System.out.print("login: "+login1);
            System.out.print("password: "+password1);
            return true;

        }
        return false;
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Connection closed");
    }


}

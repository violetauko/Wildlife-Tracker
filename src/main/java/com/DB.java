package com;

import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker","violet","3519ella");
public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-164-40-66.compute-1.amazonaws.com:5432/d4ssuc9g1odff3?sslmode=require","tngdnnalrwxdbh","5a073827c079753ac66748cb83d2e37a018447611fa0b7a6dff43d1de07c3bd0");
    public static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://ec2-54-164-40-66.compute-1.amazonaws.com:5432/d4ssuc9g1odff3?sslmode=require","tngdnnalrwxdbh","5a073827c079753ac66748cb83d2e37a018447611fa0b7a6dff43d1de07c3bd0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}}

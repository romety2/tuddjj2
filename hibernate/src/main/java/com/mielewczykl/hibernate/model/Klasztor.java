package com.mielewczykl.hibernate.model;

import java.sql.*;

public class Klasztor {

    private Connection con;

    private String url = "jdbc:jtds:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=lmielewczyk" + ";user=lmielewczyk" + ";password=224701";

    public Klasztor()
    {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException sqle) {
        }
    }

    public Connection polaczenie() {
        return con;
    }
}

package com.mielewczykl.hibernate.model.domain;

import java.sql.*;

public class Religia {

    private Connection con;

    private String url = "jdbc:jtds:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=lmielewczyk" + ";user=lmielewczyk" + ";password=224701";


    public Religia()
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

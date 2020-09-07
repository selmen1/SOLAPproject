package com.company.dao;

import com.company.crime.Crime;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

import java.sql.*;
import java.util.ArrayList;


public class CrimeDAO {

    private String url =  "jdbc:oracle:thin:@localhost:1521:orcl";
    private String user = "DBACRIME";
    private String pwd = "solap";

    /**
     * Get all crime on database
     * @return cirme list
     */
    public ArrayList<Crime> getAll() {
        Connection connection;
        ArrayList<Crime> crimes = new ArrayList<>();
        try {

            connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from CRIME");

            while (rs.next()) {
                int ID_CRIME = rs.getInt("ID_CRIME");
                int NUMZONE = rs.getInt("NUMZONE");
                System.out.println(rs);

                STRUCT st = (STRUCT) rs.getObject(3);
                //convert STRUCT into geometry
                JGeometry j_geom = JGeometry.load(st);
                crimes.add(new Crime(ID_CRIME, NUMZONE, j_geom));


            }
            connection.close();
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        return crimes;
    }

    public ArrayList<Integer> getNbCrime() {
        Connection connection;
        ArrayList<Integer> nbs = new ArrayList<>();
        try {

            connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from CRIME group by NUMZONE");

            while (rs.next()) {
                int nb = rs.getInt(1);
                System.out.println(rs);
                nbs.add(nb);
            }
            connection.close();
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        return nbs;
    }

}

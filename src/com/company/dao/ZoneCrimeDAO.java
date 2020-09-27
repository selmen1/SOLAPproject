package com.company.dao;

import com.company.crime.ZoneCrime;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

import java.sql.*;
import java.util.ArrayList;

public class ZoneCrimeDAO {
    private String url =  "jdbc:oracle:thin:@localhost:1521:orcl";
    private String user = "DBACRIME";
    private String pwd = "solap";

    /**
     * Get all zone_crime on database
     * @return zone_cirme list
     */
    public ArrayList<ZoneCrime> getAll() {
            Connection connection;
            ArrayList<ZoneCrime> zones = new ArrayList<>();
            try {

                connection = DriverManager.getConnection(url, user, pwd);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select NUMZONE, NOMEZONE, GEOM, SDO_GEOM.SDO_AREA(GEOM, 0.005), TAUXSEC, NIVEAUXVIE  from ZONE_CRIME");
                while (rs.next()) {
                    int NUMZONE = rs.getInt("NUMZONE");
                    String NOMEZONE = rs.getString("NOMEZONE");
                    Double TAUXSEC = rs.getDouble("TAUXSEC");
                    Double NIVEAUVIE = rs.getDouble("NIVEAUXVIE");
                    System.out.println(rs);
                    STRUCT st = (STRUCT) rs.getObject(3);
                    //convert STRUCT into geometry
                    JGeometry j_geom = JGeometry.load(st);
                    Double area = rs.getDouble(4);
                    zones.add(new ZoneCrime(NUMZONE, NOMEZONE, j_geom, area, TAUXSEC, NIVEAUVIE));

                }
                connection.close();
            } catch (SQLException e) {

                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return null;
            }

            return zones;
        }

    public ArrayList<Integer> getAdj(Integer id_z) {
        Connection connection;
        ArrayList<Integer> lzadjs = new ArrayList<>();
        try {

            connection = DriverManager.getConnection(url, user, pwd);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT a.NUMZONE, b.NUMZONE\n" +
                    "  FROM TABLE(SDO_JOIN('ZONE_CRIME', 'GEOM',\n" +
                    "                      'ZONE_CRIME', 'GEOM',\n" +
                    "                      'mask=ANYINTERACT')) c,\n" +
                    "       ZONE_CRIME a,\n" +
                    "       ZONE_CRIME b\n" +
                    "  WHERE c.rowid1 = a.rowid AND c.rowid2 = b.rowid AND a.NUMZONE=" + id_z +"\n" +
                    "  ORDER BY a.NUMZONE");

            while (rs.next()) {
                Integer adj = rs.getInt(2);
                lzadjs.add(adj);
            }
            lzadjs.remove(id_z);
            connection.close();
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        return lzadjs;
    }
    }

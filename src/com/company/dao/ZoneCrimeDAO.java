package com.company.dao;

import com.company.crime.ZoneCrime;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

import java.sql.*;
import java.util.ArrayList;

public class ZoneCrimeDAO {
    private String url =  "jdbc:oracle:thin:@localhost:1521:orcl";
    private String user = "pfef";
    private String pwd = "1234";

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
                ResultSet rs = statement.executeQuery("select id_mb, commune_mb, nbrcrimeg, nvieg, tauxsg, GEOMETRY, SDO_GEOM.SDO_AREA(GEOMETRY, 0.005) from commune");
                while (rs.next()) {
                    int NUMZONE = rs.getInt("id_mb");
                    String NOMEZONE = rs.getString("commune_mb");
                    Double NBCRIME   = rs.getDouble("nbrcrimeg");
                    Double TAUXSEC = rs.getDouble("tauxsg");
                    Double NIVEAUVIE = rs.getDouble("nvieg");
                    System.out.println(rs);
                    STRUCT st = (STRUCT) rs.getObject(6);
                    //convert STRUCT into geometry
                    JGeometry j_geom = JGeometry.load(st);
                    Double area = rs.getDouble(7);
                    zones.add(new ZoneCrime(NUMZONE, NOMEZONE, j_geom, area, TAUXSEC, NIVEAUVIE, NBCRIME));

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
            ResultSet rs = statement.executeQuery("SELECT a.id_mb, b.id_mb\n" +
                    "  FROM TABLE(SDO_JOIN('commune', 'GEOMETRY',\n" +
                    "                      'commune', 'GEOMETRY',\n" +
                    "                      'mask=ANYINTERACT')) c,\n" +
                    "       commune a,\n" +
                    "       commune b\n" +
                    "  WHERE c.rowid1 = a.rowid AND c.rowid2 = b.rowid AND a.id_mb="+ id_z +"\n" +
                    "  ORDER BY a.id_mb");

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

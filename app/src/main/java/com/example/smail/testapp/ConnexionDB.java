package com.example.smail.testapp;

/**
 * Created by smail on 16/03/17.
 */
import java.sql.*;
import java.util.Random;
public class ConnexionDB {

        ResultSet rs;
        Statement st;
        int ur;
        Connection con;

    public ConnexionDB() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection("jdbc:mysql://161.3.108.33:3306/db_proj", "tm06288p", "adoula23");
                System.out.println("Connexion reussi");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("connection failed");
            }


        }

    public void reqSQL(String query, char type) throws SQLException {

        if (type == 's') {
            st = con.createStatement();
            rs = st.executeQuery(query);
        }
        if (type == 'm') {
            PreparedStatement pstmt = con.prepareStatement(query);
            ur = pstmt.executeUpdate();
        }
    }

    public void afficheRequeteIntegration() throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "  " + rs.getString("name") + "  " + rs.getInt("age"));
        }

    }


}
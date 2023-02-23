package edu.jdbcexone;

import java.sql.*;

public class JdbcEx {
    public static void main(String[] args) {
        System.out.println("JDBC Start");
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // forName : driver를 찾아줌
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/malldb?useSSL=false&serverTimezone=Asia/Seoul", "root", "71dn6724!!");

            if (conn != null) {
                System.out.println("DB 연결 완료");

                statement = conn.createStatement();
                ResultSet srs = statement.executeQuery("select * from buytbl");
                printData(srs, "num", "userID", "prodName", "groupName", "price", "amount");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류");
        } catch (SQLException e) {
            System.out.println("DB 연결 오류");
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6) throws SQLException {
        while (srs.next()) {
            if (!col1.equals(""))
                System.out.print(srs.getString("num"));
            if (!col2.equals(""))
                System.out.print("\t|\t" + srs.getString("userID"));
            if (!col3.equals(""))
                System.out.printf("%-5s" + srs.getString("prodName"), "\t|\t");
            if (!col4.equals(""))
                System.out.printf("%-5s" + srs.getString("groupName"), "\t|\t");
            if (!col5.equals(""))
                System.out.printf("%-5s" + srs.getString("price"), "\t|\t");
            if (!col6.equals(""))
                System.out.println("\t|\t" + srs.getString("amount"));
            else
                System.out.println();
        }//end of while(rs.next() )
    }
}
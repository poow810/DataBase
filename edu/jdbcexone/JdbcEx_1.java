package edu.jdbcexone;

import java.sql.*;

public class JdbcEx_1 {
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
                ResultSet srs = statement.executeQuery("select * from usertbl");
                printData(srs,  "userID", "name","birthYear", "addr", "mobile1", "mobile2", "height", "mDate");
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

    private static void printData(ResultSet srs, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8) throws SQLException {
        while (srs.next()) {
            if (!col1.equals(""))
                System.out.print(srs.getString("userID"));
            if (!col2.equals(""))
                System.out.print("\t|\t" + srs.getString("name"));
            if (!col3.equals(""))
                System.out.printf("%-5s" + srs.getString("birthYear"), "\t|\t");
            if (!col4.equals(""))
                System.out.printf("%-5s" + srs.getString("addr"), "\t|\t");
            if (!col5.equals(""))
                System.out.printf("%-5s" + srs.getString("mobile1"), "\t|\t");
            if (!col6.equals(""))
                System.out.print("\t|\t" + srs.getString("mobile2"));
            if (!col7.equals(""))
                System.out.print("\t|\t" + srs.getString("height"));
            if (!col8.equals(""))
                System.out.println("\t|\t" + srs.getString("mDate"));
            else
                System.out.println();
        }//end of while(rs.next() )
    }
}

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
                ResultSet srs = statement.executeQuery("select * from student");
                System.out.println("특정 레코드만 검색");
                ResultSet s2 = statement.executeQuery("select * from student where name = '최고봉'");
                printData(statement);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
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

    private static void printData(Statement data) throws SQLException {
        ResultSet srs = data.executeQuery("select * from student");
        while(srs.next()){
            System.out.print(srs.getString("name"));
            System.out.print(srs.getString("\t|\t"+"id"));
            System.out.println(srs.getString("\t|\t"+"dept"));
        }//end of while(rs.next() )
    }
}

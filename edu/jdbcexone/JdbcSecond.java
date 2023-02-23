package edu.jdbcexone;

import java.sql.*;

public class JdbcSecond {
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
                printData(srs, "name", "id", "dept");

               // statement.executeUpdate("insert into student(name, id, dept)" + "values('강호동', '3333333', '기계공학');");
                // mysql 상에서 data가 이미 있기때문에 오류 발생
                // 중복 체크하는 코드가 필요함
                ResultSet s2 = statement.executeQuery("select * from student");
                System.out.println("record 삽입 후");
                printData(s2, "name", "id", "dept");

                statement.executeUpdate("update student set dept = '체육학과' where id = '3333333';");
                ResultSet s3 = statement.executeQuery("select * from student");
                printData(s3, "name", "id", "dept");
            }

        } catch (
                ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류");
        } catch (
                SQLException e) {
            System.out.println("DB 연결 오류");
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

    private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
        while (srs.next()) {
            if (!col1.equals(""))
                System.out.print(srs.getString("name"));
            if (!col2.equals(""))
                System.out.print("\t|\t" + srs.getString("id"));
            if (!col3.equals(""))
                System.out.println("\t|\t" + srs.getString("dept"));
            else
                System.out.println();
        }
    }
}
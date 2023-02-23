import java.sql.*;

public class BookDelete {
    public static void main(String[] args) {

        System.out.println("JDBC Start");
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // forName : driver를 찾아줌
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb?useSSL=false&serverTimezone=Asia/Seoul", "root", "71dn6724!!");

            if (conn != null) {
                System.out.println("DB 연결 완료");
                statement = conn.createStatement();
                statement.executeUpdate("delete from book where id = '1';");
                ResultSet srs = statement.executeQuery("select * from book");
                printData(srs, "id", "title", "publisher", "author");
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

    private static void printData(ResultSet srs, String col1, String col2, String col3, String col4) throws SQLException {
        System.out.println("id\t|\ttitle\t\t\t|\tpublisher\t|\tauthor");
        while (srs.next()) {
            if (!col1.equals(""))
                System.out.print(srs.getString("id"));
            if (!col2.equals(""))
                System.out.print("\t|\t" + srs.getString("title"));
            if (!col3.equals(""))
                System.out.print("\t|\t" + srs.getString("publisher"));
            if (!col4.equals(""))
                System.out.println("\t|\t" + srs.getString("author"));
            else
                System.out.println();
        }
    }
}

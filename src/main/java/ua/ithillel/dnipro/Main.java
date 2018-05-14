package ua.ithillel.dnipro;

import java.sql.*;

public class Main {

    private static Connection connection = null;
    private static String username = "student";
    private static String password = "password";
    private static String URL = "jdbc:mysql://localhost:3306/lesson_14"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    public static void main(String[] args) throws SQLException {
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());//Загружаем драйвер
            connection = DriverManager.getConnection(URL, username, password);          //соединяемся
            if(connection!=null) System.out.println("Connection is successful !\n");
            if (connection==null) System.exit(0);

        } catch (SQLException e){
            System.out.println("SQL connection is failed");
        }

        //Statement позволяет отправлять запросы базе данных
        Statement st = connection.createStatement();

        //Create
        st.execute("INSERT INTO users(user_name, pass) VALUE ('vasya', '12345')");
        st.execute("INSERT INTO users(user_name, pass) VALUE ('petya', '54321')");
        st.execute("INSERT INTO users(user_name, pass) VALUE ('vasya', '1123123123123')");

        //Update
        try {
            st.executeUpdate("UPDATE users SET pass = '777' WHERE user_name = 'petya'");
        } catch (SQLException e){
            System.out.println("update is failed");
        }

        //Delete
        try {
            st.executeUpdate("DELETE FROM users WHERE id ='10'");
        } catch (SQLException e){
            System.out.println("Something's wrong here");
        }

        //ResultSet получает результирующую таблицу
        ResultSet rs = st.executeQuery("select * from users");
        //Resultset.getMetaData()получаем информацию результирующей т
        int x = rs.getMetaData().getColumnCount();
        while(rs.next()){
            for(int i=1; i<=x;i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        if(rs!=null)rs.close();
        if(st!=null)st.close();
        if(connection!=null)connection.close();
    }
}

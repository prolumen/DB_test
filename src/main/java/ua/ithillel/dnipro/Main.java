package ua.ithillel.dnipro;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;


public class Main {

    private static Connection con = null;
    private static String username = "root";
    private static String password = "RdJOsdi14";
    private static String URL = "jdbc:mysql://127.0.0.1:3306/lesson_14";

    public static void main(String[] args) throws SQLException {
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());//Загружаем драйвер
            con = DriverManager.getConnection(URL, username, password);          //соединяемся
            if(con!=null) System.out.println("Connection Successful !\n");
            if (con==null) System.exit(0);
            Statement st = con.createStatement(); //Statement позволяет отправлять запросы базе данных
            ResultSet rs = st.executeQuery("select * from TABLENAME");
            //ResultSet получает результирующую таблицу
            int x = rs.getMetaData().getColumnCount();//Resultset.getMetaData()получаем информацию результирующей т
            while(rs.next()){
                for(int i=1; i<=x;i++){
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            System.out.println();
            if(rs!=null)rs.close();
            if(st!=null)st.close();
            if(con!=null)con.close();
        } catch (SQLException e){
            System.out.println("SQL connection failed");
        }

    }
}

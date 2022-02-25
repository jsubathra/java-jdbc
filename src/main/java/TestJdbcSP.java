import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class TestJdbcSP {

    public static void main (String[] args) {
        JdbcSPDao dao = new JdbcSPDao();
       // dao.callSimpleSP();
        dao.callSPWithINOUT();
    }

}

class JdbcSPDao {
    Connection conn = null;
    CallableStatement cs = null;
    public void callSimpleSP() {

        String department = "Engineering";
        int increaseSalary = 10000;
        //2) Load and Register Driver
        try {
            connect();
            cs = conn.prepareCall("{call increase_salaries_for_department (?,?) }");
            cs.setString(1, department);
            cs.setDouble(2, increaseSalary);
            cs.execute();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callSPWithINOUT() {

        String department = "Engineering";
        int increaseSalary = 10000;
        //2) Load and Register Driver
        try {
            connect();
            cs = conn.prepareCall("{call greet_the_department (?) }");

            // define it is an INOUT paramerer and set the value
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(1, department);

            cs.execute();

            String rs = cs.getString(1);
            System.out.println(rs);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void connect() {
        String url = "jdbc:mysql://mars2home.mysql.database.azure.com:3306/demo";
        String Username = "mysql@mars2home";
        String Password = "Mydb2021!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, Username, Password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void close() {

        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

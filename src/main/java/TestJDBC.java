import java.rmi.server.UID;
//1) Import Package
import java.sql.*;
public class TestJDBC {

    public static void main (String[] args) throws Exception{
        //DB Url/username and pwd
        String url ="jdbc:mysql://mars2home.mysql.database.azure.com:3306/testdb";
        String Username ="mysql@mars2home";
        String Password ="Mydb2021!";

       //2) Load and Register Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //3) Establish Connection
        Connection conn = DriverManager.getConnection(url,Username,Password);

        //4) Create the Statement
        Statement st = conn.createStatement();

        //5) Execute Query
        //Insert with Values then executeUpdate with Conn and CreateSt
        String insertQuery ="insert into student values ( 3, \"Agaran\")";
        //Update Insert
        int count = st.executeUpdate(insertQuery);
        System.out.println(count);


        //Select only query then use createSt/resultset and executeQuery
        String query ="select * from student";
        //DQL
        ResultSet rs = st.executeQuery(query);

       // 6) Process Results
        while(rs.next()) {
            //String name = rs.getString("username");
            //System.out.println(name);
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

        int uId = 4;
        String name = "Mithran";
        String prepQuery = "insert into student values (?,?)";

        // If same query but runtime or more values then we will use PreparedStatement
        PreparedStatement st1 = conn.prepareStatement(prepQuery);
        st1.setInt(1, uId);
        st1.setString(2, name);
        int count1 = st1.executeUpdate();
        System.out.println(count1);


        // Closing Connection
        st.close();
        conn.close();

    }
}

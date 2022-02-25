import java.sql.*;
public class TestJdbcDAO {

    public static void main(String[] args){
        StudentDAO dao = new StudentDAO();
        dao.connect();
        Student st = dao.getStudent(1);
        System.out.println(st.sUId + " " + st.sUName);
        Student st2 = new Student();
        st2.sUName = "Rithvik";
        st2.sUId = 4;
        dao.addStudent(st2);

    }
}


 class StudentDAO{
     Connection conn = null;

    public void connect(){
        String url ="jdbc:mysql://mars2home.mysql.database.azure.com:3306/testdb";
        String Username ="mysql@mars2home";
        String Password ="Mydb2021!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,Username,Password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Student getStudent(int userID){
        String query = "Select username from student where userId= ? ";
        Student student = new Student();
        student.sUId = userID;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,student.sUId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String sUserName = rs.getString(1);
            student.sUName = sUserName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public void addStudent(Student st){

        String insertQuery = "INSERT INTO STUDENT VALUES (?,?)";
        PreparedStatement ps;
        try {
            ps= conn.prepareStatement(insertQuery);
            ps.setInt(1,st.sUId);
            ps.setString(2,st.sUName);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

class Student {
    int sUId;
    String sUName;

}

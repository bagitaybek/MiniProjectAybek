import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Scanner in = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/group2601?useUnicode=true&serverTimezone=UTC","root", ""
        );
        ServerSocket server = new ServerSocket(2000);
        Socket socket = server.accept();
        System.out.println("CLIENT CONNECTED");
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        while(true){
            String request = (String) inputStream.readObject();
            if (request.equals("ADDSTUDENT")) {
                Student student = (Student) inputStream.readObject();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO students(id,name,surname,age) VALUES(null,?,?,?)");
                ps.setString(1, student.name);
                ps.setString(2, student.surname);
                ps.setInt(3, student.age);
                ps.executeUpdate();
            }
            if (request.equals("LISTSTUDENTS")){
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM students");
                ResultSet resultSet = ps.executeQuery();
                ArrayList<Student> students = new ArrayList<>();
                while(resultSet.next()){
                    Long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    students.add(new Student(id,name,surname,age));
                }
                outputStream.writeObject(students);
                System.out.println("LIST SENT TO CLIENT");
            }
        }
    }
}
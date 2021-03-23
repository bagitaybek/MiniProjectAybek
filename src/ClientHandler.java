import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    Connection connection;
    Socket socket;

    public ClientHandler(Socket socket, Connection connection) throws IOException {
        this.connection = connection;
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = (String) inputStream.readObject();
                if (request.equals("ADDSTUDENT")) {
                    Student student = (Student) inputStream.readObject();
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO students(id,name,surname,age) VALUES(null,?,?,?)");
                    ps.setString(1, student.name);
                    ps.setString(2, student.surname);
                    ps.setInt(3, student.age);
                    ps.executeUpdate();
                }
                if (request.equals("LISTSTUDENTS")) {
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM students");
                    ResultSet resultSet = ps.executeQuery();
                    ArrayList<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        Long id = resultSet.getLong(1);
                        String name = resultSet.getString(2);
                        String surname = resultSet.getString(3);
                        int age = resultSet.getInt(4);
                        students.add(new Student(id, name, surname, age));
                    }
                    outputStream.writeObject(students);
                    System.out.println("LIST SENT TO CLIENT");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("192.168.1.127",2000);
        MainFrame frame = new MainFrame();
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
      }
    public static void sendStudentToServer(Student student) throws IOException {
        outputStream.writeObject("ADDSTUDENT");
        outputStream.writeObject(student);
    }
    public static ArrayList<Student> getStudentsFromServer() throws IOException, ClassNotFoundException {
        outputStream.writeObject("LISTSTUDENTS");
        ArrayList<Student> students = (ArrayList<Student>) inputStream.readObject();
        return students;
    }
}
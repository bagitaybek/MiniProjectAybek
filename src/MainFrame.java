import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {
    AddUserPage addUserPage;
    MenuPage menuPage;
    ListUsersPage listUsersPage;
    public MainFrame(){
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BITLAB app");
        setLayout(null);

        menuPage = new MenuPage(this);
        add(menuPage);
        menuPage.setVisible(true);

        addUserPage = new AddUserPage(this);
        add(addUserPage);
        addUserPage.setVisible(false);

        listUsersPage = new ListUsersPage(this);
        add(listUsersPage);
        listUsersPage.setVisible(false);

        setVisible(true);
    }
}

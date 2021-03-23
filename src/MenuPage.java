import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuPage extends JPanel {
    public MenuPage(MainFrame frame){
        setSize(500,500);
        setLayout(null);

        JButton addUserPageButton = new JButton("ADD NEW USER");
        addUserPageButton.setBounds(100,100,300,40);
        add(addUserPageButton);

        JButton listUsersPageButton = new JButton("LIST USERS");
        listUsersPageButton.setBounds(100,150,300,40);
        add(listUsersPageButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(100,200,300,40);
        add(exitButton);

        addUserPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.addUserPage.setVisible(true);
            }
        });

        listUsersPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.listUsersPage.setVisible(true);
                try {
                    frame.listUsersPage.generateTable(Client.getStudentsFromServer());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

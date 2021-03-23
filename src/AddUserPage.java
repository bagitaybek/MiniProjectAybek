import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddUserPage extends JPanel {
    public AddUserPage(MainFrame frame){
        setSize(500,500);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(100,100,100,40);
        add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(200,100,200,40);
        add(nameTextField);

        JLabel surnameLabel = new JLabel("Surname: ");
        surnameLabel.setBounds(100,150,100,40);
        add(surnameLabel);

        JTextField surnameTextField = new JTextField();
        surnameTextField.setBounds(200,150,200,40);
        add(surnameTextField);

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(100,200,100,40);
        add(ageLabel);

        Integer [] ages = {1,2,3,4,5,6,7,8,9,10};
        JComboBox ageComboBox = new JComboBox(ages);
        ageComboBox.setBounds(200,200,200,40);
        add(ageComboBox);

        JButton addButton = new JButton("ADD USER");
        addButton.setBounds(100,250,300,40);
        add(addButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(100,300,300,40);
        add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                nameTextField.setText("");
                String surname = surnameTextField.getText();
                surnameTextField.setText("");
                int age = (int) ageComboBox.getSelectedItem();
                ageComboBox.setSelectedIndex(0);
                Student student = new Student(null,name,surname,age);
                try {
                    Client.sendStudentToServer(student);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}

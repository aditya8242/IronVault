import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IronVaultGUI {

    public static void main(String[] args) {
        // 1. Create the main window (Frame)
        JFrame frame = new JFrame("IronVault");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centers the window on screen

        // 2. Create a layout panel (Grid layout: 4 rows, 2 columns)
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 3. Create the UI Components
        JLabel lblSource = new JLabel("Source Dir / Archive File:");
        JTextField txtSource = new JTextField();

        JLabel lblDestination = new JLabel("New Archive Name (Packing):");
        JTextField txtDestination = new JTextField();
		txtDestination.setToolTipText("Only required when packing. Leave blank to unpack an archive.");

        JLabel lblPassword = new JLabel("Vault Password:");
        JPasswordField txtPassword = new JPasswordField(); // Masks the text!

        JButton btnPack = new JButton("Pack Files");
        JButton btnUnpack = new JButton("Unpack Archive");

        // 4. Add components to the panel
        panel.add(lblSource);
        panel.add(txtSource);
        panel.add(lblDestination);
        panel.add(txtDestination);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnPack);
        panel.add(btnUnpack);

        // 5. Button Click Events
        btnPack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String source = txtSource.getText();
                    String destination = txtDestination.getText();
                    String password = new String(txtPassword.getPassword()); // Safely extract password

                    // Trigger your backend logic!
                    Pack.packDirectory(source, destination, password);
                    JOptionPane.showMessageDialog(frame, "Packing Complete!");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        btnUnpack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String source = txtSource.getText();
                    String password = new String(txtPassword.getPassword());

                    // Trigger your backend logic!
                    Unpack.unpackArchive(source, password);
                    JOptionPane.showMessageDialog(frame, "Unpacking Complete!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        // 6. Display the window
        frame.add(panel);
        frame.setVisible(true);
    }
}
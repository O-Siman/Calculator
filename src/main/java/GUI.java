import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private static JButton startButton;
    private static JButton stopButton;

    private static JCheckBox useTokenBox;
    private static JTextField tokenField;

    public static JLabel label;

    public GUI() {
        //Make a frame
        JFrame frame = new JFrame();

        //Make a label
        label = new JLabel("Click below to start the bot");

        //Make a button and setup
        startButton = new JButton("Start Bot");
        startButton.addActionListener(this);

        //Make a button and setup
        stopButton = new JButton("Stop Bot");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);

        //Make checkbox and setup
        useTokenBox = new JCheckBox("Use custom bot token");
        useTokenBox.addActionListener(this);

        //Field to paste token
        tokenField = new JTextField("Token");
        tokenField.setEnabled(false);

        //Make a panel and set it up
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(useTokenBox);
        panel.add(tokenField);
        panel.add(startButton);
        panel.add(stopButton);

        //Set up the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Discord Calculator Bot");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //If button pressed is start button or stop button or checkmark
        if (event.getSource() == startButton) {
            //When button is clicked, code here will run
            startButton.setEnabled(false);
            label.setText("Starting bot...");

            if (useTokenBox.isSelected()) {
                Main.token = tokenField.getText();
            }

            //Start bot
            try {
                 if (!Main.startBot())
                     return;
            } catch (InterruptedException e) {
                label.setText("Unable to start bot: " + e);
                e.printStackTrace();
            }

            label.setText("Bot running!");
            stopButton.setEnabled(true);
            useTokenBox.setEnabled(false);
            tokenField.setVisible(false);
        } else if (event.getSource() == stopButton) {
            Main.api.shutdown();
            stopButton.setEnabled(false);
            label.setText("Bot stopped");
            System.out.println("Bot shutdown");
            startButton.setEnabled(true);
            useTokenBox.setEnabled(true);
            tokenField.setVisible(true);
        } else if (event.getSource() == useTokenBox) {
            if (useTokenBox.isSelected()) {
                tokenField.setEnabled(true);
            } else {
                tokenField.setEnabled(false);
            }
        }
    }

    public static void resetGUI() {
        useTokenBox.setEnabled(true);
        useTokenBox.setVisible(true);
        useTokenBox.setSelected(false);

        tokenField.setVisible(true);
        tokenField.setEnabled(false);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }
}

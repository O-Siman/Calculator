import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JButton startButton;
    private JButton stopButton;
    private JLabel label;

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

        //Make a panel and set it up
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(startButton);
        panel.add(stopButton);

        //Set up the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //If button pressed is start button or stop button
        if (event.getSource() == startButton) {
            //When button is clicked, code here will run
            startButton.setEnabled(false);
            label.setText("Starting bot...");
            //Start bot
            try {
                Main.startBot();
            } catch (LoginException e) {
                label.setText("Unable to start bot: " + e);
                e.printStackTrace();
            } catch (InterruptedException e) {
                label.setText("Unable to start bot: " + e);
                e.printStackTrace();
            }
            label.setText("Bot running!");
            stopButton.setEnabled(true);
        } else if (event.getSource() == stopButton) {
            Main.api.shutdown();
            stopButton.setEnabled(false);
            label.setText("Bot stopped");
            System.out.println("Bot shutdown");
            startButton.setEnabled(true);
        }
    }
}

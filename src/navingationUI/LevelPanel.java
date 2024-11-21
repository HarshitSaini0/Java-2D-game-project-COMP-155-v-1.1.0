package navingationUI;

import data.GameLevel;
import data.PlayerData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelPanel extends JPanel implements ActionListener {
    Run run;

    public JPanel topPanel;
    public JPanel bottomPanel;
    public JButton backButton;
    public JLabel playerProfile;


    public int numberOfLevels;
    public String playerID;
    public int []timingsOfLevels = new int[PlayerData.NUMBER_OF_GAME_LEVELS];
    public GameLevel gameLevel;
    public PlayerData playerData;

    public LevelPanel(Run run, String playerID) {
        this.setLayout(null);
        this.run = run;
        this.playerID = playerID;
        this.playerData = new PlayerData(this.playerID);
        this.numberOfLevels = this.playerData.gameLevel;
        this.gameLevel = new GameLevel(this.playerData);
        this.setBackground(new Color(102, 246, 106));

        topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 500, 200);

        backButton = new JButton("Go back");
        backButton.setPreferredSize(new Dimension(200, 80));
        backButton.setBounds(50, 80, 200, 80);
        backButton.addActionListener(e -> {
            this.run.goToPlayerPanel();
        });
        topPanel.add(backButton);

        // Using HTML to enable line breaks and text wrapping
        String labelText = "<html>" + this.playerData.name + "<br>" + this.playerID + "</html>";

        playerProfile = new JLabel(labelText);
        playerProfile.setBounds(300, 80, 150, 80);
        playerProfile.setPreferredSize(new Dimension(150, 80));
        playerProfile.setVerticalAlignment(SwingConstants.TOP); // Align text to top if multiline
        playerProfile.setHorizontalAlignment(SwingConstants.LEFT); // Align text to left

        topPanel.add(playerProfile);

        topPanel.setBackground(new Color(1, 1, 1));
        this.add(topPanel);

        this.setVisible(true);
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){

        }


    }
}

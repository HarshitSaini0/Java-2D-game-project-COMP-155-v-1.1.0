package navingationUI;

import javax.swing.*;

class Run {
    public  JFrame frame;
    public  PlayerPanel playerPanel;
    public  LevelPanel levelPanel;

    public Run(){
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        playerPanel = new PlayerPanel(this);
        frame.add(playerPanel);
        frame.setVisible(true);
    }



    public void goToPlayerPanel() {
        frame.getContentPane().removeAll();
        playerPanel = new PlayerPanel(this);
        frame.add(playerPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void goToLevelPanel(String playerID) {
        frame.getContentPane().removeAll();
        levelPanel = new LevelPanel(this, playerID); // Pass this instance to LevelPanel
        frame.add(levelPanel);
        frame.revalidate();
        frame.repaint();
    }
}

public class MainFrame {
    public static void main(String[] args) {
        Run run = new Run();
    }
}
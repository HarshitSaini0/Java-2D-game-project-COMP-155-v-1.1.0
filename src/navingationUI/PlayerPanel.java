package navingationUI;

import data.PlayerData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerPanel extends JPanel implements ActionListener {

    private Run run;

    private final int MAX_NUMBER_OF_PLAYERS = 4;
    String FILE_PATH = "player.txt";

    JPanel buttonsPanel = new JPanel();
    JPanel playersPanel = new JPanel();

    JButton addPlayerButton ;
    JButton delPlayerButton ;

    public int numberOfPlayers;
    HashMap <String,String> players = new HashMap<>();



    public PlayerPanel(Run run){
        this.run = run;
        this.setLayout(null);

        // buttons
        addPlayerButton = new JButton("Add Player");
        delPlayerButton = new JButton("Delete Player");
        buttonsPanel.setBounds(0,0,500,200);
        buttonsPanel.setLayout(null);
        buttonsPanel.setBackground(new Color(255, 130, 130));
        addPlayerButton.setFocusable(false);
        addPlayerButton.setBounds(100,80,100,40);
        buttonsPanel.add(addPlayerButton);

        delPlayerButton.setFocusable(false);
        delPlayerButton.setBounds(300,80,100,40);
        buttonsPanel.add(delPlayerButton);

       this.add(buttonsPanel);



       //players
        playersPanel.setBounds(0,200,500,300);
        playersPanel.setBackground(new Color(139, 158, 255));

        playersPanel.setLayout(new FlowLayout());
        playersPanel.setBorder(new EmptyBorder(10, 20, 10, 20));


        loadPlayers(this.FILE_PATH);
        updateButtons();

        addPlayerButton.addActionListener(e->{
            int mapSize = players.size();
            if(mapSize<4 ) {
                addPlayer();
                loadPlayers(this.FILE_PATH);
                updateButtons();
            }else{
                JOptionPane.showMessageDialog(
                        null,
                        "Maximum number of players reached",
                        "Can't add player",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        delPlayerButton.addActionListener(e->{
            deletionProcess();
            loadPlayers(this.FILE_PATH);
            updateButtons();
        });



        this.add(playersPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addPlayerButton){
            int mapSize = players.size();
            if(mapSize<4){
                addPlayer();
                loadPlayers(this.FILE_PATH);
                updateButtons();
            }

        }
        if(e.getSource()==delPlayerButton){
            deletionProcess();
            loadPlayers(this.FILE_PATH);
            updateButtons();
        }

    }

    public void updateButtons(){
        playersPanel.removeAll();
        for(Map.Entry<String,String > entry : players.entrySet()){
            String buttonText = entry.getValue()+" ("+entry.getKey()+")";
            JButton button = new JButton(buttonText);
            button.setPreferredSize(new Dimension(200,80));
            button.setFocusable(false);
            button.addActionListener(e->{
                run.goToLevelPanel(entry.getKey());
            });
            playersPanel.add(button);
        }
        playersPanel.revalidate();
        playersPanel.repaint();
    }

    public void loadPlayers(String fileName){
        players.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String playerID = parts[0]; // Player ID is the first part
                String name = parts[1];
                players.put(playerID,name);

            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading player data from file: " + e.getMessage());
        }
    }


    public void addPlayer(){
        String name,age;

        name = JOptionPane.showInputDialog("Enter your name");
        age = JOptionPane.showInputDialog("Enter your age");

        if(!isValidInteger(age) && name==null && age==null){
            JOptionPane.showMessageDialog(buttonsPanel,"Enter valid age or name","error",JOptionPane.INFORMATION_MESSAGE);
            return;
        }



        PlayerData playerData = new PlayerData(name.trim(),Integer.parseInt(age));
        playerData.saveToFile(this.FILE_PATH);
    }
    public void deletionProcess(){
        int mapSize = players.size();

        String [] options = new String[mapSize+1];
        String [] playerIDs = new String[mapSize];
        int index = 0;
        for(Map.Entry<String,String> entry:players.entrySet()){
            options[index]=entry.getValue()+"("+entry.getKey()+")";
            playerIDs[index]=entry.getKey();
            index++;
        }
        options[index]="CANCEL";

        int response = JOptionPane.showOptionDialog(
                null,
                "Select player",
                "delete a player",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                0
        );
//        System.out.println(response);
//        System.out.println(playerIDs[response]);
        if(mapSize>0){
            if(response!=mapSize+1) {
                String id = playerIDs[response];
                delPlayer(id);
                JOptionPane.showMessageDialog(
                        null,
                        "Player with ID : "+id+"("+players.get(id)+") \n deleted successfully",
                        "Successful deletion",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "No player was deleted",
                        "Process canceled",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    public void delPlayer(String playerID){
        PlayerData.deletePlayer(this.FILE_PATH,playerID);

    }




    public static boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // valid integer
        } catch (NumberFormatException e) {
            return false; // invalid integer
        }
    }


}

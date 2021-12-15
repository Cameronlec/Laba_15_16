package restaurant;

import javax.swing.*;

public class BeginWork extends JFrame{
    private JPanel MainPanel;
    final static int numberOfButtons=5;

    public static void main(String[] args) {
        BeginWork mainFrame= new BeginWork();
        mainFrame.setVisible(true);

    }
    JButton buttons[]=new JButton[numberOfButtons];
    public BeginWork(){
        super("Demo Rest");
        for(int i=0;i<numberOfButtons;i++) {
            buttons[i] = new JButton("Button" + i);
            MainPanel.add(buttons[i]);
        }
        add(new JScrollPane(MainPanel));
        setVisible(true);
    }
}

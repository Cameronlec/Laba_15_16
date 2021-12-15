package restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant");
        frame.setContentPane(new Restaurant().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
//        frame.add(new JButton("sdbhsb"));
//        frame.getRootPane().add(new JButton("sdbhsb"));
//        frame.getContentPane().add(new JButton("sdbhsb"));
        frame.pack();
        frame.setVisible(true);

    }

    TableOrdersManager tableOrdersManager;
    InternetOrdersManager internetOrdersManager;
    private JButton tableNumberButton;
    private JButton newInternetOrderButton;
    private JPanel mainPanel;
    private JScrollPane scrlTableOrders;
    private JPanel Tables;
    private JPanel TablesOrdersListButtons;
    private JPanel MainView;

    public Restaurant(){

//        super("The best Restaurant in the World");
//        mainPanel =new JPanel();
        tableOrdersManager = new TableOrdersManager();
        internetOrdersManager = new InternetOrdersManager();

//        setContentPane(mainPanel);
//        setSize(800, 600);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
//        JPanel panel=new JPanel();
//        int numberOfButtons=tableOrdersManager.getTablesQuantity();
//        JButton buttons[]=new JButton[numberOfButtons];
//        //Button[] buttons=new Button[15];
//        for(int i=0; i<numberOfButtons;i++){
//            buttons[i]=new JButton("Button"+i);
//            mainPanel.add(buttons[i]);
//            scrlTableOrders.add(new Button("eee №"+i), i);

//            Tables.add(new Button(""+i), i);
//            mainPanel.add(new Button("bbb №"+i), i);
//            MainView.add(new Button("aaa №"+i), i);
//            TablesOrdersListButtons.add(new Button("ccc №"+i), i);

            /*
            buttons[i]=new Button(""+i);
            buttons[i].setVisible(true);
            buttons[i].setSize(30, 30);
            buttons[i].setBounds(0, i*30, 30, 30);
            Tables.add(buttons[i], i);
*/
//        }
        //mainPanel.add(new JScrollPane(panel));

//        newInternetOrderButton.addActionListener(new ActionListener() {
//            /**
//             * Invoked when an action occurs.
//             *
//             * @param e the event to be processed
//             */
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                internetOrdersManager.add(new InternetOrder());
//                newOrder dialog = new newOrder();
//                dialog.pack();
//                dialog.setVisible(true);
//
//            }
//        });

//        tableNumberButton.addActionListener(new ActionListener() {
//            /**
//             * Invoked when an action occurs.
//             *
//             * @param e the event to be processed
//             */
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TableOrder order =new TableOrder(1, 1);
//
//                //tableOrdersManager.addOrder(1, new TableOrder(1,1));
//            }
//        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        mainPanel =new JPanel();
        JPanel panel=new JPanel();
//        int numberOfButtons=tableOrdersManager.getTablesQuantity();
        int numberOfButtons=15;
        JButton buttons[]=new JButton[numberOfButtons];
        //Button[] buttons=new Button[15];
        for(int i=0; i<numberOfButtons;i++) {
            buttons[i] = new JButton("Button" + i);
            panel.add(buttons[i]);
        }
        //JScrollPane vScroll = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
    }
}


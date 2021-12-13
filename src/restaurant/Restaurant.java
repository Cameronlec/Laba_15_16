package restaurant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant extends JFrame{
    TableOrdersManager tableOrdersManager;
    InternetOrdersManager internetOrdersManager;
    private JButton tableNumberButton;
    private JButton internetOrder1Button;
    private JButton newInternetOrderButton;
    private JPanel mainPanel;
    private JScrollPane scrlTableOrders;
    private JScrollPane scrlInternetOrders;
    private JTabbedPane tabbedPane1;

    Restaurant(){
        tableOrdersManager = new TableOrdersManager();
        internetOrdersManager = new InternetOrdersManager();

        setContentPane(mainPanel);
        setTitle("The best Restaurant in the World");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        for(int i=0; i<tableOrdersManager.getTablesQuantity();i++){
            //scrlTableOrders.
        }
        newInternetOrderButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                internetOrdersManager.add(new InternetOrder());
                newOrder dialog = new newOrder();
                dialog.pack();
                dialog.setVisible(true);

            }
        });
        tableNumberButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                TableOrder order =new TableOrder(1, 1);

                //tableOrdersManager.addOrder(1, new TableOrder(1,1));
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("Windows");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
//        JFrame.setDefaultLookAndFeelDecorated( true );
//        JDialog.setDefaultLookAndFeelDecorated( true );
        Restaurant frame=new Restaurant();
    }

}

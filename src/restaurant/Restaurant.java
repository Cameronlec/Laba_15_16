package restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant {
    static class MenuItemButton extends JButton {
        private MenuItem menuItem;
    }

    Dish[] dishes= new Dish[]{
            new Dish(150, "Суп-пюре", "Суп-пюре морковно-тыквенный"),
            new Dish(90, "Котлета", "Котлета из телятины"),
            new Dish(70, "Салат", "Салат из помидоров и огурцов"),
            new Dish(210, "Плов", "Плов из баранины")
    };
    Drink[] drinks=new Drink[]{
            new Drink("Кофе", "Кофе растворимый", 50, 0.0, DrinkTypeEnum.COFFEE),
            new Drink("Вино белое", "Вино белое столовое", 150, 8.5, DrinkTypeEnum.WINE),
            new Drink("Чай зеленый", "Чай зеленый пакетик", 49, 0.0, DrinkTypeEnum.GREEN_TEA)

    };
    private  JPanel mainPanel;
    private JPanel drinksPanel;
    private  JButton[] Zal_1_TabelsButtons;
    private JScrollPane tablesListPanel;
    private JButton a1Button;
    private JPanel content;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant");
        frame.setContentPane(new Restaurant().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
//        frame.add(new JButton("sdbhsb"));
        frame.getRootPane().add(new JButton("sdbhsb"));
        frame.getContentPane().add(new JButton("sdbhsb"));
        frame.pack();
        frame.setVisible(true);

    }

    TableOrdersManager tableOrdersManager;
    InternetOrdersManager internetOrdersManager;
    private JButton tableNumberButton;
    private JButton newInternetOrderButton;
    // TODO: place custom component creation code here

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

        ActionListener tablePressAction =new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                String text=btn.getText();
                Integer num =Integer.parseInt(text);

                TableOrder order=new TableOrder(0, num);


                drinksPanel=new JPanel();

                JPanel panel=new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        int numberOfButtons=tableOrdersManager.getTablesQuantity();
                int numberOfButtons=drinks.length;
                MenuItemButton buttons[]=new MenuItemButton[numberOfButtons];
                //Button[] buttons=new Button[15];
                for(int i=0; i<numberOfButtons;i++) {
                    MenuItemButton menuItemAddButton = new MenuItemButton() ;
                    menuItemAddButton.setText(drinks[i].getName()+"\n"+drinks[i].getCost());
                    menuItemAddButton.menuItem=drinks[i];

                    menuItemAddButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            MenuItemButton btn = (MenuItemButton)e.getSource();

                            order.add(btn.menuItem);
                            //list1.add(btn);
                        }
                    });
                    buttons[i] = menuItemAddButton;
                    panel.add(buttons[i]);
                }
                //JScrollPane vScroll = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JScrollPane tablesPanel=new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets ins = new Insets(2, 2, 2, 2);
                GridBagConstraints gbc = new GridBagConstraints(0, 2, 2, 2, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);
                mainPanel.add(tablesPanel, gbc);
                mainPanel.setVisible(true);

            }
        };
        a1Button.addActionListener(tablePressAction);
        a2Button.addActionListener(tablePressAction);
        a3Button.addActionListener(tablePressAction);
        a4Button.addActionListener(tablePressAction);
        a5Button.addActionListener(tablePressAction);
        a6Button.addActionListener(tablePressAction);
        a7Button.addActionListener(tablePressAction);

    }

//    private void createNewTableOrder(int tableNumber){
//
//    }
    private void createUIComponents() {
        mainPanel = new JPanel(new GridLayout(7, 9));

        /*
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        int numberOfButtons=tableOrdersManager.getTablesQuantity();
        int numberOfButtons=15;
        JButton buttons[]=new JButton[numberOfButtons];
        //Button[] buttons=new Button[15];
        for(int i=0; i<numberOfButtons;i++) {
            buttons[i] = new JButton("Table " + i);
            panel.add(buttons[i]);
        }
        //JScrollPane vScroll = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane tablesPanel=new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        mainPanel.add(tablesPanel);

         */
    }
}


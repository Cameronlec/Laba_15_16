package restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant {
    static class MenuItemButton extends JButton {
        private MenuItem menuItem;
        public MenuItemButton(MenuItem menuItem, Order order, JPanel orderListPanel, JLabel orderTotalLable){
            this.menuItem=menuItem;

            setText(menuItem.getName()+"\n"+menuItem.getCost());
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MenuItemButton btn = (MenuItemButton)e.getSource();
                    order.add(btn.menuItem);

                    orderListPanel.add(new orderItemButton(btn.menuItem, order, orderTotalLable), orderListPanel.getComponentCount()-1 );
                    orderTotalLable.setText("Order total: "+order.getOrderCost());

                    orderListPanel.getRootPane().setVisible(false);
                    orderListPanel.getRootPane().setVisible(true);
//                    orderListPanel.getParent().setVisible(false);
//                    orderListPanel.getParent().setVisible(true);

//                    MenuItem[]items=order.getItemsArray();
//                    for (int i=0; i< items.length;i++)
//                         {
//                        System.out.println(items[i].getName());
//                    }

                }
            });

        }
    }

    static class orderItemButton extends JButton {
        private MenuItem menuItem;
        public orderItemButton(MenuItem menuItem, Order order, JLabel orderTotalLable) {
            this.menuItem = menuItem;
            setText(menuItem.getName()+"\n"+menuItem.getCost());
            setOpaque(true);
            setBackground(Color.WHITE);

            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    orderItemButton btn = (orderItemButton)e.getSource();
                    order.deleteItemByMenuItem(btn.menuItem);
                    Container parent=btn.getParent();
                    parent.remove(btn);
                    orderTotalLable.setText("Order total: "+order.getOrderCost());
                    parent.setVisible(false);
                    parent.setVisible(true);

                }
            });
        }

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
    private JScrollPane drinksScrollPanel;
    private JScrollPane dishesScrollPanel;
    private JScrollPane orderItemsScrollPanel;

    private JPanel drinksPanel;
    private JPanel dishesPanel;
    private JPanel orderItemsPanel;
    private JLabel orderTotalLable;
    private JButton[] Zal_1_TabelsButtons;
    private JScrollPane tablesListPanel;
    private JPanel content;

    private JButton a1Button;
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
        frame.setSize(800, 600);
//        frame.add(new JButton("sdbhsb"));
//        frame.getRootPane().add(new JButton("sdbhsb"));
//        frame.getContentPane().add(new JButton("sdbhsb"));
//        frame.pack();
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

        ActionListener tablePressAction =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                String text=btn.getText();
                Integer tableNum =Integer.parseInt(text);
                int orderNum= 1+ tableOrdersManager.ordersQuantity();

                TableOrder order=new TableOrder(orderNum, tableNum);

                drinksPanel = new JPanel();
                dishesPanel = new JPanel();
                orderItemsPanel=new JPanel();

                drinksPanel.setLayout(new BoxLayout(drinksPanel, BoxLayout.PAGE_AXIS));
                dishesPanel.setLayout(new BoxLayout(dishesPanel, BoxLayout.PAGE_AXIS));
                orderItemsPanel.setLayout(new BoxLayout(orderItemsPanel, BoxLayout.PAGE_AXIS));
                orderTotalLable=new JLabel("Total: ");

                int numberOfButtons=drinks.length;
                MenuItemButton buttonsDrinks[]=new MenuItemButton[numberOfButtons];
                for(int i=0; i<numberOfButtons;i++) {
                    buttonsDrinks[i] = new MenuItemButton(drinks[i], order, orderItemsPanel, orderTotalLable) ;
                    drinksPanel.add(buttonsDrinks[i]);
                }

                numberOfButtons=dishes.length;
                MenuItemButton buttonsDishes[]=new MenuItemButton[numberOfButtons];
                for(int i=0; i<numberOfButtons;i++) {
                    buttonsDishes[i] = new MenuItemButton(dishes[i], order, orderItemsPanel, orderTotalLable) ;
                    dishesPanel.add(buttonsDishes[i]);
                }

                orderItemsPanel.add(orderTotalLable);

                drinksScrollPanel =new JScrollPane(drinksPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets ins = new Insets(2, 2, 2, 2);
                GridBagConstraints gbc = new GridBagConstraints(0, 2, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);
                mainPanel.add(drinksScrollPanel, gbc);

                dishesScrollPanel =new JScrollPane(dishesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insDish = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcDish = new GridBagConstraints(1, 2, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(dishesScrollPanel, gbcDish);

                orderItemsScrollPanel= new JScrollPane(orderItemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insOrderList = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcOrderList = new GridBagConstraints(2, 2, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(orderItemsScrollPanel, gbcOrderList);

                JButton orderCloseButton=new JButton("Close"    );
                orderCloseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            tableOrdersManager.addOrder(tableNum, order);
                        } catch (OrderAlreadyAddedException ex) {
                            ex.printStackTrace();
                        }
                        mainPanel.remove(drinksScrollPanel);
                        mainPanel.remove(dishesScrollPanel);
                        mainPanel.remove(orderItemsScrollPanel);
                        mainPanel.remove(orderCloseButton);
                        mainPanel.setVisible(false);
                        mainPanel.setVisible(true);
                    }
                });
                 ins = new Insets(2, 2, 2, 2);
                 gbc = new GridBagConstraints(0, 4, 3, 1, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);

                mainPanel.add(orderCloseButton, gbc);
                mainPanel.setVisible(false);
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
        a8Button.addActionListener(tablePressAction);
    }

    private void createUIComponents() {
        mainPanel = new JPanel(new GridLayout(7, 9));

        mainPanel.setSize( 800, 600);
        mainPanel.setVisible(false);
        mainPanel.setVisible(true);

    }
}


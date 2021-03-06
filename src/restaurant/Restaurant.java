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

            setText(menuItem.getName()+" - "+menuItem.getCost());
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
            setText(menuItem.getName()+" - "+menuItem.getCost());
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
            new Dish(150, "??????-????????", "??????-???????? ????????????????-??????????????????"),
            new Dish(90, "??????????????", "?????????????? ???? ????????????????"),
            new Dish(70, "??????????", "?????????? ???? ?????????????????? ?? ??????????????"),
            new Dish(210, "????????", "???????? ???? ????????????????")
    };
    Drink[] drinks=new Drink[]{
            new Drink("????????", "???????? ??????????????????????", 50, 0.0, DrinkTypeEnum.COFFEE),
            new Drink("???????? ??????????", "???????? ?????????? ????????????????", 150, 8.5, DrinkTypeEnum.WINE),
            new Drink("?????? ??????????????", "?????? ?????????????? ??????????????", 49, 0.0, DrinkTypeEnum.GREEN_TEA)

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
    private JTextField firstName;
    private JTextField secondName;
    private JTextField age;
    private JTextField cityName;
    private JTextField zipCode;
    private JTextField streetName;
    private JTextField buildingNumber;
    private JTextField buildingLetter;
    private JTextField apartmentNumber;
    private JPanel customerPanel;
    private JButton goToOrder;
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

                TableOrder order;
                int orderNum= 1+ tableOrdersManager.ordersQuantity();
                boolean isNewOrder=false;
                try {
                    order = (TableOrder) tableOrdersManager.getOrder(tableNum);
                }catch (IllegalTableNumber ex){
                    System.out.println(ex.getMessage());
                    order=new TableOrder(orderNum, tableNum);
                    isNewOrder=true;
                    Color bkc= btn.getBackground();

                }
                btn.setBackground(Color.BLUE);

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
                if(order.getItemsCount()>0) {
                    MenuItem[] itemsList = order.getItemsArray();
                    for (int i = 0; i < itemsList.length; i++) {
                        orderItemsPanel.add(new orderItemButton(itemsList[i], order, orderTotalLable), i);

                    }
                    orderTotalLable.setText("Order total: " + order.getOrderCost());
                }

                drinksScrollPanel =new JScrollPane(drinksPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets ins = new Insets(2, 2, 2, 2);
                GridBagConstraints gbc = new GridBagConstraints(0, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);
                mainPanel.add(drinksScrollPanel, gbc);

                dishesScrollPanel =new JScrollPane(dishesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insDish = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcDish = new GridBagConstraints(1, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(dishesScrollPanel, gbcDish);

                orderItemsScrollPanel= new JScrollPane(orderItemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insOrderList = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcOrderList = new GridBagConstraints(2, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(orderItemsScrollPanel, gbcOrderList);

                JButton orderCloseButton=new JButton("Close"    );
                JButton orderPaymentButton=new JButton("Pay&Close"    );

                TableOrder finalOrder = order;
                orderCloseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton btnClose = (JButton) e.getSource();
                        if( finalOrder.getItemsCount()>0) {
                            try {
                                tableOrdersManager.addOrder(tableNum, finalOrder);

                            } catch (OrderAlreadyAddedException ex) {
                                ex.printStackTrace();
                            }
                            btn.setBackground(Color.GREEN);
                        }else{
                            try {
                                tableOrdersManager.removeOrder(tableNum);
                            }
                            catch (IllegalTableNumber ex){
                                ex.printStackTrace();
//                                btn.setBackground(Color.WHITE);
                                btn.setBackground(new Color(238, 238, 238));
                            }
                        }
                        mainPanel.remove(drinksScrollPanel);
                        mainPanel.remove(dishesScrollPanel);
                        mainPanel.remove(orderItemsScrollPanel);
                        mainPanel.remove(orderCloseButton);
                        mainPanel.remove(orderPaymentButton);
                        mainPanel.setVisible(false);
                        mainPanel.setVisible(true);
                    }
                });
                 ins = new Insets(2, 2, 2, 2);
                 gbc = new GridBagConstraints(0, 6, 2, 1, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);

                mainPanel.add(orderCloseButton, gbc);

                orderPaymentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton btnPay = (JButton) e.getSource();

                        try {
                            tableOrdersManager.removeOrder(tableNum);
                            btn.setBackground(new Color(238, 238, 238));
                        }
                        catch (IllegalTableNumber ex){
                            ex.printStackTrace();
//                                btn.setBackground(Color.WHITE);
                            btn.setBackground(new Color(238, 238, 238));
                        }

                        mainPanel.remove(drinksScrollPanel);
                        mainPanel.remove(dishesScrollPanel);
                        mainPanel.remove(orderItemsScrollPanel);
                        mainPanel.remove(orderCloseButton);
                        mainPanel.remove(orderPaymentButton);
                        mainPanel.setVisible(false);
                        mainPanel.setVisible(true);
                    }
                });
                ins = new Insets(2, 2, 2, 2);
                gbc = new GridBagConstraints(2, 6, 1, 1, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);

                mainPanel.add(orderPaymentButton, gbc);

                mainPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        };
        ActionListener internetOrderPressAction =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JButton btn = (JButton) e.getSource();
                //btn.setBackground(Color.BLUE);

//                String text=btn.getText();
//                Integer tableNum =Integer.parseInt(text);

                firstName.setText("??????????????");
                secondName.setText("??????");
                age.setText("34");
                cityName.setText("??????????");
                zipCode.setText("123456");
                        streetName.getText(),
                        Integer.parseInt( buildingNumber.getText()),
                        buildingLetter.getText().charAt(0),
                        Integer.parseInt( apartmentNumber.getText())
                customerPanel.setVisible(true);

/*???????????????????? ?? ???????????? [?? ????????????]*/

                /*???????????????????? ?? ???????????? [?? ????????????]*/
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
        newInternetOrderButton.addActionListener(internetOrderPressAction);
        goToOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InternetOrder internetOrder = new InternetOrder( firstName.getText(),
                        secondName.getText(),
                        Integer.parseInt(   age.getText()),
                        cityName.getText(),
                        Integer.parseInt( zipCode.getText()),
                        streetName.getText(),
                        Integer.parseInt( buildingNumber.getText()),
                        buildingLetter.getText().charAt(0),
                        Integer.parseInt( apartmentNumber.getText())
                );


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
                    buttonsDrinks[i] = new MenuItemButton(drinks[i], internetOrder, orderItemsPanel, orderTotalLable) ;
                    drinksPanel.add(buttonsDrinks[i]);
                }

                numberOfButtons=dishes.length;
                MenuItemButton buttonsDishes[]=new MenuItemButton[numberOfButtons];
                for(int i=0; i<numberOfButtons;i++) {
                    buttonsDishes[i] = new MenuItemButton(dishes[i], internetOrder, orderItemsPanel, orderTotalLable) ;
                    dishesPanel.add(buttonsDishes[i]);
                }

                orderItemsPanel.add(orderTotalLable);

                drinksScrollPanel =new JScrollPane(drinksPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets ins = new Insets(2, 2, 2, 2);
                GridBagConstraints gbc = new GridBagConstraints(0, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);
                mainPanel.add(drinksScrollPanel, gbc);

                dishesScrollPanel =new JScrollPane(dishesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insDish = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcDish = new GridBagConstraints(1, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(dishesScrollPanel, gbcDish);

                orderItemsScrollPanel= new JScrollPane(orderItemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                Insets insOrderList = new Insets(2, 2, 2, 2);
                GridBagConstraints gbcOrderList = new GridBagConstraints(2, 4, 1, 2, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, insDish, 2, 2);
                mainPanel.add(orderItemsScrollPanel, gbcOrderList);

//                JButton orderCloseButton=new JButton("Close"    );
                JButton orderPaymentButton=new JButton("Pay&Close"    );


                orderPaymentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        customerPanel.setVisible(false);
                        JButton btnPay = (JButton) e.getSource();
                        internetOrdersManager.add(internetOrder);
                        //btn.setBackground(new Color(238, 238, 238));


                        mainPanel.remove(drinksScrollPanel);
                        mainPanel.remove(dishesScrollPanel);
                        mainPanel.remove(orderItemsScrollPanel);

                        mainPanel.remove(orderPaymentButton);
                        mainPanel.setVisible(false);
                        mainPanel.setVisible(true);
                    }
                });
                ins = new Insets(2, 2, 2, 2);
                gbc = new GridBagConstraints(2, 7, 1, 1, 1, 0.9, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, ins, 2, 2);

                mainPanel.add(orderPaymentButton, gbc);

                mainPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        mainPanel = new JPanel(new GridLayout(8, 8));

        mainPanel.setSize( 800, 600);
        mainPanel.setVisible(false);
        mainPanel.setVisible(true);

    }
}


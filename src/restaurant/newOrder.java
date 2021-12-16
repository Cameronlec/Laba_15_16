package restaurant;

import javax.swing.*;
import java.awt.event.*;

public class newOrder extends JDialog {

    static class DrinkButton extends JButton {
        private Drink drink;
    }

    private TableOrder order;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel dishesPanel;
    private JPanel drinksPanel;
    private JPanel orderContentPanel;
    private JList<DrinkButton> list1;

    private static Dish[] dishes= new Dish[]{
            new Dish(150, "Суп-пюре", "Суп-пюре морковно-тыквенный"),
            new Dish(90, "Котлета", "Котлета из телятины"),
            new Dish(70, "Салат", "Салат из помидоров и огурцов"),
            new Dish(210, "Плов", "Плов из баранины")
    };
    private static Drink[] drinks=new Drink[]{
            new Drink("Кофе", "Кофе растворимый", 50, 0.0, DrinkTypeEnum.COFFEE),
            new Drink("Вино белое", "Вино белое столовое", 150, 8.5, DrinkTypeEnum.WINE),
            new Drink("Чай зеленый", "Чай зеленый пакетик", 49, 0.0, DrinkTypeEnum.GREEN_TEA)

    };

    private void createUIComponents() {
        // TODO: place custom component creation code here

        contentPane=new JPanel();
        drinksPanel=new JPanel();
        list1=new JList();
        order=new TableOrder(0, 0);

        for(int i=0; i< drinks.length; i++){
            DrinkButton drinkAddButton=new DrinkButton();
            drinkAddButton.setText(drinks[i].getName()+"\n"+drinks[i].getCost());
            drinkAddButton.drink=drinks[i];

            drinkAddButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DrinkButton btn = (DrinkButton)e.getSource();

                    order.add(btn.drink);
                    list1.add(btn);
                }
            });

            drinksPanel.add(drinkAddButton);
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public newOrder(int tableNumber, Drink[] allDrinks, Dish[] allDishes) {

//        buttonOK=new JButton("Ok");
//        buttonCancel=new JButton("Cancel");
//
//        buttonOK.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onOK();
//            }
//        });
//
//        buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    public TableOrder getOrder(){
        return order;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        newOrder dialog = new newOrder(0,drinks, dishes);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

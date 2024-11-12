package view;

import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               storeManagerGUIView {
    public JButton AddCustomer;
    private JPanel Home;
    private JButton Products;
    private JButton NewOrder;
    private JButton Orders;
    public storeManagerGUIView() {
        AddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCustomerForm form = null;
                JFrame frame = new JFrame("לקוחות");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    form = new AddNewCustomerForm();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                form.setVisible(true);
            }
        });
        Products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("מוצרים");
                frame.setContentPane(new ManageCatalogForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        Orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("הזמנות הלקוחות");
                frame.setContentPane(new ViewPurchasesForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        NewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("הזמנות");
                try {
                    frame.setContentPane(new PlaceOrderForm().panel);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }

        });
        AddCustomer.setSize(500,100);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("חנות מחשבים");
        frame.setContentPane(new storeManagerGUIView().Home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Backend_DAO_List.get().loadDataFromFile();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Backend_DAO_List.get().savaDataToFile();
            }
        });


        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.pack();
        frame.setVisible(true);
    }
}

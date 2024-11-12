package view;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderForm {
    public JPanel panel;
    private JButton btnCalculateTotal;
    private JButton btnSumbitOrder;
    private JButton btnRemoveProduct;
    private JComboBox CmbCustomer;
    private JComboBox CmbProduct;
    private JList list1;
    private JLabel lblListCustomer;
    private JLabel lblListProduct;
    private JButton btnAddPToOrder;
    private JLabel txtCalculateTotal;
    Backend_DAO_List bdl = Backend_DAO_List.get();

    public PlaceOrderForm() {
        DefaultListModel SelectedProductsListModel = new DefaultListModel();
        list1.setModel(SelectedProductsListModel);
        try{
            DefaultComboBoxModel modelCustomer = new DefaultComboBoxModel();
            modelCustomer.addAll(bdl.getAllCustomer().values());
            CmbCustomer.setModel(modelCustomer);

            DefaultComboBoxModel modelProduct = new DefaultComboBoxModel();
            modelProduct.addAll(bdl.getAllProduct());
            CmbProduct.setModel(modelProduct);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        btnAddPToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CmbCustomer.getSelectedIndex()>=0 && CmbProduct.getSelectedIndex()>=0)
                    SelectedProductsListModel.addElement((Product)CmbProduct.getSelectedItem());
                else
                    JOptionPane.showMessageDialog(panel,"נא לבחור לקוח/מוצר!!");
            }
        });
        btnCalculateTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Double total = bdl.CalcProductsTotalCost(products);
                    txtCalculateTotal.setText(total.toString());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        });
        btnRemoveProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> itemToRemove = list1.getSelectedValuesList();
                for (Product p : itemToRemove) {
                    SelectedProductsListModel.removeElement(p);
                }
            }
        });
        btnSumbitOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CmbProduct.getSelectedIndex()>=0){
                    try {
                        PurchaseOrder newOrder = new PurchaseOrder();
                        newOrder.setProductsList(new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                        newOrder.setOrderingCustomer((Customer) CmbCustomer.getSelectedItem());
                        bdl.PlaceOrder(newOrder);
                        JOptionPane.showMessageDialog(panel,"ההזמנה נוספה בהצלחה!!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);
                    }
                }
                else
                    JOptionPane.showMessageDialog(panel,"נא לבחור מוצר!!");
            }
        });
    }
}

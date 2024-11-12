package view;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPurchasesForm {
    public JPanel panel;
    private JComboBox cmbCustomer;
    private JList listOfCustomer;
    private JLabel lblTotal;
    private JLabel lblTotalCulc;

    Backend_DAO_List bdl = Backend_DAO_List.get();

    public ViewPurchasesForm() {
        DefaultListModel SelectedProductsListModel = new DefaultListModel();
        try {
            DefaultComboBoxModel modelCustomer = new DefaultComboBoxModel();
            modelCustomer.addAll(bdl.getAllCustomer().values());
            cmbCustomer.setModel(modelCustomer);
        }catch (Exception e)
        {

        }

        cmbCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SelectedProductsListModel.clear();
                    SelectedProductsListModel.addAll(bdl.getCustomersOrders((Customer) cmbCustomer.getSelectedItem()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                listOfCustomer.setModel(SelectedProductsListModel);
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Double total = bdl.CalcProductsTotalCost(products);
                    lblTotalCulc.setText(total.toString());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

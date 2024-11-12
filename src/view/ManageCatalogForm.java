package view;

import controller.Backend_DAO_List;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageCatalogForm {
    private JButton addProduct;
    private JButton deletProduct;
    private JList listProduct;
    public JPanel panel;
    DefaultListModel   AllProductsListModel;


    Backend_DAO_List bdl = Backend_DAO_List.get();

    public ManageCatalogForm() {
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p();
            }
        });
        AllProductsListModel =new DefaultListModel();

        listProduct.setModel(AllProductsListModel);

        RefreshProductList();

        deletProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> selectedValuesList = listProduct.getSelectedValuesList();
                for (Product p :selectedValuesList) {
                    AllProductsListModel.removeElement(p);
                    try {
                        bdl.RemoveProduct(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                RefreshProductList();
            }
        });
    }


    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            AllProductsListModel.addAll(bdl.getAllProduct());
        } catch (Exception ex) {

        }

    }
    private void p() {
        JFrame frame = new JFrame("AddNewProductForm");
        frame.setContentPane(new AddNewProductForm(this).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package view;

import controller.Backend_DAO_List;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddNewProductForm extends Component {
    private JLabel title;
    private JLabel lblNameProduct;
    private JLabel lblDiscribtionProduct;
    private JLabel lblPriceForUnit;
    private JLabel variable;
    private JButton btnAddProduct;
    private JComboBox<type> comboBox;
    public JPanel panel;
    private JTextField txtNameP;
    private JTextField txtDesP;
    private JTextField txtPrice;
    private JTextField txtVar;
    Backend_DAO_List bdl = Backend_DAO_List.get();
    public AddNewProductForm(ManageCatalogForm m) {
        DefaultComboBoxModel<type> model =new DefaultComboBoxModel(type.values());
        comboBox.setModel(model);
        txtVar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent k) {
                if (!Character.isDigit(k.getKeyChar()) || txtVar.getText().length()>=5)
                    k.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtPrice.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) )
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        txtNameP.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (Character.isDigit(e.getKeyChar()) )
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variable.setText(isInHardwareMode()?"תקופת אחריות":"מספר משתמשים");
            }
        });

        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Product p = null;
                    if (txtNameP.getText().isBlank())
                        throw new Exception("חובה להכניס שם מוצר");
                    if (txtDesP.getText().isBlank())
                        throw new Exception("חובה להכניס תיאור מוצר");
                    if (txtPrice.getText().isBlank())
                        throw new Exception("חובה להכניס מחיר ליחידה");
                    if (txtVar.getText().isBlank())
                    {
                        if(variable.getText()=="שנות אחריות")
                            throw new Exception("חובה להכניס שנות אחריות");
                        else
                            throw new Exception("חובה להכניס מספר משתמשים");
                    }

                    if(isInHardwareMode()){
                        p= new HardwareProduct(txtNameP.getText(), txtDesP.getText(),Float.parseFloat(txtPrice.getText()),Integer.parseInt(txtVar.getText()));

                    }
                    else {
                        p= new SoftwareProduct( txtNameP.getText(), txtDesP.getText(),Float.parseFloat(txtPrice.getText()),Integer.parseInt(txtVar.getText()));
                    }



                    bdl.AddProduct(p);
                    m.RefreshProductList();
                    JOptionPane.showMessageDialog(panel,"המוצר התווסף בהצלחה");
                    System.out.println(bdl.getAllCustomer());
                    System.out.println(p);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel,ex.getMessage());
                }
            }
        });

    }


    private boolean isInHardwareMode()
    {
        return comboBox.getSelectedItem().equals(type.HARDWARE);
    }


}


package controller;

import model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Backend_DAO_List implements Bakend {
    private HashMap<Long, Customer> _Customers;
    private HashSet<Product> _Products;
    private List<PurchaseOrder> _PurchaseOrders;
    private static Backend_DAO_List b = null;
    private Backend_DAO_List() {
        _Products = new HashSet<>();
        _PurchaseOrders = new ArrayList<>();
        _Customers=new HashMap<>();
    }
    public static  Backend_DAO_List get()
    {
        if(b == null)
        {
            b = new Backend_DAO_List();
            return b;
        }
        return b;
    }

    @Override
    public void AddCustomer(Customer c) throws Exception {
        _Customers.put(c.getId(),c);
    }

    @Override
    public void AddProduct(Product p) throws Exception {
        _Products.add(p);
    }

    @Override
    public HashMap<Long, Customer> getAllCustomer() throws Exception {
        return _Customers;
    }

    @Override
    public HashSet<Product> getAllProduct() throws Exception {
        return _Products;
    }

    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        _PurchaseOrders.add(po);
    }

    @Override
    public void RemoveProduct(Product p) throws Exception {
        _Products.remove(p);
    }

    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList<Product> ret = new ArrayList<Product>();
        Iterator<PurchaseOrder> itr=_PurchaseOrders.iterator();
        while (itr.hasNext())
        {
            PurchaseOrder i = itr.next();
           if(i.getOrderingCustomer().equals(c))
                ret.addAll( i.getProductsList());
        }
        return ret;
    }

    @Override
    public double CalcProductsTotalCost(Product[] products) throws Exception {
        //var sum =Arrays.stream(products).collect((Collectors.summarizingDouble(p->p.getPrice()))).getSum();
        //return sum;
        double sum = 0;
        for (Product p:products
        ) {
            sum+=p.getPrice();

        }
        return sum;
    }

    public void	savaDataToFile()
    {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("data"));
            oos.writeObject(_Customers);
            oos.writeObject(_Products);
            oos.writeObject(_PurchaseOrders);
            // סגירה
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadDataFromFile()
    {
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("data"));
            _Customers=(HashMap<Long, Customer>) ois.readObject();
            _Products =(HashSet<Product>) ois.readObject();
            _PurchaseOrders =(List<PurchaseOrder>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

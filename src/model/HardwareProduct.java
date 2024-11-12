package model;

import java.io.Serializable;

public class HardwareProduct extends Product implements Serializable {
    private int WarrantyPeriod;
    public HardwareProduct(String name, String description, float pricePerUnit, int warrantyPeriod) {
        super( name, description, pricePerUnit);
        WarrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        WarrantyPeriod = warrantyPeriod;
    }

    @Override
    public float getPrice() {
        return WarrantyPeriod + pricePerUnit;
    }
}

package model;

import java.io.Serializable;

public class SoftwareProduct extends Product implements Serializable {
    private int NumberOfUsers;

    public SoftwareProduct(String name, String description, float pricePerUnit, int numberOfUsers) {
        super(name, description, pricePerUnit);
        NumberOfUsers = numberOfUsers;
    }

    public int getNumberOfUsers() {
        return NumberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        NumberOfUsers = numberOfUsers;
    }

    @Override
    public float getPrice() {
        return NumberOfUsers + pricePerUnit;
    }
}

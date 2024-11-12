package model;

import java.io.Serializable;

public abstract class Product implements Serializable {
    long id;
    String name;
    String Description;
    float pricePerUnit;
    static int cnt =0;


    public Product() {
    }

    public Product(String name, String description, float pricePerUnit) {
        this.id = cnt++;
        this.name = name;
        this.Description = description;
        this.pricePerUnit = pricePerUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
    public abstract float getPrice();
}

package com.architecture.Entities;

public class Dog implements IDog {
    private final long id;
	private String title;
    private String description;
    private double price;
    private double size;
    
	public Dog(long id, String title, String description, double price, double size) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(this.id);
        sb.append(", name='").append(this.title).append('\'');
        sb.append(", description=").append(this.description);
        sb.append(", price=").append(this.price);
        sb.append(", size=").append(this.size);
        sb.append('}');
        return sb.toString();
    }

}


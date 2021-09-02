package com.architecture.Entities;

public class Cat  implements ICat {
    private final long id;
	private String title;
    private String description;
    private double price;
    

	public Cat(long id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
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
        sb.append('}');
        return sb.toString();
    }
}

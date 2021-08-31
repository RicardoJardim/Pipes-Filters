package com.architecture.entities;

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
        return "ID: " + this.id + "title: "+ this.title + "description: " + this.description+ "price: "+ this.price;
    }
}

package com.architecture.Domain.Entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Dog implements IDog {
    private long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 200, message 
      = "Title must be between 10 and 200 characters")
	private String title;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 200, message 
      = "Description must be between 10 and 200 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    private double size;

    private double price;

	public Dog(long id, String title, String description, double price, double size) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.size = size;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String toString(){
        final StringBuilder sb = new StringBuilder("Dog{");
        sb.append("id=").append(this.id);
        sb.append(", name='").append(this.title).append("'");
        sb.append(", description='").append(this.description).append("'");
        sb.append(", price=").append(this.price);
        sb.append(", size=").append(this.size);
        sb.append('}');
        return sb.toString();
    }

}


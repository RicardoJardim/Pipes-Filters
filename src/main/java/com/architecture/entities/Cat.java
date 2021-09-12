package com.architecture.Entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Cat  implements ICat {
    private final long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 200, message 
      = "Title must be between 10 and 200 characters")
	private String title;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 200, message 
      = "Description must be between 10 and 200 characters")
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
        final StringBuilder sb = new StringBuilder("Cat{");
        sb.append("id=").append(this.id);
        sb.append(", name='").append(this.title).append("'");
        sb.append(", description=").append(this.description).append("'");
        sb.append(", price=").append(this.price);
        sb.append('}');
        return sb.toString();
    }
}

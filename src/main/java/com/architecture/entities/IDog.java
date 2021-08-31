package com.architecture.entities;

public interface IDog {
    public long getId();

    public String getTitle();

    public void setTitle(String title);

    public String getDescription();

    public void setDescription(String description);

	public double getPrice();

    public void setPrice(double price);

    public double getSize();

    public void setSize(double size);

    public String toString();

}
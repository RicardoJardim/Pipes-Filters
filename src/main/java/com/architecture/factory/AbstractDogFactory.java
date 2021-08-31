public abstract class AbstractFactory{

    public abstract IDog CreateObject(String title,String description, double pric));

    public IDog CreateDog(String title,String description, double pric)
    {
        
        IDog product = CreateObject(String title,String description, double pric);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}
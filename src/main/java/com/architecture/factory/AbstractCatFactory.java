public abstract class AbstractCatFactory{

    public abstract ICat CreateObject(String title,String description, double pric));

    public ICat CreateCat(String title,String description, double pric)
    {
        
        ICat product = CreateObject(String title,String description, double pric);
        // Now, use the product.
        System.out.println("Created Object "+ product.toString());

        return product;
    }

}

/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private int weight;
    private String description;
    private boolean pickable;
    private boolean drinkable;
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description)
    {
        weight = 1;
        this.name = name;
        this.description = description;
        pickable = false;
        drinkable = false;
    }
    
     public Item(String name, String description, boolean drinkable)
    {
        weight = 1;
        this.name = name;
        this.description = description;
        pickable = false;
        this.drinkable = drinkable;
    }
    
    public void setPickable(){
  
        pickable = true;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getName(){
    
        return name;
    }
    
    public boolean getPickable(){
    
        return pickable;
    }
    
    public int getWeight()
    {
        return weight;
    }
}

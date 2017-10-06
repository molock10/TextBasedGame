
/**
 * Write a description of class Beverage here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Beverage extends Item
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Beverage
     */
    public Beverage(String name, String description)
    {
        super(name, description, true);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void drink()
    {
        // put your code here
        System.out.println("Ahhhh... so refreshing");
        
    }
}

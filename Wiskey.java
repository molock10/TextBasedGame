
/**
 * Write a description of class Wiskey here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wiskey extends Item
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Wiskey
     */
    public Wiskey(String key)
    {
        super(key, "A strong drink for strong people");
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}

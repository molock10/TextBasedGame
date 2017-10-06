import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private int max_weight;
    private int current_weight;
    private ArrayList<Item> backpack;
    
    private Room currentRoom;
    private Stack<Room> previousRoom;
    
    
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room room)
    {
        this.name = name;
        currentRoom = room;
        max_weight = 5;
        current_weight = 0;
        
        backpack = new ArrayList<>(5);
        previousRoom = new Stack<>();
    }

    public String getName(){
        return name;

    }
    
    public Room getRoom(){
    
        return currentRoom;

    }
      public void setRoom(Room room){
    
        this.currentRoom = room;

    }
    
    public Room getPreviousRoom(){
    
        return previousRoom.pop();

    }
    
    public boolean isThereAPreviousRoom(){
    
        return previousRoom.empty();
    }
    
    public void setPreviousRoom(Room room){
    
        previousRoom.push(room);

    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean take(Item item)
    {
        if(item.getWeight() + current_weight <= max_weight &&
                                            item.getPickable()){
            backpack.add(item);
            current_weight++;
            return true;
        }else{
            System.out.println("I can't carry that");
            return false;
        }
    }
    
    public Item drop(String dropping){
        Iterator<Item> it = backpack.iterator();
        
        while(it.hasNext()){
            Item returner = it.next();
            if(dropping.equals(returner.getName())){
                Item dropped = returner;
                backpack.remove(returner);
                current_weight--;
                return dropped;
            }
        }
            return null;
    }
    
    public Beverage drink(String item){
        Iterator<Item> it = backpack.iterator();
        
        while(it.hasNext()){
            Item returner = it.next();
           if(item.equals(returner.getName()) && returner instanceof Beverage){
                Beverage consume = (Beverage) returner;
                backpack.remove(returner);
                return consume;
           }
        }
            
        return null;
    }
    public void checkBackPack(){
    
        System.out.println("Your backpack contains: ");
        for(Item item : backpack){
        
            System.out.println(item.getName());
        }
        System.out.println("you are carrying " + current_weight
                            + "Kg out of " + max_weight + "Kg");
    }
}

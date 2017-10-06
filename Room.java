import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        
        exits = new HashMap<>();
        items = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction The direction of the exit in which to travel.
     * @param neighbor The room next to the current room.
     */
    public void setExits(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void addItem(String itemName, Item item){
        
        items.put(itemName, item);
    }
    public void removeItem(String ItemName){
    
        items.remove(ItemName);
    }
    
    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: north west
     * 
     *  @return A description of the room, including exits.
     */
    public String getLongDescription(){
    
        String itemsList = "";
        if(!(items.isEmpty())){
            itemsList = ".\nThere lies " + getItemString();
        }
        
        return "You are currently standing " + description 
                + itemsList + ".\n" 
                    + getExitString();
    }

    public Room getExit(String direction){
    
        return exits.get(direction);
    }
    
    public Item getItem(String itemName){
    
        return items.get(itemName);
    }
    
    public String getItemString(){
        
        String itemList = "Items: ";
        Set<String> keys = items.keySet();
        for(String item : keys){
            itemList += "\n*" + item;
            itemList += " :" + getItem(item).getDescription();
        }
        return itemList;
    }
    
    /**
     * Return a description of the room's exits,
     * for example, "Exit: north west".
     * @return A description of the available exits.
     */
    public String getExitString(){
        
        String exitString = "Exits:"; 
        Set<String> keys = exits.keySet();
        for(String exit : keys){
            exitString += " " + exit;
        }
        return exitString;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}

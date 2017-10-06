import java.util.Stack;
import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player[] players;
    private int numberOfPlayers;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game(int numberOfPlayers) 
    {
        this.numberOfPlayers = numberOfPlayers;
        players = new Player[numberOfPlayers];
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, attic;
        Beverage beer1, beer2;
        Item stool;
        Wiskey wiskey;
         
        //create Items
        beer1 = new Beverage("beer","a beverage that quenches ones thirst");
        beer1.setPickable();
        beer2 = new Beverage("beer","a beverage that quenches ones thirst");
        beer2.setPickable();     
        stool = new Item("stool","a wooden instrument for sitting");
        wiskey = new Wiskey("wiskey");
        wiskey.setPickable();
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        attic = new Room("in a dark, dusty attic");
        
        // initialise room exits
        outside.setExits("west", theater);
        outside.setExits("north", lab);
        outside.setExits("east", pub);
        theater.setExits("east", outside);
        pub.setExits("west", outside);
        pub.setExits("up", attic);
        attic.setExits("down", pub);
        lab.setExits("south", outside);
        lab.setExits("west", office);
        office.setExits("east", lab);
        
        pub.addItem("beer1", beer1);
        pub.addItem("beer2", beer1);
        pub.addItem(stool.getName(), stool);
        
        attic.addItem("wiskey", wiskey);

        playerSetUp(outside);
    }

    private void playerSetUp(Room initialRoom){
        
        for(int i = 0; i < numberOfPlayers; i++){
        
            players[i] = new Player("player" + i + 1 ,initialRoom);
        }
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            for(int i = 0; i < numberOfPlayers; i++){
                Command command = parser.getCommand();
                finished = processCommand(players[i], command);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("Players: ");
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.print(players[i].getName());
        }
        System.out.println(players[0].getRoom().getLongDescription());
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Player player, Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch(commandWord){
            case "help":
            printHelp();
            break;
            
            case "go":
            goRoom(player, command);
            break;
            
            case "look":
            look(player);
            break;
            
            case "quit":
            wantToQuit = quit(command);
            break;
            
            case "back":
            back(player);
            break;
            
            case "take":
            take(player, command);
            break;
            
            case "drop":
            drop(player, command);
            break;
            
            case "check":
            player.checkBackPack();
            break;
            
            case "drink":
            drink(player, command);
            break;
            
            default:
            
        }

        return wantToQuit;
    }

    // implementations of user commands:
    
    private void drink(Player player, Command command){
    
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take
            System.out.println("Drrink what?");
            return;
        }
        
        String beverageName = command.getSecondWord();
        Beverage bev = player.drink(beverageName);
        
         if(bev == null){
            System.out.println("You can't drink that");
        }
        else{
        
            System.out.println("You have obtained a " + itemOfChoice);
        
        }
    }
    private void take(Player player, Command command){
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take
            System.out.println("Pick up what?");
            return;
        }
        
        String itemOfChoice = command.getSecondWord();
        
        Item item = player.getRoom().getItem(itemOfChoice);
        
        if(item == null){
            System.out.println("There is no such Item in this room");
        }
        else if(player.take(item)){
        
            player.getRoom().removeItem(itemOfChoice);
            System.out.println("You have obtained a " + itemOfChoice);  
        }

    }
    
    private void drop(Player player, Command command){
    
         if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take
            System.out.println("Drop what?");
            return;
        }
        
        String itemOfChoice = command.getSecondWord();
        
        Item item = player.drop(itemOfChoice);
        
        if(item == null){
            System.out.println("There is no such Item in this room");
        }
        else {
            player.getRoom().addItem(item.getName(), item);
            System.out.println("You have droppped " + itemOfChoice);
        }
        
    }
    
    private void look(Player player){
    
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    private void back(Player player){
        
        if(!(player.isThereAPreviousRoom())){
            player.setRoom(player.getPreviousRoom());
            System.out.println(player.getRoom().getLongDescription());
        }else{
        
            System.out.println("You cannot return to your previous room");
        }
    }
    
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Player player, Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setPreviousRoom(player.getRoom());
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

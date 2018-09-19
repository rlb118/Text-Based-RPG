import java.util.*;
import java.io.*;
/**
*    This is the main class of the game. It is from here that everything will run and refer 
*    back to. I'm honestly still not sure how everything is going to work, but I suppose we'll
*    just have to wait and see what happens
*    @version 0.0.4
*    @author Robert Blake
*/
public class newRPG 
{
   //the keyboard variable is here because I am tired of having it in every method that needs it
   private static Scanner keyboard = new Scanner(System.in); 
    
    /**
    *    The main (fuckin' duh) method. From here, everything runs.
    *    The method itself will be a hub to other methods, and will recieve very little use outside of the
    *    opening of "screen" of the game
    *    @param args Completely unused
    */
   public static void main(String[] args)
   {
      //Declares the player variable
      UserData player;
      
      //assigns the player to the data returned from the intro() method
      player = intro();    
      
      //sends the player to the main game method
      game(player);
   }
   
   /**
   *    The intro method is shown when the player first opens the game. From here, they choose how they will get their
   *    character data. It also allows the option to exit the game. If that's your thing.
   *    @return created or loaded character
   */
   
   public static UserData intro()
   {
      //declare player variable
      UserData player;
      
      //Lists the players options:
      System.out.println("1.) Create a new Character\n2.) Load Game\n3.) Exit Game");
      
      //Runs the player's selection. If the player chooses an invalid number, continues to run until they choose a valid one
      do
      {
         switch(keyboard.nextInt()) 
         {
            case 1:
               keyboard.nextLine();
               //gets the player value from 
               player = characterCreation();
               
               break;
            case 2:
               keyboard.nextLine();
               player = loadGame();
               System.out.println(player.toString());
               break;
            case 3:
               System.exit(0);
            default:
               player = new UserData();
               break;
         }
      } while(player.equals(new UserData()));
      return player;
   }
    
    /**
    *    The game method is where the vast majority of the redirection to other methods occurs.
    *    @param player the player created or loaded from the main menu
    */
   public static void game(UserData player)
   {
      boolean continuePlaying = true;
      do
      {
         System.out.println("Choose one of the following: \n1.) Enter a Dungeon\n2.) Rest\n3.) View Stats\n" +
                            "4.) Save game\n5.) Quit game");
         switch(keyboard.nextInt())
         {
            case 1:
               keyboard.nextLine();
               spelunking(player); 
               break;
            case 2:
               keyboard.nextLine();
               player.setCurrentHP(player.getCurrentHP() + 10);
               break;
            case 3:
               keyboard.nextLine();
               System.out.print(player.toString());
               break;
            case 4:
               keyboard.nextLine();
               saveGame(player);
               break;
            case 5:
               keyboard.nextLine();
               continuePlaying = false;
               break;
            default:
               System.out.println("Invalid input, please try again.");
               break;
         }
      }while(continuePlaying); 
   }
    
    /**
    *    The character creation class is used for the player to create their fresh PC
    *    through a series of simple questions. At the moment, there are very few choices
    *    racially, though this should change in later updates.
    *    @return returns the newly created character
    */
    
   public static UserData characterCreation()
   {
      String name, gender, race;
      int stats[] = {1, 1, 1, 1, 1};
        
      System.out.println("What is your character's name?");
      name = keyboard.nextLine();
        
      System.out.println("Is your character male or female?");
      gender = keyboard.nextLine();
      if(gender.equals("Gerg"))
      {
         stats[Stats.INTELLIGENCE.ordinal()] += 100;
         stats[Stats.LUCK.ordinal()] -= 100;
      }
      System.out.println("Choose one of the following races: \n1.)Human: +1 to any two stats\n2.)Elf: +3 to speed\n3.)Dwarf: +3 to Constitution" +
                            "\n4.)Halfing: +3 to Luck ");
      switch(keyboard.nextInt())
      {
         case 1:
            race = "Human";
            for(int statBoost = 0; statBoost < 2; statBoost++)
            {
               System.out.println("Which stat would you like to level:\n1.)Strength\n2.)Speed\n3.)Intelligence" +
                                         "\n4.)Constitution\n5.)Luck");
               switch(keyboard.nextInt())
               {
                  case 1:
                     stats[Stats.STRENGTH.ordinal()] += 1;
                     break;
                  case 2:
                     stats[Stats.SPEED.ordinal()] += 1;
                     break;
                  case 3:
                     stats[Stats.INTELLIGENCE.ordinal()] += 1;
                     break;
                  case 4:
                     stats[Stats.CONSTITUTION.ordinal()] += 1;
                     break;
                  case 5:
                     stats[Stats.LUCK.ordinal()] += 1;
                     break;
                  default:
                     System.out.println("I'm sorry, that wasn't a valid input. Please try again.");
                     statBoost -= 1;
                     break;
               }
            }
            break;
         case 2:
            race = "Elf";
            stats[Stats.SPEED.ordinal()] += 3;
            break;
         case 3:
            race = "Dwarf";
            stats[Stats.CONSTITUTION.ordinal()] += 3;
            break;   
         case 4:
            race = "Halfling";
            stats[Stats.LUCK.ordinal()] += 3;
            break;
         default:
            System.out.println("Invalid input, defaulting to human");
            race = "Human";
            for(int statBoost = 0; statBoost < 2; statBoost++)
            {
               System.out.println("Which stat would you like to level:\n1.)Strength\n2.)Speed\n3.)Intelligence" +
                                         "\n4.)Constitution\n5.)Luck");
               switch(keyboard.nextInt())
               {
                  case 1:
                     stats[Stats.STRENGTH.ordinal()] += 1;
                     break;
                  case 2:
                     stats[Stats.SPEED.ordinal()] += 1;
                     break;
                  case 3:
                     stats[Stats.INTELLIGENCE.ordinal()] += 1;
                     break;
                  case 4:
                     stats[Stats.CONSTITUTION.ordinal()] += 1;
                     break;
                  case 5:
                     stats[Stats.LUCK.ordinal()] += 1;
                     break;
                  default:
                     System.out.println("I'm sorry, that wasn't a valid input. Please try again.");
                     statBoost -= 1;
                     break;
               }
            }
            break;
      }
        
      System.out.println("Would you like to be: \n1.)Strong\n2.)Fast\n3.)Smart\n4.)Tough\n5.)Lucky");
      switch(keyboard.nextInt())
      {
         case 1:
            stats[Stats.STRENGTH.ordinal()] += 2;
            break;
         case 2:
            stats[Stats.SPEED.ordinal()] += 2;
            break;
         case 3:
            stats[Stats.INTELLIGENCE.ordinal()] += 2;
            break;
         case 4:
            stats[Stats.CONSTITUTION.ordinal()] += 2;
            break;
         case 5:
            stats[Stats.LUCK.ordinal()] += 2;
            break;
      }
      keyboard.nextLine();
      return new UserData(name, gender, race, stats);
   }
    /**
    *    foeGen uses a random number to choose from a list of premade enemies for the player to face.
    *    @return returns the selected enemy
    */
   public static EnemyData foeGen()
   {
      Random foeRNG = new Random();
      switch(foeRNG.nextInt(3))
      {
         case 0:
            return new EnemyData("Bandit", 20, 3, 10, 1);
         case 1:
            return new EnemyData("Wolf", 10, 5, 10, 1);
         case 2:
            return new EnemyData("Giant Spider", 15, 4, 10, 1);
         default:
            System.out.println("The enemy spawn system is wonky. Error Code: 113");
      }
      return null;
   }
   
   /**
   *    spelunking is the dungeon exploration method. It uses a dungeon generated through the dunGen method
   *    for the player to explore.  They explore using the cardinal directions mapped to w, a, s, and d respectively.
   *    If the player enters a tile with an enemy, they enter combat via the combat method.
   *    If the player reaches the exit tile, they gain 100 experience points and exit the dungeon. 
   *    @param player The player from the main game.
   *    @return returns the player with updated stats.
   */

   public static UserData spelunking(UserData player)
   {
      DungeonMap dungeon = dunGen(1);
      int playerX = 0;
      int playerY = 0;
      do
      {
         if(dungeon.getTile(playerY, playerX).getContainsEnemy())
         {
            player = combat(player);
         }
         System.out.println(playerY + " " + playerX);
         System.out.println("Health: " + player.getCurrentHP() + "/" + player.getMaxHP());
         dungeon.printMap();
         System.out.println("Choose an option: ");
         if(playerY != 0)
         {
            if(dungeon.getTile(playerY - 1, playerX).getExists())
            {
               System.out.println("w.) go north");
            }
         }
         if(playerX != 0)
         {
            if(dungeon.getTile(playerY, playerX - 1).getExists())
            {
               System.out.println("a.) go west");
            }
         }
         if(playerX != dungeon.getLength(playerY))
         {
            if(dungeon.getTile(playerY, playerX + 1).getExists())
            {
               System.out.println("d.) go east");
            }
         }
         if(playerY != dungeon.getWidth())
         {
            if(dungeon.getTile(playerY + 1, playerX).getExists())
            {
               System.out.println("s.) go south");
            }
         }
         System.out.println("r.) rest");
         String input = keyboard.nextLine();
         while(input.equals(""))
         {
            System.out.println("Actually enter something, asshole");
            input = keyboard.nextLine();
         }
         switch(input.toLowerCase().charAt(0))
         {
            case 'w':
               if(dungeon.getTile(playerY - 1, playerX).getExists() && playerY > 0)
               {
                  dungeon.getTile(playerY, playerX).setContainsPlayer(false);
                  playerY -= 1;
                  dungeon.getTile(playerY, playerX).setContainsPlayer(true);
               }
               else
               {
                  System.out.println("You can't go that way!");
               }
               break;
            case 'a':
               if(dungeon.getTile(playerY, playerX - 1).getExists() && playerX > 0)
               {
                  dungeon.getTile(playerY, playerX).setContainsPlayer(false);
                  playerX -= 1;
                  dungeon.getTile(playerY, playerX).setContainsPlayer(true);
               }
               else
               {
                  System.out.println("You can't go that way!");
               }
               break;
            case 'd':
               if(dungeon.getTile(playerY, playerX + 1).getExists() && playerY < dungeon.getLength(playerY))
               {
                  dungeon.getTile(playerY, playerX).setContainsPlayer(false);
                  playerX += 1;
                  dungeon.getTile(playerY, playerX).setContainsPlayer(true);
               }
               else
               {
                  System.out.println("You can't go that way!");
               }
               break;
            case 's':
               if(dungeon.getTile(playerY + 1, playerX).getExists() && playerX < dungeon.getWidth())
               {
                  dungeon.getTile(playerY, playerX).setContainsPlayer(false);
                  playerY += 1;
                  dungeon.getTile(playerY, playerX).setContainsPlayer(true);
               }
               else
               {
                  System.out.println("You can't go that way!");
               }
               break;
            case 'r':
               player.setCurrentHP(player.getCurrentHP() + 10);
               break;
            default:
               System.out.println("invalid input, please try again");
               break;
         }
      }while(!dungeon.getTile(playerY, playerX).getExitTile());
      System.out.println("You cleared the dungeon!");
      System.out.println("You gain 100 XP!");
      player.gainXP(100);
      if(player.getXP() > player.getXPNeeded())
      {
         player.levelUp();
      }
      return player;
   }
   
   /**
   *    dunGen is the method that currently creates the dungeon.
   *    In the current version, only one dungeon is able to be created. The only thing that
   *    changes between the runs of the current dungeon is the enemy count within.
   *    @param dungeonOption chooses which of several possible dungeons is generated
   *    @return the newly generated map of the chosen dungeon
   */
   
   public static DungeonMap dunGen(int dungeonOption)
   {
      Random dungRNG = new Random();
      DungeonMap map = new DungeonMap();
      
      switch(dungeonOption)
      {
         case 1:
            int width = 20;
            int length = 30;
            int lastXValue = 0;
            int lastYValue = 0;
            map = new DungeonMap(width, length);
            map.setTile(0, 0, new MapTiles(true, false, true, true, false));
            for(int gen1 = 1; gen1 < 12; gen1++)
            {
               switch(dungRNG.nextInt(9))
               {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                     map.setTile(0, gen1, new MapTiles(true, true));
                     break;
                  default:
                     map.setTile(0, gen1, new MapTiles(true));
                     break;
               }
               lastXValue = gen1;
            }
            for(int gen2 = 1; gen2 < 14; gen2++)
            {
               switch(dungRNG.nextInt(9))
               {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                     map.setTile(gen2, lastXValue, new MapTiles(true, true));
                     break;
                  default:
                     map.setTile(gen2, lastXValue, new MapTiles(true));
                     break;
               }
               lastYValue = gen2;
            }
            for(int gen3 = 1; gen3 < 12; gen3++)
            {
               lastXValue++;
               switch(dungRNG.nextInt(9))
               {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                     map.setTile(lastYValue, lastXValue, new MapTiles(true, true));
                     break;
                  default:
                     map.setTile(lastYValue, lastXValue, new MapTiles(true));
                     break;
               }
            
            }
            for(int gen4 = lastYValue; gen4 > 0; gen4--)
            {
               switch(dungRNG.nextInt(10))
               {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                     map.setTile(gen4, lastXValue, new MapTiles(true, true));
                     break;
                  default:
                     map.setTile(gen4, lastXValue, new MapTiles(true));
                     break;
               }
            }
            map.setTile(0, lastXValue, new MapTiles(true, false, false, false, true));
      }
      return map;
   }
   
    /**
    *    The combat method is used currently as the only real action the player can take. In the future, that will change.
    *    It uses an enemy from the foeGen method and has the player "fight them" through a series of "combat rounds" that
    *    continue until the player or enemy's health reaches 0. If the player's does, then the game ends because they died.
    *    @param player the player's data
    *    @return If the player wins, returns the player with their lowered hp
    */
   public static UserData combat(UserData player)
   {
      EnemyData enemy = foeGen();
      System.out.println("You are being attacked by a " + enemy.getName());
      do
      {
         System.out.println("Your health: " + player.getCurrentHP() + "/" + player.getMaxHP());
         System.out.println(enemy.getName() + " health: " + enemy.getCurrentHP() + "/" + enemy.getMaxHP());
         System.out.println("Choose an attack: \n1.) Melee Attack 2.) Ranged Attack 3.) Magic Attack");
         String input = keyboard.nextLine();
         while(!Character.isDigit(input.charAt(0)))
         {
            System.out.println("Please enter a valid digit");
            input = keyboard.nextLine();
         }
         switch(Character.getNumericValue(input.charAt(0)))
         {
            case 1:
               enemy.setCurrentHP(enemy.getCurrentHP() - player.dealMelDam());
               break;
            case 2:
               enemy.setCurrentHP(enemy.getCurrentHP() - player.dealRanDam());
               break;
            case 3:
               enemy.setCurrentHP(enemy.getCurrentHP() - player.dealMagDam());
               break;
            default:
               System.out.println("That isn't a valid input!");
         }
         if(enemy.getCurrentHP() > 0)
         {
            player.setCurrentHP(player.getCurrentHP() - enemy.dealDamage());
         }
         
      }while(player.getCurrentHP() > 0 && enemy.getCurrentHP() > 0);
      if(player.getCurrentHP() > 0)
      {
         System.out.println("Good job, you won!");
         System.out.println("You gained " + enemy.droppedXP() + " XP!");
         player.gainXP(enemy.droppedXP());
         if(player.getXP() > player.getXPNeeded())
         {
            player.levelUp();
         }
         return player;
      }
      else
      {
         gameOver();
         return null;
      }
     
   }
   /**
    *    The saveGame method currently saves to a file the UserData object through object serialization. It
    *    sets the extension to .dat automatically, though I'm considering changing it. There's a try/catch
    *    expression in there just in case and to give me some error codes should there be a weird fuck-up.
    *    @param player the character's data being saved to the file 
    */
   public static void saveGame(UserData player)
   {
        
      System.out.println("What would you like to name your save?");
      try
      {
         FileOutputStream outStream = new FileOutputStream(keyboard.nextLine() + ".dat");
         ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
         objectOutputFile.writeObject(player);
         outStream.close();
      }
      catch(IOException saveFail)
      {
         System.out.println("Something's fucky.\nError code: 112");
      }
   
   }
    /**
    *    The loadGame reads the serialized UserData object from the rqueseted file and returns it from whence it was called. 
    *    Should the file not be found, the user is informed of the error and returned to the main menu. 
    *    @return returns the character loaded from file or null if nothing is found
    */
   public static UserData loadGame()
   {
        
      System.out.println("What file would you like to open?");
      try
      {
         FileInputStream inStream = new FileInputStream(keyboard.nextLine() + ".dat");
         ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
         return (UserData) objectInputFile.readObject();
      }
      catch(IOException wrongName)
      {
         System.out.println("I'm sorry, your file was not found. Please try again.");
         return null;
      }
      catch(ClassNotFoundException somethingsFucky)
      {
         System.out.println("This is, in fact, a really bad thing. And impossible \nError Code: Fuck me sideways");
         return null;
      }
   }
   /**
   *    The gameOver method is called any time the player loses the game, typically through death. 
   *    This currently only occurs through combat, and loss results in the game ending.
   */
   public static void gameOver()
   {
      System.out.println("You died! Game over!");
      System.exit(0);
   }
}
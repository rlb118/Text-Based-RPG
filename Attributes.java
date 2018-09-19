   /**
*    This class is the Superclass baseline for creatures and their stats
*    Currently, it only contains maxHP and level, but that could change as progress is made
*    
*    @author Robert Blake
*    @version 0.0.4
*/
import java.io.Serializable;

public class Attributes implements Serializable
{
   private String name;
   private int maxHP;
   private int currentHP;
   private int level;
   
   /**
   *    This is the default constructor. Should no parameters
   *    be input during object initialization, this constructor will run. For the most part,
   *    this is for error resolution and to prevent future screw-ups
   */
   public Attributes()
   {
      name = "Fucking test code";
      maxHP = 1;
      currentHP = 1;
      level = 1;
   }
   
   /**
   *    This constructor is used to set the Attributes of a creature that isn't automatically level 1.
   *    It assigns the maxHP and level variables, as well as setting hte creature's max hp.
   *    values based upon the parameters
   *    @param namae The name parameter
   *    @param hp This is the parameter for the maxHP
   *    @param lvl The parameter used to assign the creature's current level
   */
   public Attributes(String namae, int hp, int lvl)
   {
      name = namae;
      maxHP = hp;
      currentHP = hp;
      level = lvl;
   }
   /**
   *    This constructor is used during character creation. It sets a default starting level of 1,
   *    and only changes the name and sets the max hp of a character.
   *    @param namae this is the name parameter
   *    @param hp this parameter sets the character's maxHP
   */
   public Attributes(String namae, int hp)
   {
      name = namae;
      maxHP = hp;
      currentHP = hp;
      level = 1;
   }
   
   /**
   *    The setMaxHP method is used to change the max HP of the character without changing anything else.
   *    This could be used upon level-up or the acquisition of an item that boosts constitution or any number of
   *    other occurances.
   *    @param hp the new maximum hit points of the character
   */
   public void setMaxHP(int hp)
   {
      maxHP = hp;
   }
   
   /**
   *    The setCurrentHP method is used to change the character's currentHP without affecting anything else
   *    This could be used when taking damage or when healing back damage
   *    @param hp the new hitpoints of the player
   *    
   */
   public void setCurrentHP(int hp)
   {
      if(hp > maxHP)
      {
         currentHP = maxHP;
      }
      else
      {
         currentHP = hp;
      }
       
   }
   
   /*
   
   */
   public void setLVL(int lvl)
   {
      level = lvl;
   }
   public int getMaxHP()
   {
      return maxHP;
   }
   public int getCurrentHP()
   {
      return currentHP;
   }
   public int getLVL()
   {
      return level;
   }
   public String getName()
   {
      return name;
   }
   public String toString()
   {
      return "Name: " + name + "\n" + "Health: " + currentHP + "/" + maxHP + "\n" + "level: " + level;
   }
   
    
   public boolean equals(Attributes compareValue)
   {
      if(compareValue.getName().equals(name))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}
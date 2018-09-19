/**
*    This will hold all of the data that isn't general, reusable data, such as inventory and completion flags
*    For the time being, it's dedicated specifically to the player character. That may change in the future
* 
*    @author Robert Blake
*    @version 0.0.4
*/
import java.util.*;
public class UserData extends UserStats
{
   private int experiencePoints;
   private int expNeeded;
    
   public UserData()
   {
      experiencePoints = 0;
      expNeeded = 200;
   }
   public UserData(String name, String gend, String races, int[] statsDist)
   {
      super(name, gend, races, statsDist);
      experiencePoints = 0;
      expNeeded = 200;
   }
   public void gainXP(int xp)
   {
      experiencePoints += xp;
   }
   public int getXP()
   {
      return experiencePoints;
   }
   public int getXPNeeded()
   {
      return expNeeded;
   }
   public String toString()
   {
      return super.toString();
   }
   public void levelUp()
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Congratulations! You leveled up!");
      System.out.println("Choose one of the following stats to increase: ");
      System.out.println("1.)Strength\n2.)Speed\n3.)Intelligence\n4.)Constitution\n5.)Luck");
      switch(keyboard.nextInt())
      {
         case 1:
            super.setSTR(super.getSTR() + 1);
            break;
         case 2:
            super.setSPD(super.getSPD() + 1);
            break;
         case 3:
            super.setINT(super.getINT() + 1);
            break;
         case 4:
            super.setCON(super.getCON() + 1);
            break;
         case 5:
            super.setLUC(super.getLUC() + 1);
            break;
      }
      
      switch(super.getLVL())
      {
         case 1:
            expNeeded = 400;
            break;
         case 2:
            expNeeded = 800;
            break;
         case 3:
            expNeeded = 1600;
            break;
         case 4:
            expNeeded = 3200;
            break;
         case 5:
            expNeeded = 6400;
            break;
         default:
            System.out.println("You have reached the max level, congratulations!");
            expNeeded = Integer.MAX_VALUE;
      }
      super.setLVL(super.getLVL() + 1);
      keyboard.nextLine();
   }
    
}

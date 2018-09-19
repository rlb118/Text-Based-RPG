/**
*    The UserStats class serves to hold the majority of the values both player characters and
*    NPCs might have, including gender, race, and their various stats. Additional values may
*    be implemented in the future.
*    
*    @author Robert Blake
*    @version 0.0.4
*
*/
import java.util.Random;

public class UserStats extends Attributes
{
   private String gender;
   private String race;
    
   private int[] stats = new int[5];
   private Random damRNG = new Random();

    
   public UserStats()
   {
      gender = "tumblr";
      race = "Chimera";
      for(int counter = 0; counter < stats.length; counter++)
      {
         stats[counter] = 0;
      }
   }
    /**
    *    This variation of the constructor is used to set specific data for NPCs or weird importation of characters. 
    *    @param name this parameter is used as an argument to set the name in the Attributes superclass
    *    @param gend used to set the character's gender
    *    @param races used to set the character's race
    *    @param statsDist the array parameter used to set the character's stats
    *    @param lvl used as an argument to set the character's level in the Attributes superclass
    */
   public UserStats(String name, String gend, String races, int[] statsDist, int lvl)
   {
      super(name, statsDist[Stats.CONSTITUTION.ordinal()] * 10 + lvl * 10, lvl);
      gender = gend;
      race = races;
      stats = statsDist;
   }
    /**
    *    This constructor variation is used for character creation. It runs the superclass's constructor that sets
    *    the level to 1 and sets the rest of the values to the input data.
    *    @param name This parameter used as an argument to set the name in the Attributes constructor
    *    @param gend used to set the character's gender
    *    @param races used to set the character's race
    *    @param statsDist the array parameter used to set the character's stats
    */
   public UserStats(String name, String gend, String races, int[] statsDist)
   {
      super(name, statsDist[3] * 10 + 10);
      gender = gend;
      race = races;
      stats = statsDist;
   }
    /**
    *    The setGender method is for strange cases where the gender of something needs changed.
    *    To be quite honest, I have no idea why I would ever need this, but it's a good rule
    *    of thumb to have "set" methods for each variable.
    *    @param sex the parameter used to change the character's gender.
    */
   public void setGender(String sex)
   {
      gender = sex;
   }
   public void setRace(String races)
   {
      race = races;
   }
   public void setSTR(int str)
   {
      stats[Stats.STRENGTH.ordinal()] = str;
   }
   public void setSPD(int spd)
   {
      stats[Stats.SPEED.ordinal()] = spd;
   }
   public void setINT(int intel)
   {
      stats[Stats.INTELLIGENCE.ordinal()] = intel;
   }
   public void setCON(int con)
   {
      stats[Stats.CONSTITUTION.ordinal()] = con;
      super.setMaxHP(stats[Stats.CONSTITUTION.ordinal()] * 10 + super.getLVL() * 10);
   }
   public void setLUC(int luc)
   {
      stats[Stats.LUCK.ordinal()] = luc;
   }
   public String getGender()
   {
      return gender;
   }
   public String getRace()
   {
      return race;
   }
   public int getSTR()
   {
      return stats[Stats.STRENGTH.ordinal()];
   }
   public int getSPD()
   {
      return stats[Stats.SPEED.ordinal()];
   }
   public int getINT()
   {
      return stats[Stats.INTELLIGENCE.ordinal()];
   }
   public int getCON()
   {
      return stats[Stats.CONSTITUTION.ordinal()];
   }
   public int getLUC()
   {
      return stats[Stats.LUCK.ordinal()];
   }
   public int dealMelDam()
   {
      int damDealt = 0;
      do
      {
         damDealt = (damRNG.nextInt(stats[Stats.STRENGTH.ordinal()] + 2));
      }while(damDealt < stats[Stats.STRENGTH.ordinal()] - 2 || damDealt <= 0);
      if((damRNG.nextInt(19) + stats[Stats.LUCK.ordinal()]) >= 20)
      {
         damDealt *= 2;
         System.out.println("CRIIIITTTIICCCALLLL HIIIITTT!");
      }
      System.out.println("You deal " + damDealt + " melee damage!");
      return damDealt;
   }
   public int dealRanDam()
   {
      int damDealt = 0;
      do
      {
         damDealt = (damRNG.nextInt(stats[Stats.SPEED.ordinal()] + 2));
      }while(damDealt < stats[Stats.SPEED.ordinal()] - 2 || damDealt <= 0);
      if((damRNG.nextInt(19) + stats[Stats.LUCK.ordinal()]) >= 20)
      {
         damDealt *= 2;
         System.out.println("CRIIIITTTIICCCALLLL HIIIITTT!");
      }
      System.out.println("You deal " + damDealt + " ranged damage!");
      return damDealt;
   }
   public int dealMagDam()
   {
      int damDealt = 0;
      do
      {
         damDealt = (damRNG.nextInt(stats[Stats.INTELLIGENCE.ordinal()] + 2));
      }while(damDealt < stats[Stats.INTELLIGENCE.ordinal()] - 2 || damDealt <= 0);
      if((damRNG.nextInt(19) + stats[Stats.LUCK.ordinal()]) >= 20)
      {
         damDealt *= 2;
         System.out.println("CRIIIITTTIICCCALLLL HIIIITTT!");
      }
      System.out.println("You deal " + damDealt + " magic damage!");
      return damDealt;
   }
   public String toString()
   {
      String returny = "";
      for (int stat : stats)
      {
         returny = returny + stat + "\n";
      }
      return super.toString() + "\n" + "Gender: " + gender + "\n" + "Race: " + race + "\n" + "stats: \n" + returny;
   }
}

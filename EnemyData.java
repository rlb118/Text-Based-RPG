import java.util.Random;

public class EnemyData extends Attributes
{
   private int damage;
   private int droppedXP;
   private Random damRNG = new Random();
     
   public EnemyData(String name, int maxHP, int dam, int dropped, int level)
   {
      super(name, maxHP, level);
      damage = dam;
      droppedXP = dropped;
   }
   public void setDAM(int dam)
   {
      damage = dam;
   }
   public void setDXP(int dropped)
   {
      droppedXP = dropped;
   }
   public int getDAM()
   {
      return damage;
   }
   public int droppedXP()
   {
      return droppedXP;
   }
   public int dealDamage()
   {
      int damDealt = 0;
      do
      {
         damDealt = (damRNG.nextInt(damage + 2));
      }while(damDealt < damage - 2);
      System.out.printf("%s deals %d damage to you!\n", getName(), damDealt);
      return damDealt;
   }
}
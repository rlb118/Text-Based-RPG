import java.io.*;
public class MapTiles implements Serializable
{
   boolean exists;
   boolean containsEnemy;
   boolean containsPlayer;
   boolean startingTile;
   boolean exitTile;
   
   public MapTiles(boolean there, boolean enemy, boolean player, boolean start, boolean exit)
   {
      exists = there;
      containsEnemy = enemy;
      containsPlayer = player;
      startingTile = start;
      exitTile = exit;
   }
   public MapTiles(boolean there)
   {
      exists = there;
      containsEnemy = false;
      containsPlayer = false;
      startingTile = false;
      exitTile = false;
   }
   public MapTiles(boolean there, boolean enemy)
   {
      exists = there;
      containsEnemy = enemy;
      containsPlayer = false;
   }
   public MapTiles()
   {
      exists = false;
      containsEnemy = false;
      containsPlayer = false;
      startingTile = false;
      exitTile = false;
   }
   public boolean getExists()
   {
      return exists;
   }
   public boolean getContainsEnemy()
   {
      return containsEnemy;
   }
   public boolean getContainsPlayer()
   {
      return containsPlayer;
   }
   public boolean getStartingTile()
   {
      return startingTile;
   }
   public boolean getExitTile()
   {
      return exitTile;
   }
   public void setExists(boolean exist)
   {
      exists = exist;
   }
   public void setContainsEnemy(boolean enemy)
   {
      containsEnemy = enemy;
   }
   public void setContainsPlayer(boolean player)
   {
      containsPlayer = player;
   }
   public void setExitTile(boolean exit)
   {
      exitTile = exit;
   } 
   public void printTile()
   {
      if(exists)
      {
         System.out.print("[");
         if(startingTile || exitTile)
         {
            System.out.print("X");
         }
         if(containsPlayer)
         {
            System.out.print("*");
         }
         System.out.print("]");
      }
      else
      {
         System.out.print("  ");
      }
   }
   public void defeatedEnemy()
   {
       containsEnemy = false;
   }
   
}
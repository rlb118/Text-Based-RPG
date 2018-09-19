import java.io.*;
public class DungeonMap implements Serializable
{
   private MapTiles[][] map;
   
      
   public DungeonMap(int yAxis, int xAxis)
   {
      map = new MapTiles[yAxis][xAxis];
      for(int yValues = 0; yValues < yAxis; yValues++)
      {
         for(int xValues = 0; xValues < xAxis; xValues++)
         {
            map[yValues][xValues] = new MapTiles();
         } 
      }
   }
   public DungeonMap()
   {
       
   }
   public void setTile(int yValue, int xValue, MapTiles tile)
   {
      map[yValue][xValue] = tile;
   }
   public MapTiles getTile(int yValue, int xValue)
   {
      return map[yValue][xValue];
   }
   public int getLength(int row)
   {
      return map[row].length - 1;
   }
   public int getWidth()
   {
      return map.length - 1;
   }
   public void printMap()
   {
      for(int row = 0; row < map.length; row++)
      {
         for(int column = 0; column < map[row].length; column++)
         {
            map[row][column].printTile();
         }
         System.out.println();
         System.out.print(" ");
      }
   }
}
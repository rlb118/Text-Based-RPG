import java.io.*;
import java.util.*;

public class DungeonTest
{
   public static void main(String[] args)
   {
      System.out.println("What file would you like to open?");
      DungeonMap mapTesting = new DungeonMap();
      Scanner keyboard = new Scanner(System.in);
     
      try
      {
         FileInputStream inStream = new FileInputStream(keyboard.nextLine() + ".dmd");
         ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
         mapTesting = (DungeonMap) objectInputFile.readObject();
      }
      catch(IOException wrongName)
      {
         System.out.println("I'm sorry, your file was not found. Please try again.");
         System.exit(0);
      }
      catch(ClassNotFoundException somethingsFucky)
      {
         System.out.println("This is, in fact, a really bad thing. And impossible \nError Code: Fuck me sideways");
         System.exit(0);
      }
      
      mapTesting.printMap();
      
      
   }
}
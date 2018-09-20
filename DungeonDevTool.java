import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
*    
*    The DungeonDevTool Class exists to allow me to easily design dungeons for use in the main game itself. 
*    It extends the JFrame class in order to fully function as a gui. It's divided into three main parts:
*    the tile map, the tile editor, and a menu bar. 
*    The reason the panels aren't in their own separate classes is due to the necessity of the panels pulling on
*    information from one to another, which you can't do without them being within the same class (to my current
*    knowledge and based upon what I saw on google).
*    
*/

public class DungeonDevTool extends JFrame
{

   
   //creates the constants that dictate the maximum size of the dungeon map.
   private static int mapWidth = 0;
   private static int mapLength = 0;
   
   //declares both panels
   private JPanel dungeonMapPanel = new JPanel();
   private JPanel tileDisplayPanel;
   
   //Declares the array used to hold the buttons, initialized the ButtonGroup that holds them and the DungeonMap it draws from
   private JButton[][] dungeonTile = new JButton[mapWidth][mapLength];
   private ButtonGroup buttons = new ButtonGroup();
   private DungeonMap map = new DungeonMap(mapWidth, mapLength);
   
   //Creating variables for the tileDisplayPanel
   
   //Creates the ints that hold the selected x and y values of the tile selected and the JLabels to display them
   private int yValueSelected, xValueSelected;
   private JLabel tileLabel = new JLabel("Displayed Tile:");
   private JLabel tileNumber;
   
   //Creates the label, buttons, and button group used for modifying the exists variable in the selected DungeonTile
   private JLabel existsLabel = new JLabel("Exists:");
   private JRadioButton existsTrue;
   private JRadioButton existsFalse;
   private ButtonGroup exists = new ButtonGroup();
   
   //Creates the label, buttons, and button group used for modifying the startingTile variable in the selected DungeonTile
   private JLabel startingTileLabel = new JLabel("Starting Tile:");
   private JRadioButton startingTileTrue;
   private JRadioButton startingTileFalse;
   private ButtonGroup startingTile = new ButtonGroup();
   
   
   //Creates the label, buttons, and button group used for modifying the enemyContained variable in the selected DungeonTile
   private JLabel enemyContainedLabel = new JLabel("Enemy Contained:");
   private JRadioButton enemyContainedTrue;
   private JRadioButton enemyContainedFalse;
   private ButtonGroup enemyContained = new ButtonGroup();
   
   //Creates the label, buttons, and button group used for modifying the exit variable in the selected DungeonTile
   private JLabel exitLabel = new JLabel("Exit:");
   private JRadioButton exitTrue;
   private JRadioButton exitFalse;
   private ButtonGroup exit = new ButtonGroup();
   
   //creates a submit button that is used to submit the changes made to the DungeonTile.
   private JButton submit = new JButton("Submit Changes");
   
   
   //Declares all of the parts of the menu bar
   private JMenuBar menuBar;
   private JMenu fileMenu;
   private JMenuItem saveItem;
   private JMenuItem loadItem;
   private JMenuItem exitItem;
   
   /**
   *    The main class instantiates the new window. 
   */
   public static void main(String[] args)
   {
      
      mapWidth = Integer.parseInt(JOptionPane.showInputDialog("Enter a width for the map"));
      mapLength = Integer.parseInt(JOptionPane.showInputDialog("Enter a length for the map"));
      new DungeonDevTool();
   }
   
 
   
   
   /**
   *    The DungeonDevTool constructor. When run, it creates and displays the window and runs the methods that
   *    create all of the panels within. 
   */
   public DungeonDevTool()
   {
      
      setTitle("Dungeon Development Tool");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      dungeonMapPanelGen();
      add(dungeonMapPanel, BorderLayout.CENTER);
      
      tileDisplayPanelGen();
      add(tileDisplayPanel, BorderLayout.EAST);
      
      menuBarGen();
      
      pack();
      setVisible(true);
   }
   
   /**
   *    The dungeonMapPanelGen method creates the displayed button map
   */
   
   public void dungeonMapPanelGen()
   {
      //sets the panel's layout to a 30 by 30 grid
      dungeonMapPanel.setLayout(new GridLayout(mapWidth, mapLength));
      
      
      /*
      / The nested for loops initialize all of the buttons and add them to the ButtonGroup and Panel. The outer loop is
      / the value on the y axis and the inner is the x axis.
      */ 
      for(int width = 0; width < mapWidth; width++)
      {
         for(int length = 0; length < mapLength; length++)
         {
            //creates each button with its name being its location on the grid
            dungeonTile[width][length] = new JButton(width + "," + length);
            //adds the action listener that causes the related tile's information to be displayed
            dungeonTile[width][length].addActionListener(new TileReturn());
            //adds the button to the ButtonGroup
            buttons.add(dungeonTile[width][length]);
            //adds the button to the panel
            dungeonMapPanel.add(dungeonTile[width][length]);
         }
      }
      
      //makes the panel visible
      dungeonMapPanel.setVisible(true);
   }
   
   private class TileReturn implements ActionListener
   {
      public void actionPerformed(ActionEvent button)
      {
         for(int width = 0; width < mapWidth; width++)
         {
            for(int length = 0; length < mapLength; length++)
            {
               if(button.getSource() ==  dungeonTile[width][length])
               {
                  remove(tileDisplayPanel);
                  tileDisplayPanelGen(width, length);
                  add(tileDisplayPanel, BorderLayout.EAST);
                  revalidate();
               }
               else
               {
               
               }
            }  
         }
      
      }
   }
   
   public void tileDisplayPanelGen()
   {
      tileDisplayPanel = new JPanel();
      tileDisplayPanel.setLayout(new GridLayout(20,1));
      tileDisplayPanel.add(tileLabel);
      tileDisplayPanel.add(existsLabel);
      tileDisplayPanel.add(startingTileLabel);
      tileDisplayPanel.add(enemyContainedLabel);
      tileDisplayPanel.add(exitLabel);
      
      
   }
   
   public void tileDisplayPanelGen(int yValue, int xValue)
   {
      yValueSelected = yValue;
      xValueSelected = xValue;
      tileDisplayPanel = new JPanel();
      tileDisplayPanel.setLayout(new GridLayout(20,1));
      tileNumber = new JLabel(yValue + "," + xValue);
      
      
      tileDisplayPanel.add(tileLabel);
      tileDisplayPanel.add(tileNumber);
      
      if(map.getTile(yValue,xValue).getExists())
      {
         existsTrue = new JRadioButton("True", true);
         existsFalse = new JRadioButton("False");
      }
      else
      {
         existsTrue = new JRadioButton("True");
         existsFalse = new JRadioButton("False", true);
      }
      exists.add(existsTrue);
      exists.add(existsFalse);
      
      tileDisplayPanel.add(existsLabel);
      tileDisplayPanel.add(existsTrue);
      tileDisplayPanel.add(existsFalse);
      
      if(map.getTile(yValue,xValue).getContainsEnemy())
      {
         enemyContainedTrue = new JRadioButton("True", true);
         enemyContainedFalse = new JRadioButton("False");
      }
      else
      {
         enemyContainedTrue = new JRadioButton("True");
         enemyContainedFalse = new JRadioButton("False", true);
         enemyContainedFalse.setSelected(true);
      }
      
      enemyContained.add(enemyContainedTrue);
      enemyContained.add(enemyContainedFalse);
      
      tileDisplayPanel.add(enemyContainedLabel);
      tileDisplayPanel.add(enemyContainedTrue);
      tileDisplayPanel.add(enemyContainedFalse);
      
      if(map.getTile(yValue,xValue).getStartingTile())
      {
         startingTileTrue = new JRadioButton("True", true);
         startingTileFalse = new JRadioButton("False");
      }
      else
      {
         startingTileTrue = new JRadioButton("True");
         startingTileFalse = new JRadioButton("False", true);
      }
      
      startingTile.add(startingTileTrue);
      startingTile.add(startingTileFalse);
      
      tileDisplayPanel.add(startingTileLabel);
      tileDisplayPanel.add(startingTileTrue);
      tileDisplayPanel.add(startingTileFalse);
      
      if(map.getTile(yValue,xValue).getExitTile())
      {
         exitTrue = new JRadioButton("True", true);
         exitFalse = new JRadioButton("False");
      }
      else
      {
         exitTrue = new JRadioButton("True");
         exitFalse = new JRadioButton("False", true);
      }
      
      exit.add(exitTrue);
      exit.add(exitFalse);
      
      tileDisplayPanel.add(exitLabel);
      tileDisplayPanel.add(exitTrue);
      tileDisplayPanel.add(exitFalse);
      
      
      
      submit.addActionListener(new SubmitButton());
      
      tileDisplayPanel.add(submit);
      
      tileDisplayPanel.setVisible(true);
   }
   
   private class SubmitButton implements ActionListener
   {
      public void actionPerformed(ActionEvent button)
      {
         
         map.setTile(yValueSelected,xValueSelected, new MapTiles(existsTrue.isSelected(), enemyContainedTrue.isSelected(), startingTileTrue.isSelected(), startingTileTrue.isSelected(), exitTrue.isSelected()));
           
      }
   }
   
   
   public void menuBarGen()
   {
      menuBar = new JMenuBar();
       
      fileMenu = new JMenu("File");
      fileMenu.setMnemonic(KeyEvent.VK_F);
       
      saveItem = new JMenuItem("Save");
      saveItem.setMnemonic(KeyEvent.VK_S);
      saveItem.addActionListener(new SaveListener());
      fileMenu.add(saveItem);
      
      loadItem = new JMenuItem("Load");
      loadItem.setMnemonic(KeyEvent.VK_L); 
      loadItem.addActionListener(new LoadListener());
      fileMenu.add(loadItem);
      
      exitItem = new JMenuItem("Exit");
      exitItem.setMnemonic(KeyEvent.VK_X);
      exitItem.addActionListener(new ExitListener());
      fileMenu.add(exitItem);
      
      menuBar.add(fileMenu);
      
      setJMenuBar(menuBar);
       
   }
   
   private class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent exitting)
      {
         System.exit(0);
      }
   }
   private class SaveListener implements ActionListener
   {
      public void actionPerformed(ActionEvent saving)
      {
         JFileChooser saveMap = new JFileChooser();
         if(saveMap.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
         {
            try
            {
               FileOutputStream outStream = new FileOutputStream(saveMap.getSelectedFile().getPath() + ".dmd");
               ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
               objectOutputFile.writeObject(map);
               outStream.close();
            }
            catch(IOException saveFail)
            {
               JOptionPane.showMessageDialog(null, "Something's fucky.\nError code: 112");
            }
         }
      }
   }
   
   private class LoadListener implements ActionListener
   {
      public void actionPerformed(ActionEvent loading)
      {
         JFileChooser loadMap = new JFileChooser();
         int status = loadMap.showOpenDialog(null);
         if(status == JFileChooser.APPROVE_OPTION)
         {
            try
            {
               FileInputStream inStream = new FileInputStream(loadMap.getSelectedFile().getPath());
               ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
               map = (DungeonMap) objectInputFile.readObject(); 
               mapWidth = map.getWidth();
               mapLength = map.getLength(0);
               
            }
            catch(IOException fileNotFound)
            {
               JOptionPane.showMessageDialog(null, "Error Code 004: File not found. How. The. Hell?");
            }
            catch(ClassNotFoundException missingMaps)
            {
               JOptionPane.showMessageDialog(null, "You seem to be missing the DungeonMap class. \nError Code: Missing all maps");
            }
            
         }
           
           
      }
   }
   
}
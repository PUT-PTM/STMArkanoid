package com.arkanoid.stm.objects;

import com.arkanoid.stm.ScreenProperties;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by grzeprza on 2015-04-06.
 */

 /**
  * Reads blocks from file or or generates n random blocks.
  * Sizes blocks to fit the screen.
  * */
public class Blocks
{

 ArrayList<Block> activeBlocksList;
 ArrayList<Block> passiveBlocksList;

 ArrayList<Integer> blocksInRowList;

 float singleBlock_width;
 float singleBlock_height;
 int rows;
 int columns;

 public Blocks()
 {
   activeBlocksList = new ArrayList<Block>();
   passiveBlocksList= new ArrayList<Block>();

  blocksInRowList = new ArrayList<Integer>();

 }

 //int blocksChecksum=0;

 private void addBlock(int type,float positionX, float positionY, float scaleX, float scaleY)
 {
   Block block= new Block(type,positionX,positionY, scaleX, scaleY);
   if(type != 0)
   {
     this.activeBlocksList.add(block);
   }
   else
   {
     this.passiveBlocksList.add(block);
   }
  //System.out.println(blocksChecksum++);
 }

 /**
  * Loads blocks randomly
  * */
 public void loadBlocks_randomly()
 {

  scaleBlocks();

  positionBlocksOnScreen();

 }

 /**Creates list of random number of blocks in column. Needed for direct positioning.*/
public boolean findNumberOfBlocksInRow()
{
 for(int i=0; i< rows; i++)
 {
  int randomNumber = new Random().nextInt(columns);

   blocksInRowList.add(randomNumber);
 //  System.out.print(blocksInRowList.get(i).intValue() + " ");
 }
 return true;
}

 /**
  * Finds max value in list <Integer>
  * */
 public int findMax()
 {
  int maxValue= blocksInRowList.get(0);
    for(int i=1; i< rows; i++)
    {
      maxValue = Math.max(maxValue, blocksInRowList.get(i));
    }
  return maxValue;
 }

/**
 * Positions blocks on screen.
 * */
 public boolean positionBlocksOnScreen()
 {
  findNumberOfBlocksInRow();

  for(int i=0; i < rows; i++)
  {
   int numberOfBlocks= blocksInRowList.get(i).intValue();

   for(int k= selectStartColumn(numberOfBlocks); k< numberOfBlocks; k++)
   {
    int type= new Random().nextInt(4);

    //Positions single row- left center right;
    this.addBlock(type,singleBlock_width*k,(singleBlock_height*i) + ScreenProperties.heightFit/2,singleBlock_width,singleBlock_height);
//    System.out.println("TYPE \t" + "pos_X \t" + "pos_Y \n" +
//                       + type + "\t\t" + singleBlock_width*k + "\t" + (singleBlock_height*i + 200));
   }
  }
   return true;
 }

 /**
  * Method chooses where to start drawing block, randomly.
  */
 private int selectStartColumn(int numberOfBlocks)
 {
   int startColumn = new Random().nextInt(columns - numberOfBlocks);
   return startColumn;
 }

 /**
  * Measures size for each blocks - block and row dependent size
  * */
 private boolean scaleBlocks()
 {
  columns= new Random().nextInt(5)+5;
  rows= new Random().nextInt(5)+5;

  System.out.println("Rows= " + rows + " Columns= " + columns);

  singleBlock_width = (float) (ScreenProperties.widthFit / columns);
  singleBlock_height = (float) ((ScreenProperties.heightFit/2) / rows);

//  System.out.println("Block size: x " + singleBlock_width + " y= " + singleBlock_height);

  return true;
 }

 /**Starts drawSprite method for every block in the activeBlocksList<Block>*/
 public void drawBlocks(SpriteBatch spriteBatch)
 {
    for(Block singleBlock: activeBlocksList)
    {
      singleBlock.drawSprite(spriteBatch);
    }

  for(Block singleBlock: passiveBlocksList)
  {
         singleBlock.drawSprite(spriteBatch);
  }
 }

 /**
  * Loads blocks from file
  * */
 private void loadBlocks_fromFile(File file)
 {
   /*TODO one size- positioned as in file
   * TODO File has to be square matrix*/

  }

 /**Returns list of blocks- needed for collision outside the Blocks class*/
 public ArrayList<Block> getActiveBlocksList()
 {
  return activeBlocksList;
 }

 public ArrayList<Block> getPassiveBlocksList()
 {
  return passiveBlocksList;
 }

 public void destroy()
 {
     Block block;
     Iterator<Block> it;
     if(!getPassiveBlocksList().isEmpty()) {

         it = getActiveBlocksList().iterator();
         while (it.hasNext()) {
             block = it.next();
             block.destroy();
             it.remove();
         }
     }
    it= getPassiveBlocksList().iterator();
    while(it.hasNext())
    {
       block = it.next();
       block.destroy();
        it.remove();
    }
 }

}




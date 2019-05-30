package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagesLoader {
	
	/**
    * The bufferedimage that represents the sprite sheet that is to be loaded.
 	*/
	private BufferedImage spriteSheet;  

   	/**
    * The width of each sprite.
 	*/
	private int width;

   	/**
    * The height of each sprite.
 	*/
	private int height;
   	
	/**
    * The number of rows in the sprite sheet.
 	*/
	private int rows;
	
   /**
   * The number of columns in the sprite sheet.
   */
	private int columns;
   
   /**
    * The array of buffered images that are all the sprites in the sprite sheet.
 	*/
	private BufferedImage[] sprites;
   
   /**
    * <b>Description:</b>
    * This function initializes a new images loader.
 * @param width The width of each sprite.
 * @param height The height of each sprite.
 * @param rows The number of rows in the sprite sheet.
 * @param columns The number of columns in the sprite sheet.
 * @param image The image that represents the sprite sheet that is to be loaded.
 * @throws IOException The exception that throws if the file isn't found.
 */
public ImagesLoader(int width, int height, int rows, int columns, String image) throws IOException {
      this.width = width;//32
      this.height = height;//32
      this.rows = rows;//7
      this.columns = columns;//4
      spriteSheet = ImageIO.read(new File(image));
      sprites = new BufferedImage[rows * columns];
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            sprites[(i * columns) + j] = spriteSheet.getSubimage(j * width, i * height, width, height);
         }
      }
   }
   
   /**
    * <b>Description:</b>
    * This function obtains the sprites of the sprite sheet.
 * @return The array of buffered images that represent the sprites of the sprite sheet.
 */
public BufferedImage[] getSprites() {
	   return sprites;
   }
}
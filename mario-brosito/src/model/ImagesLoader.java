package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagesLoader {
	
   private BufferedImage spriteSheet;   
   private int width;
   private int height;
   private int rows;
   private int columns;
   
   private BufferedImage[] sprites;
   
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
   
   public BufferedImage[] getSprites() {
	   return sprites;
   }
}
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	
   BufferedImage spriteSheet = ImageIO.read(new File("src/uiImg/player.png"));   
   
   int width;
   int height;
   int rows;
   int columns;
   BufferedImage[] sprites;
   
   public SpriteSheetLoader(int width, int height, int rows, int columns) throws IOException {
      this.width = width;//32
      this.height = height;//32
      this.rows = rows;//7
      this.columns = columns;//4
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
package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ImagesLoaderTest {
	
	private ImagesLoader imagesLoader;
	
	private void setupScenary1() {
		
	}
	
	@Test
	public void testImagesLoader() {
		setupScenary1();
		int width = 32;
		int height = 32;
		int rows = 1;
		int columns = 3;
		String image = "src/uiImg/QuestionMark.png";
		
		try {
			imagesLoader = new ImagesLoader(width, height, rows, columns, image);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		assertNotNull("The image load is null", imagesLoader);
		assertTrue("The sprites were not added", imagesLoader.getSprites().length>0);
		
	}

}

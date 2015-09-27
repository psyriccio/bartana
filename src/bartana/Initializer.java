package bartana;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

public class Initializer {
	
	public static void init() throws IOException{
		
		Properties properties = new Properties();
		
		properties.load(new FileInputStream("bartana.properties"));
		
		Scene.tempPath = (String) properties.get("scene.tempPath");
		Comparator.tempPath = (String) properties.get("comparator.tempPath");
		Comparator.tempPath = (String) properties.get("comparator.tempPath");
		Genecy.targetImage = (String) properties.get("genecy.targetImage");
		
		BufferedImage bufferedImage = ImageIO.read(new File(Genecy.targetImage));
		Scene.width = bufferedImage.getWidth();
		Scene.height = bufferedImage.getHeight();

	}
	
	public static void main(String[] args) throws IOException {
		Initializer.init();
		
		System.out.println(Scene.tempPath);
		System.out.println(Comparator.tempPath);
		System.out.println(Genecy.targetImage);
	}

}

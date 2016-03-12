package Model;

import java.util.Observable;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.nio.file.*;
import javax.imageio.*;

public class Model extends Observable {
	// List of loaded images
	HashMap<ImageModel, BufferedImage> selectedImages = new HashMap<ImageModel, BufferedImage>();
	public Model() {

	}

	public void addFilePaths (ArrayList<String> filePaths) {
		for (String fp : filePaths) {
			Path filePath = Paths.get(fp);
			File imgFile = new File(fp);
			BufferedImage bufImg;
			Image image;
			BasicFileAttributes fileAttr;
			ImageModel imModel;

			try {
				image = ImageIO.read(imgFile);
				fileAttr = Files.readAttributes(filePath, BasicFileAttributes.class);
				bufImg = (BufferedImage) image;
				imModel = new ImageModel(fp, fileAttr.creationTime(), fileAttr.lastAccessTime(), fileAttr.size());
				System.out.println("Image loaded: " + imModel.toString());
				selectedImages.put(imModel, bufImg);
			} catch (IOException ex) {
				System.err.println("File not found: " + fp);
			}
		}
	}
}
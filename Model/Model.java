package Model;

import Resources.*;
import java.util.Observable;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.nio.file.*;
import javax.imageio.*;

public class Model extends Observable {
	// List of loaded images
	private HashMap<ImageModel, ImageIcon> selectedImages;
	
	public Model() {
		selectedImages = new HashMap<ImageModel, ImageIcon>();
	}



	public void addFilePaths (ArrayList<String> filePaths) {
		for (String fp : filePaths) {
			Path filePath = Paths.get(fp);
			File imgFile = new File(fp);
			Image image;
			BasicFileAttributes fileAttr;
			ImageModel imModel;
			Image scaledImg;
			ImageIcon thumbnail;

			try {
				image = ImageIO.read(imgFile);
				fileAttr = Files.readAttributes(filePath, BasicFileAttributes.class);
				scaledImg = image.getScaledInstance(GlobalConstants.THUMBNAIL_WIDTH,
					GlobalConstants.THUMBNAIL_HEIGHT, Image.SCALE_SMOOTH);
				thumbnail = new ImageIcon(scaledImg);
				imModel = new ImageModel(fp, fileAttr.creationTime(), fileAttr.lastAccessTime(), fileAttr.size());
				System.out.println("Image loaded: \n" + imModel.toString());
				selectedImages.put(imModel, thumbnail);
			} catch (IOException ex) {
				System.err.println("File not found: " + fp);
			}
		}
		setChanged();
		notifyObservers();
	}

	public HashMap<ImageModel, ImageIcon> getSelectedImages () {
		return selectedImages;
	}
}
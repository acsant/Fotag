package Model;

import Resources.GlobalConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.UUID;

public class Model extends Observable {
	// List of loaded images
	private HashMap<ImageModel, ImageIcon> selectedImages;
	private boolean showName = true;
	private Dimension collectionSize = GlobalConstants.SCREEN_SIZE;
	private Dimension currentSize = GlobalConstants.SCREEN_SIZE;
	private int numImages = 0;
	private boolean gridView = true;
	private int filtered = 0;
	public Model() {
		selectedImages = new HashMap<ImageModel, ImageIcon>();
	}



	public void addFilePaths (ArrayList<String> filePaths) {
		for (String fp : filePaths) {
			ImageIcon icon;
			Path filePath = Paths.get(fp);
			File imgFile = new File(fp);
			BufferedImage image;
			BasicFileAttributes fileAttr;
			ImageModel imModel;

			try {
				image = ImageIO.read(imgFile);
				double height = image.getHeight();
				double width = image.getWidth();
				double ratio = width/height;
				icon = new ImageIcon(image.getScaledInstance(((Double)(GlobalConstants.THUMBNAIL_WIDTH*ratio)).intValue(),
						GlobalConstants.THUMBNAIL_HEIGHT, Image.SCALE_FAST));
				fileAttr = Files.readAttributes(filePath, BasicFileAttributes.class);
				imModel = new ImageModel(fp, fileAttr.creationTime(), fileAttr.lastAccessTime(), fileAttr.size());
				selectedImages.put(imModel, icon);
			} catch (IOException ex) {
				System.err.println("File not found: " + fp);
			}
		}

		double numRows = (selectedImages.size() / ((currentSize.width - (GlobalConstants.THUMBNAIL_WIDTH)) / GlobalConstants.THUMBNAIL_WIDTH)) + 1;
		if (!isGridView()) {
			numRows = findNumFiltered();
		}
		collectionSize = new Dimension(currentSize.width ,((Double)(numRows * (GlobalConstants.THUMBNAIL_HEIGHT + 100))).intValue());
		setChanged();
		notifyObservers();
	}

	public HashMap<ImageModel, ImageIcon> getSelectedImages () {
		return selectedImages;
	}

	public void setShowName (boolean show) {
		showName = show;
		setChanged();
		notifyObservers();
	}

	public boolean isShowName() {
		return showName;
	}


	public Dimension getCollectionSize() {
		return collectionSize;
	}

	public void setCollectionSize(Dimension collectionSize) {
		this.collectionSize = collectionSize;
	}


	public Dimension getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(Dimension _currentSize) {
		this.currentSize = _currentSize;
		double numRows = (numImages / ((currentSize.width - (GlobalConstants.THUMBNAIL_WIDTH)) / GlobalConstants.THUMBNAIL_WIDTH)) + 1;
		if (!isGridView()) {
			numRows = findNumFiltered();
		}
		collectionSize = new Dimension(currentSize.width, ((Double)(numRows * (GlobalConstants.THUMBNAIL_HEIGHT + 100))).intValue());
		setChanged();
		notifyObservers();
	}

	public void rankImage (UUID id, int rank) {
		for (HashMap.Entry<ImageModel, ImageIcon> entry : selectedImages.entrySet()) {
			ImageModel imgToRank = entry.getKey();
			if (imgToRank.getImageId().equals(id)) {
				imgToRank.rankImage(rank);
			}
		}
		setChanged();
		notifyObservers();
	}

	public void setGridView (boolean view) {
		gridView = view;
		setCurrentSize(currentSize); // Adjust size and call notify
	}

	public int getFilter () {
		return filtered;
	}

	public void setFilter (int f) {
		filtered = f;
		setCurrentSize(collectionSize); // Adjust the size and call notify
	}

	public boolean isGridView () {
		return gridView;
	}

	private int findNumFiltered () {
		int count = 0;
		if (filtered > 0) {
			for (HashMap.Entry<ImageModel, ImageIcon> entry : selectedImages.entrySet()) {
				if (entry.getKey().getRank() == filtered) {
					count++;
				}
			}
		} else {
			count = selectedImages.size();
		}
		return count;
	}
}
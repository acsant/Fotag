package Model;

import Resources.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.lang.*;
import java.nio.file.*;
import java.util.UUID;

public class ImageModel {
	// Image attributes including metadata
	private String imgPath;
	private String creationDateTime;
	private String lastAccessDateTime;
	private String imgSize;
	private String fileName;
	private int ranking = 0;
	private UUID imageId;
	
	public ImageModel (String filePath, FileTime creationTime, FileTime lastAccess, long size) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
		Path path = Paths.get(filePath);
		imgPath = filePath;
		creationDateTime = dateFormat.format(creationTime.toMillis());
		lastAccessDateTime = dateFormat.format(lastAccess.toMillis());
		imgSize = formatImageSize(size);
		fileName = path.getFileName().toString();
		imageId = UUID.randomUUID();
	}

	public String getImagePath () {
		return imgPath;
	}

	public String getCreationDateTime () {
		return creationDateTime;
	}

	public String getLastAccessDateTime () {
		return lastAccessDateTime;
	}

	public String getImageSize () {
		return imgSize;
	}

	private String formatImageSize (long size) {
		DecimalFormat sizeFormat = new DecimalFormat(GlobalConstants.SIZE_FORMAT);
		double kilo = size / 1024;
		double mega = size / Math.pow(1024, 2);
		double giga = size / Math.pow(1024, 3);
		double tera = size / Math.pow(1024, 4);

		if (Math.round(tera) > 0) {
			return sizeFormat.format(tera) + " TB";
		} else if (Math.round(giga) > 0) {
			return sizeFormat.format(giga) + " GB";
		} else if (Math.round(mega) > 0) {
			return sizeFormat.format(mega) + " MB";
		} else if (Math.round(kilo) > 0) {
			return sizeFormat.format(kilo) + " KB";
		}
		return size + " bytes";
	}

	@Override
	public String toString () {
		return "Path: " + imgPath +  
				"\n" + "Image Size: " + imgSize +
				"\n" + "Creation Date: " + creationDateTime + 
				"\n" + "Last Accessed: " + lastAccessDateTime;
	}

	public String htmlString () {
		return "<html>Path: " + fileName +  
				"<br>" + "Image Size: " + imgSize +
				"<br>" + "Creation Date: " + creationDateTime + 
				"<br>" + "Last Accessed: " + lastAccessDateTime + "</html>";
	}

	public UUID getImageId () {
		return imageId;
	}

	public void rankImage(int rank) {
		ranking = rank;
	}

	public int getRank () {
		return ranking;
	}
}
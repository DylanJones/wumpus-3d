package display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.EntityGremlin;

/** A class to contain the World template data */
public final class WorldTemplate {
	public final ArrayList<String> entities = new ArrayList<String>();
	public final ArrayList<String> walls = new ArrayList<String>();
	public String version;
	public String image;
	private String worldNorth = "null.wld";
	private String worldSouth = "null.wld";
	private String worldEast = "null.wld";
	private String worldWest = "null.wld";

	public WorldTemplate(String filename) {
		parseFile(filename);
	}

	private void parseFile(String filename) {
		// The file is in the assets/worlds directory
		filename = "assets/worlds/" + filename;
		// Read the file, ignoring comments
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> fileContents = new ArrayList<String>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (!(line.startsWith("#") || line.equals("")))
				fileContents.add(line);
		}
		// Split into sections
		String lastLine = fileContents.get(0);
		int fileIndex = 0;
		// Skip to metadata
		while (!lastLine.contains("metadata:")) {
			fileIndex++;
			lastLine = fileContents.get(fileIndex);
		}
		// Read metadata UNTIL walls:
		while (!lastLine.contains("walls:")) {
			fileIndex++;
			lastLine = fileContents.get(fileIndex);
			if (lastLine.startsWith("version "))
				this.version = lastLine.replace("version ", "");
			if (lastLine.startsWith("image "))
				this.image = "assets/backgrounds/" + lastLine.replace("image ", "");
			if (lastLine.startsWith("north "))
				this.worldNorth = lastLine.replace("north ", "");
			if (lastLine.startsWith("south "))
				this.worldSouth = lastLine.replace("south ", "");
			if (lastLine.startsWith("east "))
				this.worldEast = lastLine.replace("east ", "");
			if (lastLine.startsWith("west "))
				this.worldWest = lastLine.replace("west ", "");
		}
		// Read walls UNTIL entities
		while (!lastLine.contains("entities:")) {
			fileIndex++;
			lastLine = fileContents.get(fileIndex);
			if (!lastLine.equalsIgnoreCase("entities:"))
				walls.add(lastLine);
		}
		// Read entities UNTIL EOF
		while (fileIndex < fileContents.size()) {
			lastLine = fileContents.get(fileIndex);
			entities.add(lastLine);
			fileIndex++;
		}
	}

	public void load() {
		World.setBackgroundImage(image);
		for (String wall : this.walls) {
			String[] coords = wall.split(" ");
			int x = Integer.parseInt(coords[0]);
			int y = Integer.parseInt(coords[1]);
			int length = Integer.parseInt(coords[2]);
			int direction;
			try {
				direction = Integer.parseInt(coords[3]);
			} catch (NumberFormatException e) {
				if(coords[3].contains("horizontal")) {
					direction = World.HORIZONTAL;
				}else {
					direction = World.VERTICAL;
				}
			}
			World.addWall(x, y, length, direction);
		}

		for (String entity : this.entities) {
			createEntity(entity);
		}
	}

	private void createEntity(String entity) {
		// Switch on the first word (entity name)
		String[] args = entity.split(" ");
		switch (args[0]) {
		case "gremlin":
			new EntityGremlin(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),
					Integer.parseInt(args[4]));
			break;
		}
	}

	public String getNextWorld(int direction) {
		switch (direction) {
		case World.NORTH:
			return worldNorth;
		case World.SOUTH:
			return worldSouth;
		case World.EAST:
			return worldEast;
		case World.WEST:
			return worldWest;
		default:
			return null;
		}
	}

}

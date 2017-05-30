// Written by Parth V

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class fileOperations {
	public static String deletePath = "";
	public static String one_level;
	public static String multi_level;
	public static String two_lvl_1;
	public static String two_lvl_2;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select file structure");
		System.out.println("1) One level structure");
		System.out.println("2) Two level structure");
		System.out.println("3) Multi level hierarchial structure");
		int structure_choice = sc.nextInt();
		switch(structure_choice){
		case 1:
			System.out.println("You have selected one level directory");
			System.out.println("Enter root directory name");
			String root = sc.next();
			String directory11 = "D:\\"+root;
			one_level = directory11;
			if(new File(directory11).mkdir())
				System.out.println("File create successfully");	
			System.out.println("directory structure is : " + directory11 );
			break;
			
		case 2:
			System.out.println("You have selected two level directory");
			System.out.println("Enter root directory name");
			String root21 = sc.next();
			System.out.println("Enter sub directory name 1");
			String name21 = sc.next();
			System.out.println("Enter sub directory name 2");
			String name22 = sc.next();
			String directory21 = "D:\\"+root21+"\\"+name21;
			String directory22 = "D:\\"+root21+"\\"+name22;
			two_lvl_1 = directory21;
			two_lvl_2 = directory22;
			if(new File(directory21).mkdirs() && new File(directory22).mkdirs())
				System.out.println("File create successfully");
			System.out.println("directory structure is : " + directory21 );
			System.out.println("directory structure is : " + directory22 );
			break;
			
			
		case 3:
			System.out.println("You have selected hierarchical directory structure");
			System.out.println("five level directory will be created");
			System.out.println("Enter root directory name");
			String root33 = sc.next();
			System.out.println("Enter sub directory level 1");
			String name31 = sc.next();
			System.out.println("Enter sub directory level 2");
			String name32 = sc.next();
			System.out.println("Enter sub directory level 3");
			String name33 = sc.next();
			System.out.println("Enter sub directory level 4");
			String name34 = sc.next();
			String directory31 = "D:\\" + root33 + "\\" + name31 + "\\" + name32 + "\\" + name33 + "\\" + name34;
			multi_level = directory31;
			if (new File(directory31).mkdirs()) 
				System.out.println("File create successfully");
			System.out.println("directory structure is : " + directory31 );
			break;
		}			
		while (true) {
			System.out.println("");
			System.out.println("Choose Operation..");
			System.out.println("1) Delete File");
			System.out.println("2) Search File");
			System.out.println("3) Display File");
			System.out.println("4) Create File");
			System.out.println("5) End Operation");
			int operation_choice = sc.nextInt();
			switch (operation_choice) {
			case 1:
				deleteFile();
				break;
			case 2:
				searchFile();
				break;
			case 3:
				displayFile();
				break;
			case 4:
				createFile();
				break;
			case 5:
				return;
			}
		}
	}
			
	// Search file
	public static void findFile(String name, File file) {
		File[] list = file.listFiles();
		if (list != null)
			for (File fil : list) {
				if (fil.isDirectory()) {
					findFile(name, fil);
				} else if (name.equalsIgnoreCase(fil.getName())) {
					System.out.println("File found at " + fil.getParentFile());
					deletePath = fil.getAbsolutePath();
				}

			}
	}

	public static void displayFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter absolute path of the directory");
		String dir = sc.next();
		File[] list = new File(dir).listFiles();
		if (list == null)
			System.out.println("No files found");
		else {
			System.out.println("Printing all files...");
			for (File fil : list) {
				System.out.println("file found " + fil.getName());
			}
		}

	}

	public static void searchFile() {
		deletePath = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name with extension");
		String searchFile = sc.next();
		System.out.println("Give parent directory (C/D)");
		String parent = sc.next();
		parent = parent + "://";
		findFile(searchFile, new File(parent));
		if (deletePath.equalsIgnoreCase("")) {
			System.out.println("file not found !!");
		} else {
			System.out.println("Your file is found !");
		}
	}

	public static void deleteFile() {
		deletePath = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name with extension");
		String searchFile = sc.next();
		System.out.println("Give parent directory (C/D)");
		String parent = sc.next();
		parent = parent + "://";
		findFile(searchFile, new File(parent));
		if (deletePath.equalsIgnoreCase("")) {
			System.out.println("file not found !!");
		} else {
			delete(deletePath);
		}
	}

	public static void delete(String name) {
		try {

			File f = new File(name);
			if (f.delete())
				System.out.println(f.getName() + " is deleted");
			else
				System.out.println("failed");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void createFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Give file a name with extension");
		String name = sc.next();
		System.out.println("Give directory");
		String dir = sc.next();
		String f = dir + "\\" + name;
		try {

			File file = new File(f);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

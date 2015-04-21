package main;

import java.io.File;
import java.nio.file.Path;

public class ErrorManager {
	
	public static void throwFileError(File notFound){
		System.out.println("Could not find file: " + notFound.toString() + " " + Thread.currentThread().getStackTrace()[2]);
	}
	
	public static void throwFileAlreadyExists(Path exists){
		System.out.println("File: " + exists.toString() + " already exists. " + Thread.currentThread().getStackTrace()[2]);
	}
	
	public static void throwFileDeletionError(Path notFound){
		System.out.println("File: " + notFound.toString() + " not found or deleted. " + Thread.currentThread().getStackTrace()[2]);
	}
}

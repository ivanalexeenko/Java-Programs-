package com.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public FileReader(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if(!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        Scanner scanner = new Scanner(filename);
    }
}

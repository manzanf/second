package com.playtika.second;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;

class FilesProcessor {

    public static void main(String[] args) {
        File dir = new File("C:\\Users\\Manzana\\IdeaProjects\\second\\processedDir");
        if (dir.isDirectory()) {
            StringBuilder fileContent = new StringBuilder("");
            try {
                for (File file : dir.listFiles()) {
                    if (file.isFile()) {
                        getFileInfo(file);
                        try {
                            fileContent.append(new String(Files.readAllBytes(file.toPath())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            Text text = new Text(fileContent.toString());
            System.out.println("Aggregated words' frequency is:\n" + text.getFrequencies());
        }

        File sourceFile = new File(dir.getPath() + "\\file.txt");
        File destinationFile = new File(dir.getPath() + "\\file_copy.txt");
        copyFile(sourceFile, destinationFile);

    }

    private static void getFileInfo(File file) {
        try {
            FileTime fileCreationDate = (FileTime) Files.getAttribute(file.toPath(), "basic:creationTime");
            System.out.println(String.format("The file %s has %s bytes size and was created at %s",
                    file.getPath(), file.length(), fileCreationDate));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) {
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead = fileInputStream.read(buffer);
            while (bytesRead > 0) {
                fileOutputStream.write(buffer, 0, bytesRead);
                bytesRead = fileInputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

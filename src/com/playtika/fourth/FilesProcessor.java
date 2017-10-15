package com.playtika.fourth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class FilesProcessor {

    public static void main(String[] args) throws IOException {
        File dir = new File("processedDir");
        if (!dir.isDirectory() || dir.listFiles() == null) {
            System.out.println("Target path is not a directory");
            return;
        }
        for (File file : dir.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            printFileInfo(file);
        }

        System.out.println("Aggregated words' frequency is:\n" + getWordsFrequenciesInDir(dir));

        File sourceFile = new File(dir, "file.txt");
        File destinationFile = new File(dir, "file_copy.txt");
        try {
            copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            System.out.println("Copying of the file is failed");
            e.printStackTrace();
        }
    }

    private static String getFileContent(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("The file cannot be processed: " + path);
            e.printStackTrace();
            return "";
        }
    }

    private static Map<String, Long> getWordsFrequenciesInDir(File dir) throws IOException {
        return Files.list(dir.toPath())
                .map(FilesProcessor::getFileContent)
                .map(Text::new)
                .map(Text::getFrequencies)
                .flatMap(map -> map.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, counting()));
    }

    private static void printFileInfo(File file) {
        try {
            FileTime fileCreationDate = (FileTime) Files.getAttribute(file.toPath(), "basic:creationTime");
            System.out.println(String.format("The file %s has %s bytes size and was created at %s",
                    file.getPath(), file.length(), fileCreationDate));
        } catch (IOException e) {
            System.out.println("Can't return info for " + file.getPath());
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) throws IOException {
        if (destinationFile.exists()) {
            destinationFile.delete();
        }
        try (FileInputStream in = new FileInputStream(sourceFile);
             FileOutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            while (bytesRead > 0) {
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }
        }
    }
}

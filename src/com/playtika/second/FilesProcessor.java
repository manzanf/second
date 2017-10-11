package com.playtika.second;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;

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
        }
    }

    private static Map<String, Integer> getWordsFrequenciesInDir(File dir) {
        Map<String, Integer> dirWordsFrequencies = new HashMap<>();
        for (File file : dir.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            try {
                String fileContent = new String(Files.readAllBytes(file.toPath()));
                Text text = new Text(fileContent);
                Map<String, Integer> fileWordsFrequencies = text.getFrequencies();
                dirWordsFrequencies = mergeMaps(dirWordsFrequencies, fileWordsFrequencies);
            } catch (IOException e) {
                System.out.println("Can't read the file content");
            }
        }
        return dirWordsFrequencies;
    }

    private static Map<String, Integer> mergeMaps(Map<String, Integer> oldMap, Map<String, Integer> newMap) {
        for (String word : newMap.keySet()) {
            Integer oldFrequency = oldMap.get(word) == null ? 0 : oldMap.get(word);
            oldMap.put(word, oldFrequency + newMap.get(word));
        }
        return oldMap;
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

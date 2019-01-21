package com.epam.brest.cources.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFile {

    public static Map<String, String> getMapFromCSV(final String filePath) throws IOException {

        String paths = CSVFile.class.getClassLoader().getResource(filePath).getPath();
        Stream<String> lines = Files.lines(Paths.get(paths));
        Map<String, String> resultMap =
                lines.map(line -> line.split(",")).collect(Collectors.toMap(line -> line[0], line -> line[1]));

        lines.close();

        return resultMap;
    }


}

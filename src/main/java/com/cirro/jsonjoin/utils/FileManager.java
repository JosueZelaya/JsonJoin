/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cirro.jsonjoin.utils;

import com.cirro.jsonjoin.entity.ResponseRow;
import com.cirro.jsonjoin.entity.Row;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author alexander
 */
public class FileManager {    
    
    public static <T extends Row> List<T> loadFile(File file, Class<T> valueType) throws IOException{
        List rowList = new ArrayList();
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        while (it.hasNext()) {
            String line = it.nextLine();
            Row row = convertToRow(line, valueType);
            rowList.add(row);
        }        
        return rowList;
    }
    
    public static <T extends Row> Stream<T> loadFileStream(File file, Class<T> valueType) throws IOException{
        List rowList = new ArrayList();
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        while (it.hasNext()) {
            String line = it.nextLine();
            Row row = convertToRow(line, valueType);
            rowList.add(row);
        }        
        return rowList.stream();
    }
    
    public static <T extends Row> T convertToRow(String input,  Class<T> valueType) throws IOException{
        return new ObjectMapper().readValue(input, valueType);
    }
    
    public static String convertToJsonString(Row row) throws IOException{
        return new ObjectMapper().writeValueAsString(row);
    }
    
    public static void saveResults(List<ResponseRow> result, String path) throws IOException{
        new ObjectMapper().writeValue(new File(path), result);
    }
    
}

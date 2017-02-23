/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cirro.jsonjoin.tests;

import com.cirro.jsonjoin.core.Processor;
import com.cirro.jsonjoin.entity.RowA;
import com.cirro.jsonjoin.entity.RowB;
import com.cirro.jsonjoin.utils.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author alexander
 */
public class TestResult {

    @Test
    public void TestResult() {
        try {            
            ResourceBundle configBundle = ResourceBundle.getBundle("paths");
            System.out.println("Obtaining file path from config file");
            File firstFile = new File(configBundle.getString("file1"));
            File secondFile = new File(configBundle.getString("file2"));
            
            System.out.println("Loading files");
            Stream<RowA> streamA = FileManager.loadFileStream(firstFile, RowA.class);
            Stream<RowB> streamB = FileManager.loadFileStream(secondFile, RowB.class);
            
            Processor processor = new Processor(streamA, streamB);
            
            System.out.println("Start processing");
            processor.start();                  
            System.out.println("Verifing results");
            Set<Double> uniqueXs = new HashSet<>();
            processor.getResult().forEach((item) -> {
                uniqueXs.add(item.getT1x());
            });            
            System.out.println("Total rows: " + processor.getResult().size() + ". Total unique x values: " + uniqueXs.size());
            Assert.assertEquals(processor.getResult().size(), uniqueXs.size());
        } catch (IOException ex) {
            Logger.getLogger(TestResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

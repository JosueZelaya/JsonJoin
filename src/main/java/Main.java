
import com.cirro.jsonjoin.core.Processor;
import com.cirro.jsonjoin.entity.ResponseRow;
import com.cirro.jsonjoin.entity.RowA;
import com.cirro.jsonjoin.entity.RowB;
import com.cirro.jsonjoin.utils.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexander
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            print("Welcome Omar!");
            print("Please specify the path of the first file: ");
            File firstFile = new File(getInput());
            print("Please specify the path of the second file: ");
            File secondFile = new File(getInput());
            print("Please specify the path of the output file: ");
            String outPutPath = getInput();
            
            Stream<RowA> streamA = FileManager.loadFileStream(firstFile, RowA.class);
            Stream<RowB> streamB = FileManager.loadFileStream(secondFile, RowB.class);
            Processor processor = new Processor(streamA, streamB);
            processor.start();
            
            //Object to JSON in file            
            FileManager.saveResults(processor.getResult(), outPutPath);
            print("RESULTS ARE SAVED IN: " + outPutPath);
            
            printResults(processor.getResult());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void print(String message){
        System.out.println(message);
    }
    
    public static String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void printResults(List<ResponseRow> result){        
        result.forEach( row -> {
            System.out.printf("%10.2f  %10.2f   %10.2f \n", row.getT1x(), row.getT1Y(), row.getT2Y());
        });
    }
}

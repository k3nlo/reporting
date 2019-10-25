package demo.reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {


  public static void writeToFile(String content){
//    content+="\n";
    // If the file doesn't exists, create and write to it
    // If the file exists, append
    try (FileWriter writer = new FileWriter("report.txt", true);
        BufferedWriter bw = new BufferedWriter(writer)) {
      bw.write(content);

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }

  public static void clearFile(String path){
    try (FileWriter writer = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(writer)) {
      bw.write("");

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }


}


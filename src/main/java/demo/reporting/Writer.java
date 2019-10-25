package demo.reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

  String fileName;

  public Writer() {
  }

  public Writer(String fileName) {
    this.fileName = fileName;
  }

  public void writeToFile(String content){
    // If the file doesn't exists, create and write to it
    // If the file exists, append
    try (FileWriter writer = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(writer)) {
      bw.write(content);

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }

  public void clearFile(){
    try (FileWriter writer = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(writer)) {
      bw.write("");

    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }


}


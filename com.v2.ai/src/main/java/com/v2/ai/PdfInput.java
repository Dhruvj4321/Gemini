package com.v2.ai;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class PdfInput {

//  public static void main(String[] args) throws IOException {
//    // TODO(developer): Replace these variables before running the sample.
////    String projectId = "extreme-world-434410-u8";
////    String location = "asia-south1";
////    String modelName = "gemini-1.5-flash-001";
//	  String projectId = "extreme-world-434410-u8";
//	    String location = "asia-south1";
//	  //  String modelName = "gemini-1.0-pro-vision";
//	    String modelName = "gemini-1.5-flash-001";
//	    
//
//    pdfInput(projectId, location, modelName);
//  }
//
//  // Analyzes the given video input.
//  public static String pdfInput(String projectId, String location, String modelName)
//      throws IOException {
//    // Initialize client that will be used to send requests. This client only needs
//    // to be created once, and can be reused for multiple requests.
//    try (VertexAI vertexAI = new VertexAI(projectId, location)) {
//     
//    byte data[] =   FileUtils.readFileToByteArray(new File("C:\\Users\\USER\\Desktop\\Dhruv\\V2\\sample.pdf"));
//
//      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
//      GenerateContentResponse response = model.generateContent(
//          ContentMaker.fromMultiModalData("pls construct 5 MCQ questions from the attached data with 4 choices and 1 correct choice. Return the response in json format", PartMaker.fromMimeTypeAndData("application/pdf", data)));
////model.ge
//
//      String output = response.getCandidates(0).getContent().getParts(0).getText();
//      System.out.println(output);
//      return output;
//    }
//  }
}

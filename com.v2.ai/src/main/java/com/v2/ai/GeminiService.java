package com.v2.ai;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;

@Service
public class GeminiService {

	public String processFile(String prompt, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        
        // Check if fileName is not null and determine file type by extension
        if (fileName != null && fileName.toLowerCase().endsWith(".pdf")) {
            return generateQuestionsFromPdf(prompt, file);
        } else if (fileName != null && fileName.toLowerCase().endsWith(".mp3")) {
            return summarizeAudioFromMultipart(prompt, file);
        }
        else if(fileName != null && fileName.toLowerCase().endsWith(".mp4")) {
        	return videoAudioInput(prompt, file);
        }
        else {
            throw new IllegalArgumentException("Unsupported file type. Only PDF and MP3 are supported.");
        }
    }

    private String generateQuestionsFromPdf(String prompt, MultipartFile pdfFile) throws IOException {
        String projectId = "extreme-world-434410-u8";
        String location = "asia-south1";
        String modelName = "gemini-1.5-flash-001";

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            // Convert MultipartFile to byte array
            byte[] data = pdfFile.getBytes();

            // Create the model and send the request
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(
                ContentMaker.fromMultiModalData(prompt, PartMaker.fromMimeTypeAndData("application/pdf", data))
            );

            // Extract and return the output
            String output = response.getCandidates(0).getContent().getParts(0).getText();
            return output;
        }
    }

    private String summarizeAudioFromMultipart(String prompt, MultipartFile audioFile) throws IOException {
        String projectId = "extreme-world-434410-u8";
        String location = "asia-south1";
        String modelName = "gemini-1.5-flash-001";

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            // Convert MultipartFile to byte array
            byte[] data = audioFile.getBytes();

            // Create the model and send the request for audio summarization
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(
                ContentMaker.fromMultiModalData(
                    prompt, PartMaker.fromMimeTypeAndData("audio/mp3", data)
                ));

            // Extract and return the output
            String output = response.getCandidates(0).getContent().getParts(0).getText();
            return output;
        }
    }
    
    
    private String videoAudioInput(String prompt, MultipartFile videoFile) throws IOException {
        String projectId = "extreme-world-434410-u8";
        String location = "asia-south1";
        String modelName = "gemini-1.5-flash-001";

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            // Convert MultipartFile to byte array
            byte[] data = videoFile.getBytes();

            // Create the model and send the request
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(
                ContentMaker.fromMultiModalData(prompt, PartMaker.fromMimeTypeAndData("video/mp4", data))
            );

            // Extract and return the output
            String output = response.getCandidates(0).getContent().getParts(0).getText();
            return output;
        }
    }

}
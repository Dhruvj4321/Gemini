package com.v2.ai;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//public class GeminiApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(GeminiApplication.class, args);
//    }
//}



@RestController
@RequestMapping("/api/gemini")
class GeminiController {
	
	@Autowired
	GeminiService geminiService;
	
	@PostMapping("/call")
	public ResponseEntity<?> processFile(
            @RequestParam("prompt") String prompt,
            @RequestParam("file") MultipartFile file) {

        try {
            String response = geminiService.processFile(prompt, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the file.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

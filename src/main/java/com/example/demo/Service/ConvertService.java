package com.example.demo.Service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ConvertService {

    public List<Map<String, String>> processFile(MultipartFile file) {
        List<Map<String, String>> results = new ArrayList<>();

        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            String question = null;
            StringBuilder answers = new StringBuilder();
            Pattern questionPattern = Pattern.compile("^Câu \\d+:");

            for (XWPFParagraph paragraph : paragraphs) {
                if (questionPattern.matcher(paragraph.getText()).find()) {
                    // If a previous question is set and answers are accumulated, save it before moving to the next question
                    if (question != null && answers.length() > 0) {
                        Map<String, String> qna = new HashMap<>();
                        qna.put("question", question);
                        qna.put("answer", answers.toString());
                        results.add(qna);

                        // Reset answers for the next question
                        answers.setLength(0);
                    }
                    question = paragraph.getText();
                } else {
                    for (XWPFRun run : paragraph.getRuns()) {
                        if (run.getUnderline().name().equals("SINGLE")) {
                            // Accumulate all underlined parts under a question
                            answers.append(run.text()).append(" ");
                        }
                    }
                }
            }

            // Add any remaining question-answer pair
            if (question != null && answers.length() > 0) {
                Map<String, String> qna = new HashMap<>();
                qna.put("question", question);
                qna.put("answer", answers.toString());
                results.add(qna);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}

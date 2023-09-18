package com.example.demo.Controller;

import com.example.demo.Service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class ConvertController {

    @Autowired
    private ConvertService convertService;

    @GetMapping("/")
    public String index() {
        return "fileUpload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        List<Map<String, String>> results = convertService.processFile(file);
        model.addAttribute("results", results);
        return "results";
    }
}

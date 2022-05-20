package com.vladislavgoncharov.testtaskforits.controller;

import com.vladislavgoncharov.testtaskforits.main_package.SearchSubstring;
import com.vladislavgoncharov.testtaskforits.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@MultipartConfig
public class ControllerForSearchSubstring {

    @Autowired
    ProgramService programService;

    @Autowired
    FirstViewController firstViewController;


    @GetMapping("/search-substring")
    public String searchSubstring(@ModelAttribute("searchSubstring") SearchSubstring searchSubstring, Model model) {

        try {
            searchSubstring.getResult();
            if (searchSubstring.getFirstArrayString().isEmpty() || searchSubstring.getSecondArrayString().isEmpty())
                throw new NullPointerException();
        } catch (Exception e) {
            return firstViewController.firstView(model, "Не было найдено ни одной подстроки,\n попробуйте еще раз");
        }

        return "search-substring";
    }

    @RequestMapping("/save-search-substring")
    public String saveProgramForSearchSubstring(@ModelAttribute("searchSubstring") SearchSubstring searchSubstring,
                                                Model model) {

        programService.saveSearchSubstring(
                        searchSubstring.getFirstArrayString(),
                        searchSubstring.getSecondArrayString(),
                        searchSubstring.getResultArrayString());
        model.addAttribute("searchSubstring", searchSubstring);

        return "search-substring";
    }

    @PostMapping("/upload-search-substring")
    public String uploadSearchSubstring(@RequestParam("file") MultipartFile file, Model model) {

        List<String> futureObjectFromFile = new ArrayList<>();
        SearchSubstring searchSubstring = null;

        try (InputStreamReader inputStream = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStream)) {

            reader.lines().forEach(futureObjectFromFile::add);
            searchSubstring = SearchSubstring.createObjectFromFile(futureObjectFromFile);
            searchSubstring.getResult();

        } catch (Exception e) {
            return firstViewController.firstView(model, "Ошибка загрузки/чтения файла");
        }
        model.addAttribute("searchSubstring", searchSubstring);

        return "search-substring";
    }

    @GetMapping("/downland-file-search-substring")
    public void downlandFileSearchSubstring(@ModelAttribute("searchSubstring") SearchSubstring searchSubstring,
                                            HttpServletResponse response) {

        File file = new File("SearchSubstring.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("\t\tSearch substring\n");
            bufferedWriter.write(searchSubstring.getFirstArrayString() + "\n");
            bufferedWriter.write("\t\t//----------//\n");
            bufferedWriter.write(searchSubstring.getSecondArrayString() + "\n");
            bufferedWriter.write("\t//------------/------------//\n");
            bufferedWriter.write(searchSubstring.getResultArrayString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-Disposition", "attachment; filename=SearchSubstring.txt");
        response.setContentType("application/octet-stream");

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int byteRead = -1;
            while ((byteRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.vladislavgoncharov.testtaskforits.controller;

import com.vladislavgoncharov.testtaskforits.main_package.SemiMagicSquare;
import com.vladislavgoncharov.testtaskforits.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ControllerForSemiMagicSquare {

    @Autowired
    ProgramService programService;

    @Autowired
    FirstViewController firstViewController;

    @GetMapping("/semi-magic-square")
    public String semiMagicSquare(@ModelAttribute("semiMagicSquare") SemiMagicSquare semiMagicSquare, Model model) {

        try {
            semiMagicSquare.getResult();
        } catch (Exception e) {
            return firstViewController.firstView(model,
                    "Видимо вы допустили ошибку,\n прочтите инструкцию и попробуйте еще раз");
        }
        model.addAttribute("semiMagicSquare", semiMagicSquare);

        return "semi-magic-square";
    }

    @GetMapping("/save-semi-magic-square")
    public String saveProgramForSemiMagicSquare(@ModelAttribute("semiMagicSquare") SemiMagicSquare semiMagicSquare,
                                                Model model) {

        programService.saveSemiMagicSquare(
                semiMagicSquare.getOriginalSemiMagicSquare(),
                semiMagicSquare.getNewSemiMagicSquare(),
                semiMagicSquare.getLowestCost(),
                semiMagicSquare.getSideSize() + "x" + semiMagicSquare.getSideSize());

        model.addAttribute("semiMagicSquare", semiMagicSquare);

        return "semi-magic-square";
    }

    @PostMapping("/upload-semi-magic-square")
    public String uploadSemiMagicSquare(@RequestParam("file") MultipartFile file, Model model) {

        List<String> futureObjectFromFile = new ArrayList<>();
        SemiMagicSquare semiMagicSquare = null;

        try (InputStreamReader inputStream = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStream)) {

            reader.lines().forEach(futureObjectFromFile::add);
            semiMagicSquare = SemiMagicSquare.createObjectFromFile(futureObjectFromFile);
            semiMagicSquare.getResult();

        } catch (Exception e) {
            return firstViewController.firstView(model, "Ошибка загрузки/чтения файла");
        }
        model.addAttribute("semiMagicSquare", semiMagicSquare);

        return "semi-magic-square";
    }


    @GetMapping("/downland-file-semi-magic-square")
    public void downlandFileSemiMagicSquare(@ModelAttribute("semiMagicSquare") SemiMagicSquare semiMagicSquare,
                                            HttpServletResponse response) {

        File file = new File("SemiMagicSquare.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("\t\tSemi magic square\n");
            bufferedWriter.write(semiMagicSquare.getOriginalSemiMagicSquare() + "\n");
            bufferedWriter.write("\t\t//----------//\n");
            bufferedWriter.write(semiMagicSquare.getNewSemiMagicSquare() + "\n");
            bufferedWriter.write("\t//------------/------------//\n");
            bufferedWriter.write(semiMagicSquare.getLowestCost() + "\n");
            bufferedWriter.write("\t//------------/------------//\n");
            bufferedWriter.write(semiMagicSquare.getSideSize() + "x" + semiMagicSquare.getSideSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-Disposition", "attachment; filename=SemiMagicSquare.txt");
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

    @GetMapping("/load-semi-magic-square")
    public String loadSemiMagicSquare(@RequestParam("entity") SemiMagicSquare semiMagicSquare, Model model) {

        try {
            semiMagicSquare.getResult();
        } catch (Exception e) {
            return firstViewController.firstView(model,
                    "Видимо вы допустили ошибку,\n прочтите инструкцию и попробуйте еще раз");
        }
        model.addAttribute("semiMagicSquare", semiMagicSquare);

        return "semi-magic-square";
    }
}

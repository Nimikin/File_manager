package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Write extends Command{
    public Write(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));
        Scanner sc = new Scanner(System.in);
        if (args.isEmpty()){
            return "Enter valid file name";
        }
        if (!file.exists()){
            return "File does not exist";
        }
        System.out.print("Enter your line: ");
        String fileStringHolder = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(file, fileStringHolder + "\n" + sc.nextLine(), StandardCharsets.UTF_8);
        return "Line was added successfully";
    }
}

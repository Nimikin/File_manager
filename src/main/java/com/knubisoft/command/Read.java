package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Read extends Command{
    public Read(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        if (args.isEmpty()){
            return "Enter valid file name";
        }
        File file = new File(currentDirectory, args.get(0));
        if (!file.exists()){
            return "File does not exist";
        }
        if (file.isDirectory()){
            return "This is not a file";
        }
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}

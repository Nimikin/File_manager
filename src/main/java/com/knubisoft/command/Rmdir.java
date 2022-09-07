package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rmdir extends Command{
    public Rmdir(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));
        if (!file.isDirectory() || !file.exists()){
            return "Directory not found";
        }else {
            FileUtils.deleteDirectory(file);
            return "Directory was deleted";
        }
    }
}

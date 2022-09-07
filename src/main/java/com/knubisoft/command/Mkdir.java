package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Mkdir extends Command{
    public Mkdir(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));
        FileUtils.forceMkdir(file);
        return "Directory was created";
    }
}

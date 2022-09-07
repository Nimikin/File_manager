package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Mkfile extends Command{
    public Mkfile(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));
        if (!args.get(0).contains(".")){
            return "Please, enter files extension";
        }
        FileUtils.touch(file);
        return "File was created";
    }
}

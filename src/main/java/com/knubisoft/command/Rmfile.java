package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rmfile extends Command{
    public Rmfile(Context context) {
        super(context);
    }

    @Override
    @SneakyThrows
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File file = new File(currentDirectory, args.get(0));
        if (!file.exists() || !file.isFile()){
            return "File not found";
        }else {
            FileUtils.delete(file);
            return "File was deleted";
        }
    }
}

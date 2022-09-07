package com.knubisoft.command;

import java.io.File;
import java.util.List;

public class Ls extends Command {

    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        if (allFiles == null) {
            return "There is no files";
        }
        if (args.isEmpty()) {
            System.out.format("%-" + maxNameLength(allFiles) + "s \n", "Name");
        }else {
            System.out.format("%-" + maxNameLength(allFiles) + "s ", "Name");
            System.out.format(args.get(0).contains("s") ? "%-" + maxSizeLength(allFiles) + "s " : "", "Size");
            System.out.format(args.get(0).contains("r") ? "%-6s" : "", "Read");
            System.out.format(args.get(0).contains("w") ? "%-6s" : "", "Write");
            System.out.format(args.get(0).contains("e") ? "%-" + maxExtLength(allFiles) + "s\n" : "\n", "Ext.");
        }
        printArgs(args, allFiles);
        return "";
    }

    private void printArgs(List<String> args, File[] allFiles) {
        String extension = "";
        String canRead = "";
        String canWrite = "";
        for (File each : allFiles) {
            if (each.isFile()) {
                extension = each.getName().contains(".") ? each.getName().substring(each.getName().indexOf(".")) : "no ext.";
                canRead = each.canRead() ? "Yes" : "No";
                canWrite = each.canWrite() ? "Yes" : "No";
            } else if (each.isDirectory()) {
                extension = "folder";
                canRead = "No";
                canWrite = "No";
            }
            if (args.isEmpty()) {
                System.out.format("%-" + maxNameLength(allFiles) + "s \n", each.getName());
            }else {
                System.out.format("%-" + maxNameLength(allFiles) + "s ", each.getName());
                System.out.format(args.get(0).contains("s") ? "%-" + maxSizeLength(allFiles) + "s " : "", each.getUsableSpace());
                System.out.format(args.get(0).contains("r") ? "%-6s" : "", canRead);
                System.out.format(args.get(0).contains("w") ? "%-6s" : "", canWrite);
                System.out.format(args.get(0).contains("e") ? "%-" + maxExtLength(allFiles) + "s\n" : "\n", extension);
            }
        }
    }

    public int maxNameLength(File[] allFiles) {
        int result = 1;
        for (File each : allFiles) {
            int current = each.getName().length();
            if (result < current) {
                result = current;
            }
        }
        return result;
    }

    public int maxSizeLength(File[] allFiles) {
        int result = 1;
        for (File each : allFiles) {
            String s = String.valueOf(each.getUsableSpace());
            int current = s.length();
            if (result < current) {
                result = current;
            }
        }
        return result;
    }

    public int maxExtLength(File[] allFiles) {
        int result = 1;
        for (File each : allFiles) {
            if (each.isFile() && each.getName().contains(".")) {
                int current = each.getName().substring(each.getName().indexOf(".")).length();
                if (result < current) {
                    result = current;
                }
            }
        }
        return result;
    }
}

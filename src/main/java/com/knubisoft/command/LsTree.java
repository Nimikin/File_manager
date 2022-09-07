package com.knubisoft.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class LsTree extends Command {
    public LsTree(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        return printDirectoryTree(file, args);
    }

    public static String printDirectoryTree(File folder, List<String> args) {
        if (!folder.isDirectory()) {
            return "Folder is not a Directory";
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(folder, indent, sb, args, 0);
        return sb.toString();
    }

    @SneakyThrows
    private static void printDirectoryTree(File directory, int indent,
                                           StringBuilder sb, List<String> args, int depth) {
        int userDepth;
        if (!args.isEmpty()){
            userDepth = Integer.parseInt(args.get(0));
        }else {
            userDepth = 1;
        }
        sb.append(getIndentString(indent));
        sb.append("├──");
        sb.append(directory.getName());
        sb.append("/");
        sb.append("\n");
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory() && !args.isEmpty() && ++depth < userDepth) {
                printDirectoryTree(file, indent + 1, sb, args, depth);
            } else {
                printFile(file, indent + 1, sb);
            }
        }
    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("└──");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("|  ".repeat(Math.max(0, indent)));
        return sb.toString();
    }
}

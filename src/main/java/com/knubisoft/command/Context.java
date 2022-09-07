package com.knubisoft.command;

import lombok.*;

import java.io.File;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Context {

    private Map<String, Command> commandMap;
    private File currentDirectory;
}

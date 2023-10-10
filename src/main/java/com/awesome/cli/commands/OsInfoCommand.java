package com.awesome.cli.commands;

import com.awesome.cli.application.usecase.InitOsInfoUseCase;
import com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class OsInfoCommand {
    private final InitOsInfoUseCase initOsInfoUseCase = new InitOsInfoUseCaseImpl();

    @ShellMethod(value = "Print your computer info", key = "os-info")
    public String printOsInfo() {
        return String.join("\n", initOsInfoUseCase.getFormattedFromFileSystem());
    }
}

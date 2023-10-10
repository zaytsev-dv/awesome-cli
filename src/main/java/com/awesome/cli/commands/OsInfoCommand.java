package com.awesome.cli.commands;

import com.awesome.cli.application.usecase.OsInfoUseCase;
import com.awesome.cli.application.usecase.impl.OsInfoUseCaseImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class OsInfoCommand {
    private final OsInfoUseCase osInfoUseCase = new OsInfoUseCaseImpl();

    @ShellMethod(value = "Print your computer info", key = "os-info")
    public String printOsInfo() {
        return String.join("\n", osInfoUseCase.getFormatted());
    }
}

package com.awesome.cli.application.util;


import com.awesome.cli.application.usecase.OsInfoUseCase;
import com.awesome.cli.application.usecase.impl.OsInfoUseCaseImpl;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    private boolean alreadyShowBanner;
    private final ShellHelper shellHelper;

    public CustomPromptProvider(ShellHelper shellHelper) {
        this.shellHelper = shellHelper;
    }

    @Override
    public AttributedString getPrompt() {
        if (!alreadyShowBanner) {
            System.out.println(this.getBanner());
            alreadyShowBanner = true;
        }
        AttributedStyle foreground = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE);
        return new AttributedString("AWESOME-CLI:>", foreground);
    }

    private String getBanner() {
        OsInfoUseCase osInfoUseCase = new OsInfoUseCaseImpl();
        StringBuilder buf = new StringBuilder();
        String copyright = shellHelper.getInfoMessage("BEEP BEEP MOTHERFUCKER \n") +
                "▄▄▄▌▐██▌█ " + shellHelper.getInfoMessage(" I am a console utility created by:" + " zaytsev_dv" + "\n");

        String car = "       ▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌ \n" +
                "   ▄▄██▌█          " + copyright +
                "███████▌█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌ \n" +
                "▀(@)▀▀▀▀▀▀▀(@)(@)▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀(@)▀";

        buf.append(car);
        buf.append("\n");
        buf.append("===================================================================");
        buf.append("\n");
        buf.append(shellHelper.getWarningMessage("if you want to know what i can do just enter the command: \"Help\""));
        buf.append("\n");
        buf.append("===================================================================");
        buf.append("\n");
        buf.append(osInfoUseCase.getFormatted());
        buf.append("\n");
        buf.append("===================================================================");
        buf.append("\n");
        return buf.toString();
    }
}

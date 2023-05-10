package com.app.manager;


import com.app.manager.cache.Cache;
import com.app.manager.util.ShellHelper;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    private boolean alreadyShowBanner;
    private final Cache cache;
    private final ShellHelper shellHelper;

    public CustomPromptProvider(Cache cache, ShellHelper shellHelper) {
        this.cache = cache;
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

    //TODO: порефакторить
    private String getBanner() {
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
        buf.append(shellHelper.getInfoMessage(cache.getFormattedOSInfo()));
        buf.append("\n");
        return buf.toString();
    }
}

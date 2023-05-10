package com.app.manager;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    private boolean alreadyShowBanner;

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
        StringBuffer buf = new StringBuffer();

        String copyright = "BEEP BEEP MOTHERFUCKER \n" +
                "▄▄▄▌▐██▌█   I am a console utility created by:" + " zaytsev_dv" + "\n";

        String car = "       ▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌ \n" +
                "   ▄▄██▌█          " + copyright +
                "███████▌█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌ \n" +
                "▀(@)▀▀▀▀▀▀▀(@)(@)▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀(@)▀";

        buf.append(car);
        buf.append("\n");
        buf.append("===================================================================");
        buf.append("\n");
        buf.append("if you want to know what i can do just enter the command: \"Help\"");
        buf.append("\n");
        buf.append("===================================================================");
        return buf.toString();
    }
}

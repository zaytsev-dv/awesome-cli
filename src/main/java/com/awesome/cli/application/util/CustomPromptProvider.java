package com.awesome.cli.application.util;


import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.BaseFolderInfo;
import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.usecase.InitFolderInfoUseCase;
import com.awesome.cli.application.usecase.InitPropUseCase;
import com.awesome.cli.application.usecase.InitOsInfoUseCase;
import com.awesome.cli.application.usecase.impl.InitFolderInfoUseCaseImpl;
import com.awesome.cli.application.usecase.impl.InitPropUseCaseImpl;
import com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    private boolean alreadyShowBanner = false;
    private final ShellHelper shellHelper;
    private final CacheStore<OsInfo> osInfoCacheStore;
    private final CacheStore<BaseFolderInfo> baseFolderInfoCacheStore;

    public CustomPromptProvider(
            final ShellHelper shellHelper,
            final CacheStore<OsInfo> osInfoCacheStore,
            final CacheStore<BaseFolderInfo> baseFolderInfoCacheStore
    ) {
        this.alreadyShowBanner = false;
        this.shellHelper = shellHelper;
        this.osInfoCacheStore = osInfoCacheStore;
        this.baseFolderInfoCacheStore = baseFolderInfoCacheStore;
    }

    @Override
    public AttributedString getPrompt() {
        if (!alreadyShowBanner) {
            System.out.println(this.getBanner());
            alreadyShowBanner = true;
        }
        AttributedStyle foreground = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE);
        InitPropUseCase initPropUseCase = new InitPropUseCaseImpl(new InitFolderHelper(osInfoCacheStore, baseFolderInfoCacheStore));
        initPropUseCase.init();

        return new AttributedString("AWESOME-CLI:>", foreground);
    }

    private String getBanner() {
        InitOsInfoUseCase initOsInfoUseCase = new InitOsInfoUseCaseImpl();
        InitFolderInfoUseCase initFolderInfoUseCase = new InitFolderInfoUseCaseImpl();
        StringBuilder buf = new StringBuilder();
        String copyright = shellHelper.getInfoMessage("BEEP BEEP MOTHERFUCKER \n") +
                "▄▄▄▌▐██▌█ " + shellHelper.getInfoMessage(" I am a console utility created by:" + " zaytsev_dv" + "\n");

        String car = "       ▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌ \n" +
                "   ▄▄██▌█          " + copyright +
                "███████▌█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌ \n" +
                "▀(@)▀▀▀▀▀▀▀(@)(@)▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀(@)▀";

        buf.append(car);
        buf.append("\n");
        buf.append("======================================================================");
        buf.append("\n");
        buf.append(shellHelper.getWarningMessage("To start using the CLI, enter the command: \"Start\""));
        buf.append("\n");
        buf.append("======================================================================");
        buf.append("\n");
        buf.append(shellHelper.getWarningMessage("if you want to know what commands i have just enter the command: \"Help\""));
        buf.append("\n");
        buf.append("======================================================================");
        buf.append("\n");
        buf.append(initOsInfoUseCase.getFormattedFromFileSystem());
        buf.append("\n");
        buf.append("======================================================================");
        buf.append("\n");
        buf.append(initFolderInfoUseCase.getFormattedFromFileSystem());
        buf.append("\n");
        buf.append("======================================================================");
        buf.append("\n");
        return buf.toString();
    }
}

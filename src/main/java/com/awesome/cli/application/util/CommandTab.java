package com.awesome.cli.application.util;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandTab implements ValueProvider {

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        return List.of(new CompletionProposal(completionContext.currentWordUpToCursor()));
    }
}

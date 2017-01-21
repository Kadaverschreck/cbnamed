package com.gmail.kadaverschreck.cbnamed;


import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.logging.Logger;


/**
 * Created by Kadaverschreck on 21.01.2017.
 */

@Plugin(id="cbnamed", name="CbNamed", version="1.0")
public class cbnamed {

    @Inject
    Game game;

    @Inject
    Logger logger;

    @Listener
    public void onInit(GameInitializationEvent event) {
        CommandSpec namecbCmd = CommandSpec.builder()
                .description(Text.of("Einen Command Block mit eigenem Namen geben"))
                .arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
                        GenericArguments.remainingJoinedStrings(Text.of("cbname")))
                .executor(new cbnamedExecutor())
                .build();
        game.getCommandManager().register(this, namecbCmd, "cbnamed", "namedcb", "cbn", "ncb");
    }
}
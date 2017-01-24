/*
The MIT license (MIT)

Copyright (c) 2017 Kadaverschreck

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

This File is part of the cbnamed Plugin
 */

package com.gmail.kadaverschreck.cbnamed;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

@Plugin(id="cbnamed", name="CbNamed", version="1.1", authors = "Kadaverschreck",
        url = "https://github.com/Kadaverschreck/cbnamed")
public class cbnamed {
    public static void main(String[] args){}

    @Inject
    private Game game;

    @Inject
    private Logger logger;

    @Listener
    public void preInit(GamePreInitializationEvent event){

    }

    @Listener
    public void postInit(GamePostInitializationEvent event) {

    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("cbnamed Plugin has started!");
    }

    @Listener
    public void onServerStop(GameStoppedServerEvent event) {
        logger.info("cbnamed Plugin has stopped!");
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        CommandSpec namecbCmd = CommandSpec.builder()
                .description(Text.of("Gibt dir einen benannten Commandblock in die Hauphand"))
                .permission("cbn.cmd")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("cbname"))))
                //.arguments(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))),
                //        GenericArguments.remainingJoinedStrings(Text.of("cbname")))
                .executor(new cbnamedExecutor())
                .build();
        game.getCommandManager().register(this, namecbCmd, "cbn");
    }

}

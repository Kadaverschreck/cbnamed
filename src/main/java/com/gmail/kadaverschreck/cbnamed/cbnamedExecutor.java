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

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;


public class cbnamedExecutor implements CommandExecutor{

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException{
        Player target;
        // Player target = args.<Player>getOne("player").get();

        String cbname = args.<String>getOne("cbname").get();
        

        ItemStack namedcb = ItemStack.builder()
                .itemType(ItemTypes.COMMAND_BLOCK).build();
        namedcb.offer(Keys.DISPLAY_NAME, Text.of(cbname));
        target = (Player)src;

        //prüft, ob der Spieler ein Item in der Haupthand hält
        if (target.getItemInHand(HandTypes.MAIN_HAND).isPresent())
        {
            //Wenn ja, dann wird dem Spieler eine Fehlermeldung angezeigt.
            target.sendMessage(Text.of(TextColors.RED, "Bitte mache erst deine rechte Hand frei."));
        }
        else
        {
            //Wenn nicht, dann bekommt der Spieler den CommandBlock in die Haupthand gelegt
            target.setItemInHand(HandTypes.MAIN_HAND, namedcb);
            // target.getInventory().query(GridInventory.class).offer(namedcb);

            //Und in der Konsole wird eine Meldung ausgegeben
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.GRAY, TextStyles.ITALIC, "[cbnamed]: Dem Spieler ", target.getName(), " wurde ein CommandBlock mit dem Namen ", cbname, " gegeben."));
        }
        return CommandResult.success();
    }
}
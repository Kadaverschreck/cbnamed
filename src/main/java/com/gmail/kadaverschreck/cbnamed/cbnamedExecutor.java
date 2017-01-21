package com.gmail.kadaverschreck.cbnamed;

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


/**
 * Created by Kadaverschreck on 21.01.2017.
 */
public class cbnamedExecutor implements CommandExecutor{

 /*   public ItemStack namedcb() {
        String cbname = args.<String>getOne("cbname").get();
        ItemStack namedcb = ItemStack.builder()
                .itemType(ItemTypes.COMMAND_BLOCK).build();
        namedcb.offer(Keys.DISPLAY_NAME, Text.of());
        return namedcb;
    }
*/


    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException{
        Player target = args.<Player>getOne("player").get();
        String cbname = args.<String>getOne("cbname").get();
        ItemStack namedcb = ItemStack.builder()
                .itemType(ItemTypes.COMMAND_BLOCK).build();
        namedcb.offer(Keys.DISPLAY_NAME, Text.of( cbname ));
        target.setItemInHand(HandTypes.OFF_HAND, namedcb);
        return CommandResult.success();
    }
}

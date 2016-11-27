package io.github.codeblocteam.essentials.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

public class NightCommand implements CommandExecutor {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of(TextColors.RED, "Commande utilisable par un joueur uniquement"));
			return CommandResult.success();
		}
		World world = ((Player) src).getWorld();
		world.getProperties().setWorldTime(14000);
		src.sendMessage(Text.of(TextColors.GREEN, "Heure du monde réglée à 20h"));
		return CommandResult.success();
	}
}

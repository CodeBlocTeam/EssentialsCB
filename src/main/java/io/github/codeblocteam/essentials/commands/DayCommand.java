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

public class DayCommand implements CommandExecutor {
	@Override
	public CommandResult execute(CommandSource source, CommandContext args) throws CommandException {
		if (!(source instanceof Player)) {
			source.sendMessage(Text.of(TextColors.RED, "Commande utilisable par un joueur uniquement"));
			return CommandResult.success();
		}
		World world = ((Player) source).getWorld();
		world.getProperties().setWorldTime(1);
		source.sendMessage(Text.of(TextColors.GREEN, "Heure du monde réglée à 6h"));
		return CommandResult.success();
	}
}

package io.github.codeblocteam.essentials.commands;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.weather.Weathers;

public class StormCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Optional<World> target = args.<World>getOne("world");
		World world;
		if (! target.isPresent()) {
			if (!(src instanceof Player)) {
				throw new CommandException(Text.of(TextColors.RED, "Veuillez préciser le monde."));
			}
			world = ((Player) src).getWorld();
		} else {
			world = target.get();
		}
		world.setWeather(Weathers.THUNDER_STORM);
		src.sendMessage(Text.of(TextColors.GREEN, "Orage programmé dans ", TextStyles.ITALIC, world.getName()));
		return CommandResult.success();
	}

}

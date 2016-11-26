package io.github.codeblocteam.essentials.commands;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.entity.GameModeData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class GmCommand implements CommandExecutor {
	@Override
	public CommandResult execute(CommandSource source, CommandContext args) throws CommandException {
		int mode = (int) args.<Integer>getOne("mode").get();
		Optional<Player> target = args.<Player>getOne("player");
		Player player;
		if (! target.isPresent()) {
			if (! (source instanceof Player)) {
				source.sendMessage(Text.of(TextColors.RED, "Tentative de changement de mode de jeu d'un utilisateur ou d'une entité non joueur..."));
				return CommandResult.success();
			}
			player = (Player) source;
		} else {
			player = target.get();
		}
		GameModeData data;
		switch (mode) {
		case 0:
			data = player.getGameModeData().set(Keys.GAME_MODE, GameModes.SURVIVAL);
			player.offer(data);
			source.sendMessage(Text.of(TextColors.GREEN, "Mode de jeu de " + player.getName() + " changé en ", TextStyles.ITALIC, "SURVIE"));
			break;
		case 1:
			data = player.getGameModeData().set(Keys.GAME_MODE, GameModes.CREATIVE);
			player.offer(data);
			source.sendMessage(Text.of(TextColors.GREEN, "Mode de jeu de " + player.getName() + " changé en ", TextStyles.ITALIC, "CREATIF"));
			break;
		case 2:
			data = player.getGameModeData().set(Keys.GAME_MODE, GameModes.ADVENTURE);
			player.offer(data);
			source.sendMessage(Text.of(TextColors.GREEN, "Mode de jeu de " + player.getName() + " changé en ", TextStyles.ITALIC, "AVENTURE"));
			break;
		case 3:
			data = player.getGameModeData().set(Keys.GAME_MODE, GameModes.SPECTATOR);
			player.offer(data);
			source.sendMessage(Text.of(TextColors.GREEN, "Mode de jeu de " + player.getName() + " changé en ", TextStyles.ITALIC, "SPECTATEUR"));
			break;
		default:
			source.sendMessage(Text.of(TextColors.RED, "Mode de jeu incorrect. Entrer 0, 1, 2, ou 3 pour survie, créatif, aventure, et spectateur respectivement"));
			return CommandResult.success();
		}
		return CommandResult.success();
	}
}

package io.github.codeblocteam.essentials;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import io.github.codeblocteam.essentials.commands.DayCommand;
import io.github.codeblocteam.essentials.commands.GmCommand;
import io.github.codeblocteam.essentials.commands.NightCommand;
import io.github.codeblocteam.essentials.commands.RainCommand;
import io.github.codeblocteam.essentials.commands.StormCommand;
import io.github.codeblocteam.essentials.commands.SunCommand;

@Plugin(id = "essentialscb", name = "Essentials CB", version = "0.1")
public class EssentialsCB {
	
	private CommandSpec dayCmd = CommandSpec.builder()
			.description(Text.of("Règle l'heure à 6h"))
			.permission("essentialscb.command.day")
			.executor(new DayCommand())
			.build();
	
	private CommandSpec nightCmd = CommandSpec.builder()
			.description(Text.of("Règle l'heure à 18h"))
			.permission("essentialscb.command.night")
			.executor(new NightCommand())
			.build();
	
	private CommandSpec gmCmd = CommandSpec.builder()
			.description(Text.of("Change le mode de jeu"))
			.permission("essentialscb.command.gm")
			.arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("mode"))), GenericArguments.optional(GenericArguments.player(Text.of("player"))))
			.executor(new GmCommand())
			.build();
	
	private CommandSpec sunCmd = CommandSpec.builder()
			.description(Text.of("Programme un temps ensoleillé"))
			.permission("essentialscb.command.weather.sun")
			.arguments(GenericArguments.optional(GenericArguments.world(Text.of("world"))))
			.executor(new SunCommand())
			.build();
	
	private CommandSpec rainCmd = CommandSpec.builder()
			.description(Text.of("Fait pleuvoir sur le monde"))
			.permission("essentialscb.command.weather.rain")
			.arguments(GenericArguments.optional(GenericArguments.world(Text.of("world"))))
			.executor(new RainCommand())
			.build();
	
	private CommandSpec stormCmd = CommandSpec.builder()
			.description(Text.of("Programme l'orage dans le monde"))
			.permission("essentialscb.command.weather.storm")
			.arguments(GenericArguments.optional(GenericArguments.world(Text.of("world"))))
			.executor(new StormCommand())
			.build();
	
	private CommandManager cmdManager = Sponge.getCommandManager();
	
	@Listener
	public void onServerStart(GameInitializationEvent initialization){
		cmdManager.register(this, dayCmd, "day", "jour");
		cmdManager.register(this, nightCmd, "night", "nuit");
		cmdManager.register(this, gmCmd, "gm", "mode");
		cmdManager.register(this, sunCmd, "sun", "soleil");
		cmdManager.register(this, rainCmd, "rain", "pluie");
		cmdManager.register(this, stormCmd, "storm", "tempete");
}
}

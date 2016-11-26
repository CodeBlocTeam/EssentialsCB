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

@Plugin(id = "essentialscb", name = "Essentials CB", version = "0.1")
public class EssentialsCB {
	private CommandSpec dayCommandSpec = CommandSpec.builder()
			.description(Text.of("Règle l'heure à 6h"))
			.permission("essentialscb.command.day")
			.executor(new DayCommand())
			.build();
	
	private CommandSpec nightCommandSpec = CommandSpec.builder()
			.description(Text.of("Règle l'heure à 18h"))
			.permission("essentialscb.command.night")
			.executor(new NightCommand())
			.build();
	
	private CommandSpec gmCommandSpec = CommandSpec.builder()
			.description(Text.of("Change le mode de jeu"))
			.permission("essentialscb.command.gm")
			.arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("mode"))), GenericArguments.optional(GenericArguments.player(Text.of("player"))))
			.executor(new GmCommand())
			.build();
	
	private CommandManager cmdManager = Sponge.getCommandManager();
	
	@Listener
	public void onServerStart(GameInitializationEvent initialization){
		cmdManager.register(this, dayCommandSpec, "day", "jour");
		cmdManager.register(this, nightCommandSpec, "night", "nuit");
		cmdManager.register(this, gmCommandSpec, "gm", "mode");
}
}

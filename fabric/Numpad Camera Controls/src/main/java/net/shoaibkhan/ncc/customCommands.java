package net.shoaibkhan.ncc;

import com.mojang.brigadier.CommandDispatcher;

import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.shoaibkhan.ncc.config.Config;

public class customCommands implements ClientCommandPlugin {
	MinecraftClient client;
	
	@Override
	public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
		dispatcher.register(ArgumentBuilders.literal("ncc").then(ArgumentBuilders.literal("narrator")
			.then(ArgumentBuilders.literal("off").executes(
	            source -> {
	            	Config.set(Config.getNarratorkey(), false);
	            	source.getSource().sendFeedback(new LiteralText("Narrator off"), true);
	                return 1;
	            }))
			.then(ArgumentBuilders.literal("on").executes(
	            source -> {
	            	Config.set(Config.getNarratorkey(), true);
	            	source.getSource().sendFeedback(new LiteralText("Narrator on"), true);
	                return 1;
	            }))
		));
	}

}

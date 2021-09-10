package net.shoaibkhan.ncc;

import com.mojang.brigadier.CommandDispatcher;

import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.text.LiteralText;
import net.shoaibkhan.ncc.config.Config;

public class customCommands implements ClientCommandPlugin {

	@Override
	public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
		dispatcher.register(ArgumentBuilders.literal("ncc").then(ArgumentBuilders.literal("narrator")
			.then(ArgumentBuilders.literal("off").executes(
	            source -> {
	            	Config.set(Config.getNarratorkey(), false);
	            	NarratorManager.INSTANCE.narrate("Narrator off");
	                return 1;
	            }))
			.then(ArgumentBuilders.literal("on").executes(
	            source -> {
	            	Config.set(Config.getNarratorkey(), true);
					NarratorManager.INSTANCE.narrate("Narrator off");
	                return 1;
	            }))
		));

		dispatcher.register(ArgumentBuilders.literal("ba").executes(
			source -> {
				new BridgingAngle();
				return 1;
		}));
	}

}

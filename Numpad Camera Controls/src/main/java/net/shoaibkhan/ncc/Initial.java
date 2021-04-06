package net.shoaibkhan.ncc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.java.games.input.Component;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class Initial implements ClientModInitializer {
	public static KeyBinding num_0,num_1,num_2,num_3,num_4,num_5,num_6,num_7,num_8,num_9;
	private HudRenderCallbackClass hudRenderCallbackClass;
	@Override
	public void onInitializeClient() {
		num_0 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_0",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.0").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));

		num_1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_1",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.1").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_2",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.2").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_3",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.3").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_4 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_4",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.4").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_5 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_5",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.5").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_6 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_6",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.6").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_7 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_7",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.7").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_8 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_8",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.8").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		num_9 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_9",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.9").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));

		hudRenderCallbackClass = new HudRenderCallbackClass();
	}
}

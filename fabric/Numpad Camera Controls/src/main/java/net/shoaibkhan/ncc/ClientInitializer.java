package net.shoaibkhan.ncc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer {
	public static KeyBinding lookSouth
							,rotateDownwards
							,lookWest
							,rotateLeft
							,centreAxis
							,rotateRight
							,lookNorth
							,rotateUp
							,lookEast;
	@SuppressWarnings("unused")
	private HudRenderCallbackClass hudRenderCallbackClass;
	@Override
	public void onInitializeClient() {

		lookSouth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_1",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.1").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		rotateDownwards = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_2",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.2").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		lookWest = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_3",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.3").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		rotateLeft = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_4",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.4").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		centreAxis = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_5",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.5").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		rotateRight = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_6",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.6").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		lookNorth = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_7",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.7").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		rotateUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_8",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.8").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));
		lookEast = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nccbyshoaibkhan.num_9",
				InputUtil.Type.KEYSYM,
				InputUtil.fromTranslationKey("key.keyboard.keypad.9").getCode(),
				"category.nccbyshoaibkhan.numpad"
		));

		hudRenderCallbackClass = new HudRenderCallbackClass();
	}
}

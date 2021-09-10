package net.shoaibkhan.ncc;

import net.fabricmc.api.ModInitializer;
import net.shoaibkhan.ncc.config.Config;

public class MainModInitializer implements ModInitializer {
  @Override
  public void onInitialize() {
    Config.loadConfig();
  }

}

package me.srgantmoomoo.postman.client.module.modules.movement;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.client.Main;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import me.srgantmoomoo.postman.client.setting.settings.BooleanSetting;
import me.srgantmoomoo.postman.client.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.InputUpdateEvent;

	public class NoSlow extends Module {
	public BooleanSetting food = new BooleanSetting("food", this, true);
	public BooleanSetting web = new BooleanSetting("web", this, true);
	public BooleanSetting soulSand = new BooleanSetting("soulSand", this, true);
	public BooleanSetting slimeBlock = new BooleanSetting("slimeBlock", this, true);
	
	public NoSlow() {
		super ("noSlow", "slow? no.", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(food,web,soulSand,slimeBlock);
	}
	
	public void onEnable() {
		super.onEnable();
		Main.EVENT_BUS.subscribe(this);
		Blocks.DIRT.setLightOpacity(10);
	}
	
	public void onDisable() {
		super.onDisable();
		Main.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
	private final Listener<InputUpdateEvent> eventListener = new Listener<>(event -> {
			if (mc.player.isHandActive() && !mc.player.isRiding() && food.isEnabled()) {
				event.getMovementInput().moveStrafe *= 5;
				event.getMovementInput().moveForward *= 5;
		}
	});
}
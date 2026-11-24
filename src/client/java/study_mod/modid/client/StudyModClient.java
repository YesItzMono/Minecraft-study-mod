package study_mod.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

import net.minecraft.client.Minecraft;
import study_mod.modid.StudyMod;

public class StudyModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudElementRegistry.addLast(StudyMod.id("study_hud"), (graphics, tickCounter) -> {


			String text = "Study time:" + StudyMod.studyMinutes + "mins";

			graphics.text(Minecraft.getInstance().font, text, 20, 20, 0xFFFFFFFF, true);

		});
	}
}

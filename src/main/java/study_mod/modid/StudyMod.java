package study_mod.modid;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudyMod implements ModInitializer {
	public static final String MOD_ID = "study-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static int studyMinutes = 0;

	@Override


	public void onInitialize() {
		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
			if (entity instanceof ServerPlayer player  && damageTaken > 0) {
				studyMinutes += 10;
				LOGGER.info(studyMinutes + "mins");

				player.connection.send(new ClientboundSetTitlesAnimationPacket(0,15,5));
				player.connection.send(new ClientboundSetTitleTextPacket(Component.literal("§c§l + 10 Mins!")));
				player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
						SoundEvents.ANVIL_LAND, SoundSource.MASTER, 1.0f, 1.0f);

			}


		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}


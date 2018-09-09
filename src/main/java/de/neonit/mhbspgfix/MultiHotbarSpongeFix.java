package de.neonit.mhbspgfix;

import com.google.inject.Inject;
import net.minecraft.entity.player.InventoryPlayer;
import org.slf4j.Logger;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

@Plugin(
        id = "mhbspgfix",
        name = "MultiHotbarSpongeFix",
        description = "Fix rolandoislas MultiHotbar mod for use with Sponge servers.",
        authors = {
                "Neonit"
        }
)
public class MultiHotbarSpongeFix {

    @Inject
    private Logger logger;


    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        // Try loading some configuration settings for a welcome message to players
        // when they join!
        this.logger.info("hotbar size: " + InventoryPlayer.getHotbarSize());
    }


    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Getter("getTargetEntity") Player player) {
        // The text message could be configurable, check the docs on how to do so!
        player.sendMessage(Text.of(TextColors.AQUA, TextStyles.BOLD, "Hi " + player.getName()));
    }

}

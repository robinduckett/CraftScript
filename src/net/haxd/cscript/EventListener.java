package net.haxd.cscript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.mozilla.javascript.Context;

/**
 * @author Robin Duckett &lt;rduckett@specificmedia.com&gt;
 */
public class EventListener implements Listener {
    CraftScript plugin;

    public EventListener(CraftScript plugin) {
        this.plugin = plugin;
    }

    private void processEvent(String sevent, PlayerEvent event) {
        Object jsEvent = Context.javaToJS(event, plugin.scope);
        Object player = Context.javaToJS(event.getPlayer(), plugin.scope);
        Object args[] = { jsEvent, player };

        plugin.scriptLoader.callFunction("on" + sevent, args);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerLogin(PlayerLoginEvent event) {
        this.processEvent("PlayerLogin", event);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        this.processEvent("PlayerCommand", event);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.processEvent("PlayerJoin", event);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.processEvent("PlayerQuit", event);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerCommand(PlayerRespawnEvent event) {
        this.processEvent("PlayerRespawn", event);
    }
}

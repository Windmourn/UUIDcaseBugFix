package io.github.windmourn.lcuuid.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by Administrator on 2015/12/28.
 */
public class Main extends JavaPlugin implements Listener {

    Field uuid;

    public void onEnable() {
        if (!getServer().getOnlineMode()) {
            getServer().getPluginManager().registerEvents(this, this);
            try {
                uuid = AsyncPlayerPreLoginEvent.class.getDeclaredField("uniqueId");
                uuid.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        String playername = event.getName();
        if (!playername.toLowerCase().equals(playername)) {
            try {
                UUID newUUID = UUID.nameUUIDFromBytes(("OfflinePlayer:" + playername.toLowerCase()).getBytes(Charset.forName("UTF-8")));
                uuid.set(event, newUUID);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}

package io.github.windmourn.lcuuid.bungee;

import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by Administrator on 2015/12/28.
 */
public class Main extends Plugin implements Listener {

    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        if (!event.getConnection().isOnlineMode()) {
            String playername = event.getConnection().getName();
            if (!playername.toLowerCase().equals(playername)) {
                event.getConnection().setUniqueId(UUID.nameUUIDFromBytes(("OfflinePlayer:" + playername.toLowerCase()).getBytes(Charset.forName("UTF-8"))));
            }
        }
    }

}

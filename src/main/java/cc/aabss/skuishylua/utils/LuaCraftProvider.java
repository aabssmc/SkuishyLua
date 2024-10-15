package cc.aabss.skuishylua.utils;

import com.shawnjb.luacraft.LuaCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

import java.lang.reflect.Field;

public class LuaCraftProvider {

    public static Globals globals;
    public static Class<?> luaCraft;
    public static LuaCraft plugin;

    public static LuaValue luaCraftLibrary;

    static {
        plugin = (LuaCraft) Bukkit.getPluginManager().getPlugin("LuaCraft");
        try {
            luaCraft = LuaCraft.class;
            Field globals = luaCraft.getDeclaredField("globals");
            globals.setAccessible(true);
            LuaCraftProvider.globals = (Globals) globals.get(plugin);
            luaCraftLibrary = LuaCraftProvider.globals.get("LuaCraft");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandSender getLastSender() {
        return plugin.getLastSender();
    }
}

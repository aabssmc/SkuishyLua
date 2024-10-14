package cc.aabss.skuishylua;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.java.JavaPlugin;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import java.lang.reflect.Field;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public final class SkuishyLua extends JavaPlugin {

    private Globals globals;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("LuaCraft") == null) {
            Bukkit.getConsoleSender().sendMessage("Please download LuaCraft to use this.");
            setEnabled(false);
        } else {
            try {
                Class<?> luaCraft = Class.forName("com.shawnjb.luacraft.LuaCraft");
                Field globals = luaCraft.getDeclaredField("globals");
                globals.setAccessible(true);
                this.globals = (Globals) globals.get(Bukkit.getPluginManager().getPlugin("LuaCraft"));
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            loadLuaValues();
        }
    }

    public void loadLuaValues() {
        LuaValue table = LuaValue.tableOf();

        table.set("test", new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.getWorld().spawn(p.getLocation(), Wolf.class, (wolf) -> {
                        wolf.customName(miniMessage().deserialize("<gradient:ff0000:0000ff>whats up guys im a wolf"));
                        wolf.setCollarColor(DyeColor.GREEN);
                        wolf.setVariant(Wolf.Variant.CHESTNUT);
                    });
                }
                return LuaValue.NIL;
            }
        });

        globals.set("Skuishy", table);
    }
}

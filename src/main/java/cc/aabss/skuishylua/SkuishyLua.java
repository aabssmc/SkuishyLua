package cc.aabss.skuishylua;

import cc.aabss.skuishylua.elements.SetSkin;
import org.bukkit.plugin.java.JavaPlugin;
import org.luaj.vm2.LuaValue;

import static cc.aabss.skuishylua.utils.LuaCraftProvider.globals;

public final class SkuishyLua extends JavaPlugin {

    @Override
    public void onEnable() {
        loadLuaValues();
    }

    public void loadLuaValues() {
        LuaValue table = LuaValue.tableOf();
        table.set("setSkin", new SetSkin());
        globals.set("Skuishy", table);
    }
}

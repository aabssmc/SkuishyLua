package cc.aabss.skuishylua.elements;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import static cc.aabss.skuishylua.utils.LuaCraftProvider.getLastSender;

public class SetSkin extends VarArgFunction {

    @Override
    public Varargs invoke(Varargs varargs) {
        Player player = Bukkit.getPlayer(varargs.checkjstring(2) == null ? "hi" : varargs.checkjstring(2));
        if (player == null) {
            player = getLastSender() instanceof Player ? (Player) getLastSender() : null;
            if (player == null) {
                getLastSender().sendMessage("No player applicable.");
                return LuaValue.NIL;
            }
        }
        String skin =  varargs.checkjstring(1);
        if (player.getName().equals(skin)) {
            PlayerProfile e = player.getPlayerProfile();
            e.getTextures().clear();
            PlayerTextures t = e.getTextures();
            t.clear();
            e.setTextures(t);
            player.setPlayerProfile(e);
        }
        PlayerProfile newprofile = Bukkit.createProfile(skin);
        newprofile.setTextures(newprofile.getTextures());
        newprofile.complete();
        PlayerProfile profile = player.getPlayerProfile();
        profile.setProperties(newprofile.getProperties());
        player.setPlayerProfile(profile);
        return LuaValue.NIL;
    }
}

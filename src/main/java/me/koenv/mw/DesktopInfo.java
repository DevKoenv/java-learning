package me.koenv.mw;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesktopInfo {

    private static DesktopInfo desktopInfo;

    private String os_name = System.getProperty("os.name");
    private String os_version = System.getProperty("os.version");
    private String os_arch = System.getProperty("os.arch");
    private String user_name = System.getProperty("user.name");
    private String pc_name;
    private File users_file;
    private List<String> users_dir;

    public DesktopInfo(){
        run();
    }

    public void run() {
        desktopInfo = this;
        try {
            pc_name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        users_file = new File("C:\\Users");
        if (users_file.list() != null) {
            users_dir = new ArrayList<>(Arrays.asList(users_file.list()));
        }
        users_dir.removeIf(name -> !new File("C:\\Users\\" + name).isDirectory());
        try {
            users_dir.remove("Default");
            users_dir.remove("All Users");
            users_dir.remove("Default User");
            users_dir.remove("Public");
        } catch (Exception ignored) {}
    }

    public File getUsers_file() {
        return users_file;
    }

    public List<String> getUsers_dir() {
        return users_dir;
    }

    public String getOs_arch() {
        return os_arch;
    }

    public String getOs_name() {
        return os_name;
    }

    public String getOs_version() {
        return os_version;
    }

    public String getPc_name() {
        return pc_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setOs_arch(String os_arch) {
        this.os_arch = os_arch;
    }

    public void setOs_name(String os_name) {
        this.os_name = os_name;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public void setPc_name(String pc_name) {
        this.pc_name = pc_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUsers_dir(List<String> users_dir) {
        this.users_dir = users_dir;
    }

    public void setUsers_file(File users_file) {
        this.users_file = users_file;
    }
}

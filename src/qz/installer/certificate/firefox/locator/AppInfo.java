package qz.installer.certificate.firefox.locator;

import com.github.zafarkhaja.semver.Version;

import java.nio.file.Path;

/**
 * Container class for installed app information
 */
public class AppInfo {
    String name;
    Path path;
    Path exePath;
    Version version;
    boolean isBlacklisted = false;

    public AppInfo() {}

    public AppInfo(String name, Path exePath, String version) {
        this.name = name;
        this.path = exePath.getParent();
        this.exePath = exePath;
        this.version = parseVersion(version);
    }

    public AppInfo(String name, Path exePath) {
        this.name = name;
        this.path = exePath.getParent();
        this.exePath = exePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getExePath() {
        return exePath;
    }

    public void setExePath(Path exePath) {
        this.exePath = exePath;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = parseVersion(version);
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public boolean isBlacklisted() {
        return isBlacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        isBlacklisted = blacklisted;
    }

    private static Version parseVersion(String version) {
        try {
            // Ensure < 3 octets (e.g. "56.0") doesn't failing
            while(version.split("\\.").length < 3) {
                version = version + ".0";
            }
            if (version != null) {
                return Version.valueOf(version);
            }
        } catch(Exception ignore) {}
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof AppLocator && o != null && path != null) {
            return path.equals(((AppInfo)o).getPath());
        }
        return false;
    }
}

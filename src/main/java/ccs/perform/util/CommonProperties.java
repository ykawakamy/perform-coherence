package ccs.perform.util;

import java.io.IOException;
import java.util.Properties;

public class CommonProperties {
    static Properties prop  = new Properties();
    static {
        try {
            prop.load(CommonProperties.class.getClassLoader().getResourceAsStream("common.properties"));
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    static public String get(String key) {
        return get(key, null);
    }

    static public String get(String key, String def) {
        return prop.getProperty(key, def);
    }

}

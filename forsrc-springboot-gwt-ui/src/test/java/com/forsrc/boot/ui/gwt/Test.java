package com.forsrc.boot.ui.gwt;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("Test");
        String fileName = "C:\\game\\Command Conquer 4 Tiberian Twilight";
        File file = new File(fileName);

        final Map<String, String> map = new HashMap<>();
        map.put("YK000", "exe");
        map.put("YK001", "dll");
        map.put("YK002", "ini");
        map.put("YK003", "ocx");
        map.put("YK004", "dat");
        map.put("YK005", "lst");
        map.put("YK006", "xml");
        map.put("YK007", "swf");
        map.put("YK008", "log");
        map.put("YK009", "png");
        map.put("YK010", "bmp");
        map.put("YK011", "jpg");
        map.put("YK012", "htm");
        map.put("YK013", "html");
        map.put("YK014", "txt");
        map.put("YK015", "bat");
        map.put("YK016", "ico");
        map.put("YK017", "zip");
        map.put("YK018", "gbc");

        list(file, new Handler() {
            @Override
            public void handle(File file) {
                //System.out.println(file);
                String name = file.getName();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (name.endsWith(key)) {
                        System.out.println(file);
                        file.renameTo(new File(file.getAbsolutePath().replace(key, value)));
                    }
                }
            }
        });
    }

    public static void list(File file, Handler handler) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                list(f, handler);
            }
        } else {
            handler.handle(file);
        }
    }


    public static interface Handler {
        void handle(File file);
    }
}

package de.achimonline.kickassembler.acbg.properties;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.PropertyKey;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

public class KickAssemblerProperties {
    private static final String BUNDLE = "properties.kickassembler";

    private static Reference<ResourceBundle> resourceBundle;

    public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return CommonBundle.message(getBundle(), key, params);
    }

    private static ResourceBundle getBundle() {
        ResourceBundle bundle = null;

        if (resourceBundle != null) {
            bundle = resourceBundle.get();
        }

        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE);
            resourceBundle = new SoftReference<>(bundle);
        }

        return bundle;
    }
}

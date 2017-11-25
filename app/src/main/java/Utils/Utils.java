package Utils;

import android.support.annotation.NonNull;

/**
 * Created by Gautam on 25-11-2017.
 */

public class Utils {
    public static String capitalizeFirstLetter(@NonNull String customText) {
        int count = customText.length();
        if (count == 0) {
            return customText;
        }
        if (count == 1) {
            return customText.toUpperCase();
        }
        return customText.substring(0, 1).toUpperCase() + customText.substring(1).toLowerCase();
    }

}

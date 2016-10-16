package util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Contains the com.thoughtworks.android.tictactoe.main.common utilities needed for App.
 */
public class AppUtils {
    public static void displayLongSnackBar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void displayLongSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static Snackbar displayIndefiniteSnackBar(View view, String msg) {
        return Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
    }
}

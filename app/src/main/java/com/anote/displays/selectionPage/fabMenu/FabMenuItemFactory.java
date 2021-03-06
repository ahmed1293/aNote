package com.anote.displays.selectionPage.fabMenu;

import android.app.Activity;
import android.support.annotation.NonNull;

public class FabMenuItemFactory {

    public FabMenuItemFactory() {}

    public FabMenuItem getFab(@NonNull String fabType, Activity activity) {
        if (fabType == null) {
            throw new IllegalArgumentException("Null fabType entered.");
        }

        if (fabType.equals(FabMenuItem.PIN)) {
            return new PinFab(activity);
        }
        else if (fabType.equals(FabMenuItem.OTHER_NOTE)) {
            return new NoteFab(activity);
        }
        else {
            throw new IllegalArgumentException("Invalid fabType entered.");
        }
    }
}

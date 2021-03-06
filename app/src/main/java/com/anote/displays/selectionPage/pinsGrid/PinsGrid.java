package com.anote.displays.selectionPage.pinsGrid;

import android.content.Intent;
import android.widget.GridView;

import com.anote.R;
import com.anote.auth.BiometricAuth;
import com.anote.displays.pinDisplay.PinDisplayActivity;
import com.anote.displays.selectionPage.NoteSelectionActivity;

import net.sqlcipher.Cursor;

public class PinsGrid {

    private GridView gridview;

    public PinsGrid(NoteSelectionActivity activity, Cursor cursor) {
        this.gridview = activity.findViewById(R.id.pins_grid);

        Intent intent = new Intent(activity, PinDisplayActivity.class);
        BiometricAuth biometricAuth = new BiometricAuth(activity, intent);

        new ClickableTiles(activity, cursor, intent, biometricAuth);

        PinsAdapter pinsAdapter = new PinsAdapter(activity, cursor, 0);
        gridview.setAdapter(pinsAdapter);
    }
}

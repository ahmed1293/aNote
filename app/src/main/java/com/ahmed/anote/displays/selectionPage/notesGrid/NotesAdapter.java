package com.ahmed.anote.displays.selectionPage.notesGrid;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.ahmed.anote.R;
import com.ahmed.anote.db.Contract;

public class NotesAdapter extends CursorAdapter {

    public final static int TILE_SIZE = 400;

    public NotesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_notes_grid_tile, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Contract.Notes.COLUMN_TITLE));

        view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, TILE_SIZE));

        TextView main = view.findViewById(R.id.note_tile_main_text);
        main.setTextSize(40);
        main.setTextColor(ContextCompat.getColor(context, R.color.lightOrange));
        main.setText(title);
    }
}

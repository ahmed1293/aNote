package com.anote.common.dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.anote.common.activities.DbRecordDeleter;
import com.anote.db.sql.SqlQueries;
import com.anote.displays.selectionPage.NoteSelectionActivity;

public class DeleteAlertDialog {

    private static final String ALERT_MESSAGE = "Are you sure?";
    private static final String ALERT_POSITIVE = "YES";
    private static final String ALERT_NEGATIVE = "NO";

    private AlertDialog alertDialog;
    private DbRecordDeleter activity;
    private SqlQueries sql;

    public DeleteAlertDialog(DbRecordDeleter activity, SqlQueries sql, String title) {
        this.sql = sql;
        this.activity = activity;
        this.alertDialog = new AlertDialog.Builder(activity).setTitle(title)
                .setMessage(ALERT_MESSAGE)
                .setPositiveButton(ALERT_POSITIVE,
                        (dialog, which) -> positiveAction(dialog))
                .setNegativeButton(ALERT_NEGATIVE,
                        (dialog, which) -> dialog.dismiss())
                .create();
    }

    public void show() {
        alertDialog.show();
    }

    private void positiveAction(DialogInterface dialog) {
        sql.DELETE(activity.getPK());
        dialog.dismiss();
        Intent intent = new Intent(activity, NoteSelectionActivity.class);
        activity.startActivity(intent);
    }

}

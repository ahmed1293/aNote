package com.anote.displays.selectionPage.notesGrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.anote.R;
import com.anote.auth.BiometricAuth;
import com.anote.db.Contract;
import com.anote.displays.selectionPage.NoteSelectionActivity;

import net.sqlcipher.Cursor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ClickableTilesTest {

    @ParameterizedTest
    @CsvSource({"0, 1, 0", "1, 0, 1"})
    public void authenticationOnClickForLockedNotesOnly(
            int securityLevel, int noOfStartActivityInvocations, int noOfAuthInvocations
    ) {
        NoteSelectionActivity activityMock = mock(NoteSelectionActivity.class);
        doReturn(mock(GridView.class)).when(activityMock).findViewById(eq(R.id.notes_grid));

        Intent intentMock = mock(Intent.class);

        Cursor cursorMock = mock(Cursor.class);
        doReturn(4).when(cursorMock).getColumnIndex(eq(Contract.Notes.COLUMN_SECURITY_LEVEL));
        doReturn(securityLevel).when(cursorMock).getInt(eq(4));

        BiometricAuth biometricAuthMock = mock(BiometricAuth.class);

        ClickableTiles clickableTiles =
                new ClickableTiles(activityMock, cursorMock, intentMock, biometricAuthMock);
        clickableTiles.onItemClick(mock(AdapterView.class), createViewMock(), 1, 1);

        verify(intentMock, times(1)).putExtras(any(Bundle.class));
        verify(activityMock, times(noOfStartActivityInvocations)).startActivity(intentMock);
        verify(biometricAuthMock, times(noOfAuthInvocations)).authenticateUser();
    }

    private View createViewMock() {
        View viewMock = mock(View.class);
        TextView textViewMock = mock(TextView.class);
        doReturn("TITLE").when(textViewMock).getText();
        doReturn(textViewMock).when(viewMock).findViewById(eq(R.id.note_tile_main_text));

        return viewMock;
    }
}

package com.anote.displays.selectionPage.fabMenu;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;

import com.anote.R;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class FabMenuItemFactoryTest {

    private static Activity activityMock = mock(Activity.class);
    private FabMenuItemFactory factory = new FabMenuItemFactory();

    @BeforeAll
    public static void setUp() {
        FloatingActionButton buttonMock = mock(FloatingActionButton.class);

        doReturn(buttonMock).when(activityMock).findViewById(eq(R.id.pin_fab));
        doReturn(buttonMock).when(activityMock).findViewById(eq(R.id.note_fab));
    }

    @Test
    public void pinFabCreatedSuccessfully() {
        assertThat(factory.getFab(FabMenuItem.PIN, activityMock)).isInstanceOf(PinFab.class);
    }

    @Test
    public void otherNoteFabCreatedSuccessfully() {
        assertThat(factory.getFab(FabMenuItem.OTHER_NOTE, activityMock)).isInstanceOf(NoteFab.class);
    }

    @Test
    public void illegalArgumentExceptionIfInvalidFabTypeGiven() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> factory.getFab("invalid", activityMock))
                .withMessage("Invalid fabType entered.");
    }

    @Test
    public void illegalArgumentExceptionIfNullFabTypeGiven() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> factory.getFab(null, activityMock))
                .withMessage("Null fabType entered.");
    }
}

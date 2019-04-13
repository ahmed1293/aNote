package com.ahmed.anote.displays.pinDisplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ahmed.anote.R;
import com.ahmed.anote.db.Contract;
import com.ahmed.anote.displays.pinDisplay.PinDisplayActivity;
import com.ahmed.anote.displays.pinDisplay.PinValues;
import com.ahmed.anote.forms.pin.PinFormActivity;

public class EditButton implements View.OnClickListener {

    private ImageView button;
    private PinValues values;
    private PinDisplayActivity activity;
    private Intent intent;

    public EditButton(PinDisplayActivity activity, PinValues values, Intent intent) {
        this.activity = activity;
        this.intent = intent;
        this.values = values;
        this.button = activity.findViewById(R.id.edit_pin_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Contract.Pins.COLUMN_KEY, values.getKey());
        bundle.putString(Contract.Pins.COLUMN_HINT, values.getHint());
        bundle.putString(Contract.Pins.COLUMN_PIN, values.getPin());
        intent.putExtras(bundle);

        activity.startActivity(intent);
    }
}
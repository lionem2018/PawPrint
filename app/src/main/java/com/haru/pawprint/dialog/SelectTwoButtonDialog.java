package com.haru.pawprint.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haru.pawprint.R;

import androidx.annotation.NonNull;

/**
 * TODO: document your custom view class.
 */
public class SelectTwoButtonDialog extends Dialog {

    private Context context;

    private TextView textViewDialogTitle;
    private RadioGroup radioGroup;

    public SelectTwoButtonDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    public void setText(@NonNull String title){
        textViewDialogTitle.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_select_two_button);

        textViewDialogTitle = findViewById(R.id.textview_dialog_title);
        radioGroup = findViewById(R.id.radiogroup_gender);

        Button btnConfirm = findViewById(R.id.button_dialog_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

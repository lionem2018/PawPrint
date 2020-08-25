package com.haru.pawprint.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haru.pawprint.R;
import com.haru.pawprint.RecordActivity;
import com.haru.pawprint.adapter.DialogHealthListItemAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * TODO: document your custom view class.
 */
public class EditTextDialog extends Dialog {

    private Context context;

    private TextView textViewDialogTitle;
    private EditText editText;

    public EditTextDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    public void setText(@NonNull String title, @NonNull String editTextHint, String editTextDefaultText){
        textViewDialogTitle.setText(title);
        editText.setHint(editTextHint);
        if(editTextDefaultText != null) {
            editText.setText(editTextDefaultText);
            editText.setSelection(editText.getText().length());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_edit_text);

        textViewDialogTitle = findViewById(R.id.textview_dialog_title);
        editText = findViewById(R.id.eidttext_dialog);

        Button btnConfirm = findViewById(R.id.button_dialog_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haru.pawprint.dialog.EditTextDialog;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        EditTextDialog editTextDialog = new EditTextDialog(this);
        editTextDialog.setCanceledOnTouchOutside(true);
        editTextDialog.setCancelable(true);

        TextView btnBack = findViewById(R.id.textview_back);
        ImageButton btnEditName = findViewById(R.id.button_edit_name);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextDialog.show();
                editTextDialog.setText("반려동물 이름 수정", "이름을 입력하세요.", "코페르니쿠스");
            }
        });
    }
}
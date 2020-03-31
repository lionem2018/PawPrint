package com.haru.pawprint.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.haru.pawprint.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionPageTwoFragment extends Fragment {

    // Button과 EditText 객체를 불러오기 위한 View 변수
    private View view;

    // DatePicker를 연결할 Button 변수
    private Button buttonCalBirthday;
    private Button buttonCalAdopt;

    // DatePicker의 결과를 보여줄 EditText 변수
    private EditText editTextBirthday;
    private EditText editTextAdopt;

    // 성별 선택 Button 변수
    private Button buttonMale;
    private Button buttonFemale;

    // 성별 정보 변수(남자는 false, 여자는 true)
    private boolean gender;

    // 생일 관련 날짜 요소 변수
    private int year_birthday;
    private int month_birthday;
    private int day_birthday;

    // 입양 관련 날짜 요소 변수
    private int year_adopt;
    private int month_adopt;
    private int day_adopt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Button과 EditText 객체를 불러오기 위한 View 객체 가져오기
        view = inflater.inflate(R.layout.fragment_question_page_two, container, false);

        // 성별 선택 Button 객체 가져오기
        buttonMale = (Button) view.findViewById(R.id.button_male);
        buttonFemale = (Button) view.findViewById(R.id.button_female);

        // 성별 선택 Button Enable 기본 세팅(남자아이로 기본 선택)
        // 향후 수정 가능성 있음
        buttonMale.setEnabled(false);
        gender = false;

        // DatePicker를 연결할 Button 객체 가져오기
        buttonCalBirthday = (Button) view.findViewById(R.id.button_cal_birthday);
        buttonCalAdopt = (Button) view.findViewById(R.id.button_cal_adopt);

        // DatePicker의 결과를 출력할 EditText 객체 가져오기
        editTextBirthday = (EditText) view.findViewById(R.id.edit_text_birthday);
        editTextAdopt = (EditText) view.findViewById(R.id.edit_text_adopt);

        // 날짜 요소들의 값을 현재 날짜로 초기화
        year_birthday = year_adopt = Calendar.getInstance().get(Calendar.YEAR);
        month_birthday = month_adopt = Calendar.getInstance().get(Calendar.MONTH);
        day_birthday = day_adopt = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        // 남자아이 버튼 클릭 리스너 등록
        buttonMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 버튼 상태 변경 및 성별 정보 변경(남자. false)
                // 향후 수정 가능성 있음
                buttonMale.setEnabled(false);
                buttonFemale.setEnabled(true);
                gender = false;
            }
        });

        // 여자아이 버튼 클릭 리스너 등록
        buttonFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 버튼 상태 변경 및 성별 정보 변경(여자, true)
                // 향후 수정 가능성 있음
                buttonMale.setEnabled(true);
                buttonFemale.setEnabled(false);
                gender = true;
            }
        });

        // 생일 날짜 선택 버튼 클릭 리스너 등록
        buttonCalBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DatePickerDialog 생성
                DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    // 날짜 선택 시
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // 생일 관련 날짜 요소 값 갱신
                        year_birthday = i;
                        month_birthday = i1;
                        day_birthday = i2;

                        // 생일 날짜 출력 EditText 갱신
                        editTextBirthday.setText(String.format("%d-%02d-%02d", year_birthday, month_birthday+1, day_birthday));
                    }
                }, year_birthday, month_birthday, day_birthday);
                // DatePickerDialog 보이기
                datepickerdialog.show();
            }
        });

        // 입양 날짜 선택 버튼 클릭 리스너 등록
        buttonCalAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DatePickerDialog 실행
                DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    // 날짜 선택 시
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // 입양 관련 날짜 요소 값 갱신
                        year_adopt = i;
                        month_adopt = i1;
                        day_adopt = i2;

                        // 입양 날짜 출력 EditText 갱신
                        editTextAdopt.setText(String.format("%d-%02d-%02d", year_adopt, month_adopt+1, day_adopt));
                    }
                }, year_adopt, month_adopt, day_adopt);
                // DatePickerDialog 보이기
                datepickerdialog.show();
            }
        });

        return view;
    }

    /**
     * 프래그먼트의 View 요소들의 값이 다 채워졌는지 확인하는 함수
     * @return boolean, 값이 다 채워졌는지 여부
     */
    public boolean isFilledAllView()
    {
        if(!editTextBirthday.getText().toString().equals("") && !editTextAdopt.getText().toString().equals(""))
            return true;
        else
            return false;
    }
}

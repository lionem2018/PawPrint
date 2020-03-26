package com.haru.pawprint;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haru.pawprint.fragment.QuestionPageOneFragment;
import com.haru.pawprint.fragment.QuestionPageThreeFragment;
import com.haru.pawprint.fragment.QuestionPageTwoFragment;

public class RegisterPetActivity extends BaseActivity {

    FragmentManager fragmentManager;

    private int pragmentNum;

    QuestionPageOneFragment questionPageOneFragment;
    QuestionPageTwoFragment questionPageTwoFragment;
    QuestionPageThreeFragment questionPageThreeFragment;

    View viewPageOne;
    View viewPageTwo;
    View viewPageThree;

    Button button_next_page;
    Button button_prev_page;

    TextView textViewQuestionTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register_pet);

        questionPageOneFragment = new QuestionPageOneFragment();
        questionPageTwoFragment = new QuestionPageTwoFragment();
        questionPageThreeFragment = new QuestionPageThreeFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageOneFragment).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
        pragmentNum = 1;

        button_next_page = (Button)findViewById(R.id.button_next_page);
        button_prev_page = (Button)findViewById(R.id.button_prev_page);

        viewPageOne = (View)findViewById(R.id.view_page_one);
        viewPageTwo = (View)findViewById(R.id.view_page_two);
        viewPageThree = (View)findViewById(R.id.view_page_three);

        textViewQuestionTitle = (TextView)findViewById(R.id.text_view_quesiton_title);

        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pragmentNum == 1) {
//                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageTwoFragment).commit();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left)
                            .addToBackStack(null)
                            .replace(R.id.framelayout_page, questionPageTwoFragment)
                            .commit();

                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    textViewQuestionTitle.setText("추가 정보를\n기입해주세요");

                    pragmentNum = 2;

                    button_prev_page.setEnabled(true);
                }
                else if(pragmentNum == 2) {
//                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageThreeFragment).commit();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left)
                            .addToBackStack(null)
                            .replace(R.id.framelayout_page, questionPageThreeFragment)
                            .commit();

                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    textViewQuestionTitle.setText("선호하는 방식을\n선택해주세요");

                    pragmentNum = 3;

                    button_next_page.setEnabled(false);
                }
                else{
                    button_next_page.setEnabled(false);
                    ;
                }

            }
        });

        button_prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pragmentNum == 2) {
//                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageOneFragment).commit();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right)
                            .addToBackStack(null)
                            .replace(R.id.framelayout_page, questionPageOneFragment)
                            .commit();

                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    textViewQuestionTitle.setText("당신의 반려동물을\n소개해주세요");

                    pragmentNum = 1;

                    button_prev_page.setEnabled(false);
                }
                else if(pragmentNum == 3) {
//                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageTwoFragment).commit();
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right)
                            .addToBackStack(null)
                            .replace(R.id.framelayout_page, questionPageTwoFragment)
                            .commit();

                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    textViewQuestionTitle.setText("추가 정보를\n기입해주세요");

                    pragmentNum = 2;

                    button_next_page.setEnabled(true);
                }
                else{
                    button_prev_page.setEnabled(false);
                    ;
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int request = requestCode & 0xffff;

        questionPageOneFragment.onActivityResult(request, resultCode, data);
    }
}

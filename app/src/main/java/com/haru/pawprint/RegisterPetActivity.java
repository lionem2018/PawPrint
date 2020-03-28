package com.haru.pawprint;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haru.pawprint.fragment.QuestionPageOneFragment;
import com.haru.pawprint.fragment.QuestionPageThreeFragment;
import com.haru.pawprint.fragment.QuestionPageTwoFragment;

public class RegisterPetActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    private int fragmentNum;

    private QuestionPageOneFragment questionPageOneFragment;
    private QuestionPageTwoFragment questionPageTwoFragment;
//    private QuestionPageThreeFragment questionPageThreeFragment;

    private View viewPageOne;
    private View viewPageTwo;
//    private View viewPageThree;

    private Button button_next_page;
    private Button button_prev_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        questionPageOneFragment = new QuestionPageOneFragment();
        questionPageTwoFragment = new QuestionPageTwoFragment();
//        questionPageThreeFragment = new QuestionPageThreeFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.framelayout_page, questionPageOneFragment);
        fragmentTransaction.add(R.id.framelayout_page, questionPageTwoFragment);
//        fragmentTransaction.add(R.id.framelayout_page, questionPageThreeFragment);

        fragmentTransaction.show(questionPageOneFragment);
        fragmentTransaction.hide(questionPageTwoFragment);
//        fragmentTransaction.hide(questionPageThreeFragment);

        fragmentTransaction.commit();

        fragmentNum = 1;

        button_next_page = (Button)findViewById(R.id.button_next_page);
        button_prev_page = (Button)findViewById(R.id.button_prev_page);

        viewPageOne = (View)findViewById(R.id.view_page_one);
        viewPageTwo = (View)findViewById(R.id.view_page_two);
//        viewPageThree = (View)findViewById(R.id.view_page_three);

        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fragmentNum == 1) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left);
                    fragmentTransaction.hide(questionPageOneFragment)
                            .show(questionPageTwoFragment)
//                            .hide(questionPageThreeFragment)
                            .commit();

                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    fragmentNum = 2;

                    button_prev_page.setEnabled(true);
                    button_next_page.setEnabled(false);

                }
//                else if(fragmentNum == 2) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left);
//                    fragmentTransaction.hide(questionPageOneFragment).hide(questionPageTwoFragment).show(questionPageThreeFragment).commit();
//
//                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
//                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
//
//                    fragmentNum = 3;
//
//                    button_next_page.setEnabled(false);
//                }
                else{
                    button_next_page.setEnabled(false);
                    button_prev_page.setEnabled(true);

                }

            }
        });

        button_prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fragmentNum == 2) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right);
                    fragmentTransaction.show(questionPageOneFragment)
                            .hide(questionPageTwoFragment)
//                            .hide(questionPageThreeFragment)
                            .commit();

                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    fragmentNum = 1;

                    button_prev_page.setEnabled(false);
                    button_next_page.setEnabled(true);

                }
//                else if(fragmentNum == 3) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right);
//                    fragmentTransaction.hide(questionPageOneFragment).show(questionPageTwoFragment).hide(questionPageThreeFragment).commit();
//
//                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
//                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
//
//                    fragmentNum = 2;
//
//                    button_next_page.setEnabled(true);
//                }
                else{
                    button_prev_page.setEnabled(false);
                    button_next_page.setEnabled(true);
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

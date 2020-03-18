package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haru.pawprint.fragment.QuestionPageOneFragment;
import com.haru.pawprint.fragment.QuestionPageThreeFragment;
import com.haru.pawprint.fragment.QuestionPageTwoFragment;

public class MainActivity extends BaseActivity {

    FragmentManager fragmentManager;

    private int pragmentNum;

    QuestionPageOneFragment questionPageOneFragment;
    QuestionPageTwoFragment questionPageTwoFragment;
    QuestionPageThreeFragment questionPageThreeFragment;

    View viewPageOne;
    View viewPageTwo;
    View viewPageThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_add_pet);

        questionPageOneFragment = new QuestionPageOneFragment();
        questionPageTwoFragment = new QuestionPageTwoFragment();
        questionPageThreeFragment = new QuestionPageThreeFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageOneFragment).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
        pragmentNum = 1;

        Button button_next_page = (Button)findViewById(R.id.button_next_page);
        Button button_prev_page = (Button)findViewById(R.id.button_prev_page);

        viewPageOne = (View)findViewById(R.id.view_page_one);
        viewPageTwo = (View)findViewById(R.id.view_page_two);
        viewPageThree = (View)findViewById(R.id.view_page_three);

        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pragmentNum == 1) {
                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageTwoFragment).commit();
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
                    pragmentNum = 2;
                }
                else if(pragmentNum == 2) {
                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageThreeFragment).commit();
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
                    pragmentNum = 3;
                }
                else{
                    //button invisible
                    ;
                }

            }
        });

        button_prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pragmentNum == 2) {
                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageOneFragment).commit();
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
                    pragmentNum = 1;
                }
                else if(pragmentNum == 3) {
                    fragmentManager.beginTransaction().replace(R.id.framelayout_page, questionPageTwoFragment).commit();
                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
                    pragmentNum = 2;
                }
                else{
                    //button invisible
                    //page state change
                    ;
                }

            }
        });
    }
}

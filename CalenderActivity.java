package com.example.myapplicationhab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {
    public static final String EXTRA_DATE = "selected_date"; // 의미 있는 변수명으로 변경
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calender);

        // 액션 바 숨기기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 뷰 초기화
        Button homeBtn = findViewById(R.id.homeBtn);
        Button graphBtn = findViewById(R.id.graphBtn);
        CalendarView cal = findViewById(R.id.cal);
        TextView idText = findViewById(R.id.idText);

        // 전역 클래스를 사용하여 ID 출력
        userId = ((User) getApplication()).getId();
        idText.setText(userId);

        // 버튼 클릭 리스너 설정
        homeBtn.setOnClickListener(this::navigateToHome);
        graphBtn.setOnClickListener(this::navigateToGraph);
        Button graph2Btn = (Button) findViewById(R.id.graph2Btn);

        // 날짜 선택 리스너 설정
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // 월(month)는 0부터 시작하므로 1을 더해준다.
                String date = String.format("%d/%02d/%02d", year, month + 1, dayOfMonth);
                navigateToSaveMoney(date);
            }
        });

        graph2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalenderActivity.this, Graph2Activity.class);
                startActivity(intent);
            }
        });
    }

    // 홈 화면으로 이동하는 메서드
    private void navigateToHome(View view) {
        Intent intent = new Intent(CalenderActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    // 그래프 화면으로 이동하는 메서드
    private void navigateToGraph(View view) {
        Intent intent = new Intent(CalenderActivity.this, GraphActivity.class);
        startActivity(intent);
    }



    // 날짜를 선택하여 가계부 화면으로 이동하는 메서드
    private void navigateToSaveMoney(String date) {
        Intent intent = new Intent(CalenderActivity.this, SaveMoneyActivity.class);
        intent.putExtra(EXTRA_DATE, date); // 의미 있는 상수명을 사용
        startActivity(intent);
    }
}

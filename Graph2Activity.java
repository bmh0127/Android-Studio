package com.example.myapplicationhab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class Graph2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graph);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 바 그래프 구현 /////////////////////////////////
        BarChart barChart = findViewById(R.id.barChart);

        // 월별 데이터 가져오기
        User user = (User) getApplication();
        int[] monthlyIncome = user.getAllInSumMoney(); // 수입 데이터

        // 데이터 준비
        ArrayList<BarEntry> incomeEntries = new ArrayList<>();
        ArrayList<BarEntry> expenseEntries = new ArrayList<>();

        for (int i = 0; i < monthlyIncome.length; i++) {
            incomeEntries.add(new BarEntry(i + 1, monthlyIncome[i]));
        }

        // 수입 데이터 세트 생성
        BarDataSet incomeDataSet = new BarDataSet(incomeEntries, "월별 수입 금액");
        incomeDataSet.setValueTextSize(14f);
        incomeDataSet.setColor(getResources().getColor(R.color.purple_200)); // 수입 색상 설정
        incomeDataSet.setValueTextSize(10f);

        // BarData 생성 및 BarChart에 설정
        BarData data = new BarData(incomeDataSet);
        barChart.setData(data);

        // X축 설정: 월 이름 표시
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "월";
            }
        });

        // Y축 설정
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextSize(12f);
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setTextSize(12f);
        leftAxis.setGranularity(100f);
        rightAxis.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setTextSize(18f);  // 설명의 글자 크기 설정

        // 애니메이션 추가
        barChart.animateY(1000);

        // 차트 갱신
        barChart.invalidate();

        // 버튼 클릭 리스너 설정
        Button calBtn = findViewById(R.id.calBtn);
        Button homeBtn = findViewById(R.id.homeBtn);
        Button graphBtn = findViewById(R.id.graphBtn);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph2Activity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph2Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Graph2Activity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
    }
}
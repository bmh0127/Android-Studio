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

public class GraphActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graph);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // 바 그래프 구현 /////////////////////////////////
        // 1. 차트 객체 초기화
        BarChart barChart = findViewById(R.id.barChart);

        // 월별 데이터 준비
        ArrayList<BarEntry> monthlyEntries = new ArrayList<>();
        int[] monthlyExpenses = ((User) getApplication()).getAllOutSumMoney();
        for (int i = 0; i < monthlyExpenses.length; i++) {
            monthlyEntries.add(new BarEntry(i + 1, monthlyExpenses[i]));
        }

        // BarDataSet 생성
        BarDataSet dataSet = new BarDataSet(monthlyEntries, "월별 지출 금액");
        dataSet.setValueTextSize(14f);
        dataSet.setColors(new int[]{R.color.purple_200}, this);  // 데이터 세트의 색상 설정
        dataSet.setValueTextSize(10f);

        // BarData 생성 및 BarChart에 설정
        BarData data = new BarData(dataSet);
        barChart.setData(data);

        // X축 설정: 월 이름 표시
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // X축 위치 설정
        xAxis.setGranularity(1f);  // X축 간격 설정
        xAxis.setValueFormatter(new ValueFormatter() {  // X축 값 포맷 설정
            @Override
            public String getFormattedValue(float value) {
                // X축에 월 표시
                return (int) value + "월";
            }
        });

        // Y축 설정
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextSize(12f);
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setTextSize(12f);
        leftAxis.setGranularity(100f);  // Y축 간격 설정
        rightAxis.setEnabled(false);  // 오른쪽 Y축 비활성화

        Legend legend = barChart.getLegend();
        legend.setTextSize(18f);  // 설명의 글자 크기 설정

        // 애니메이션 추가
        barChart.animateY(1000);

        // 차트 갱신
        barChart.invalidate();  // 차트 갱신하여 변경사항 반영

        Button calBtn = (Button) findViewById(R.id.calBtn);
        Button homeBtn = (Button) findViewById(R.id.homeBtn);
        Button graph2Btn = (Button) findViewById(R.id.graph2Btn);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraphActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraphActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        graph2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraphActivity.this, Graph2Activity.class);
                startActivity(intent);
            }
        });

    }
}
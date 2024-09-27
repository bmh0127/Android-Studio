package com.example.myapplicationhab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SaveMoneyActivity extends AppCompatActivity {
    public static final String EXTRA_DATE = "";
    static int inSumMoney = 0;
    static int outSumMoney = 0;
    private ArrayList<String> data; // ArrayList를 필드로 변경
    private ArrayAdapter<String> adapterList; // 어댑터도 필드로 변경
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_savemoney);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ListView listView = (ListView) findViewById(R.id.listView);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        EditText editKate = (EditText) findViewById(R.id.ediKate);
        EditText editMoney = (EditText) findViewById(R.id.editMoney);
        TextView inSumText = (TextView) findViewById(R.id.inSumText);
        TextView outSumText = (TextView)findViewById(R.id.outSumText);

        // 스피너 구현 ////////////////////////////////////////////////
        String[] items = {"수입", "지출"};
        Spinner spinner_SelMoney = (Spinner) findViewById(R.id.spinner_SelMoney);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_SelMoney.setAdapter(adapter);
        /////////////////////////////////////////////////////////////////

        // 선택 날짜 구현 ////////////////////////////////////////////////
        TextView calText = (TextView) findViewById(R.id.calText);
        Intent intent = getIntent();
        String date = intent.getStringExtra(CalenderActivity.EXTRA_DATE);
        calText.setText(date);
        /////////////////////////////////////////////////////////////////

        // 선택한 날짜의 월 가져오기 (예: "2023-08-01"에서 "08" 추출)
        String selectedMonth = date.substring(5, 7);
        int month = Integer.parseInt(selectedMonth);  // 문자열을 정수로 변환

        // 리스트 구현 ///////////////////////////////////////////////////////
        // ArrayList 초기화 및 복원
        if (savedInstanceState != null) {
            data = savedInstanceState.getStringArrayList("data");
            inSumMoney = savedInstanceState.getInt("inSumMoney");
            outSumMoney = savedInstanceState.getInt("outSumMoney");
        } else {
            data = new ArrayList<>();
        }

        // 어댑터 설정 및 데이터 세팅
        adapterList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, data);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapterList);

        ArrayList<String> newList = ((User) getApplication()).getAdd(month);
        for(String item : newList){
            int count = adapterList.getCount();
            data.add(Integer.toString(count+1) + ". " + item);
            adapterList.notifyDataSetChanged();
        }

        // 수입 및 지출 합계 업데이트
        updateSumTexts(month);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                int count = adapterList.getCount();
                int money = Integer.parseInt(editMoney.getText().toString());
                String str = spinner_SelMoney.getSelectedItem().toString() + "  ||  항목:  " + editKate.getText().toString() + "  ||  금액:  " + money + "원" ;
                editMoney.setText("");
                editKate.setText("");
                data.add(Integer.toString(count+1) + ". " + str);
                list.add(str);
                ((User) getApplication()).setAdd(month, list);
                adapterList.notifyDataSetChanged();

                if (spinner_SelMoney.getSelectedItem().toString().equals("수입")) {
                    ((User) getApplication()).addInSumMoney(month, money);  // 월별 수입 추가
                } else if (spinner_SelMoney.getSelectedItem().toString().equals("지출")) {
                    ((User) getApplication()).addOutSumMoney(month, money);  // 월별 지출 추가
                }

                updateSumTexts(month);

            }
        });

        /////////////////////////////////////////////////////////////////////////////

        // 화면 전환 메소드 //////////////////////////////////////////////
        Button homeBtn = (Button) findViewById(R.id.homeBtn);
        Button graphBtn = (Button) findViewById(R.id.graphBtn);
        Button calBtn = (Button) findViewById(R.id.calBtn);
        Button graph2Btn = (Button) findViewById(R.id.graph2Btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveMoneyActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveMoneyActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });

        graph2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveMoneyActivity.this, Graph2Activity.class);
                startActivity(intent);
            }
        });

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveMoneyActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////////////

    }

    private void updateSumTexts(int month) {
        // 월별 수입 및 지출 합계를 User 클래스에서 가져와 업데이트
        int inSum = ((User) getApplication()).getInSumMoney(month);
        int outSum = ((User) getApplication()).getOutSumMoney(month);

        TextView inSumText = (TextView) findViewById(R.id.inSumText);
        TextView outSumText = (TextView) findViewById(R.id.outSumText);

        inSumText.setText("수입: +" + inSum + "원");
        outSumText.setText("지출: -" + outSum + "원");
    }

    // 상태 저장 메서드
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("data", data);
        outState.putInt("inSumMoney", inSumMoney);
        outState.putInt("outSumMoney", outSumMoney);
    }
}
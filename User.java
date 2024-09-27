package com.example.myapplicationhab;

import android.app.Application;
import java.util.ArrayList;

public class User extends Application {
    private String id = "";
    private ArrayList<String>[] add = new ArrayList[12];
    private int[] inSumMoney = new int[12];  // 월별 수입 배열 (1월부터 12월까지)
    private int[] outSumMoney = new int[12]; // 월별 지출 배열 (1월부터 12월까지)

    public User() {
        // 각 월별 리스트를 초기화
        for (int i = 0; i < 12; i++) {
            add[i] = new ArrayList<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id +"님의 가계부";
    }

    public ArrayList<String> getAdd(int month) {
        return add[month - 1];
    }

    public void setAdd(int month, ArrayList<String> add) {
        this.add[month - 1].addAll(add);
    }

    // 월별 수입 관련 메서드
    public int getInSumMoney(int month) {
        return inSumMoney[month - 1]; // 월은 1부터 시작하므로 배열의 인덱스는 month - 1
    }

    public void setInSumMoney(int month, int amount) {
        inSumMoney[month - 1] = amount; // 해당 월의 수입을 설정
    }

    public void addInSumMoney(int month, int amount) {
        inSumMoney[month - 1] += amount; // 해당 월의 수입을 추가
    }

    // 월별 지출 관련 메서드
    public int getOutSumMoney(int month) {
        return outSumMoney[month - 1]; // 월은 1부터 시작하므로 배열의 인덱스는 month - 1
    }

    public void setOutSumMoney(int month, int amount) {
        outSumMoney[month - 1] = amount; // 해당 월의 지출을 설정
    }

    public void addOutSumMoney(int month, int amount) {
        outSumMoney[month - 1] += amount; // 해당 월의 지출을 추가
    }

    // 전체 월별 수입을 반환하는 메서드
    public int[] getAllInSumMoney() {
        return inSumMoney;
    }

    // 전체 월별 지출을 반환하는 메서드
    public int[] getAllOutSumMoney() {
        return outSumMoney;
    }
}

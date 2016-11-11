package com.navercorp.jiwoo.revive.Util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jiwoo Ma on 2016-10-31.
 */
public class parsingSMS {

    /**
     * 지출금액을 추출합니다. (int로)
     * @param originalMessage
     * @return (int) expense
     */
    private int extractExpenseToInt(String originalMessage) {
        String expenseMessage = "";
        StringBuilder sb = new StringBuilder("");
        int expense = 0;
        Pattern pattern = Pattern.compile("([0-9]+|[0-9]{1,3}(,[0-9]{3})*)원");
        Matcher matcher = pattern.matcher(originalMessage);

        if(matcher.find()) {
            expenseMessage = matcher.group();
            Log.e("AAAA", "지출내역 : " + expenseMessage);

            Pattern pattern2 = Pattern.compile("[0-9]{1,3}");
            Matcher matcher2 = pattern2.matcher(expenseMessage);

            while (matcher2.find()) {
                String expenseTemp = matcher2.group();
                sb.append(expenseTemp);

            }

        }
        expense = Integer.parseInt(sb.toString());
        Log.e("AAAA", "지출내역 : " + expense);
        return expense;
    }


    /**
     * 지출금액을 추출합니다. (String으로)
     * @param originalMessage
     * @return (String) expense
     */
    private String extractExpenseToString(String originalMessage) {
        String expenseMessage = "";
        Pattern pattern = Pattern.compile("([0-9]+|[0-9]{1,3}(,[0-9]{3})*)원");
        Matcher matcher = pattern.matcher(originalMessage);

        if(matcher.find()) {
            expenseMessage = matcher.group();
            Log.e("AAAA", "지출내역(String) : " + expenseMessage);
        }

        return expenseMessage;
    }

    /**
     * 누적사용량을 추출합니다.
     * @param originalMessage
     * @return (String) totalExpense
     */
    private String extractAccumExpense(String originalMessage) {
        String accumExpense = "";
        Pattern pattern = Pattern.compile("누적 ([0-9]+|[0-9]{1,3}(,[0-9]{3})*)원");
        Matcher matcher = pattern.matcher(originalMessage);

        if(matcher.find()) {
            accumExpense = matcher.group();
        }

        Log.e("AAAA", "누적: " + accumExpense);
        return accumExpense;
    }

    /**
     * 지출한 날짜를 추출합니다.
     * @param originalMessage
     * @return (int) expenseDate
     */
    private Long extractExpenseDate(String originalMessage) {
        StringBuilder sb = new StringBuilder("");
        String originalDate = "";
        Long expenseDate = 0L;

        // 현재 년도 계산
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date currentTime = new Date();
        String year = simpleDateFormat.format(currentTime);
        sb.append(year);

        // originalMessage에서 날짜 및 시간 추출 (ex) 10/08 14:09)
        Pattern pattern = Pattern.compile("[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}");
        Matcher matcher = pattern.matcher(originalMessage);

        if(matcher.find()) {
            originalDate = matcher.group(0);
            Pattern pattern2 = Pattern.compile("[0-9]{2}");
            Matcher matcher2 = pattern2.matcher(originalDate);

            //원하는 형태로 변형해 붙여줍니다. (ex) 201610081409)
            while (matcher2.find()) {
                String searchStr = matcher2.group();
                sb.append(searchStr);
            }
        }

        Log.e("AAAA", "날짜: " + sb.toString());
        expenseDate = Long.valueOf(sb.toString());
        return expenseDate;
    }


}

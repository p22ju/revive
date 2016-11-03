package com.navercorp.jiwoo.revive.SMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

/**
 * Created by Jiwoo Ma on 2016-10-14.
 */
public class SMSReceiver extends BroadcastReceiver {

    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        //수신한 액션을 이 onReceive메소드에서 처리한다.

        //TODO Q.왜 이렇게는 안되나요? (SMS_RECEIVED가 없는것으로 뜸)
        // android.provider.Telephony.SMS_RECEIVED == intent.getAction()
        if(intent.getAction().equals(ACTION)) {
            // sms수신시 할 행동을 정의한다.
            Log.d("onReceive()","문자가 수신되었습니다");

            //Bundle 널 체크
            Bundle bundle = intent.getExtras();
            if(bundle == null) {
                return;
            }

            //pdu 객체 널 체크
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if(pdusObj == null) {
                return;
            }

            // message 처리
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
            for(int i=0; i<pdusObj.length; i++) {
                //pdu 포맷으로 되어있는 메세지 복원
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                // SMS 수신 시간 확인
                Date curDate = new Date(smsMessages[0].getTimestampMillis());
                Log.d("문자 수신 시간", curDate.toString());

                // SMS 발신 번호 확인
                String origNumber = smsMessages[0].getOriginatingAddress();
                Log.d("발신자", origNumber);

                // SMS 메시지 확인
                String message = smsMessages[0].getMessageBody().toString();
                Log.d("내용", message);
            }

        }


    }
}

package com.example.myapplication_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony

class MyReceiver_1 : BroadcastReceiver() {
    interface Listner{
        fun onTextRecieved(sPhone: String?, sMsg: String?)
    }
    var listner:Listner?=null
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            var sPhone = ""
            var sSMSBody = ""
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                for(smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent))
                {
                    sPhone = smsMessage.displayOriginatingAddress
                    sSMSBody += smsMessage.messageBody
                }
                if(listner != null)
                {
                    listner?.onTextRecieved(sPhone, sSMSBody)
                }
            }
        }
    }
}
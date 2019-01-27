package android.lab_1;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DataBase implements Serializable {

    private Integer lbl_balance;
    private List<Friend> friends;
    private List<TransactionEvent> history;

    public DataBase(Integer lbl_balance) {
        this.lbl_balance = lbl_balance;
        this.friends = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public List<TransactionEvent> getHistory() {
        return history;
    }

    public boolean newTransaction(Friend friend, Integer transferAmount) {

        // if number is to big or to small
        if (transferAmount > getLbl_balance() || transferAmount == 0) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        String time = formatter.format(date);
        Integer newBalance = this.lbl_balance - transferAmount;
        TransactionEvent transactionEvent = new TransactionEvent(time, friend,transferAmount,newBalance);
        this.lbl_balance = newBalance;
        history.add(transactionEvent);

        return true;
    }

    public Integer getLbl_balance() {
        return lbl_balance;
    }

    public String lblBalanceToFormattedString() {
        return   String.format(java.util.Locale.US,"%.02f",(this.lbl_balance.floatValue() / 100));
    }

    public void setLbl_balance(Integer lbl_balance) {

        this.lbl_balance = lbl_balance;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void addFriend(String str) {
        Friend newFriend = new Friend(str);
        this.friends.add(newFriend);
    }

    public String[] friendsAsStringArray () {
        List<String> temp = new ArrayList<>();
        for (Friend friend : friends ) {
            temp.add(friend.toString());
        }

        return temp.toArray(new String[temp.size()]);
    }

    public boolean validFriend(String s) {

        if (friends.contains(new Friend(s))) {

            return true;
        }

        return false;
    }

    public Friend copyFriend(String name){
        if (validFriend(name)){
            int index = friends.indexOf(new Friend(name));
            Friend fr1 = friends.remove(index);
            Friend fr2 = new Friend(fr1);
            friends.add(fr1);
            return fr2;
        }
        return null;
    }

    private class TransactionEvent implements Serializable {
        String timeStamp;
        Friend recipient;
        Integer transferAmount;
        Integer newBalance;

        public TransactionEvent(String timeStamp, Friend recipient, Integer transferAmount, Integer newBalance) {
            this.timeStamp = timeStamp;
            this.recipient = recipient;
            this.transferAmount = transferAmount;
            this.newBalance = newBalance;
        }


    }
}

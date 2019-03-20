package android.lab_1;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// a in memory database
public class DataBase implements Serializable {

    private Integer lbl_balance;
    private String mUsername;
    private List<Friend> friends;
    private List<TransactionEvent> history;

    public DataBase(Integer lblBalance, String username) {
        // handle special case for initialization
        this.lbl_balance = lblBalance * 2;
        this.mUsername = username;
        this.friends = new ArrayList<>();
        this.history = new ArrayList<>();
        // create the first TransactionEvent
        if (!newTransaction(new Friend(mUsername),lblBalance)){
            Log.d("Transaction", "not logged");
            return;
        }
    }
    // returns list of old transactions
    public List<TransactionEvent> getHistory(){

        return this.history;
    }
    // make new transaction
    public boolean newTransaction(Friend friend, Integer transferAmount) {

        // if number is to big
        if (transferAmount > getLbl_balance() ) {
            return false;
        }
        // register time of transaction
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time = formatter.format(date);

        Integer newBalance = this.lbl_balance - transferAmount;
        TransactionEvent transactionEvent = new TransactionEvent(time, friend,transferAmount,newBalance);
        this.lbl_balance = newBalance;
        // add transaction to history list
        try {
            history.add(transactionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public Integer getLbl_balance() {

        return lbl_balance;
    }
    // converts Integer to String
    public String lblBalanceToFormattedString() {
        return   String.format(java.util.Locale.US,"%.02f",(this.lbl_balance.floatValue() / 100));
    }

    public void setLbl_balance(Integer lbl_balance) {

        this.lbl_balance = lbl_balance;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    // make new friend and add to friends list, uniques not guarantied
    public void addFriend(String str) {
        Friend newFriend = new Friend(str);
        this.friends.add(newFriend);
    }
    // convets list of friends for list of Strings
    public String[] friendsAsStringArray () {
        List<String> temp = new ArrayList<>();
        for (Friend friend : friends ) {
            temp.add(friend.toString());
        }

        return temp.toArray(new String[temp.size()]);
    }
    // check if friend exits
    public boolean validFriend(String s) {

        if (friends.contains(new Friend(s))) {

            return true;
        }

        return false;
    }
    //  copy/clone of friend
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


}

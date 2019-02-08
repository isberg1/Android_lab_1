package android.lab_1;

import java.io.Serializable;
// stores a transaction
public class TransactionEvent implements Serializable {
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
    // converts fields to string
    @Override
    public String toString() {
        String separator = " | ";
        String formattedString = "\n";
        formattedString += timeStamp;
        formattedString += separator;
        formattedString += recipient.toString();
        formattedString += separator;
        formattedString += lblBalanceToString(transferAmount);
        formattedString += separator;
        formattedString += lblBalanceToString(newBalance);

        return formattedString;
    }
    // converts some of the fields to string
    public String shortToString(){

        String formattedString = recipient.toString();
        formattedString += "  ";
                formattedString += lblBalanceToString(transferAmount);

        return formattedString;
    }
    // converts Integer to String
    public String lblBalanceToString (Integer value) {
        return   String.format("%.02f",(value.floatValue() / 100));
    }
}

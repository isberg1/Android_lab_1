# lab_1 IMT3673 2019

this app was made using a Samsung galaxy S5 with android 6.0.1


This is a simple in memory banking app. it consists of 3 activities. 
*   MainActivity: the launching activity, displays current balance, one button for moving to TransferActivity and 
one button for moving to TransactionsActivity.
*   TransferActivity: allows the user to make a payment to a friend.
*   TransactionsActivity: displays a list of all past transactions.

## technical implementation

the app consists of 3 java classes
* TransactionEvent: stores a transaction
* DataBase: a in memory database, with fields for the current balance, the name of the apps user(hardcoded)
a list of all the users friends and a list of all past transactions.
* Friend: a simple class for storing the name of a friend.

and 4 android classes
* MainActivity described above
* TransferActivity described above
* TransactionsActivity described above
* RecyclerAdapter is used for creating and displaying a list of past transactions

### Bonus
* the TransferActivity pay button plays a sound when clicked (media volume must be on)  
* i used a RecyclerView to display the list in TransactionsActivity, this is a lot more difficult than using listView


# Marking 

Marking/testing/scoring of the implementation is based on the form specified below. The total score is based on the sum of the individual items tested. The test either "Fails" or "Passes" - the task cannot be "Partial", ie. partial means "Fail". Each test case has a score associated with it - most are worth 1 point, but some are worth more. The marking schedule is based on ticking the appropriate test cases and then summing up those that have been ticked. 

* [x] (1pt) The app has a custom icon (not the default Android one).
* [x] (1pt) The app `MainActivity` loads.
* [x] (1pt) The app's `MainActivity` contains all required UI elements as per SPEC.
* [x] (1pt) Pressing Android's "back button" on the `MainActivity` always quits the app, AND, this behaviour is not hardcoded in the code, ie. the code does not handle the Android back button presses, but instead, relies on the default Android behaviour.
* [x] (1pt) Pressing `btn_transactions` moves the user to TransactionsActivity.
* [x] (1pt) The default founding transaction from Angel is done correctly and visible in the `TransactionsActivity`. The user balance in `lbl_balance` matches the funding transaction.
* [x] (1pt) TransactionsActivity shows new payments correctly.
* [x] (1pt) TransactionsActivity moves the user back to MainActivity on "back botton" press.
* [x] (1pt) Pressing btn_transfer moves the user to TransferActivity.
* [x] (5pts) The `TransferActivity` has all required UI, the balance amount handling is working correctly with the appropriate lbl_amount_check error messages, and the btn_pay is enabled/disabled when appropriate. Pressing the `btn_pay` creates the new transaction, visible in TransactionsActivity, and updates the user balance accordingly.
* [x] (1pt) The app never crashes. In particular, changing from landscape to portrait and vice-versa does not crash the app.

**Bonus tasks**

* [x] (2pts) The app UI elements are all well laid-out, look appealing and tidy, and the app has a solid/professional feel, ie. buttons properly centred, UI elements well-arranged, colour schemes appealing, and so on. The app works well in both, portrait and landscape modes. 
* [x] (1pt) The app codebase is well-structured, readable, and follows professional Java and Android practices.
* [x] (1pt) The Recipient list for the drop-down is done programmatically such that the list source of names is provided as a string array from MainActivity, eg. `list = String[]{"Alice", "Bob", "Charlie", "Dawn", "Elvis", "Frode"};` This requires to dynamically update the TransferActivity UI based on the data passed to it from `MainActivity`. 
* [x] (1pt) An extra bonus point for something outside of scope. 


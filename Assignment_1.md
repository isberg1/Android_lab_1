# Lab 1: in-memory banking app

For this lab, we will create an application that mocks a simple mobile banking app. The app consists of 3 activities, that allow a user to check current account balance, make a payment to a friend, and see the list of recent transactions. Note: the app is in-memory stateful, but, it **should not** use persistence or any networking facilities. All the data within the app is mocked. When the app is started, the app stores the internal state in-memory, until the app is "restarted" afresh, ie. killed by the Android OS and re-initialized upon requesting the entry-level Activity. 


## MainActivity

The first, entry activity will be called MainActivity. It will be the main entry point for the app, and the "main menu" for the app. It will consist of:

* A fixed label with text: "Balance [EUR]"
* Another fixed label with a text representing the actual account balance. The activity should generate a random integer that represents the current account balance, and it should be between 90 and 110. We will refer to this field as `lbl_balance`
* A button with the label: "Transactions", referred as `btn_transactions`
* A button with the label "Transfer", referred as `btn_transfer`

Behaviour:
* upon starting up the activity, the field `lbl_balance` should have the newly generated random value between 90..110 euros. 
* pressing btn_transfer moves the user to the `TransferActivity`
* pressing btn_transactions moves the user to the `TransactionsActivity`
* pressing the normal android "back button", quits the app.


## TransferActivity

This activity will have the following UI elements:

* A fixed text label "Recipient" 
* A drop-down list with names of your friends, e.g. "Alice, Bob, Charlie, Dawn, Elvis, Frode". The list must consist of at least 6 names. 
* A fixed label with text: "Amount [EUR]"
* A single editable text field referred as: `txt_amount`
* A single label with no text, referred as `lbl_amount_check`
* A button with label "Pay", referred as: `btn_pay`, that is disabled by default

Behaviour:
* The `btn_pay` should not be enabled unless the recipient and amount are correctly selected. If the amount is provided and it is within correct bounds, and when the recipient is selected, then `btn_pay` is enabled.
* Pressing `btn_pay` when enabled triggers the payment, which means, that the value from `txt_amount` is subtracted from the user total balance; a new transaction is made to the recipient selected from the drop-down. This new transaction is added to the list, subsequently visible in the `TransactionsActivity`. After the payment is triggered the user is moved back to the `MainActivity`.
* When the amount is entered, and it is less or equal to 0, or, if the amount is greater then the current user balance, then `btn_pay` continues to be disabled, and the `lbl_amount_check` will display to the user the error, eg. "Amount is outside of bounds" or something similar. 
* pressing the normal android "back button" takes the user back to the `MainActivity` and no new transaction is made.


## TransactionsActivity

This activity will communicate to the user all the historical transactions.  The first transaction should be the "founding" transaction with the total amount of the initial user balance. Therefore, for example, if the random number generated as the initial balance for the user account is 95.43 Euros, then, the very first transaction recorded should be the one on the very moment the number was generated, and it should be, for example: 
```
15:30:21    |   Angel    |   95.43   |  95.43
```
which means, that at 15:30:21 (when the clock was at 3.30pm with 21 secs) the amount 95.43 Euros have been transferred to the user account, and the final balance on the account is 95.43 (the last column is the final balance on the account). Any subsequent payments, will be listed later as "payments" and the balance will be appropriately updated, eg:
```
15:30:21    |   Angel    |   95.43   |  95.43
15:31:14    |   Alice    |   15.43   |  80.00
15:32:18    |   Charlie  |   60.00   |  20.00
```

The list items formatting is up to the implementation, but, it needs to communicate those 4 fields: exact time (hours:mins:sec of the time of payment), recipient, amount, final account balance.  Use your creativity to format the list items accordingly. 


Behaviour:
* selecting (short-click) on the list items has no effect, but, user should be allowed to select a single item from the list
* long-click on the list item displays a Toast that says "XXX YYY" where XXX is the name of the recipient from this particular transfer, and YYY is the amount.
* pressing the normal android "back button" takes the user back to the `MainActivity` 


# Notes

* You do not need to control for the app "restarts" - this should be left to Android. 
* You should not program the handling of the Android's "back button" presses - the behaviour should be the standard Android behaviour - in other words, to not code any back-button handling in your code. 
* You should use XML-based UI designs for your Activities, with the exception of programmatically populating the "Recipients" drop-down list. 
* You should also use XML-based view template for the Transaction list items.
* The app should work in both, portrait and landscape modes.


# Questions to consider

When implementing the application with the above specification, not everything is precisely defined. Some things require you to think about the implementation details. This list of questions guides you through the process of clarifying the actual implementation details not covered in the SPEC. 

* Why do we represent the account balance as Integer? EURO has cents, and therefore it is a float with two decimal places, so why it is a bad idea to represent it as float for example? 
* How will you convert back-and-forth from Integer to a currency that has two decimal places in the UI for the user?
* How will you represent the Transactions? Should it be a struct? Or class? 
* How will you represent the Recipients? 
* Where to store the application state in-memory, such as user account balance, and the list of transactions?
* How will the Activities communicate data between themselves? 
* Who will be responsible for actually making the transfer: MainActivity or TransferActivity? Why?


# Marking 

Marking/testing/scoring of the implementation is based on the form specified below. The total score is based on the sum of the individual items tested. The test either "Fails" or "Passes" - the task cannot be "Partial", ie. partial means "Fail". Each test case has a score associated with it - most are worth 1 point, but some are worth more. The marking schedule is based on ticking the appropriate test cases and then summing up those that have been ticked. 

* [ ] (1pt) The app has a custom icon (not the default Android one).
* [ ] (1pt) The app `MainActivity` loads.
* [ ] (1pt) The app's `MainActivity` contains all required UI elements as per SPEC.
* [ ] (1pt) Pressing Android's "back button" on the `MainActivity` always quits the app, AND, this behaviour is not hardcoded in the code, ie. the code does not handle the Android back button presses, but instead, relies on the default Android behaviour.
* [ ] (1pt) Pressing `btn_transactions` moves the user to TransactionsActivity.
* [ ] (1pt) The default founding transaction from Angel is done correctly and visible in the `TransactionsActivity`. The user balance in `lbl_balance` matches the funding transaction.
* [ ] (1pt) TransactionsActivity shows new payments correctly.
* [ ] (1pt) TransactionsActivity moves the user back to MainActivity on "back botton" press.
* [ ] (1pt) Pressing btn_transfer moves the user to TransferActivity.
* [ ] (5pts) The `TransferActivity` has all required UI, the balance amount handling is working correctly with the appropriate lbl_amount_check error messages, and the btn_pay is enabled/disabled when appropriate. Pressing the `btn_pay` creates the new transaction, visible in TransactionsActivity, and updates the user balance accordingly.
* [ ] (1pt) The app never crashes. In particular, changing from landscape to portrait and vice-versa does not crash the app.

**Bonus tasks**

* [ ] (2pts) The app UI elements are all well laid-out, look appealing and tidy, and the app has a solid/professional feel, ie. buttons properly centred, UI elements well-arranged, colour schemes appealing, and so on. The app works well in both, portrait and landscape modes. 
* [ ] (1pt) The app codebase is well-structured, readable, and follows professional Java and Android practices.
* [ ] (1pt) The Recipient list for the drop-down is done programmatically such that the list source of names is provided as a string array from MainActivity, eg. `list = String[]{"Alice", "Bob", "Charlie", "Dawn", "Elvis", "Frode"};` This requires to dynamically update the TransferActivity UI based on the data passed to it from `MainActivity`. 
* [ ] (1pt) An extra bonus point for something outside of scope. 


**Total: 20**
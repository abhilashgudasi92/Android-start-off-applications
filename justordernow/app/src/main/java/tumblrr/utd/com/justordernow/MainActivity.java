package tumblrr.utd.com.justordernow;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //display(quantity);
        //displayPrice(quantity * 5);
        CheckBox  whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox  chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        EditText emailEditText = (EditText) findViewById(R.id.email_edit_text);
        String email = emailEditText.getText().toString();

        int price = calculatePrice(5,hasWhippedCream,hasChocolate);
        String orderDetails = createOrderSummary(price,hasWhippedCream,hasChocolate,name,email);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:"+email));
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT,orderDetails);
        try {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        displayMessage(orderDetails);
    }

    /**
     * Calculates the price of the order.
     *
     * @param pricePerCup is the price of each cup of coffee ordered
     * @param hasWhippedCream tells whether user has checked in whipped cream topping or not
     * @param hasChocolate tells whether user has checked in chocolate topping or not
     */
    private int calculatePrice(int pricePerCup,boolean hasWhippedCream,boolean hasChocolate) {
        //int price = quantity * pricePerCup;
        int price = 0;
        if(hasWhippedCream)
            price += 1;
        if(hasChocolate)
            price += 2;
        price += pricePerCup;

        price *= quantity;

        return price;
    }

    /**
     * Creates the order summary.
     *
     * @param price is the total price the order.
     * @param  addWhippedCream is whether or not the customer wants whipped cream topping
     * @return summary of the order
     */
    private String createOrderSummary(int price,boolean addWhippedCream,boolean addChocolate,String name,String email){
        //String name = "Abhilash Gudasi";
        String orderSummary = getString(R.string.order_summary_name,name);
        orderSummary += "\n" + getString(R.string.order_summary_email,email);
        orderSummary += "\n" + getString(R.string.order_summary_whipped_cream,addWhippedCream);
        orderSummary += "\n" + getString(R.string.order_summary_chocolate,addChocolate);
        orderSummary += "\n" + getString(R.string.order_summary_quantity,quantity);
        orderSummary += "\n" + getString(R.string.order_summary_price,price)  +"\n" + getString(R.string.thank_you);
        return  orderSummary;
    }


    /**
     * This method displays the given quantity value on <>              </>he screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view){
        if(quantity == 100){
            Toast toastMore = Toast.makeText(MainActivity.this,"Cannot order more than 100",Toast.LENGTH_LONG);
            toastMore.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
            toastMore.show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if(quantity == 1){
            Toast toastLess = Toast.makeText(MainActivity.this,"Cannot order less than 1",Toast.LENGTH_LONG);
            toastLess.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
            toastLess.show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}


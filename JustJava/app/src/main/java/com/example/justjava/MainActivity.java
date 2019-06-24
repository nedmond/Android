package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.content.Intent.ACTION_SENDTO;
import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sumbitOrder(View view) {
        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = creamCheckBox.isChecked();
        CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.choclate_checkbox);
        boolean hasChoclate = choclateCheckBox.isChecked();


        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChoclate);
        String priceMessage = createOrderSummary(name, quantity, hasWhippedCream, hasChoclate);
        displayPrice(price);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("mailto"));

        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Order Summary :"); // priceMessage);
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
        startActivity(sendIntent);
    }
        displayMessage(priceMessage);
}

    private void displayMessage(String priceMessage) {
        TextView OrderSummary = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummary.setText("" + priceMessage);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChoclate) {

        int basePrice = 5;
        if (addWhippedCream) {
            basePrice += 2;
        }
        if (addChoclate) {
            basePrice += 4;
        }

        int quantity = 5;
        return quantity * 5;
    }

    public void onCheckBoxClicked(View view) {

    }


    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }


    /*
     *creating the order summary
     * add another paramter folr the whipped ceam which is a boolean
     *
     *
     *
     *
     */
    public String createOrderSummary(String name, int quantity, boolean addWhippedCream, boolean hasChoclate) {

        String priceMessage = "Name : \t " + name;
        priceMessage += "\nAdd WhippedCream \t" + addWhippedCream;
        priceMessage += "\n Add Choclate? \t" + hasChoclate;
        priceMessage += "\nquantity \t" + quantity;
        priceMessage += "\nTotal: \t$";
        priceMessage += "\nThank you" + name + " For Supporting us";
        return priceMessage;

    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);

    }

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);


    }


    public void display(int quantity) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
   }

}

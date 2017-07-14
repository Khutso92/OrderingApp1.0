package com.example.khutsomatlala.orderingapp10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Price extends Activity implements AdapterView.OnItemSelectedListener {


    String StrengtType, MilkType, NumberSugar, CoffeBlendType;
 boolean emailStart =false;

    public   int StrengtPrice, MilkPrice, SugarPrice, CoffeBlendPrice;

    boolean viewQuotation = true,View = false;


    public static String i  = "";


    int quantity = 0;

    String[] CoffeeBlend = {"Regular", "Single Origin", "Decaf"};

    String[] Milk = {"None", "Skim", "Regular"};

    String[] Sugar = {"1", "2", "3"};


    String[] Strength = {"Weak", "Extra Strong", "Strong"};

    Button order_quotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        order_quotation = (Button) findViewById(R.id.order_quotation);
        Spinner spinnerCoffeeBlend = (Spinner) findViewById(R.id.spinnerCoffeeBlend);
        spinnerCoffeeBlend.setOnItemSelectedListener(this);


        Spinner spinnerMilk = (Spinner) findViewById(R.id.spinnerMilk);
        spinnerMilk.setOnItemSelectedListener(this);


        Spinner spinnerSugar = (Spinner) findViewById(R.id.spinnerSugar);
        spinnerSugar.setOnItemSelectedListener(this);


        Spinner spinnerStrength = (Spinner) findViewById(R.id.spinnerStrength);
        spinnerStrength.setOnItemSelectedListener(this);


        ArrayAdapter<String> CoffeeBlendAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CoffeeBlend);
        CoffeeBlendAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCoffeeBlend.setAdapter(CoffeeBlendAdapter);

        ArrayAdapter<String> MilkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Milk);
        MilkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMilk.setAdapter(MilkAdapter);

        ArrayAdapter<String> SuagrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Sugar);
        SuagrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSugar.setAdapter(SuagrAdapter);

        ArrayAdapter<String> StrengthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Strength);
        StrengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStrength.setAdapter(StrengthAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getSelectedItem().toString();

        emailStart = false;
        viewQuotation = true;
        order_quotation.setText("View quotation");


        //testing for the  Strength coffee
        if (item == Strength[i].toString()) {

            StrengtPrice = 2;
            StrengtType = Strength[i];

        } else if (item == Strength[i].toString()) {
            StrengtType = Strength[i];
            StrengtPrice = 3;
        } else {
            StrengtType = Strength[i];
            StrengtPrice = 5;
        }


        //testing for the  milk type
        if (item == Milk[i].toString()) {

            MilkPrice = 6;
            MilkType = Milk[i];

        } else if (item == Milk[i].toString()) {
            MilkType = Milk[i];
            MilkPrice = 8;
        } else {
            MilkType = Milk[i];
            MilkPrice = 10;
        }


        //testing for the Number of Sugar
        if (item == Sugar[i].toString()) {
            SugarPrice = 1;
            NumberSugar =  Sugar[i];

        } else if (item == Sugar[i].toString()) {
            NumberSugar = Sugar[i];
            SugarPrice = 2;
        } else {
            NumberSugar = Sugar[i];
            SugarPrice = 3;
        }


        // //testing for the Coffe Blend type
        if (item == Sugar[i].toString()) {
            CoffeBlendPrice = 2;
            CoffeBlendType = CoffeeBlend[i];

        } else if (item == Sugar[i].toString()) {
            CoffeBlendType = CoffeeBlend[i];
            CoffeBlendPrice = 4;
        } else {
            CoffeBlendType = CoffeeBlend[i];
            CoffeBlendPrice = 6;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void calculate(View view) {
        //Toast.makeText(Price.this, "coffeeBlend - "+i, Toast.LENGTH_SHORT).show();

        if (emailStart == false) {
            i = createOrderSummarey(quantity);



        } else {
            email(i);



        }
    }

    //Adiing coffees
    public void increment(View view) {
        emailStart = false;

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setTextColor(Color.GRAY);
        viewQuotation = false;
        order_quotation.setText("View quotation");
        if (quantity == 100) {

            Toast.makeText(this, "the quantity cant be more than 5", Toast.LENGTH_SHORT).show();
            return; //leaving the method NB

        }
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the  plus minus is clicked
     */
    public void decrement(View view) {
        emailStart = false;

        viewQuotation = true;
        order_quotation.setText("View quotation");

        quantity -= 1;

        if (quantity >= 0) {
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "the quantity cant be less than zero", Toast.LENGTH_SHORT).show();
            quantity = 0;
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public String createOrderSummarey(int number) {


        //Total Price              base Price
        int total = number * (StrengtPrice + MilkPrice + SugarPrice + CoffeBlendPrice);//could have wrote a calculate method

        String infor =
                "\n Number of cups : " + quantity +
                        "\n Type of Milk:" + MilkType +
                        "\n\n Number of Sugar spoons : " + NumberSugar +
                        "\n\n Type of blend : " + CoffeBlendType +
                        "\n\n Strength of coffee : " + StrengtType +
                        "\n\n " + "Total : R" + total;

        if (viewQuotation && quantity == 0) {


            Toast.makeText(this, "Add Number of Cups ", Toast.LENGTH_SHORT).show();
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setTextColor(Color.RED);

        }

        else {

            solution(infor);
           // viewQuotation = false;
            order_quotation.setText("ORDER");

            emailStart = true;

        }

        return infor;
    }


    public void solution(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("quotation for coffee");
        builder.setMessage(msg);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }



    public void email( String infor){

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); //email apps will handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, " Coffee Order for Khutso ");
            intent.putExtra(Intent.EXTRA_TEXT, infor);
            startActivity(intent);

    }


    @Override
    protected void onPause() {
        super.onPause();

        emailStart = false;

    }
}

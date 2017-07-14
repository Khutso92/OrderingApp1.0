package com.example.khutsomatlala.orderingapp10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {




    //Coffee  quotes

 int i ;

    String[] quote_1 = { "Morning call: \n" +
            "Coffee please because its too early for wine", "Men are like coffee: \n" +
            "They're strong ,warm and keep you up all night", "Depresso : \n" +
            "The feeling you get when you've out of coffee."};

    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void GoToPrice (View view )
    {

        Intent intent = new Intent(this,Price.class);
        startActivity(intent);
    }

    public void shareApp(View view){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_TEXT,"Don't miss out on the best coffee ordering app of the 21th century,\n WhatsApp Khutso(071)-227 0677 for the android app ");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }




    //the dialog box
    public void solution(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton("press,okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    public void ViewQuotes( View view )
    {
        if(i<=2){
        solution(quote_1[i]);
        }
        else
        {
            Toast.makeText(this, "Check more quotes tomorrow ", Toast.LENGTH_SHORT).show();
        }
        i++;
    }
}

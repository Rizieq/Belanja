package com.rizieq.belanja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Jumlah pesanan 'default' adalah kosong
    int quantiy = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void jumlahPesanan(int jumlahPesanan){
        TextView quantiyTextView = (TextView) findViewById(R.id.quantiy_text_view);
        quantiyTextView.setText(""+jumlahPesanan);
    }
    private void hasilPesanan(String message){
        TextView showOrderSummary = (TextView) findViewById(R.id.showOrderSummary);
        showOrderSummary.setText(message);
    }

    public void tambahPesanan(View view){
        if (quantiy == 100){
            return;
        }
        quantiy = quantiy + 1;
        jumlahPesanan(quantiy);
    }
    public void kurangiPesanan(View view){
        if (quantiy == 1){
            return;
        }
        quantiy = quantiy - 1;
        jumlahPesanan(quantiy);
    }

    public void submitOrder(View view){
        EditText nameField = (EditText)findViewById(R.id.name_field);
        Editable nameEdittable = nameField.getText();
        String name = nameEdittable.toString();

        CheckBox ayamBakarCheckBox = (CheckBox) findViewById(R.id.ayam_bakar_checkbox);
        boolean tambahAyamBakar = ayamBakarCheckBox.isChecked();

        CheckBox telorBebekCheckBox = (CheckBox) findViewById(R.id.telor_bebek_checkbox);
        boolean tambahTelorBebek = telorBebekCheckBox.isChecked();

        int price = hitunganHarga(tambahAyamBakar, tambahTelorBebek);

        String message = createOrderSummary(name, price, tambahAyamBakar, tambahTelorBebek);
        hasilPesanan(message);



    }

    private String createOrderSummary(String name, int price, boolean tambahAyamBakar, boolean tambahTelorBebek) {

        String priceMessage = "Nama : " + name + "\n";
        priceMessage = priceMessage + "Jumlah : " + quantiy + "\n";
        priceMessage = priceMessage + "Ayam Goreng "+ tambahAyamBakar + "\n";
        priceMessage = priceMessage + "Telor Bebek "+ tambahTelorBebek + "\n";
        priceMessage = priceMessage + "Total : Rp" + price + "\n";
        priceMessage = priceMessage + "Terimakasih Telah Memilih Kepercayaan Ke[ada Kami ";
        return  priceMessage;
    }

    private int hitunganHarga(boolean tambahAyamBakar, boolean tambahTelorBebek) {

        int basePrice = 3000;

        if (tambahAyamBakar){
            basePrice = basePrice + 100000;
        }

        if (tambahTelorBebek){
            basePrice = basePrice + 4000;
        }
        return quantiy * basePrice;
    }
}

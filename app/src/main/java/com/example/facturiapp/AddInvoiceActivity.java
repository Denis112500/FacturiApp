package com.example.facturiapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddInvoiceActivity extends AppCompatActivity {

    // Declarare elemente interfata
    private TextInputEditText etClientName, etAmount, etDate, etDescription;
    private Switch switchIsPaid;
    private Button btnSaveInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Legare cod de design xml
        setContentView(R.layout.activity_add_invoice);

        // Gasire element dupa id din xml
        etClientName = findViewById(R.id.etClientName);
        etAmount = findViewById(R.id.etAmount);
        etDate = findViewById(R.id.etDate);
        etDescription = findViewById(R.id.etDescription);
        switchIsPaid = findViewById(R.id.switchIsPaid);
        btnSaveInvoice = findViewById(R.id.btnSaveInvoice);

        btnSaveInvoice.setOnClickListener(v -> {
            // Citire text
            String clientName = etClientName.getText().toString().trim();
            String amount = etAmount.getText().toString().trim();

            // Validare de nume
            if (clientName.isEmpty() || amount.isEmpty()) {
                Toast.makeText(AddInvoiceActivity.this, "Completează numele și suma!", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Firebase / server
            Toast.makeText(AddInvoiceActivity.this, "Factură adăugată: " + clientName, Toast.LENGTH_LONG).show();

            finish();
        });
    }
}
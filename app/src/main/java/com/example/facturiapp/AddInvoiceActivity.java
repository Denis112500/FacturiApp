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
            String date = etDate.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            boolean isPaid = switchIsPaid.isChecked();

            // Validare de nume si suma
            if (clientName.isEmpty() || amount.isEmpty()) {
                Toast.makeText(AddInvoiceActivity.this, "Completeaza numele si suma ", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                try {
                    String serverIP = "10.0.2.2";
                    int serverPort = 12345;

                    java.net.Socket socket = new java.net.Socket(serverIP, serverPort);
                    java.io.PrintWriter out = new java.io.PrintWriter(socket.getOutputStream(), true);
                    java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));

                    // Protocolul propriu
                    // Format: ADD_INVOICE|Nume|Suma|Data|Descriere|Status
                    String mesajComanda = "ADD_INVOICE|" + clientName + "|" + amount + "|" + date + "|" + description + "|" + isPaid;

                    // Request catre server
                    out.println(mesajComanda);

                    // Raspuns server
                    String raspunsServer = in.readLine();

                    System.out.println("Raspuns Server: " + raspunsServer);

                    socket.close();

                } catch (Exception e) {
                    throw new RuntimeException("Conexiune la server esuata");
                }
            }).start();

            //Trimite catre MainActivity
            android.content.Intent returnIntent = new android.content.Intent();
            returnIntent.putExtra("CLIENT_NAME", clientName);
            returnIntent.putExtra("AMOUNT", Double.parseDouble(amount));
            returnIntent.putExtra("DATE", date);
            returnIntent.putExtra("DESCRIPTION", description);
            returnIntent.putExtra("IS_PAID", isPaid);

            setResult(RESULT_OK, returnIntent);

            finish();
        });
    }
}
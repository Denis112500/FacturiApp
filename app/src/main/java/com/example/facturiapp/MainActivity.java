package com.example.facturiapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.facturiapp.adapters.InvoiceAdapter;
import com.example.facturiapp.models.Factura;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewInvoices;
    private InvoiceAdapter invoiceAdapter;
    private List<Factura> facturaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // legare de fisierul xml

        // Gasire element dupa ID
        recyclerViewInvoices = findViewById(R.id.recyclerViewInvoices);
        FloatingActionButton fabAddInvoice = findViewById(R.id.fabAddInvoice);

        // Lista verticala
        recyclerViewInvoices.setLayoutManager(new LinearLayoutManager(this));

        // Test
        facturaList = new ArrayList<>();
        final androidx.activity.result.ActivityResultLauncher<android.content.Intent> addInvoiceLauncher =
                registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        android.content.Intent data = result.getData();
                        String clientName = data.getStringExtra("CLIENT_NAME");
                        double amount = data.getDoubleExtra("AMOUNT", 0.0);
                        String date = data.getStringExtra("DATE");
                        String description = data.getStringExtra("DESCRIPTION");
                        boolean isPaid = data.getBooleanExtra("IS_PAID", false);

                        // Creare factura nou
                        String newId = String.valueOf(System.currentTimeMillis());
                        Factura facturaNoua = new Factura(newId, clientName, amount, date, description, isPaid);

                        // Redesenare la adaugare
                        facturaList.add(facturaNoua);
                        invoiceAdapter.notifyItemInserted(facturaList.size() - 1);
                    }
                });

        invoiceAdapter = new InvoiceAdapter(facturaList);
        recyclerViewInvoices.setAdapter(invoiceAdapter);

        fabAddInvoice.setOnClickListener(v -> {
            // Deschidem ecranul nou
            Intent intent = new Intent(MainActivity.this, AddInvoiceActivity.class);
            startActivity(intent);
        });

        fabAddInvoice.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(MainActivity.this, AddInvoiceActivity.class);
            addInvoiceLauncher.launch(intent);
        });
    }
}
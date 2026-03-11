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
        facturaList.add(new Factura("1", "SC Ardealul SRL", 1500.50, "10-10-2023", "Servicii IT", true));
        facturaList.add(new Factura("2", "Ion Popescu", 350.00, "15-10-2023", "Reparatie PC", false));
        facturaList.add(new Factura("3", "Tech Solutions", 4200.00, "20-10-2023", "Achizitie Servere", true));
        facturaList.add(new Factura("1", "SC Ardealul SRL", 1500.50, "10-10-2023", "Servicii IT", true));
        facturaList.add(new Factura("2", "Ion Popescu", 350.00, "15-10-2023", "Reparatie PC", false));
        facturaList.add(new Factura("3", "Tech Solutions", 4200.00, "20-10-2023", "Achizitie Servere", true));
        facturaList.add(new Factura("1", "SC Ardealul SRL", 1500.50, "10-10-2023", "Servicii IT", true));
        facturaList.add(new Factura("2", "Ion Popescu", 350.00, "15-10-2023", "Reparatie PC", false));
        facturaList.add(new Factura("3", "Tech Solutions", 4200.00, "20-10-2023", "Achizitie Servere", true));
        facturaList.add(new Factura("1", "SC Ardealul SRL", 1500.50, "10-10-2023", "Servicii IT", true));
        facturaList.add(new Factura("2", "Ion Popescu", 350.00, "15-10-2023", "Reparatie PC", false));
        facturaList.add(new Factura("3", "Tech Solutions", 4200.00, "20-10-2023", "Achizitie Servere", true));
        facturaList.add(new Factura("1", "SC Ardealul SRL", 1500.50, "10-10-2023", "Servicii IT", true));
        facturaList.add(new Factura("2", "Ion Popescu", 350.00, "15-10-2023", "Reparatie PC", false));
        facturaList.add(new Factura("3", "Tech Solutions", 4200.00, "20-10-2023", "Achizitie Servere", true));

        invoiceAdapter = new InvoiceAdapter(facturaList);
        recyclerViewInvoices.setAdapter(invoiceAdapter);

        fabAddInvoice.setOnClickListener(v -> {
            // Deschidem ecranul nou
            Intent intent = new Intent(MainActivity.this, AddInvoiceActivity.class);
            startActivity(intent);
        });

        fabAddInvoice.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Aici vom deschide ecranul de Adaugare", Toast.LENGTH_SHORT).show();
        });
    }
}
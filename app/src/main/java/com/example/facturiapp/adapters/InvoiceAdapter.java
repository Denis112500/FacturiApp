package com.example.facturiapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.facturiapp.R;
import com.example.facturiapp.models.Factura;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private List<Factura> invoiceList;

    public InvoiceAdapter(List<Factura> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Factura factura = invoiceList.get(position);

        holder.tvClientName.setText(factura.getClientName());
        holder.tvAmount.setText(factura.getAmount() + " RON");
        holder.tvDate.setText(factura.getDate());

        if (factura.isPaid()) {
            holder.tvStatus.setText("Platit");
            holder.tvStatus.setTextColor(Color.parseColor("#388E3C"));
        } else {
            holder.tvStatus.setText("Neplatit");
            holder.tvStatus.setTextColor(Color.parseColor("#D32F2F"));
        }
    }

    @Override
    public int getItemCount() {
        return invoiceList.size(); // numar de elemente in lista
    }

    // Legatura cu item_invoice.xml
    static class InvoiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvClientName, tvAmount, tvDate, tvStatus;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
package com.jenisk.roomcrudoprations.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jenisk.roomcrudoprations.MyApplication;
import com.jenisk.roomcrudoprations.R;
import com.jenisk.roomcrudoprations.ShowOnMapActivity;
import com.jenisk.roomcrudoprations.UpdateDirectoryActivity;
import com.jenisk.roomcrudoprations.database.AppDatabase;
import com.jenisk.roomcrudoprations.model.DirectoryData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenis Kasundra on 17/05/2018.
 */
public class DirectoryListAdapter extends RecyclerView.Adapter<DirectoryListAdapter.ViewHolder>  {
    private List<DirectoryData> directoryModelsArrayList;
    private  Context context;

    public DirectoryListAdapter(Context context, List<DirectoryData> directoryModelsArrayList) {

        this.context=context;
        this.directoryModelsArrayList=directoryModelsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       final DirectoryData directoryData = directoryModelsArrayList.get(position);
        holder.tvName.setText(directoryData.getName());
        holder.tvNumber.setText(directoryData.getNumber());
        holder.tvAddress.setText(directoryData.getAddress()+" ("+directoryData.getLatitude()+","+directoryData.getLongitude()+")");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowOnMapActivity.class);
                intent.putExtra("username",directoryData.getName());
                intent.putExtra("latitude",directoryData.getLatitude());
                intent.putExtra("longitude",directoryData.getLongitude());
                context.startActivity(intent);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUpdateDirectoryActivity(directoryData.getId());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDirectory(directoryData,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return directoryModelsArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvName, tvNumber,tvAddress;
        public Button btnEdit,btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvNameRaw);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumberRaw);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddressRaw);
            btnEdit=(Button)itemView.findViewById(R.id.btnEditRaw);
            btnDelete=(Button)itemView.findViewById(R.id.btnDeleteRaw);
        }
    }

    private void deleteDirectory(final DirectoryData directoryData,final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(
                context, R.style.AppAlertDialog);
        dialog.setTitle("Delete");
        dialog.setMessage("Are you sure you want to delete?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyApplication.getInstance().getDB().directoryDao().delete(directoryData);
                directoryModelsArrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Record deleted successfully.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    private void loadUpdateDirectoryActivity(int id)
    {
        Intent intent=new Intent(context, UpdateDirectoryActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
}
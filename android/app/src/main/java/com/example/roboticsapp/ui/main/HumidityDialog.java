package com.example.roboticsapp.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.roboticsapp.R;

public class HumidityDialog extends AppCompatDialogFragment {

    private EditText editHumidity;
    private HumidityDialogListener listener;

    public HumidityDialog(HumidityDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_humidity_dialog, null);

        builder.setView(view)
                .setTitle("Update humidity threshold")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newHumidityThreshold = editHumidity.getText().toString();
                        listener.applyText(newHumidityThreshold);
                    }
                });


        this.editHumidity = view.findViewById(R.id.editHumidity);
        return builder.create();
    }

    public interface HumidityDialogListener {
        void applyText(String newHumidityThreshold);
    }
}

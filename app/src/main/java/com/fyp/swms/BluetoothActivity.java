package com.fyp.swms;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    TextView tvBTStatus, tvAvailableDevices;
    ImageView ivBTIcon;

    ListView devicesList;

    Button btnScanDevices;

    BluetoothAdapter bluetoothAdapter;

    BroadcastReceiver broadcastReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        tvBTStatus = findViewById(R.id.tvBTStatus);
        tvAvailableDevices = findViewById(R.id.tvAvailableDevices);
        ivBTIcon = findViewById(R.id.ivBTIcon);
        btnScanDevices = findViewById(R.id.btnScanDevices);
        devicesList = findViewById(R.id.devicesList);


        btnScanDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bluetoothAdapter.isEnabled()){
                    tvAvailableDevices.setText("");
                    Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                    for (BluetoothDevice device : devices){
                        tvAvailableDevices.append(device.getName()+"\n"/*+" | "+device*/);
                    }


                    /* Bellow code for Scan Devices */
                    bluetoothAdapter.startDiscovery();

                    ArrayList<String> names = new ArrayList<>();
                    ArrayList<BluetoothDevice> devicesArrayList = new ArrayList<>();

                    broadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String action = intent.getAction();
                            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                                Log.d("BLUETOOTH_DEVICES", device.getName());
                                names.add(device.getName());
                            }
                            if (names.size()!=0) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
                                devicesList.setAdapter(adapter);
                            }

                        }
                    };

                    IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(broadcastReceiver, intentFilter);




                }else {
                    Toast.makeText(BluetoothActivity.this, "Turn on Bluetooth to scan devices", Toast.LENGTH_SHORT).show();
                }
            }
        });




        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter==null){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            tvBTStatus.setText("Not Available");
            btnScanDevices.setEnabled(false);
        }

        if (bluetoothAdapter.isEnabled()){
            ivBTIcon.setImageResource(R.drawable.ic_bluetooth_on);
            tvBTStatus.setText("Turned On");
        }else {
            ivBTIcon.setImageResource(R.drawable.ic_bluetooth_off);
            tvBTStatus.setText("Turned Off");
        }




    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
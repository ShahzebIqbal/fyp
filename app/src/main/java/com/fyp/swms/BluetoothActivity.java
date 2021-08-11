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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    TextView tvBTStatus, tvAvailableDevices;
    ImageView ivBTIcon;

    ListView devicesList;

    Button btnScanDevices;

    BluetoothAdapter bluetoothAdapter;

    BroadcastReceiver broadcastReceiver;

    ArrayList<BluetoothDevice> devicesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        tvBTStatus = findViewById(R.id.tvBTStatus);
        tvAvailableDevices = findViewById(R.id.tvAvailableDevices);
        ivBTIcon = findViewById(R.id.ivBTIcon);
        btnScanDevices = findViewById(R.id.btnScanDevices);
        devicesList = findViewById(R.id.devicesList);

        devicesArrayList = new ArrayList<>();



        devicesList.setOnItemClickListener((parent, view, position, id) -> {
//            Toast.makeText(BluetoothActivity.this, devicesArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            BluetoothDevice device = devicesArrayList.get(position);

            if (device.getBondState()==BluetoothDevice.BOND_BONDED){
                Toast.makeText(BluetoothActivity.this, "'" + device.getName() + "' is Connected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(BluetoothActivity.this, "Connecting with '" + device.getName() + "'", Toast.LENGTH_SHORT).show();
                try {
                    createBond(device);
                } catch (Exception e) {
                    Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                }
            }
        });




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
                    devicesArrayList = new ArrayList<>();

                    broadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String action = intent.getAction();
                            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                                Log.d("BLUETOOTH_DEVICES", device.getName());
                                names.add(device.getName());
                                devicesArrayList.add(device);
                            }
                            if (names.size()!=0) {
                                ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<BluetoothDevice>(getApplicationContext(), android.R.layout.simple_list_item_1, devicesArrayList);
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

    public boolean createBond(BluetoothDevice btDevice) throws Exception {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
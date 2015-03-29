/*
 * Copyright (C) 2014 Disrupted Systems
 *
 * This file is part of Rumble.
 *
 * Rumble is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Rumble is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Rumble.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.disrupted.rumble.network.linklayer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.disrupted.rumble.app.RumbleApplication;
import org.disrupted.rumble.network.linklayer.LinkLayerNeighbour;

/**
 * @author Marlinski
 */
public class BluetoothNeighbour implements LinkLayerNeighbour {

    private String bluetoothMacAddress;

    public BluetoothNeighbour(BluetoothNeighbour neighbour){
        this.bluetoothMacAddress = neighbour.getLinkLayerAddress();
    }

    public BluetoothNeighbour(String macAddress){
        this.bluetoothMacAddress = macAddress;
    }

    @Override
    public String getLinkLayerAddress() {
        return bluetoothMacAddress;
    }

    @Override
    public String getLinkLayerIdentifier() {
        return BluetoothLinkLayerAdapter.LinkLayerIdentifier;
    }

    public String getBluetoothDeviceName() {
        BluetoothAdapter adapter = BluetoothUtil.getBluetoothAdapter(RumbleApplication.getContext());
        if(adapter != null) {
            BluetoothDevice remote = adapter.getRemoteDevice(this.bluetoothMacAddress);
            if(remote != null)
                return remote.getName();
            else
                return "#no name#";
        } else {
            return "#no name#";
        }
    }
}

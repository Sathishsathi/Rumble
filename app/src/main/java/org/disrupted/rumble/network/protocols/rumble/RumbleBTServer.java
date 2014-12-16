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

package org.disrupted.rumble.network.protocols.rumble;

import android.bluetooth.BluetoothSocket;

import org.disrupted.rumble.network.Neighbour;
import org.disrupted.rumble.network.NetworkCoordinator;
import org.disrupted.rumble.network.NetworkThread;
import org.disrupted.rumble.network.exceptions.RecordNotFoundException;
import org.disrupted.rumble.network.linklayer.bluetooth.BluetoothNeighbour;
import org.disrupted.rumble.network.linklayer.bluetooth.BluetoothServer;
import org.disrupted.rumble.network.linklayer.bluetooth.BluetoothServerConnection;

/**
 * @author Marlinski
 */
public class RumbleBTServer extends BluetoothServer {


    public RumbleBTServer() {
        super(RumbleBTConfiguration.RUMBLE_BT_UUID_128, RumbleBTConfiguration.RUMBLE_BT_STR, false);
    }

    @Override
    public String getNetworkThreadID() {
        return RumbleProtocol.protocolID + super.getNetworkThreadID();
    }


    @Override
    protected NetworkThread onClientConnected(BluetoothSocket mmConnectedSocket) {
        Neighbour neighbour = new BluetoothNeighbour(mmConnectedSocket.getRemoteDevice().getAddress());
        try {
            if (NetworkCoordinator.getInstance().isNeighbourConnectedWithProtocol(neighbour, RumbleProtocol.protocolID)) {
                /*
                 * We are receiving a connection from someone we are already connected to.
                 * This case happen only if both device discover at the same time.
                 * we cannot simply drop the connection because the other end would do the same
                 * and that would result in both side closing the connection
                 * To solve this issue, we drop only the lower mac address side of the connection.
                 */
                if(neighbour.getMacAddress().compareTo(localMacAddress) < 0)
                    return null;
            }
            return new RumbleBTProtocol(new BluetoothServerConnection(mmConnectedSocket));
        }catch(RecordNotFoundException first) {
            return null;
        }
    }
}
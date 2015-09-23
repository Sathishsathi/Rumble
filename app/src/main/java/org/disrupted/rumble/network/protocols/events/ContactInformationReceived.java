/*
 * Copyright (C) 2014 Disrupted Systems
 * This file is part of Rumble.
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
 * You should have received a copy of the GNU General Public License along
 * with Rumble.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.disrupted.rumble.network.protocols.events;

import org.disrupted.rumble.database.objects.Contact;
import org.disrupted.rumble.network.linklayer.LinkLayerNeighbour;
import org.disrupted.rumble.network.protocols.ProtocolChannel;

import java.util.List;

/**
 * @author Marlinski
 */
public class ContactInformationReceived {

    public Contact contact;
    public int     flags;   // see class Contact
    public ProtocolChannel channel;
    public LinkLayerNeighbour neighbour;
    public boolean authenticated;
    public long    size;
    public long    duration;

    public ContactInformationReceived(Contact contact, int flags, ProtocolChannel channel, LinkLayerNeighbour neighbour, long size, long duration) {
        this.contact = contact;
        this.flags = flags;
        this.channel = channel;
        this.neighbour = neighbour;
        this.authenticated = false;
        this.size = size;
        this.duration = duration;
    }

}

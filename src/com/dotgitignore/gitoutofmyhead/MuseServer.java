/*
 * Copyright (C) 2016 Christopher Wells <cwellsny@nycap.rr.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dotgitignore.gitoutofmyhead;

import oscP5.*;

/**
 * The <code>MuseServer</code> class handles input from the Muse device.
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class MuseServer {

    private final int port;
    private final OscP5 oscP5Server;

    /**
     * Instantiates a <code>MuseServer</code> object with the given port.
     *
     * @param p The port number of the MuseServer.
     */
    public MuseServer(int p) {
        this.port = p;
        this.oscP5Server = new OscP5(this, p);
    }

    /**
     * Handles the data given by the Muse.
     *
     * @param msg The data given by the Muse.
     */
    public void oscEvent(OscMessage msg) {
        if (msg.checkAddress("/muse/elements/jaw_clench")) {
            System.out.println("Jaw Clench: " + msg.get(0).intValue());
        }
    }
}

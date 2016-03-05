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

/**
 * Objects of the <code>DataAnalyzer</code> class analyze the input given by the
 * MuseServer and send the output as commands to the Controller.
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class DataAnalyzer {

    private final MuseServer museServer;
    private final Double[][] dataBuffer;
    private final int bufferSize;
    private final int streams;
    private int nextCol;

    private double baseThreshold;
    private double highThreshold;
    private double lowThreshold;

    /**
     * Initializes a <code>DataAnalyzer</code> with the given buffer size.
     *
     * @param b The buffer size of the DataAnalyzer.
     * @param p The port of the MuseServer.
     */
    public DataAnalyzer(int b, int p) {
        this.baseThreshold = 10;
        this.highThreshold = 20;
        this.lowThreshold = 5;
        this.streams = 4;
        this.bufferSize = b;
        this.dataBuffer = new Double[this.streams][b];
        this.nextCol = 0;
        this.museServer = new MuseServer(this, p);
    }

    /**
     * Adds the given data measurement to the given index.
     *
     * @param i The index of the data stream.
     * @param d The data measurement to be added.
     */
    public void addData(int i, double d) {
        this.dataBuffer[i][this.nextCol] = d;
        this.nextCol++;

        // Checks if the buffer is full, if so then analyze the data
        if (this.nextCol == this.bufferSize) {
            this.analyzeData();
        }
    }

    /**
     * Analyzes the data in the buffer and send the calculated command to the
     * Controller.
     */
    private void analyzeData() {
        // Get the gesture
        String currentGesture = this.getGesture();

        // Empty the buffer
        this.emptyBuffer();

        // Run the command
        // HERE RUN THE COMMAND
        System.out.println(currentGesture);
    }

    /**
     * Analyzes and returns the current gesture.
     *
     * @return The current gesture.
     */
    private String getGesture() {
        // Check for jaw clenches
        int jawClenches = 0;
        for (double measurement : this.dataBuffer[3]) {
            jawClenches += (int) Math.floor(measurement);
        }

        if (jawClenches > 1) {
            // DOUBLE JAW CLENCH
            System.out.println("DOUBLE JAW CLENCH");
            return "Double Jaw Clench";
        } else if (jawClenches == 1) {
            // SINGLE JAW CLENCH
            System.out.println("SINGLE JAW CLENCH");
            return "Single Jaw Clench";
        }

        // Get averages, maxes, and mins of Acc0 and Acc2
        double acc0Avg = 0;
        double acc0Max = 0;
        double acc0Min = 99999.9;
        for (double measure : this.dataBuffer[0]) {
            acc0Avg += measure;

            if (measure > acc0Max) {
                acc0Max = measure;
            }

            if (measure < acc0Min) {
                acc0Min = measure;
            }
        }
        acc0Avg /= this.bufferSize;

        double acc2Avg = 0;
        double acc2Max = 0;
        double acc2Min = 999999.9;
        for (double measure : this.dataBuffer[2]) {
            acc2Avg += measure;

            if (measure > acc2Max) {
                acc2Max = measure;
            }

            if (measure < acc2Min) {
                acc2Min = measure;
            }
        }
        acc2Avg /= this.bufferSize;

        // Check for shakeHorizontal
        if (acc2Avg < this.highThreshold && acc2Avg > this.lowThreshold
                && acc2Max > this.highThreshold && acc2Min < this.lowThreshold) {
            // SHAKE HORIZONTAL
            System.out.println("SHAKE HORIZONTAL");
            return "Shake Horizontal";
        }

        // Check for shakeVertical
        if (acc0Avg < this.highThreshold && acc0Avg > this.lowThreshold
                && acc0Max > this.highThreshold && acc0Min < this.lowThreshold) {
            // SHAKE VERTICAL
            System.out.println("SHAKE VERTICAL");
            return "Shake Vertical";
        }

        // Check for headUp
        if (acc0Avg > this.highThreshold) {
            // HEAD UP
            System.out.println("HEAD UP");
            return "Head Up";
        }
        
        // Check for headDown
        if (acc0Avg < this.lowThreshold) {
            // HEAD DOWN
            System.out.println("HEAD DOWN");
            return "Head Down";
        }

        // Note that no gesture was found
        return "No Gesture";
    }

    /**
     * Empties the data buffer.
     */
    private void emptyBuffer() {
        for (int i = 0; i < this.streams; i++) {
            for (int j = 0; j < this.bufferSize; j++) {
                this.dataBuffer[i][j] = null;
            }
        }

        this.nextCol = 0;
    }

}

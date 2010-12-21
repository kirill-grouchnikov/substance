/*
 * @(#)Worker.java  2.1  2005-10-16
 *
 * Copyright (c) 1998-2005 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Werner Randelshofer. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Werner Randelshofer.
 */
//package ch.randelshofer.util;
package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.util;

import java.awt.ActiveEvent;
import javax.swing.SwingUtilities;

/**
 * This is an abstract class that you subclass to
 * perform GUI-related work in a dedicated event dispatcher.
 * <p>
 * This class is similar to SwingWorker but less complex.
 * Like a SwingWorker it can run using an an internal
 * worker thread but it can also be like a Runnable object.
 *
 * @author Werner Randelshofer
 * @version 2.1 2005-10-16 Method start() added.
 * <br>2.0 2005-09-27 Revised.
 * <br>1.1.1 2001-08-24 Call finished() within finally block.
 * <br>1.1 2001-08-24 Reworked for JDK 1.3.
 * <br>1.0 1998-10-07 Created.
 */
public abstract class Worker implements Runnable {
    private Object value;  // see getValue(), setValue()
    
    /**
     * Calls #construct on the current thread and invokes
     * #finished on the AWT event dispatcher thread.
     */
    public final void run() {
        final Runnable doFinished = new Runnable() {
            public void run() { finished(getValue()); }
        };
        try {
            setValue(construct());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            SwingUtilities.invokeLater(doFinished);
        }
    }
    
    /**
     * Compute the value to be returned by the <code>get</code> method.
     */
    public abstract Object construct();
    /**
     * Called on the event dispatching thread (not on the worker thread)
     * after the <code>construct</code> method has returned.
     *
     * @param value The return value of the construct method.
     */
    public abstract void finished(Object value);
    /**
     * Get the value produced by the worker thread, or null if it
     * hasn't been constructed yet.
     */
    protected synchronized Object getValue() {
        return value;
    }
    /**
     * Set the value produced by worker thread
     */
    private synchronized void setValue(Object x) {
        value = x;
    }
    
    /**
     * Starts the Worker on an internal worker thread.
     */
    public void start() {
        new Thread(this).start();
    }
}
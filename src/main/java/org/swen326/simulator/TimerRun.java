package org.swen326.simulator;

/**
 * Author: Patrick Mills
 * Student ID: 300611130
 *
 * This interface provides the methods which are automatically executed by the Timer class every
 * frame and every second.
 */
public interface TimerRun {
    /**
     * This method is automatically executed by the timer every frame.
     */
    public void runEveryFrame(SimulatorTimer timer);

    /**
     * This method is automatically executed by the timer every
     * @param timer
     */
    public void runEverySecond(SimulatorTimer timer);
}
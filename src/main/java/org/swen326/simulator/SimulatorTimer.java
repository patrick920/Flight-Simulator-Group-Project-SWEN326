package org.swen326.simulator;

/**
 * The simulator timer will run on a separate thread and will execute a method in Simulator.java
 * 120 times per second.
 */
public class SimulatorTimer implements Runnable {
    /**
     * The number of frames per second.
     */
    private int fps;

    /**
     * The current frame where the timer is at. This will keep counting down when the timer is playing.
     */
    private int currentFrame;

    /**
     * The current second where the timer is at. This will keep counting down when the timer is playing.
     */
    private int currentSecond;

    /**
     * The state that the timer is currently in. It could be playing, paused or not active.
     */
    private TimerState timerState;

    /**
     * This interface is used to supply methods that the timer will automatically call every second
     * and every frame. GameBoardPanel currently implements it.
     */
    private TimerRun timerRun;

    /**
     * This is the thread for the timer.
     */
    private Thread timerThread;

    /**
     * The maximum time in seconds that the game will last.
     */
    private int maxTime;

    /**
     * The number of frames it takes to perform one move across the game board.
     * This will be used to redisplay the game board several times as it moves across during a move.
     * It will also be used by the Recorder module to play moves.
     */
    private int framesPerMove;

    /**
     * Get the number of frames it takes for one move across the game board.
     * @param framesPerMove Number of frames it takes for one move across the game board.
     */
    public void framesPerMove(int framesPerMove){this.framesPerMove = framesPerMove;}

    /**
     * A reference to the simulator object, which is used to control the simulation.
     */
    private final Simulator simulator;

    /**
     * If this is set to true, the timer will exit the game
     */
    private boolean exitNow = false;

    /**
     * Create a new timer object to keep track of time in the game.
     * @param simulator A reference to the Simulator class to access the aircraft simulation.
     * @param timerRun The TimerRun interface provides methods that will be executed every frame and every second.
     * @param fps Frames per second for the timer.
     * @param framesPerMove How many frames it takes for one entire move to complete.
     */
    public SimulatorTimer(Simulator simulator, TimerRun timerRun, int fps, int framesPerMove){
        this.simulator = simulator;
        timerState = TimerState.NOT_ACTIVE;
        timerThread = new Thread(this); //Create a new thread, but don't actually start it.
        this.timerRun = timerRun;
        this.framesPerMove = framesPerMove;
        this.maxTime = maxTime;
        //The timer starts at the maximum time. It will start counting down when the start()
        //method is called.
        currentFrame = maxTime * fps;
        currentSecond = maxTime;
        if(fps <= 0){
            throw new IllegalArgumentException("Error calling Timer constructor. fps (frames per second) " +
                    "must be greater than 0.");
        }
        this.fps = fps;
    }

    /**
     * Set the TimerRun variable. The TimerRun interface provides methods that will be executed
     * every frame and every second when the timer is currently playing.
     * @param timerRun TimerRun interface with methods that execute every frame and every second.
     */
    public void timerRun(TimerRun timerRun){this.timerRun = timerRun;}

    /**
     * Get the current state of the timer, if it is playing, paused or not active.
     * @return Current state of the timer.
     */
    public TimerState getTimerState() { return timerState; }

    /**
     * Get how many frames it takes to perform one move across the game board.
     * @return Number of frames it takes to perform one entire move across the game board.
     */
    public int framesPerMove(){return framesPerMove;}

    /**
     * Get the frames per second this Timer uses.
     * @return frames per second
     */
    public int fps(){return fps;}

    /**
     * Get the current frame the timer is currently at.
     * @return current frame.
     */
    public int currentFrame(){return currentFrame;}

    /**
     * Return the current second the timer is at.
     * @return current second the timer is at.
     */
    public int currentSecond(){return currentSecond;}

    /**
     * Decrease the current frame by one. Decrease the current second by one if all of the frames
     * for one second have passed.
     */
    private void decreaseTimer(){
        currentFrame--;
        if(currentFrame % fps == 0){
            currentSecond--;
        }
    }

    /**
     * Increase the current frame by one. Increase the current second by one if all of the frames
     * for one second have passed.
     */
    private void increaseTimer(){
        currentFrame++;
        if(currentFrame % fps == 0){
            currentSecond++;
        }
    }

    /**
     * Start the timer, counting down from the maximum time.
     */
    public void startTimer(){
        if(timerState != TimerState.NOT_ACTIVE){
            throw new IllegalStateException("Error. You can't start the timer unless it is in the " +
                    "NOT_ACTIVE state. To set the timer to this state, please call the reset timer method.");
        }
        exitNow = false;
        timerThread.start(); //Start running the thread. This triggers the run() method to be executed.
        timerState = TimerState.PLAYING;
    }

    /**
     * Reset the timer back to 0.
     */
    public void resetTimer(){
        //TODO: What if this should be change to "new SimulatorTimer"???
        timerThread = new Thread(); //Create a new thread to reset the timer.
        //Reset currentFrame and currentSecond back to their original values:
        currentFrame = maxTime * fps;
        currentSecond = maxTime;
        timerState = TimerState.NOT_ACTIVE;
        exitNow = false; //exitNow should be false otherwise the timer can't start running.
    }

    /**
     * Start playing the timer if it is currently paused. Note: this method CAN'T be used to start
     * the timer from 0. Use the startTimer() method instead.
     */
    public void playTimer(){
        if(timerState != TimerState.PAUSED){
            throw new IllegalStateException("Error. You can't start playing the timer unless it is currently" +
                    "paused. To start the timer from 0, use the startTimer() method instead.");
        }
        exitNow = false;
        timerThread.start(); //Start running the thread.
        timerState = TimerState.PLAYING;
    }

    /**
     * Pause the timer. Note that the timer must currently be playing in order for this to work.
     */
    public void pauseTimer(){
        if(timerState != TimerState.PLAYING){
            throw new IllegalStateException("Error. You can't start pause the timer unless it is currently" +
                    "playing.");
        }
        //Create a new thread, but do not reset currentFrame and currentSecond to 0.
        exitNow = true;
        timerThread = new Thread();
        timerState = TimerState.PAUSED;
    }

    /**
     * Called by timerThread.start(). Runs the thread. Decreases the currentFrame and currentSecond
     * in regular intervals.
     */
    @Override
    public void run() {
        if(timerState != TimerState.PLAYING){
            throw new IllegalStateException("Error. You can't start the timer if it is currently playing. " +
                    "If you want to start or play the timer, please reset it first.");
        }

        //Continue running the timer unless it is stopped or paused:
        while(true){
            //Source: https://www.youtube.com/watch?v=w-qd_mQ3s1g&ab_channel=GenuineCoder
            if(exitNow){
                break; //If the variable to exit the thread is true, stop the thread.
            }

            //if(currentFrame <= 0){
            //    //Timer has finished. Restart the game.
            //    //app.restartGame(true);
            //    System.out.println("Timer has finished.");
            //    return;
            //}

            //Increase the currentFrame, and potentially currentSecond if all frames in a second have passed:
            increaseTimer();
            if(currentFrame % fps == 0){
                System.out.println("currentSecond = " + currentSecond + " | currentFrame = " + currentFrame);
                timerRun.runEverySecond(this);
            }

            //This calls the runEveryFrame() method in the class implementing TimerRun. In GameBoardPanel,
            //which implements TimerRun, this method handles player movements.
            timerRun.runEveryFrame(this);

            try {
                Thread.sleep(1000 / fps); //Wait for an interval of time.
            } catch (InterruptedException e) {
                //Note: this will be executed by the interrupt() method.
                System.out.println("InterruptedException e = " + e);
                e.printStackTrace();
            }

        }
    }

    /**
     * The TimerState is used to store whether the timer is currently playing, it is paused,
     * or if it is not currently used (NOT_ACTIVE).
     */
    public enum TimerState{
        NOT_ACTIVE,
        PLAYING,
        PAUSED
    }
}

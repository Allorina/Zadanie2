package com.company;

public class TickTock {
    String state;

    synchronized void tick(boolean running) {
        if (!running) {
            state = "ticked";
            notify();
            return;
        }
        System.out.print("tik ");


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany");
        }

        state = "ticked";
        notify();

        try {
            while (!state.equals("tocked"))
                wait();
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany");
        }
    }

    synchronized void tock(boolean running) {
        if (!running) {
            state = "tocked";
            notify();
            return;
        }
        System.out.println("tak");

        //czeka pół sekundy
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany");
        }

        state = "tocked";
        notify();
        try {
            while (!state.equals("ticked"))
                wait();
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany");
        }
    }
}

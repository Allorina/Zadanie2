package com.company;

class MyThreadClock implements Runnable {
    Thread thread;
    TickTock ttOb;
    //tworzymy nowy wątek
    MyThreadClock(String name, TickTock tt){
        thread = new Thread(this, name);
        ttOb = tt;
    }
    //metoda wywoławcza która tworzy i uruchamia wątek
    public static MyThreadClock createAndStart(String name, TickTock tt){
        MyThreadClock myThreadClock = new MyThreadClock(name, tt);
        myThreadClock.thread.start(); //uruchamia nowy wątek
        return  myThreadClock;
    }

    public void run(){
        if(thread.getName().compareTo("tik") == 0){
            for(int i=0; i < 20; i++) ttOb.tick(true);
            ttOb.tick(false);
        } else {
            for(int i=0; i < 20; i++) ttOb.tock(true);
            ttOb.tock(false); }
    }
}
public class Main {

    public static void main(String[] args) {
        TickTock tt = new TickTock();
        MyThreadClock myThreadClock1 = MyThreadClock.createAndStart("tik", tt);
        MyThreadClock myThreadClock2 = MyThreadClock.createAndStart("tak", tt);
        try{
            myThreadClock1.thread.join();
            myThreadClock2.thread.join();
        } catch (InterruptedException e){
            System.out.println("Wątek główny został przerwany");
        }
    }
}

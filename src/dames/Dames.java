/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dames;
import java.util.concurrent.*;
/**
 *
 * @author IAZERTYUIOPI
 */
public class Dames {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
           
        //PlateauJeu p = new PlateauJeu(42, true);
        //p.Go();
        
        //Init exception handler used by the threads
        Thread.UncaughtExceptionHandler eHandler = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable ex) {
            th.interrupt();
        }
        };
        
        //init threadPool
        ExecutorService threadPool = Executors.newFixedThreadPool(4);


        for(int i = 300; i < 400; i++){
            
            Thread currentThread = new Thread(new PlateauJeu(i, true));
            currentThread.setUncaughtExceptionHandler(eHandler);
            threadPool.submit(currentThread);
        }

        threadPool.shutdown();
         
        }
        
        
    }
    


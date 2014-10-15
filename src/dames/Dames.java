/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dames;
import java.util.concurrent.*;
import java.util.Scanner;
/**
 *
 * @author IAZERTYUIOPI
 */
public class Dames {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
           
        Scanner s = new Scanner(System.in);
        System.out.println("Start index :");
        int startIndex = s.nextInt();
        System.out.println("End index :");
        int endIndex = s.nextInt();
        System.out.println("Display solutions graphically (0/1)?");
        boolean displayPlateau = s.nextInt()==1;
        
        //PlateauJeu p = new PlateauJeu(420, true);
        //p.Go();
        
        //Init exception handler used by the threads
        Thread.UncaughtExceptionHandler eHandler = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable ex) {
            th.interrupt();
        }
        };
        
        //init threadPool
        ExecutorService threadPool = Executors.newFixedThreadPool(4);


        for(int i = startIndex; i < endIndex; i++){
            
            Thread currentThread = new Thread(new PlateauJeu(i, displayPlateau));
            currentThread.setUncaughtExceptionHandler(eHandler);
            threadPool.submit(currentThread);
        }

        threadPool.shutdown();
         
        }
        
        
    }
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dames;
import java.util.ArrayList;
/**
 *
 * @author IAZERTYUIOPI
 */
public class Dames {
    
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
           
        ArrayList<Integer> successful = new ArrayList<>(3);
        
        //Thread t = new Thread(new PlateauJeu(8, true));
        //t.start();
        for(int i = 50 ; i < 54 ; i+=4)
        {
            Thread[] threadtab = new Thread[4];
            
            for(int j=0; j<4; j++){
                
            threadtab[j] = new Thread(new PlateauJeu(i+j, true));
            threadtab[j].start();
            
            }
            
            Thread.sleep(10000);
            
            for (int j=0; j<4; j++) {
                if(threadtab[j].isAlive()){
                    threadtab[j].interrupt();
                } 
                else{
                    successful.add(i+j);
                }
            }
            
            
        }
        
        System.out.println(successful.toString());
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dames;

/**
 *
 * @author IAZERTYUIOPI
 */
public class PlateauJeu implements Runnable {
    
    private int[] tableau;
    private int npermutations;
    private boolean displayPlateau;
    /**
     *
     */
    public void run() 
    {
       this.Go();
    }
    
    public PlateauJeu(int taille, boolean isPlateauVisible)
    {
        displayPlateau = isPlateauVisible;
        tableau = new int[taille];
        npermutations = 0;
        for (int i = 0;i<taille;i++){
              tableau[i] = i;
        }
        
    }
    
    public int[] getTableau(){
      return tableau;  
    }
    
    public int getNpermutations() {
        return npermutations;
    }
    
    public void permuter(int[] t,int i, int j)
    {
       int swap = t[i];
       t[i] = t[j];
       t[j] = swap;
       
    }
    
    public int evaluatePositionIfPermutait(int i, int j)
    {
       int[] tmpTab = tableau.clone();
       permuter(tmpTab, i, j);
       return evaluatePosition(tmpTab);
    }
    
    public String toString() {
    
        String output = "";
        for (int i = 0;i<tableau.length;i++){
            for(int j = 0;j<tableau.length;j++) {
                output+= tableau[i]==j? "D":".";
                output+=" ";
            } 
            output+="\n";
        }
        output+="Nombre de permutations : " + npermutations + "\n";
        
        return output;
    }
    
    public int evaluatePosition(int[] t) {
        
        int score=0;
        
        for (int i = 0;i<t.length;i++){
            for (int j = 0;j<t.length;j++){
                if( Math.abs(t[i]-t[j])==Math.abs(i-j) && i!=j)
                {
                    score-=10;
                }
            }
        }
        return score;
    }

    public void Go() {
                
        while (evaluatePosition(tableau)!=0)
        {
            if(!choisirLaMeilleurePermutation()){
                System.out.println("N : "+tableau.length+" FAILURE");
                throw new RuntimeException();}
        }

        System.out.println("N : " + tableau.length+" SUCCESS");
        if(displayPlateau){System.out.println(toString());}
        throw new RuntimeException();
    }
    
    public boolean choisirLaMeilleurePermutation() {
  
    int scoreActuel = evaluatePosition(tableau);
        
    for(int i = 0; i<tableau.length;i++){
        for(int j = 0; j<tableau.length;j++){
            if(i!=j && evaluatePositionIfPermutait(i, j) > scoreActuel) {
                permuter(tableau,i,j);
                npermutations++;
                return true;
            }  
        }   
    }
    return false;
    }
}

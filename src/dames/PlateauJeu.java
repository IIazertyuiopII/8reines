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
    
    /**
     *
     */
    public void run() 
    {
       this.Go();
    }
    
    public PlateauJeu(int taille, boolean isRandom)
    {
        tableau = new int[taille];

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
        output+="\n";
        //output+="Position score : "+evaluatePosition(tableau)+", N = "+tableau.length+"";
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
        
        //System.out.print(toString());
        
        while (evaluatePosition(tableau)!=0)
        {
            if(!choisirLaMeilleurePermutation()){throw new RuntimeException();};
            //System.out.println(toString());
        }

        System.out.println(toString());
        System.out.println("N : " + tableau.length);
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

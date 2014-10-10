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
        output+="Position score : "+evaluatePosition(tableau)+"\n";
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
        int compteur = 0;
        
        while (evaluatePosition(tableau)!=0)
        {
            choisirLaMeilleurePermutation();
            compteur++;
            //System.out.println(toString());
        }

        //System.out.println(toString());
        //System.out.println("Nombre de permutations :" + compteur);
    }
    
    public void choisirLaMeilleurePermutation() {

    int indiceLigneMeilleurePermutation = 0;
    int indiceColonneMeilleurePermutation = 0;  
    int scoreMeilleurePermutation = -100000;
    int scorePermutationCourante;
        
    for(int i = 0; i<tableau.length;i++){
        for(int j = 0; j<tableau.length;j++){
            scorePermutationCourante = evaluatePositionIfPermutait(i, j);
            if(i!=j && scorePermutationCourante > scoreMeilleurePermutation) {
                indiceLigneMeilleurePermutation = i;
                indiceColonneMeilleurePermutation = j;
                scoreMeilleurePermutation = scorePermutationCourante;
            }
            
        }   
    }
      
        permuter(tableau,indiceLigneMeilleurePermutation,indiceColonneMeilleurePermutation);
    
    }
}

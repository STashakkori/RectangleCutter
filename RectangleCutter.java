/*
   author sinat
 */
public class RectangleCutter{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
         int[] lengthwiseCutSizes = new int[]{2,4,6,8,10};
         int[] widthwiseCutSizes = new int[]{2,8,10,12,16};
         int[] cutPrices = new int[]{40,320,600,920,160};
         
         RectangleCutter testCutter = new RectangleCutter();
         testCutter.cutSheet(lengthwiseCutSizes,  widthwiseCutSizes, cutPrices,lengthwiseCutSizes.length,
                              widthwiseCutSizes.length);
    }
    
    public int calcMax(int a, int b){
        return a > b ? a : b;
    }
    
    public int calcMin(int a, int b){
        return a < b ? a : b;
    }
    
    public int calcSum(int a, int b){
        return a + b;
    }
    
    /**
     *
     * @param lengthSizes
     * @param widthSizes
     * @param prices
     * @param height
     * @param width
     */
    public void cutSheet(int[] lengthSizes , int[]  widthSizes, int[] prices, int length, int width){
        int maxProfit = 0;
        int[][] profitTable = new int[length][width];
        
        //build profitTable
        for(int h = 0; h < length; h++){
            for(int i = 0; i < length; i++){
                for(int j = 0; j < width; j++){
                    if(lengthSizes[h] <= i &&  widthSizes[h] <= j && prices[h] > profitTable[i][j]){
                        profitTable[i][j] = prices[h];
                    }
                }
            }
        }
        
        for(int h = 0; h < length; h++){
            for(int i = 0; i < width; i++){
                for(int j = 0; j < Math.floor(h/2); j++){
                    int sumOfCurrentAndPrevious = calcSum(profitTable[j][i], profitTable[h-1][i]);
                    // if the current profit is better than the last recorded profit,
                    // then set the last recorded profit to our current profit.
                    if(sumOfCurrentAndPrevious > profitTable[h][j]){
                        profitTable[h][i] = sumOfCurrentAndPrevious;
                    }    
                }
                for(int g = 0; g < Math.floor(i/2); g++){
                    int sumOfCurrentAndPrevious = calcSum(profitTable[h][g],profitTable[i][i-g]);
                    if(sumOfCurrentAndPrevious > profitTable[h][i]){
                        profitTable[h][i] = sumOfCurrentAndPrevious;
                    } 
                }    
            }
        }
        
        // Display Outputs
        maxProfit = profitTable[profitTable.length-1][profitTable.length-1];
        printProfitTable(profitTable);
        System.out.println("Max Profit: " + maxProfit);
    }
    
    public void printProfitTable(int[][] profitTable){
        for(int i =0; i<profitTable.length; i++){
            for(int j = 0; j<profitTable.length; j++){
                if(i > 0)
                    System.out.print(profitTable[i][j] + " ");
                else
                    System.out.print(profitTable[i][j] + "   ");
            }
            System.out.println("\n");
        }
        System.out.println("------------------------");
    }
}

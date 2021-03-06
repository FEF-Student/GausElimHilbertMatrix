
/**
 * Write a description of class projectGE here.
 *
 *Math 143M course project, Numerical analysis.
 *
 *Exercise description: 
 *
 *Consider three systems of equations defined by:
 *H x = b ,  n = size of H. We will take n = 11,12
 *and 13, where b is a  vector chosen in such a way 
 *that the exact solution of our system is [1  1  1  1  .... 1]. 
 *
 * @author: Felix Estay-Foix
 * @version: Due on 10/11/2020 by midnight
 * @updated: 12/26/2020 cleaned up the code a bit for github submission. Coding still needs more work.
 *                       
 */

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.lang.Object;
import java.lang.*;



public class projectGE
{

    //public static float n;
    //Let H be the Hilbert Matrix
    public static float[][] setH;
    public static float[][] setHX;
    public static float[][] setX;
    public static float[][] augM;
    public static float[][] mLastColumn;
    public static float[][] rrefSolution;
    public static float[][] diffSolution;
    public static float nV;

    public static int clicker;
    public static int clickerSecond;
    //public static int n;
    /**
     * Method to build a hilbert matrix
     * 
     * @param n, variable value to determine the size of a hilbert n x n squre matrix
     */
    static void matrix(int n)
    {
        float H[][] = new float[n][n];

        for(int i = 0; i <n; i++)
        {
            for(int j = 0; j<n;j++)
            {
                H[i][j] = (float)1.0 /((i + 1) + (j + 1) - (float)1.0); 
            }
        }
        for(int i = 0; i <n; i++)
        {
            for(int j = 0; j<n;j++)
            {
                System.out.print(H[i][j] + " \t");  
                //System.out.println();  
            }
            System.out.println();
        }       
        setH = H;
    }

    static void matrixB(int n)
    {
        float B[][] = new float[n][n];
        for(int row = 0; row <n; row++)
        {
            for(int column = 0; column <n ;column++)
            {
                B[row][column] = (float) 1; 
            }
        }
        for(int i = 0; i <n; i++)
        {
            // for(int j = 0; j<n;j++)
            //  {
            System.out.print(B[i][1] + " \t");  
            //System.out.println();  
            // }
            System.out.println();
        }

        setX = B;
    }

    
    static void matrixX0(int n)
    {

    }

    /**
     * Method to mutiply two matrices
     * 
     * @ param: matrixOne, matrixTwo, r1, r2, c1, c2
     * 
     */
    
    public static float[][] multiplyMatrices(float[][] matrixOne, float[][] matrixTwo, int r1, int r2, int c1, int c2)
    {

        float HX[][] = new float[r1][c2];

        for(int i=0;i<r1;i++)
        {    
            for(int j=0;j<c2;j++)
            {    
                for(int k=0;k<c1;k++)      
                {      
                    HX[i][j]+=matrixOne[i][k]*matrixTwo[k][j];      
                }//end of k loop 

                // System.out.print(HX[i][j]+" ");  //printing matrix element  
            }//end of j loop  

            System.out.println();//new line    
        } 
        setHX = HX;
        return HX;
    }

    /**
     * Method to perform gaussian elimination
     * 
     * @param: mat, take in a matrix to perform gaussian elimination with pivoting.
     */
    
    public static float[][] gausE(float[][] mat)
    {
        float[][] rref = new float[mat.length][mat[0].length];

        /* Copy matrix */
        for (int r = 0; r < rref.length; ++r)
        {
            for (int c = 0; c < rref[r].length; ++c)
            {
                rref[r][c] = augM[r][c];
            }
        }
        for (int p = 0; p < rref.length; ++p)
        {
            // work with the pivots here!
            double pv = rref[p][p];
            if (pv != 0)
            {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < rref[p].length; ++i)
                {
                    rref[p][i] *= pvInv;
                    clicker ++;
                }

            }

            // make everything below the pivot to 0, this should work finally!
            for (int r = 0; r < rref.length; ++r)
            {
                if (r != p)
                {
                    double f = rref[r][p];
                    for (int i = 0; i < rref[r].length; ++i)
                    {
                        rref[r][i] -= f * rref[p][i];
                        clickerSecond++;
                    }
                }

            }
        }
        rrefSolution = rref;
        return rref;
    } 

    public static void showProduct(float[][] product)
    {
        System.out.println("Product results for matrix H and x");
        for(float[] row:product)
        {
            for(float column: row)
            {
                System.out.print(column + " ");
            }
            System.out.println();
        }

    }

    public static void printGaus(float[][] gaus)
    {
        //System.out.println("Product results for matrix H and x");
        for(float[] row:gaus)
        {
            for(float column: row)
            {
                //String.format("%8.4f", column);
                System.out.print(column + " ");
                //System.out.printf("%8.4f", column);
            }
            System.out.println();
        }

    }

    public static void printGausShort(float[][] gaus)
    {
        //System.out.println("Product results for matrix H and x");
        for(float[] row:gaus)
        {
            for(float column: row)
            {
                //String.format("%8.4f", column);
                System.out.format("%.4f%n",column );
                //System.out.printf("%8.4f", column);
            }
            System.out.println();
        }

    }

    static float eucledianNorm(float mat[][])  
    {
        float sumSq = 0;  
        for (int i = 0; i < mat.length; i++)  
        {  
            for (int j = 0; j < 1; j++)  
            {  
                sumSq += (float)Math.pow(mat[i][j], 2);  
            }  
        }  

        // Return the square root of  
        // the sum of squares  
        float res = (float)Math.sqrt(sumSq);  
        return res;  
    }

    static float findMax(float mat[][])
    {
        float maxNum = mat[0][0];
        float minNum = mat[0][0];
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[i].length; j++)
            {
                if(maxNum < mat[i][j])
                {
                    maxNum = Math.abs(mat[i][j]);
                }
                else if(minNum > mat[i][j])
                {
                    minNum = Math.abs(mat[i][j]);
                }
            }
        }
        return maxNum;
    }

    public static void error(float[][] m, float [][]m1)
    {
        //subtract the roginal 1s matrix solution with the solution
        // setX - S

        float[][] diff= new float[m.length][m[0].length];
        for (int i =0 ; i < nV ; i++ )
        {
            for ( int j = 0 ; j < 1 ; j++ )
            {
                //for(int k = 0; k<1; k++)
                diff[i][j] = m[i][j] - m1[i][j];  
            }
        }
        diffSolution = diff;
    }

    public static void main(String[] args) 
    {

        //input();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to MATH 143M, Project One, Gaussian Eliminination assignment. ");
        System.out.println("Let  H  be our Hilbert matrix");
        System.out.println("Please enter the dimension for your n x n Hilbert matrix");
        System.out.println("");
        int n = scan.nextInt();
        nV = (float)n;
        //System.out.println("You've decided over a" + n + "x" + n +" sized Hilbert matrix");
        System.out.println("Here's is the given Hilbert Matrix....");
        matrix(n);

        //let m1 be the Hilbert matrix
        //float [][] m1 = matrix(n); 

        System.out.println();
        System.out.println("for n = " + n+ ", the exact X matrix solution is " );
        System.out.println();
        matrixB(n);
        System.out.println("computed solution = Transpose of  ");
        System.out.println("" );
        System.out.println("The following is the given " + n + " by " + n + " matrix times matrix X.");

        
        float matrixOne [][]= setH;
        float matrixTwo [][]= setX;
        int r1, c1, r2;
        r1 = n;
        c1 = n;
        r2 = n;
        int c2 =1;
        float product [][]= multiplyMatrices(matrixOne, matrixTwo, r1, c1, r2, c2);

        //float m[][] = Arrays.copyOf(setH, setH.length + setHX.length);
        // System.arraycopy(setHX, 0, m, setH.length, setHX.length);

        //print matrix product

        showProduct(setHX);
        System.out.println("" );
        System.out.println("" );
        System.out.println("" );
        System.out.println("The following should be the Augmented matrix for both the Hilbert Matrix and product" );

        float[][]m = new float[n][n+1];
        for(int i = 0; i < n; i++) 
        { 
            for(int j = 0; j < n; j++) 
            { 
                // To store elements 
                // of matrix A 
                m[i][j] = setH[i][j]; 

                // To store elements 
                // of matrix B 
                // m[i][j + n] = product[i][j]; 

            }      
        }

        //last column copy
        for(int i = 0; i < n; i++) 
        { 
            for(int j = 0; j < 1; j++) 
            { 
                // To store elements 
                // of matrix A 
                //m[i][j+n] = product[i][j]; 

                // To store elements 
                // of matrix B 
                m[i][j+n] = product[i][j]; 

            }      
        } 
        augM = m;
        //print entire column
        for(int i =0; i< m.length; i++)
        {
            for(int j = 0; j< m[i].length; j++)
            {
                System.out.print(m[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("" );
        System.out.println("--------------------------------------" );
        System.out.println("Now we will use Gaussian Elimination to clean up this augmented matrix" );
        gausE(m);

        printGaus(rrefSolution);

        //now just take the last column in a different matrix!
        System.out.println("" );

        System.out.println("----------------------------------" );
        System.out.println("" );
        float[][]mSolution = new float[n][1];
        for(int i = 0; i < n; i++) 
        { 
            for(int j = 0; j < 1; j++) 
            { 
                // To store elements 
                mSolution[i][j] = rrefSolution[i][j+n]; 

            }      
        }

        System.out.println("for n = " + n+ ", the exact X matrix solution is " );
        System.out.println();
        matrixB(n);
        System.out.println("the last colulmn of this augmented matrix is...." );
        for(int i =0; i< n; i++)
        {
            for(int j = 0; j< mSolution[i].length; j++)
            {
                System.out.print(mSolution[i][j]+ " ");
            }
            System.out.println();
        }

        mLastColumn = mSolution;
        System.out.println("" );
        System.out.println("The difference between the actual solution and from our augmented matrix is the following..." );
        //write code for difference
        error(setX,mSolution);
        printGaus(diffSolution);
        System.out.println("" );
        System.out.println("Eucledian Norm for " + n + "x" + n + " matrix: ");
        eucledianNorm(diffSolution);  
        //printGaus(diffSolution);
        System.out.println(eucledianNorm(diffSolution));
        System.out.println("Infinity Norm for " + n + "x" + n + " matrix: ");
        System.out.println(findMax(diffSolution));
        System.out.println("Within the Gaussian operations for " + n + "x" + n + " matrix" );
        System.out.println("Number of arithmetic operations: " + clicker );
        System.out.println("Number of multiplication operations: " + clickerSecond );
        double x = nV/3;
        double nShould =  Math.pow(nV,3)/3 + Math.pow((nV),2 )+x;

        System.out.println("Number of multiplications for n=" +n +", using the formula in our book, my answer should have been  "+nShould );
        //System.out.println(diffSolution);
        //for(int i =0; i< n; i++)
        //{
        //    for(int j = 0; j< 1; j++)
        //    {
        //        System.out.print(diffSolution[i][j]+ " ");
        //    }
        //    System.out.println();
        //}
        //printGaus(diffSolution);
        System.out.println("" );
        System.out.println("End of Code" );

    }

}

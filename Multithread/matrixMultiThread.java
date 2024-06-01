import java.util.Scanner;

class MatrixMulti {
    int sa1, sa2, s1, s2;
    int [][] mat1, mat2, mult;
    long sTime, eTime;
    
    MatrixMulti(int satir1, int satir2, int sutun1, int sutun2, int [][] matrix1, int [][] matrix2) {
        sa1 = satir1;
        sa2 = satir2;
        s1 = sutun1;
        s2 = sutun2;
        mat1 = matrix1;
        mat2 = matrix2;
    }

    synchronized void multiplication() {
        if(s1 != sa2) {
            System.out.print("ERROR!\n");
        } 
        else {
            mult = new int[sa1][s2];
            sTime = System.nanoTime();
            for(int i=0; i < sa1; i++) {
                for(int j=0; j < s2; j++) {
                    mult[i][j] = 0;
                    for(int k=0; k < s1; k++) {
                        mult[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }
            }
            eTime = System.nanoTime();
            System.out.print("Sonuc: \n");
            for(int i=0; i < sa1; i++) {
                for(int j=0; j < s2; j++) {
                    System.out.print(mult[i][j]+" ");
                }
                System.out.println(" ");
            }
        }
    }
    
    long getExecutionTime() {
        return eTime - sTime;
    }
}


class calculate implements Runnable {
    MatrixMulti mxm;

    calculate(int satir1, int satir2, int sutun1, int sutun2, int [][] matrix1, int [][] matrix2) {
        this.mxm = new MatrixMulti(satir1, satir2, sutun1, sutun2, matrix1, matrix2);
    }
    synchronized public void run() {
        System.out.println("\n");
        mxm.multiplication();
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {}
        System.out.println("\nExecution Time: " + mxm.getExecutionTime() + "ns");
    }
}

public class matrixMultiThread {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" 1. matrisin satir sayisini girin: ");
        int satir1 = in.nextInt();
        System.out.println(" 1. matrisin sutun sayisini girin: ");
        int sutun1 = in.nextInt();
        
        int[][] matrix1 = new int[satir1][sutun1];
        System.out.print("1. matrisin sayilarini girin: ");
        for(int i=0; i < satir1; i++) {
            for(int j=0; j < sutun1; j++) {
                int data = in.nextInt();
                matrix1[i][j] = data;
            }
        }
        System.out.println();

        System.out.println(" 2. matrisin satir sayisini girin: ");
        int satir2 = in.nextInt();
        System.out.println(" 2. matrisin sutun sayisini girin: ");
        int sutun2 = in.nextInt();
        
        int[][] matrix2 = new int[satir2][sutun2];
        System.out.print("2. matrisin sayilarini girin: ");
        for(int i=0; i < satir2; i++) {
            for(int j=0; j < sutun2; j++) {
                int data = in.nextInt();
                matrix2[i][j] = data;
            }
        }
        calculate c1 = new calculate(satir1, satir2, sutun1, sutun2, matrix1, matrix2);
        Thread thread = new Thread(c1);
        thread.start();

        try {
            thread.join();
        }
        catch(InterruptedException e) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }
    
} 

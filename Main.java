package com.geekbrains;

public class Main {

    public static void main(String[] args) {
        potok();
        potoki();
        System.out.println("кто-то победил(и),Ура(Магия)");


    }
    public static void potok(){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) { arr[i] = ((float) 1); }
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}

        System.currentTimeMillis();
        System.out.println("1 поток справился за " + (System.currentTimeMillis() - a));
    }
    public static void potoki(){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1= new float[size];
        float[] a2= new float[size];
        for (int i = 0; i < arr.length; i++) { arr[i] = ((float) 1); }
        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++) {a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++) {a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}
            }
        });
        t1.start();
        t2.start();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.currentTimeMillis();
        System.out.println("2 потока справились за " + (System.currentTimeMillis() - a));
    }
}

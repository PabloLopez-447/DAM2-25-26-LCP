package UD1.Ejemplos;

public class Timer {
    public static void main(String[] args) {
        int N = 777777;
        long t;
        {
            String str = "";
            t = System.currentTimeMillis();
            for (int i = N; i-- > 0;)
                str += "x";
            System.out.println(System.currentTimeMillis() - t);
        }
        {
            StringBuffer sb = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i-- > 0;)
                sb.append("x");
            System.out.println(System.currentTimeMillis() - t);
        }
        {
            StringBuilder sb = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i-- > 0;)
                sb.append("x");
            System.out.println(System.currentTimeMillis() - t);
        }
    }
}
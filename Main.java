/**
 * Main
 */
public class Main {
    private static final String pi = "3.141";
    private static final String e = "2.7182818284";


    public static double exponential(double x, int n) {
        double result = 1.0;
        if (n == 0)
            return 1.0;
        if(n == 1)
            return x;

        int posCount=n;
        if(n < 0)
            posCount = -n;
        for (int i = 0; i < posCount; i++) {
            result *= x;
        }
        if(n<0)
            return 1/result;
        return  result;
    }

    public static double ln(double x){
        if (x <= 0){
            System.out.println("error system exit");
        }
        if(x == 1){
            return 0;
        }
        int precision = 1000;
        if (x > 400){
            precision = 5000;
        }
        double sum = 0;
        for (int i = 0; i < precision; i++){
            sum += 1.0/(2*i +1)*exponential(((x-1)/(x+1)), (2*i + 1));
        }
        return 2 * sum;

    }
//ln x = loge x = y https://stackoverflow.com/questions/35968963/trying-to-calculate-logarithm-base-10-without-math-h-really-close-just-having
    public static double log( double x ) {
        return ln(x) / ln(10);
    }

    public static double log(float x, double v)
    {
        return (log(v)/log(x));  	// Usage of identity logb x = ln x / ln b
    }


    public static void main(String[] args)
    {
        System.out.println(log(16));
    }
}
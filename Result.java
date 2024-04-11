import static java.lang.StrictMath.log;
import static java.lang.StrictMath.sin;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.function.Function;

class Result {
    public static String error_message = "";
    public static boolean has_discontinuity = false;
    
    private static double first_function(double x) {
        return 1 / x;
    }

    private static double second_function(double x) {
        return sin(x) / x;
    }

    private static double third_function(double x) {
        return x * x + 2;
    }

    private static double fourth_function(double x) {
        return 2 * x + 2;
    }

    private static double five_function(double x) {
        return log(x);
    }

    /*
    * How to use this function:
    *    Function<Double, Double> function = get_function(4);
    *    function.apply(0.0001);
    */
    private static Function<Double, Double> get_function(int n) {
        switch (n) {
            case (1):
                return Result::first_function;
            case (2):
                return Result::second_function;
            case (3):
                return Result::third_function;
            case (4):
                return Result::fourth_function;
            case (5):
                return Result::five_function;
            default:
                throw new UnsupportedOperationException("Function " + n + " not defined.");
        }
    }
    
    
    /*
     * Complete the 'calculate_integral' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. DOUBLE a
     *  2. DOUBLE b
     *  3. INTEGER f
     *  4. DOUBLE epsilon
     */

     public static double calculate_integral(double a, double b, int f, double epsilon) {
        double dx = epsilon;
        Function<Double, Double> function = get_function(f);
        double integral;
        double prevIntegral;
      
        while (true) {
            integral = 0.00;
            prevIntegral = Double.MAX_VALUE;
        
            for (double x = a; x < b; x += dx) {
                double middlePoint = x + dx / 2.0;
                integral += function.apply(middlePoint) * dx;
            }
        
            if (Math.abs(integral - prevIntegral) <= epsilon) {
                break;
            }
        
            dx /= 2.0;
        }
      
        if (Double.isNaN(integral) || Double.isInfinite(integral)) {
          error_message = "Integrated function has discontinuity or does not defined in current interval";
          has_discontinuity = true;
        }
      
        return a > b ? -integral : integral;
      }
      
}

import java.lang.Math;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExponentTwoVariables {
    public static final int NEWTON_ITERATIONS = 40;
    public static final int MAX_SCALE = 50;

    public static double exponentTwoVariables(int base, int exponent) {
        BigDecimal newBase = new BigDecimal(String.valueOf(base));
        BigDecimal newExponent = new BigDecimal(String.valueOf(exponent));

        return exponentTwoVariables(newBase, newExponent).doubleValue();
    } 

    public static double exponentTwoVariables(double base, double exponent) {
        BigDecimal newBase = new BigDecimal(String.valueOf(base));
        BigDecimal newExponent = new BigDecimal(String.valueOf(exponent));

        return exponentTwoVariables(newBase, newExponent).doubleValue();
    }

    public static BigDecimal exponentTwoVariables(BigDecimal base, BigDecimal exponent) {
        // Edge cases
        if (base.doubleValue() == 1.0 && exponent.doubleValue() >= 1.0 || base.doubleValue() == 0.0 && exponent.doubleValue() != 0.0) {
            return base;
        }
        else if (exponent.doubleValue() == 0.0) {
            return new BigDecimal("1.0");
        } 
        else if (base.doubleValue() == -1.0 && exponent.doubleValue() % 2 == 0) {
            return new BigDecimal("1.0");
        }
        else if (base.doubleValue() == -1.0 && exponent.doubleValue() % 2 != 0) {
            return new BigDecimal("-1.0");
        }

        boolean isPositiveExponent = exponent.compareTo(BigDecimal.ZERO) == 1;

        base.setScale(100);
        exponent.setScale(100);

        // If the exponent can be expressed as an integer, calculate using simple iterative algorithm
        if (exponent.doubleValue() / exponent.intValue() == 1.0) {
            return exponentIterative(base, exponent.intValue(), isPositiveExponent);
        }

        // Otherwise, split the exponent into two parts, pre and post decimal
        String[] exponentDivided = exponent.toPlainString().split("\\.");

        // Calculate base raised to the power of pre-decimal value
        BigDecimal preDecimalResult = exponentIterative(base, Integer.valueOf(exponentDivided[0]), isPositiveExponent);

        // Calculate base raised to the power of post-decimal value (using Newton's nth root method)
        BigDecimal postDecimalResult = nthRoot(base, Integer.valueOf(exponentDivided[1]), isPositiveExponent);

        return preDecimalResult.multiply(postDecimalResult);
    }

    // Simple iterative algorithm for finding exponent when the exponent is an integer
    public static BigDecimal exponentIterative(BigDecimal base, int exponent, boolean isPositiveExponent) {
        if (exponent == 0) {
            return new BigDecimal(1);
        }
        BigDecimal result = base;

        for (int i = 0; i < Math.abs(exponent) - 1; i++) {
            result = result.multiply(base); 
            result = result.setScale(MAX_SCALE, RoundingMode.HALF_UP);
        }

        return isPositiveExponent ? result : (new BigDecimal(1)).divide(result, MAX_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal nthRoot(BigDecimal base, int nthRoot, boolean isPositiveExponent) {
        BigDecimal nthRootDenominator = exponentIterative(new BigDecimal(10), String.valueOf(nthRoot).length(), true);
    
        // Start with initial guesss
        BigDecimal root = new BigDecimal("1.0");
        BigDecimal one = new BigDecimal("1.0");

        // Refine root based on Newton's method
        for (int i = 0; i < NEWTON_ITERATIONS; i++) {
            BigDecimal newtonPartI = root.multiply(one.subtract(one.divide(nthRootDenominator, MAX_SCALE, RoundingMode.HALF_UP)));
            BigDecimal newtonPartII = base.divide(nthRootDenominator, MAX_SCALE, RoundingMode.HALF_UP);
            BigDecimal newtonPartIII = newtonPartII.divide(exponentIterative(root, nthRootDenominator.subtract(one).intValue(), true), MAX_SCALE, RoundingMode.HALF_UP);

            root = newtonPartI.add(newtonPartIII); 
        }

        return (isPositiveExponent) ? exponentIterative(root, nthRoot, true) : exponentIterative(root, nthRoot, false);
    }
}
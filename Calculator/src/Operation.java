public class Operation {
    public Double calculate(double a) { return null; }
    public Double calculate(double a, double b) { return null; }
}
class Addition extends Operation {
    @Override
    public Double calculate(double a, double b) {
        return a + b;
    }
}
class Subtraction extends Operation {
    @Override
    public Double calculate(double a, double b) {
        return a - b;
    }
}
class Division extends Operation {
    @Override
    public Double calculate(double a, double b) {
        if (b == 0) return null;
        return a / b;
    }
}
class Multiplication extends Operation {
    @Override
    public Double calculate(double a, double b) {
        return a * b;
    }
}
class SquareRoot extends Operation {
    @Override
    public Double calculate(double a) {
        if (a < 0) return null;
        return Math.sqrt(a);
    }
}
class Power extends Operation {
    @Override
    public Double calculate(double a, double b) {
        return Math.pow(a, b);
    }
}
class Factorial extends Operation {
    @Override
    public Double calculate(double a) {
        if (a < 0) return null;
        return fac(a);
    }
    private double fac(double a) {
        if (a == 0 || a == 1) return 1;
        return a * fac(a - 1);
    }
}

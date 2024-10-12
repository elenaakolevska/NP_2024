package av2.complex;

import java.math.BigDecimal;

public class BigComplex {

    private BigDecimal realPart;
    private BigDecimal imaginaryPart;

    public BigComplex() {
    }

    public BigComplex(BigDecimal realPart, BigDecimal imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }


    public BigComplex add(BigComplex complex){
        return new BigComplex(this.realPart.add(complex.realPart), this.imaginaryPart.add(complex.imaginaryPart));
    }

    public BigComplex subtract(BigComplex complex){
        return new BigComplex(this.realPart.subtract(complex.realPart), this.imaginaryPart.subtract(complex.imaginaryPart));
    }

    @Override
    public String toString() {
        return "BigComplex{" +
                "realPart=" + realPart +
                ", imaginaryPart=" + imaginaryPart +
                '}';
    }
}


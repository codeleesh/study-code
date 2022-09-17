package item11.domain;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.comparingInt;

public class PhoneNumber implements Cloneable {

    private final Comparator<PhoneNumber> COMPARATOR = comparingInt((PhoneNumber pn) -> pn.areaCode)
            .thenComparingInt(pn -> pn.prefix)
            .thenComparingInt(pn -> pn.lineNum);

    private int areaCode;
    private int prefix;
    private int lineNum;

    private PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    public static PhoneNumber of(int areaCode, int prefix, int lineNum) {

        return new PhoneNumber(areaCode, prefix, lineNum);
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (PhoneNumber) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNum);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phone1=" + areaCode +
                ", phone2=" + prefix +
                ", phone3=" + lineNum +
                '}';
    }

    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }
}

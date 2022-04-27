package ir.malakouti.questionaire.utils.filter;

import java.util.Objects;

public class BinaryFilter {
    private Filter first;
    private Filter second;

    public BinaryFilter() {
    }

    public BinaryFilter(Filter first, Filter second) {
        this.first = first;
        this.second = second;
    }

    public Filter getFirst() {
        return first;
    }

    public void setFirst(Filter first) {
        this.first = first;
    }

    public Filter getSecond() {
        return second;
    }

    public void setSecond(Filter second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryFilter that = (BinaryFilter) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

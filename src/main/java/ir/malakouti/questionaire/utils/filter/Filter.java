package ir.malakouti.questionaire.utils.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Filter {

    public enum Operator {
        _EQUAL("equal"), EQUAL("eq"), NOT_EQUAL("neq"), LIKE("like"), BETWEEN("between"), AFTER("after"),
        BEFORE("before"), IN("in"), NOT_IN("nin"), GREATER_THAN_OR_EQUAL("gte"), LESS_THAN_OR_EQUAL("lte"),
        GREATER_THAN("gt"), LESS_THAN("lt"), IS_NULL("isn"), IS_NOT_NULL("inn"), MAX("max"), LIKE_CASE_INSENSITIVE("lci");

        private String name;

        Operator(String name) {
            this.name = name;
        }

        @JsonCreator
        public static Operator fromString(String value) {
            return getValue(value);
        }

        @JsonValue
        public String fromObject(Operator operator) {
            return operator.name;
        }

        public static Operator getValue(String value) {
            for (Operator operator : Operator.values()) {
                if (operator.name.equalsIgnoreCase(value))
                    return operator;
            }
            return null;
        }
    }

    private String property;
    private String value;
    private Operator operator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (!property.equalsIgnoreCase(filter.property)) return false;
        Operator op1 = operator;
        Operator op2 = filter.operator;

        if(Operator._EQUAL.equals(op1))
            op1 = Operator.EQUAL;
        if(Operator._EQUAL.equals(op2))
            op2 = Operator.EQUAL;

        return op1 == op2;
    }

    @Override
    public int hashCode() {
        int result = property.toLowerCase().hashCode();
        Operator op = operator;
        if(Operator._EQUAL.equals(op))
            op = Operator.EQUAL;
        result = 31 * result + op.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "property='" + property + '\'' +
                ", value='" + value + '\'' +
                ", operator=" + operator +
                '}';
    }
}
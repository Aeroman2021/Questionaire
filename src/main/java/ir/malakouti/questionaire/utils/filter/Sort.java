package ir.malakouti.questionaire.utils.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Sort {
    public enum Direction {

        ASC("asc"), DESC("desc");

        private String name;

        Direction(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @JsonCreator
        public Direction fromString(String value) {
            return getValue(value);
        }

        @JsonValue
        public String fromEnum(Direction direction) {
            return direction.name;
        }

        public static Direction getValue(String value) {
            for (Direction direction : Direction.values()) {
                if (direction.name.equalsIgnoreCase(value)) {
                    return direction;
                }
            }
            return null;
        }
    }

    private Direction direction;
    private String property;

    public Sort() {
    }

    public Sort(String json) throws IOException {
        Map<String, String> map = new ObjectMapper().readValue(json, Map.class);
        this.direction = Direction.getValue(map.get("direction"));
        this.property = map.get("property");
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "direction=" + direction +
                ", property='" + property + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sort sort = (Sort) o;
        return direction == sort.direction &&
                property.equals(sort.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, property);
    }

}

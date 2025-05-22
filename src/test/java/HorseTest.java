import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void nullParams() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2, 3));
    }

    @Test
    public void nullParamsMassage() {
        try {
            new Horse(null, 3, 4);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "   "})
    public void emptyParams(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2, 3));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeed() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 3));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistance() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getNameTest() {
        String name = "name";
        Horse pony = new Horse(name, 2, 1);
        assertEquals(name, pony.getName());
    }

    @Test
    public void getSpeedTest() {
        int speed = 2;
        Horse pony = new Horse("name", speed, 1);
        assertEquals(speed, pony.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        int dist = 2;
        Horse pony = new Horse("name", 2, dist);
        assertEquals(dist, pony.getDistance());
    }

    @Test
    public void getZeroDistance() {
        Horse pony = new Horse("name", 2, 0);
        assertEquals(0, pony.getDistance());
    }

    @Test
    public void checkMove() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("name", 2, 3).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.2, 0.4})
    public void checkMoveDistance(Double doubles) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            Horse pony = new Horse("name", 2, 3);
            Double expectedDistance = pony.getDistance() + pony.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            pony.move();
            assertEquals(expectedDistance, pony.getDistance());
        }
    }
}

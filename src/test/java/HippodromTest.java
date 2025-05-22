import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromTest {
    @Test
    public void nullParams() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void emptyParams() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void horsesList(){
        List<Horse> horses = new ArrayList<>();
        for(int i=1;i<31;i++){
            horses.add(new Horse("horse "+i,i,i));
        }
        Hippodrome hippodrome= new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());

    }

    @Test
    public void moveHippodrom(){
        List<Horse> horses = new ArrayList<>();
        for(int i=1;i<51;i++){
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome =new Hippodrome(horses);
        hippodrome.move();
        for(Horse horse:horses){
            verify(horse).move();
        }
    }

    @Test
    public void checkWinner(){
        Horse horse = new Horse("1",2,3);
        Horse horse2 = new Horse("2",2,4);
        Horse horse3 = new Horse("3",2,5);

        Hippodrome hippodrome=new Hippodrome(List.of(horse,horse2,horse3));
        assertSame(horse3,hippodrome.getWinner());


    }


}


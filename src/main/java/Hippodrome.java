import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            Main.LOGGER.error("Hippodrome: Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            Main.LOGGER.error("Hippodrome: Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        Main.LOGGER.debug("Hippodrome: Создание Hippodrome, лошадей ",horses);
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}

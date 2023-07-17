package pr.iceworld.fernando.spring6.junit5.repository;

import org.springframework.stereotype.Repository;
import pr.iceworld.fernando.spring6.junit5.entity.Fruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Repository
public class FruitRepository {

    static List<Fruit> fruits = Arrays.asList(
            Fruit.builder().id(301L).name("apple").build(),
            Fruit.builder().id(302L).name("pear").build()
    );

    public List<Fruit> getFruits() {
        return fruits;
    }

    public Fruit getById(Long id) {
        return fruits.stream().filter(e -> e.getId() == id).findFirst().orElseGet(() -> Fruit.builder().build());
    }

    public void save(Fruit fruit) {
        fruits.add(fruit);
    }
}

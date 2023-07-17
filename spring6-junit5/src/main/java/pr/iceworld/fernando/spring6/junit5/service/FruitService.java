package pr.iceworld.fernando.spring6.junit5.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import pr.iceworld.fernando.spring6.junit5.entity.Fruit;
import pr.iceworld.fernando.spring6.junit5.repository.FruitRepository;

import java.util.List;

@Service
public class FruitService {

    @Resource
    FruitRepository fruitRepository;

    public List<Fruit> getFruits() {
        return fruitRepository.getFruits();
    }

    public void save(Fruit fruit) {
        fruitRepository.save(fruit);
    }

    public Fruit getFruitById(Long id) {
        return fruitRepository.getById(id);
    }
}

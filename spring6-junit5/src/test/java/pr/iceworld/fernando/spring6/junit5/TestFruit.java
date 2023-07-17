package pr.iceworld.fernando.spring6.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pr.iceworld.fernando.spring6.junit5.entity.Fruit;
import pr.iceworld.fernando.spring6.junit5.repository.FruitRepository;
import pr.iceworld.fernando.spring6.junit5.service.FruitService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("This is a test class - for Fruit")
@Slf4j
public class TestFruit extends BaseTest {

    @InjectMocks
    FruitService fruitService;
    @Mock
    FruitRepository fruitRepository;

    @DisplayName("test get one fruit by id")
    @Test
    public void testGetOneFruitById() {
        Fruit apple = Fruit.builder().build();
        Long id = 301L;
        apple.setId(id);
        apple.setName("apple");
        when(fruitRepository.getById(id)).thenReturn(apple);
        Fruit foundFruit = fruitService.getFruitById(id);
        assertEquals(apple.getId(), foundFruit.getId());
    }

    @Disabled
    @Test
    public void testDisableMethod() {
        log.info("@Disabled, specific this method will still run, if run test whole class, this will be ignore");
    }

    @Disabled
    @Test
    public void testassertEqualsFailed() {
        assertEquals(5, 5);
    }

    @Disabled
    @Test
    public void testDisabledRuntimeException() {
        throw new RuntimeException("RuntimeException");
    }
}

package pr.iceworld.fernando.spring6.junit5;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
@Slf4j
class BaseTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        log.info("@BeforeEach");
    }

    @BeforeAll
    public static void staticInit() {
        log.info("@BeforeAll");
    }

    @AfterEach
    public void tearDown() {
        log.info("@AfterEach");
    }

    @AfterAll
    public static void staticFinished() {
        log.info("@AfterAll");
    }
}

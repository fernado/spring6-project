package pr.iceworld.mockspring6ioc.simulatespringioc.context;

public interface CustomBeanFactory {

    /**
     * get bean by name
     * @param name
     * @return
     * @param <T>
     */
    <T extends Object> T getBean(String name);

    /**
     * get bean by class
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T getBean(Class<?> clazz);

    /**
     * check whether container's has the bean
     * @param name
     * @return
     */
    boolean containsBean(String name);

}

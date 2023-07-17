package pr.iceworld.mockspring6ioc.simulatespringioc.context;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pr.iceworld.mockspring6ioc.simulatespringioc.annotation.*;
import pr.iceworld.mockspring6ioc.simulatespringioc.exception.CustomRuntimeException;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CustomAnnotationConfigApplicationContext implements CustomConfigurableApplicationContext {

    /**
     * Cache of singleton objects: bean name to bean instance.
     * same as spring's DefaultSingletonBeanRegistry#singletonObjects
     * (K, V) => (${componentName}, ${component.class})
     * eg (helloComponent,pr.iceworld.mockspring6ioc.simulatespringioc.component.HelloComponent.class)
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private Set<String> allClasses = new LinkedHashSet<>(256);

    public CustomAnnotationConfigApplicationContext(Class<?> configClazz) {
        init(configClazz);
    }

    @Override
    public <T extends Object> T getBean(String name) {
        Object obj = singletonObjects.get(name);
        return null == obj ? null : (T) obj;
    }

    @Override
    public <T> T getBean(Class<?> clazz) {
        return (T) singletonObjects.get(getBeanName(clazz));
    }

    private String getBeanName(Class<?> clazz) {
        return convertClazzNameAsRegular(clazz.getSimpleName());
    }

    @Override
    public boolean containsBean(String name) {
        return singletonObjects.containsKey(name);
    }

    private Class<?> configClazz;
    public void refresh()  {
        //invokeBeanFactoryPostProcessors();
        parseBean();
        finishBeanFactoryInitialization();
    }

    private void init(Class<?> configClazz) {
        this.configClazz = configClazz;
        refresh();
    }

    private void finishBeanFactoryInitialization() {
        try {
            registerBean();
            doDependenceInjection();
        } catch (Exception e) {
            throw new CustomRuntimeException(e);
        }
    }

    private void parseBean() {
        String[] scanBeanPackages = getScanBeanPackages();
        for (String scanBeanPath: scanBeanPaths()) {
            for (String scanBeanPackage: scanBeanPackages) {
                loadBeanClass(scanBeanPath, scanBeanPackage, true);
            }
        }
    }

    private String[] getScanBeanPackages() {
        return getCustomComponentScan().value();
    }

    private CustomComponentScan getCustomComponentScan() {
        CustomComponentScan customComponentScan = configClazz.getAnnotation(CustomComponentScan.class);
        if (null == customComponentScan) {
            throw new CustomRuntimeException("No package is specific.");
        }
        return customComponentScan;
    }

    protected String[] scanBeanPaths() {
        return new String[] { configClazz.getClassLoader().getResource("").getPath() };
    }

    /**
     *
     * @param path
     * @param packageName
     * @param first 路径解析标记
     */
    private void loadBeanClass(String path, String packageName, boolean first) {
        String newPath = first ? path + packageName.replace(".", "/") : path;
        File parent = new File(newPath);
        if (parent != null) {
            for (File child : parent.listFiles()) {
                if (child.isDirectory()) {
                    loadBeanClass0(child, packageName);
                } else {
                    addClassNames(child, packageName);
                }
            }
        }
    }

    private void loadBeanClass0(File file, String packageName) {
        loadBeanClass(file.getPath(), packageName + "." + file.getName(), false);
    }

    private void addClassNames(File file, String packageName) {
        if (file.getName().indexOf(".class") > 0) {
            allClasses.add(getClassName(file.getName(), packageName));
        }
    }

    private String getClassName(String filename, String packageName) {
        return packageName + "." + filename.replace(".class", "");
    }

    private void registerBean() throws Exception {
        if (CollectionUtils.isEmpty(allClasses))  return;
        for (String className : allClasses) {
            Class clazz = Class.forName(className);
            Annotation[] annotations = clazz.getAnnotations();

            for (Annotation annotation: annotations) {
                registerBean0(annotation, clazz);
            }
        }
    }

    /**
     * 获取 注解组件类
     * @param annotation
     * @param clazz
     * @throws Exception
     */
    private void registerBean0(Annotation annotation, Class clazz) throws Exception {
        String beanName = null;
        boolean matched = false;
        if (annotation instanceof CustomComponent component) {
            component = (CustomComponent) clazz.getAnnotation(CustomComponent.class);
            if (null != component) {
                beanName = component.value();
                matched = true;
            }
        } else if (annotation instanceof CustomRepository component) {
            component = (CustomRepository) clazz.getAnnotation(CustomRepository.class);
            if (null != component) {
                beanName = component.value();
                matched = true;
            }
        } else if (annotation instanceof CustomService component) {
            component = (CustomService) clazz.getAnnotation(CustomService.class);
            if (null != component) {
                beanName = component.value();
                matched = true;
            }
        }

        if (matched) {
            beanName = getBeanName0(beanName, clazz);
            if (null != beanName) {
                singletonObjects.put(beanName, clazz.getDeclaredConstructor().newInstance());
            }
        }
    }

    private void doDependenceInjection() throws Exception {
        if (singletonObjects.isEmpty()) return;
        for (Object obj : singletonObjects.values()) {
            doInjection(obj);
        }
    }

    private void doInjection(Object targetObj) throws Exception {
        Field[] fields = targetObj.getClass().getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {
            for (Field field : fields) {
                if (null != field.getAnnotation(CustomAutowired.class)) {
                    doInjection0(field, targetObj);
                }
            }
        }
    }

    private void doInjection0(Field field, Object specificObj) throws Exception {
        String beanName = getBeanName(field.getType());
        if (!singletonObjects.containsKey(beanName)) {
            singletonObjects.put(beanName, field.getType().getDeclaredConstructor().newInstance());
        }
        field.setAccessible(true);
        field.set(specificObj, singletonObjects.get(beanName));
        doInjection(singletonObjects.get(beanName));
    }

    private String getBeanName0(String beanName, Class<?> clazz) {
        return StringUtils.isEmpty(beanName)
                ? convertClazzNameAsRegular(clazz.getSimpleName())
                : beanName;
    }

    public static String convertClazzNameAsRegular(String clazzName) {
        return convert1stCharacter2LowerCase(clazzName);
    }

    public static String convert1stCharacter2LowerCase(String clazzName) {
        if (null != clazzName) {
            return Character.toLowerCase(clazzName.charAt(0)) + clazzName.substring(1);
        }
        return null;
    }
}

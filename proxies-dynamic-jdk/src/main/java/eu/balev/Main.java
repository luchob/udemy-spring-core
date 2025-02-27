package eu.balev;

import eu.balev.proxy.CacheableInvocationHandler;
import eu.balev.proxy.CalculatorIfc;
import eu.balev.proxy.CalculatorImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        CalculatorIfc calculatorIfc = getBean(CalculatorIfc.class, CalculatorImpl.class);

        System.out.println(calculatorIfc.calculateNumber());
        System.out.println(calculatorIfc.calculateNumber());
        System.out.println(calculatorIfc.calculateNumber());
    }

    @SuppressWarnings("unchecked")
    private static <T> T getBean(Class<T> ifc, Class<?> clazz)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (T) Proxy.
            newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{ifc},
                new CacheableInvocationHandler(clazz.getConstructor().newInstance())
            );
    }


}
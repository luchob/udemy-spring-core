package eu.balev;

import eu.balev.proxy.CacheableInvocationHandler;
import eu.balev.proxy.CalculatorIfc;
import eu.balev.proxy.CalculatorImpl;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args)
        throws Exception {

        CalculatorIfc calculatorIfc = createBean(CalculatorIfc.class, CalculatorImpl.class);

        System.out.println(calculatorIfc.calculateNumber());
        System.out.println(calculatorIfc.calculateNumber());
        System.out.println(calculatorIfc.calculateNumber());
    }

    @SuppressWarnings("unchecked")
    private static <T> T createBean(Class<T> ifcClazz, Class<? extends T> implClazz) throws Exception {
        return (T)Proxy.newProxyInstance(
            Main.class.getClassLoader(),
            new Class[]{ifcClazz},
            new CacheableInvocationHandler(implClazz.getConstructor().newInstance())
        );
    }
}
package item9.config;

import item9.common.FirstError;
import item9.common.SecondException;

public class MyResource implements AutoCloseable {

    public void doSomething() throws FirstError {
        System.out.println("doing something");
        throw new FirstError();
    }

    @Override
    public void close() throws SecondException {
        System.out.println("clean my resource");
        throw new SecondException();
    }
}

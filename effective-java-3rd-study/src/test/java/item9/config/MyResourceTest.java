package item9.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyResourceTest {

    @Test
    void try_finally_error_exception() {

        MyResource myResource = null;
        try {
            myResource = new MyResource();
            myResource.doSomething();
        } finally {
            if (myResource != null) {
                myResource.close();
            }
        }
    }

    @Test
    void try_with_resource_error_exception() {

        try (MyResource myResource = new MyResource()) {
            myResource.doSomething();
        }
    }
}
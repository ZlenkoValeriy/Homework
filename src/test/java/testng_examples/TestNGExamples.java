package testng_examples;

import org.testng.annotations.Test;

public class TestNGExamples {

    @Test(expectedExceptions = {ArithmeticException.class})
    public void testFirst() throws Exception {
        System.out.println(4/0);
    }

    @Test(timeOut = 3000)
    public void timeoutTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

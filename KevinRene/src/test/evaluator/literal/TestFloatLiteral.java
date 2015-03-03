package test.evaluator.literal;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import test.evaluator.BaseTest;
import cons.Value;
import cons.value.FloatValue;

@RunWith(value = Parameterized.class)
public class TestFloatLiteral extends BaseTest {
	 @Parameters
     public static Collection<Object[]> data() {
    	 return Arrays.asList(new Object[][] {
    			 { "10.5", new FloatValue((float) 10.5) },
    			 { "-10.5", new FloatValue((float) -10.5) },
    			 { "floatQuestion", new FloatValue((float) 10.5) }
    	 });
     }

     @SuppressWarnings("rawtypes")
     public TestFloatLiteral(String input, Value expected) {
    	 super(input, expected);
     }
}

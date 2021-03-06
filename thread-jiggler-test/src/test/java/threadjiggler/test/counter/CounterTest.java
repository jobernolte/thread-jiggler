package threadjiggler.test.counter;

import org.junit.Test;
import threadjiggler.test.Threads;

import java.util.concurrent.Callable;

import static junit.framework.TestCase.assertEquals;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class CounterTest {
	Counter counter;
	int n = 1000;

	@Test
	public void singleThreadedTest() throws Exception {

		for (int i = 0; i < n; i++) {
			counter.count();
		}

		assertEquals(n, counter.getCount());
	}

	@Test
	public void threadedTest() throws Exception {

		Threads.call(n, 2, new Callable<Void>() {
			@Override
			public Void call() {
				counter.count();
				return null;
			}
		});

		assertEquals(n, counter.getCount());
	}
}

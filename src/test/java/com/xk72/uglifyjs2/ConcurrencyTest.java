package com.xk72.uglifyjs2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;

public class ConcurrencyTest {

	@Test
	public void testConcurency() throws ScriptException, InterruptedException, ExecutionException, IOException {
		final UglifyJS2 u = new UglifyJS2();
		
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return u.compress("(function() { var karl = \"abc\"; return karl; })();");
			}
			
		};
		
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		Collection<Future<String>> futures = new ArrayList<>();
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 1000; i++) {
			futures.add(executorService.submit(callable));
		}
		
		System.out.println("Submitted all tasks in " + (System.currentTimeMillis() - startTime) + " ms");
		
		for (Future<String> future : futures) {
			Assert.assertEquals("(function(){var karl=\"abc\";return karl})();", future.get());
		}
		
		System.out.println("Completed all tasks in " + (System.currentTimeMillis() - startTime) + " ms");
	}
	
}

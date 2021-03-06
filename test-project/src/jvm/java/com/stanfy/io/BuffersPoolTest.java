package com.stanfy.io;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.util.Log;

import com.stanfy.test.EnroscarTestRunner;

/**
 * Tests for {@link BuffersPool}. Absolutely useless.
 */
@RunWith(EnroscarTestRunner.class)
public class BuffersPoolTest {

  /** Buffers pool instance. */
  private BuffersPool buffersPool;

  /** Random. */
  private final Random r = new Random();

  /** User hread. */
  private class UserThread extends Thread {
    @Override
    public void run() {
      final int count = 100;
      final int maxSize = 32000;
      byte[] buffer = null;
      for (int i = 0; i < count; i++) {
        final boolean get = r.nextBoolean();
        final int size = r.nextInt(maxSize);
        if (buffer == null || get) {
          buffer = buffersPool.get(size);
        } else {
          buffersPool.release(buffer);
          buffer = null;
        }
      }
    }
  }

  @Before
  public void createPool() {
    this.buffersPool = new BuffersPool();
  }


  // XXX Is it a good way???
  @Test
  public void threadsTest() throws Exception {
    Log.i(getClass().getSimpleName(), "XXX Don't mind following 'Be careful'...");
    final int count = 10;
    final ArrayList<UserThread> threads = new ArrayList<UserThread>(count);
    for (int i = 0; i < count; i++) {
      final UserThread t = new UserThread();
      threads.add(t);
      t.start();
    }
    for (final UserThread t : threads) {
      t.join();
    }
  }

}

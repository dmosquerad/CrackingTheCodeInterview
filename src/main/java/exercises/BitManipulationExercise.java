package exercises;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BitManipulationExercise {

  static final String ERROR_MESSAGE = "ERROR";

  // Exercise 5.1
  public static int updateBits(final int baseInt, final int insertInt, final int startBit,
                               final int endBit) {
    if (startBit > endBit || startBit < 0 || endBit >= Integer.SIZE) {
      return 0;
    }

    final int allOnes = ~0;
    final int left = endBit < 31 ? (allOnes << (endBit + 1)) : 0;
    final int right = ((1 << startBit) - 1);
    final int mask = left | right;
    final int baseCleared = baseInt & mask;
    final int insertShifted = insertInt << startBit;

    return baseCleared | insertShifted;
  }

  // Exercise 5.2
  public static String printBinary(final double value) {
    if (value >= 1 || value <= 0) {
      return ERROR_MESSAGE;
    }

    final StringBuilder binary = new StringBuilder("0.");
    final AtomicReference<Double> counter = new AtomicReference<>(value);

    while (counter.get() > 0) {
      if (binary.length() >= 34) {
        return ERROR_MESSAGE;
      }

      counter.set(counter.get() * 2);
      if (counter.get() >= 1) {
        binary.append("1");
        counter.set(counter.get() - 1);
      } else {
        binary.append("0");
      }
    }

    return String.valueOf(binary);
  }

  public static String printBinaryLambda(final double fraction) {
    if (fraction <= 0 || fraction >= 1) {
      return ERROR_MESSAGE;
    }

    return "0." + buildBinary(fraction, 0);
  }

  private static String buildBinary(final double fraction, final int bitCount) {
    if (fraction == 0) {
      return "";
    }

    if (bitCount >= 32) {
      return ERROR_MESSAGE;
    }

    final double doubledFraction = fraction * 2;

    return (doubledFraction >= 1 ? "1" : "0") + buildBinary(doubledFraction
        - (doubledFraction >= 1 ? 1 : 0), bitCount + 1);
  }

  // Exercise 5.3
  public static int longestSequence(final int value) {
    if (Objects.equals(value, 0)) {
      return 1;
    }

    final AtomicInteger valueAtomic = new AtomicInteger(value);

    final AtomicInteger maxLength = new AtomicInteger(0);
    final AtomicInteger previousCount = new AtomicInteger(0);
    final AtomicInteger currentCount = new AtomicInteger(0);

    while (valueAtomic.get() != 0) {
      if ((valueAtomic.get() & 1) == 1) {
        currentCount.incrementAndGet();
      } else {
        maxLength.set(Math.max(maxLength.get(), previousCount.get() + currentCount.get() + 1));

        previousCount.set(currentCount.get());
        currentCount.set(0);
      }
      valueAtomic.getAndUpdate(x -> x >> 1);
    }

    maxLength.set(Math.max(maxLength.get(), previousCount.get() + currentCount.get() + 1));

    return maxLength.get();
  }

  public static int longestSequenceLambda(final int value) {
    if (Objects.equals(value, 0)) {
      return 1;
    }

    final AtomicInteger maxLength = new AtomicInteger(0);
    final AtomicInteger previousCount = new AtomicInteger(0);
    final AtomicInteger currentCount = new AtomicInteger(0);

    IntStream.iterate(value, x -> !Objects.equals(x, 0), x -> x >> 1)
        .forEach(bit -> {
          if ((bit & 1) == 1) {
            currentCount.incrementAndGet();
          } else {
            maxLength.set(Math.max(maxLength.get(), previousCount.get() + currentCount.get() + 1));
            previousCount.set(currentCount.get());
            currentCount.set(0);
          }
        });

    maxLength.set(Math.max(maxLength.get(), previousCount.get() + currentCount.get() + 1));

    return maxLength.get();
  }

  // Exercise 5.4
  public static int nextSmallest(final int value) {
    final AtomicInteger valueAtomic = new AtomicInteger(value);
    final int numTrailingZeros = Integer.bitCount(value & (value - 1)) - Integer.bitCount(value);
    final int numOnes = Integer.bitCount(value);

    if (Objects.equals(numTrailingZeros + numOnes, 31)
        || Objects.equals(numTrailingZeros + numOnes, 0)) {
      return -1;
    }

    final int positionToFlip = numTrailingZeros + numOnes;

    valueAtomic.getAndUpdate(x -> x | (1 << positionToFlip));
    valueAtomic.getAndUpdate(x -> x & -(1 << positionToFlip));
    valueAtomic.getAndUpdate(x -> x | (1 << (numOnes - 1)) - 1);

    return valueAtomic.get();
  }

  public static int nextLargest(final int value) {
    final AtomicInteger valueAtomic = new AtomicInteger(value);
    final int numTrailingOnes = Integer.bitCount(value & (value - 1));
    int numOnes = Integer.bitCount(value);

    if (Objects.equals(numOnes, 0)) {
      return -1;
    }

    final int positionToFlip = numTrailingOnes + numOnes;

    valueAtomic.getAndUpdate(x -> x & ((~0) << (positionToFlip + 1)));
    final int mask = (1 << (numOnes + 1)) - 1;
    valueAtomic.getAndUpdate(x -> x | mask << (numTrailingOnes - 1));

    return valueAtomic.get();
  }

  // Exercise 5.5
  public static boolean isPowerOfBinaryTwo(final int value) {
    return value > 0 && ((value & (value - 1)) == 0);
  }

  // Exercise 5.6
  public static int countBitsToFlip(final int source, final int target) {
    return Integer.bitCount(source ^ target);
  }

  // Exercise 5.7
  public static int swapOddEvenBits(final int value) {
    final int evenBitMask = 0xAAAAAAAA;
    final int oddBitMask = 0x55555555;

    final AtomicInteger oddBits = new AtomicInteger(value & oddBitMask);
    final AtomicInteger evenBits = new AtomicInteger(value & evenBitMask);

    oddBits.getAndUpdate(x -> x << 1);
    evenBits.getAndUpdate(x -> x >> 1);

    return oddBits.get() | evenBits.get();
  }

  // Exercise 5.8
  @Builder
  public static class MonochromeScreen {

    @Getter
    private byte[] screen;

    private final int width;

    private final int height;

    public void initialize() {
      if (width % 8 != 0) {
        throw new IllegalArgumentException("Width must be divisible by 8.");
      }
      this.screen = new byte[(width / 8) * height];  // Each row is width / 8 bytes
    }

    public byte[] drawHorizontalLine(final int x1, final int x2, final int y) {
      if (y < 0 || y >= height) {
        throw new IllegalArgumentException("Invalid row index y.");
      }
      if (x1 < 0 || x1 >= width || x2 < 0 || x2 >= width || x1 > x2) {
        throw new IllegalArgumentException("Invalid x1 or x2 values.");
      }

      final int byteRowStartIndex = (y * width) / 8;

      for (int x = x1; x <= x2; x++) {
        final int byteOffset = byteRowStartIndex + (x / 8);
        final int bitPosition = x % 8;

        screen[byteOffset] |= (byte) (1 << (7 - bitPosition));
      }

      return this.screen;
    }
  }

}

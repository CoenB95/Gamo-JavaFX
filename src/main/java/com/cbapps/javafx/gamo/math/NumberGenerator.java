package com.cbapps.javafx.gamo.math;

import java.util.Random;

public class NumberGenerator {
	private Random random;

	private NumberGenerator(Random random) {
		this.random = random;
	}

	public double nextDouble(double maximalExclusive) {
		return nextDouble(0, maximalExclusive);
	}

	public double nextDouble(double minimalInclusive, double maximalExclusive) {
		return minimalInclusive + random.nextDouble() * (maximalExclusive - minimalInclusive);
	}

	public static NumberGenerator ofSeed(long seed) {
		return new NumberGenerator(new Random(seed));
	}

	public static NumberGenerator random() {
		return new NumberGenerator(new Random());
	}

	public static double randomDouble(double maximalExclusive) {
		return random().nextDouble(maximalExclusive);
	}

	public static double randomDouble(double minimalInclusive, double maximalExclusive) {
		return random().nextDouble(minimalInclusive, maximalExclusive);
	}
}

package Ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

	public static void main(String[] args) {
		List<Apples> inventory = Arrays.asList(
				new Apples(80, Color.GREEN),
				new Apples(100, Color.GREEN),
				new Apples(120, Color.GREEN));
		// [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
				List<Apples> greenApples = filterApplesByColor(inventory, Color.GREEN);
				System.out.println(greenApples);

				// [Apple{color=RED, weight=120}]
				List<Apples> redApples = filterApplesByColor(inventory, Color.RED);
				System.out.println(redApples);

				// [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
				greenApples = filter(inventory, new AppleColorPredicate());
				System.out.println(greenApples);

				// [Apple{color=GREEN, weight=155}]
				List<Apples> heavyApples = filter(inventory, new AppleWeightPredicate());
				System.out.println(heavyApples);

				// []
				List<Apples> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
				System.out.println(redAndHeavyApples);

				// [Apple{color=RED, weight=120}]
				redApples = filter(inventory, new ApplePredicate() {
					@Override
					public boolean test(Apples a) {
						return a.getColor() == Color.RED;
					}
				});
				System.out.println(redApples);
			}

			public static List<Apples> filterGreenApples(List<Apples> inventory) {
				List<Apples> result = new ArrayList<>();
				for (Apples apple : inventory) {
					if (apple.getColor() == Color.GREEN) {
						result.add(apple);
					}
				}
				return result;
			}

			public static List<Apples> filterApplesByColor(List<Apples> inventory, Color color) {
				List<Apples> result = new ArrayList<>();
				for (Apples apple : inventory) {
					if (apple.getColor() == color) {
						result.add(apple);
					}
				}
				return result;
			}

			public static List<Apples> filterApplesByWeight(List<Apples> inventory, int weight) {
				List<Apples> result = new ArrayList<>();
				for (Apples apple : inventory) {
					if (apple.getWeight() > weight) {
						result.add(apple);
					}
				}
				return result;
			}

		public static List<Apples> filter(List<Apples> inventory, ApplePredicate p) {
			List<Apples> result = new ArrayList<>();
			for (Apples apple : inventory) {
				if (p.test(apple)) {
					result.add(apple);
				}
			}
			return result;
		}	

enum Color{
	GREEN, RED;
}

public static class Apples{
	private int weight;
	private Color color;
	
	public Apples(int weight, Color color) {
		super();
		this.weight = weight;
		this.color = color;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Apples [weight=" + weight + ", color=" + color + "]";
	}
	}
interface ApplePredicate {
	boolean test(Apples a);
}

static class AppleWeightPredicate implements ApplePredicate {
	@Override
	public boolean test(Apples apple) {
		return apple.getWeight() > 150;
	}
}

static class AppleColorPredicate implements ApplePredicate {
	@Override
	public boolean test(Apples apple) {
		return apple.getColor() == Color.GREEN;
	}
}

static class AppleRedAndHeavyPredicate implements ApplePredicate {
	@Override
	public boolean test(Apples apple) {
		return apple.getColor() == Color.RED && apple.getWeight() > 150;
	}
}
}
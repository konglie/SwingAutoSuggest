/**
 * Created by konglie on 10/11/16.
 */
public class EnumTest {
	public enum Disc {
		CD, DVD, BluRay;

		@Override
		public String toString() {
			return "X " + super.toString();
		}
	}

	public static void main(String[] args){
		System.out.println(Disc.BluRay);
	}
}

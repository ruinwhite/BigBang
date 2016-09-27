package husky;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
	public static void main(String[] args) {
		TestApp app = new TestApp();
		Dog spot = app.new Dog();
		spot.name="spot";
		spot.says="Ruff";
		
		Dog scruffy = app.new Dog();
		spot.name="scruffy";
		spot.says="Wurf";
		
		
	}
	
	class Dog{
		String name;
		String says;
	}
}

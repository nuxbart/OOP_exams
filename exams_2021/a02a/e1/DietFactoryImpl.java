package a02a.e1;

import java.util.Map;


public class DietFactoryImpl implements DietFactory {

	@Override
	public Diet standard() {
		return new Diet() {
			
			Diet diet;
			
			@Override
			public boolean isValid(Map<String, Double> dietMap) {
				var cal = 0.0;
				for(var s : dietMap.keySet() ) {
					cal = dietMap.get(s)/100;
					
				}
				
				return false;
			}
			
			@Override
			public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
				
			}
		};
	}

	@Override
	public Diet lowCarb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diet highProtein() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diet balanced() {
		// TODO Auto-generated method stub
		return null;
	}

}

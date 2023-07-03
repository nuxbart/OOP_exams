package a02b.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

	@Override
	public UniversityProgram flexible() {
		return new UniversityProgram() {

			Map<String, Pair<UniversityProgram.Sector, Integer>> course = new HashMap<>();
			int count = 0;
			
			@Override
			public void addCourse(String name, Sector sector, int credits) {
				course.put(name, new Pair<UniversityProgram.Sector, Integer>(sector,credits));
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var s : courseNames) {
					count += course.get(s).get2();
				}
				return count==60;
			}
			
		};
	}

	@Override
	public UniversityProgram scientific() {
		return new UniversityProgram() {

			Map<String, Pair<Sector, Integer>> course = new HashMap<>();
			int countM = 0;
			int countF = 0;
			int count = 0;
			
			@Override
			public void addCourse(String name, Sector sector, int credits) {
				course.put(name, new Pair<Sector, Integer>(sector,credits));
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var s : courseNames) {
					if(s.equals("MAT1") || s.equals("MAT2")) {
						 countM++;
					}
					if(s.equals("FIS1") || s.equals("FIS2")) {
						countF++;
					}
					count += course.get(s).get2();
				}
				return (countM ==2 && countF == 2 && count == 60);
			}
			
		};
	}

	@Override
	public UniversityProgram shortComputerScience() {
		return new UniversityProgram() {
			Map<String, Pair<Sector, Integer>> course = new HashMap<>();
			int countC = 0;
			int count = 0;
			
			@Override
			public void addCourse(String name, Sector sector, int credits) {
				course.put(name, new Pair<Sector, Integer>(sector,credits));
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var s :courseNames ) {
					if(course.get(s).get1() == UniversityProgram.Sector.COMPUTER_ENGINEERING ||
					   course.get(s).get1() == UniversityProgram.Sector.COMPUTER_SCIENCE ) {
						countC+=course.get(s).get2();
					}
					count += course.get(s).get2();
				}
				return (countC >=30 && count >= 48) ;
			}
			
		};
	}

	@Override
	public UniversityProgram realistic() {
		return new UniversityProgram() {

			Map<String, Pair<Sector, Integer>> course = new HashMap<>();
			int countMF = 0;
			int count = 0;
			
			@Override
			public void addCourse(String name, Sector sector, int credits) {
				course.put(name, new Pair<Sector, Integer>(sector,credits));
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var s : courseNames) {
					if(s.equals("MAT1") || s.equals("MAT2") ||
					   s.equals("FIS1") || s.equals("FIS2")) {
						 countMF++;
					}
					count += course.get(s).get2();
				}
				return (countMF <=3 && count >= 60);
			}
			
		};
	}

}

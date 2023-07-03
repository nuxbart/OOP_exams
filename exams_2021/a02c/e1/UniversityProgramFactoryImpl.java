package a02c.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

	@Override
	public UniversityProgram flexible() {
		return new UniversityProgram() {
			
			Map<String, Pair<Set<Group>, Integer>> c = new HashMap<>();
			int count = 0;
			@Override
			public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
				c = courses;
				
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var course :courseNames) {
					count += c.get(course).get2();
				}
				return count==48;
			}
			
		};
	}

	@Override
	public UniversityProgram fixed() {
		return new UniversityProgram() {
			
			Map<String, Pair<Set<Group>, Integer>> c = new HashMap<>();
			int count = 0;
			int countM = 0;
			int countO =0;
			int countT = 0;
			@Override
			public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
				c = courses;
				
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var course :courseNames) {
					if(c.get(course).get1().contains(UniversityProgram.Group.MANDATORY)) {
						countM+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.OPTIONAL)) {
						countO+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.THESIS)) {
						countT+=c.get(course).get2();
					}
					count+=c.get(course).get2();
				}
				return count==60 && countM==12 && countO==36 && countT==12;
			}
			
		};
	}

	@Override
	public UniversityProgram balanced() {
		return new UniversityProgram() {
			
			Map<String, Pair<Set<Group>, Integer>> c = new HashMap<>();
			int count = 0;
			int countM = 0;
			int countO =0;
			int countT = 0;
			int countF = 0;
			@Override
			public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
				c = courses;
				
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var course :courseNames) {
					if(c.get(course).get1().contains(UniversityProgram.Group.MANDATORY)) {
						countM+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.OPTIONAL)) {
						countO+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.THESIS)) {
						countT+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.FREE)) {
						countF+=c.get(course).get2();
					}
					count+=c.get(course).get2();
				}
				return count==60 && countM==24 && countO>=24 && countT<=12 && countF<=12;
			}
			
		};
	}

	@Override
	public UniversityProgram structured() {
		return new UniversityProgram() {
			
			Map<String, Pair<Set<Group>, Integer>> c = new HashMap<>();
			int count = 0;
			int countM = 0;
			int countO_a =0;
			int countO_b = 0;
			int countF = 0;
			int countT = 0;
			
			@Override
			public void setCourses(Map<String, Pair<Set<Group>, Integer>> courses) {
				c = courses;
				
			}

			@Override
			public boolean isValid(Set<String> courseNames) {
				for(var course :courseNames) {
					if(c.get(course).get1().contains(UniversityProgram.Group.MANDATORY)) {
						countM+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.OPTIONAL_A)) {
						countO_a+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.OPTIONAL_B) &&
					  !c.get(course).get1().contains(UniversityProgram.Group.OPTIONAL_A)) {
						countO_b+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.FREE)) {
						countF+=c.get(course).get2();
					}
					if(c.get(course).get1().contains(UniversityProgram.Group.THESIS)) {
						countT+=c.get(course).get2();
					}
					count+=c.get(course).get2();
				}
				return count==60 && countM==12 && (countO_a + countO_b) == 30 && countO_a>=6 && countO_b>=6 && (countF + countT) == 18;
			}
			
		};
	}

}

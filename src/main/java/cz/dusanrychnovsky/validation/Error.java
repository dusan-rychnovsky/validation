package cz.dusanrychnovsky.validation;

import java.util.List;

public class Error {

	public Error(String fieldName, ErrorMessage message) {
		// TODO Auto-generated constructor stub
	}

	public Error(ErrorMessage valueDoesNotMatchRegexp) {
		// TODO Auto-generated constructor stub
	}

	public Error(Path path, ErrorMessage emailIsMalformed) {
		// TODO Auto-generated constructor stub
	}

	public Error(List<Path> asList, ErrorMessage dateRangeIsInvalid) {
		// TODO Auto-generated constructor stub
	}

}

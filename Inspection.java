package project3;

public class Inspection {
	Date date;
	int score;
	String violation;
	String risk;
	
	public Inspection(Date date, int score, String violation, String risk) throws IllegalArgumentException {
		if (date == null) {
			throw new IllegalArgumentException("Illegal inspection date entered");
		}
		if (score < 0 || score > 100) {
			throw new IllegalArgumentException("Illegal inspection score entered");
		}
		this.date = date;
		this.score = score;
		this.violation = violation;
		this.risk = risk;
	}
	
	/**
	 * @return a string in the format "score, date, risk, violation"
	 */
	@Override
	public String toString( ) {
		String inspectionString = score + ", " + date;
		if (risk != null) {
			inspectionString += ", " + risk + ", ";
		}
		
		if (violation != null) {
			inspectionString += violation;
		}
		return inspectionString;
	}

	/**
	 * @param o; an inspection object to compare to
	 * @return true if two inspection objects are equal, false otherwise
	 * Two inspection objects are equal if all of their score, date, risk, and violation values are equal
	 */
	public boolean equals(Inspection o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Inspection)) {
			return false;
		}
		Inspection other = (Inspection) o;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		}
		if (score == 0) {
			if (other.score != 0) {
				return false;
			}
		}
		if (violation == null) {
			if (other.violation != null) {
				return false;
			}
		}
		if (risk == null) {
			if (other.violation != null) {
				return false;
			}
		}
		else if (!date.equals(other.date)  || score != other.score || !violation.equalsIgnoreCase(other.violation) || !risk.equalsIgnoreCase(other.risk)) {
			return false;
		}
		return true;
	}
}

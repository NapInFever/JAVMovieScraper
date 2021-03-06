package moviescraper.doctord.dataitem;



public class MPAARating extends MovieDataItem {
	
	private String MPAARating;
	
	public static final MPAARating RATING_XXX = new MPAARating("XXX");

	public MPAARating(String MPAARating) {
		setMPAARating(MPAARating);
	}

	public String getMPAARating() {
		return MPAARating;
	}

	public void setMPAARating(String mPAARating) {
		this.MPAARating = sanitizeString(mPAARating);
	}

	@Override
	public String toString() {
		return "MPAARating [MPAARating=" + MPAARating + "]";
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}

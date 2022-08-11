package com.masai.enums;

public enum Slot {
	
	SLOT1{		
		@Override
	    public String timeslot() {	    	
	    	return "9:00-9:30";
	    }
	},	
	
	SLOT2{		
		@Override
	    public String timeslot() {	    	
	    	return "9:30-10:00";
	    }
	},	
	
	SLOT3{		
		@Override
	    public String timeslot() {	    	
	    	return "10:00-10:30";
	    }
	},
	
	SLOT4{		
		@Override
	    public String timeslot() {	    	
	    	return "10:30-11:00";
	    }
	},	
	
	SLOT5{		
		@Override
	    public String timeslot() {	    	
	    	return "11:00-11:30";
	    }
	},	
	
	SLOT6{		
		@Override
	    public String timeslot() {	    	
	    	return "11:30-12:00";
	    }
	},
	
	SLOT7{		
		@Override
	    public String timeslot() {	    	
	    	return "1:00-1:30";
	    }
	},	
	
	SLOT8{		
		@Override
	    public String timeslot() {	    	
	    	return "1:30-2:00";
	    }
	},	
	
	SLOT9{		
		@Override
	    public String timeslot() {	    	
	    	return "2:00-2:30";
	    }
	},
	
	SLOT10{		
	    @Override
	    public String timeslot() {	    	
	    	return "2:30-3:00";
	    }		
	};
	
	public String timeslot() {
		return null;
	}

	
}

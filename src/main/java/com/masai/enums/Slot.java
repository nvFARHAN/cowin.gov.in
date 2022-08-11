package com.masai.enums;

public enum Slot {
	
	SLOT1{
		@Override
		public void setSlot() {
			timeslot = "9:00-9:30";
		}
	},	SLOT2{
		@Override
		public void setSlot() {
			timeslot = "9:30-10:00";
		}
	},	SLOT3{
		@Override
		public void setSlot() {
			timeslot = "10:00-10:30";
		}
	},
	SLOT4{
		@Override
		public void setSlot() {
			timeslot = "10:30-11:00";
		}
	},	SLOT5{
		@Override
		public void setSlot() {
			timeslot = "11:00-11:30";
		}
	},	SLOT6{
		@Override
		public void setSlot() {
			timeslot = "11:30-12:00";
		}
	},
	SLOT7{
		@Override
		public void setSlot() {
			timeslot = "1:00-1:30";
		}
	},	SLOT8{
		@Override
		public void setSlot() {
			timeslot = "1:30-2:00";
		}
	},	SLOT9{
		@Override
		public void setSlot() {
			this.timeslot = "2:00-2:30";
		}
	},SLOT10{
		@Override
		public void setSlot() {
			this.timeslot = "2:30-3:00";
		}
	};
	
	public String timeslot;

	public void setSlot() {
		this.timeslot = null;
	}
	
	

	
}

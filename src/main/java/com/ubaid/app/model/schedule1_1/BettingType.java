package com.ubaid.app.model.schedule1_1;

public enum BettingType
{
	HomeDrawAway(69) {
		@Override
		public String toString() {
			return "HomeDrawAway";
		}
	},
	
	OverUnder(47) {
		@Override
		public String toString() {
			return "OverUnder";
		}
	},
	
	AssianHandicap(48) {
		@Override
		public String toString() {
			return "AssianHandicap";
		}
	},
	
	Null(-1) {
		@Override
		public String toString() {
			return "NULL";
		}
	};
	
	private int id;
    BettingType(int id) { this.id = id; }
    public int getValue() { return id; }	
    
    public static BettingType getBettingType(long id)
    {
    	for(BettingType bettingType : BettingType.values())
    	{
    		if(bettingType.getValue() == id)
    			return bettingType;
    	}
    	
    	return BettingType.Null;
    }

}

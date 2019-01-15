package com.ubaid.app.model.schedule1_1;

public enum BettingType
{
	HomeDrawAway {
		@Override
		public String toString() {
			return "HomeDrawAway";
		}
	},
	
	OverUnder {
		@Override
		public String toString() {
			return "OverUnder";
		}
	},
	
	AssianHandicap {
		@Override
		public String toString() {
			return "AssianHandicap";
		}
	}
}

package com.ubaid.app.model.schedule1_1;

public enum Helper
{
	OUTCOMEID {
		@Override
		public String toString()
		{
			return "outcomeId";
		}
	},
	
	HOMETEAM {
		@Override
		public String toString() {
			return "homeTeam";
		}
	},
	
	AWAYTEAM {
		@Override
		public String toString() {
			return "awayTeam";
		}
	},

	PARTICIPANT {
		@Override
		public String toString() {
			return "participant";
		}
	},

	LEAGUENAME {
		@Override
		public String toString() {
			return "leagueName";
		}
	},

	MATCHNAME {
		@Override
		public String toString() {
			return "matchName";
		}
	},

	THRESHOLD {
		@Override
		public String toString() {
			return "threshold";
		}
	},

	ODDS {
		@Override
		public String toString() {
			return "odds";
		}
	},
	
	LASTUPDATETIME {
		@Override
		public String toString() {
			return "lastUpdateTime";
		}
	},
	
	OLDODDS {
		@Override
		public String toString() {
			return "oldOdds";
		}
	},
	
	OLDThresHOLD{
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "oldThreshold";
		}
	}

}

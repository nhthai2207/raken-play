package racom.be.config;

public enum TableStructure {
	AdkUser("Id", ""), counters("EntityId", ""), Click("EntityId", "createdAt"), Conversion("EntityId", "createdAt"), View("EntityId", "createdAt"), CounterDaily("EntityId", ""), HourlyReport(
			"HourTime", "EntityId"), LanderHourlyReport("HourTime", "uniqueKey");
	TableStructure(String hashKey, String rankKey) {
		this.hashKey = hashKey;
		this.rankKey = rankKey;
	}

	public String hashKey;
	public String rankKey;

}
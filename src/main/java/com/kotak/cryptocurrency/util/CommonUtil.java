package com.kotak.cryptocurrency.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public final class CommonUtil {

	private CommonUtil() {
	}
	
	public static Timestamp getTimeTillLastSecondOfTheDay(Date srcDate) {
		LocalDateTime ldt = new Timestamp(srcDate.getTime()).toLocalDateTime().withHour(23).withMinute(59).withSecond(59);
		return Timestamp.valueOf(ldt);
	}
	
	public static Timestamp getBackTimestamp(Date srcDate, int backDays) {
		LocalDateTime ldt = new Timestamp(srcDate.getTime()).toLocalDateTime().minusDays(backDays).withHour(23)
				.withMinute(59).withSecond(59);
		return Timestamp.valueOf(ldt);
	}
}

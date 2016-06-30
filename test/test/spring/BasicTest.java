package test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BasicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String configLocation = Play.application().configuration().getString("spring/application-context.xml");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/spring/application-context.xml");
		
		/*ReportingDao reportingDao = (ReportingDao) applicationContext.getBean(ReportingDao.class);
		Calendar startDate = Calendar.getInstance();		
		startDate.set(Calendar.HOUR_OF_DAY, 5);
		Calendar endDate = Calendar.getInstance();		
		endDate.set(Calendar.HOUR_OF_DAY, 6);
		List<RawData> findDataInTimeRange = reportingDao.findDataInTimeRange(startDate, endDate);
		System.out.println(findDataInTimeRange.size());*/
	}

}

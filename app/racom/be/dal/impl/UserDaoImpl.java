package racom.be.dal.impl;

import org.springframework.stereotype.Service;

import racom.be.dal.UserDao;
import racom.be.model.User;

@Service("userDao")
public class UserDaoImpl implements UserDao {
	private static String cf = "AdkUser";	
	private static String NAME = "name";
	private static String CREATED = "created";


	@Override
	public void save(User user) {
		try {
			/*DBUtils dbUtils = DBFactory.getDBUtils();
			dbUtils.startTransaction();
			Map<String, String> data = new HashMap<String, String>();
			data.put(NAME, user.getName());
			data.put(CREATED, String.valueOf(user.getCreated()));
			dbUtils.insertRowData(cf, user.getId(), data);
			dbUtils.executeTransaction();*/
		
		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(String.format("ERROR: ") ,e);
		}
	}
}

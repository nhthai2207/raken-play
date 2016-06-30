package racom.be.jobs;

import java.util.Calendar;

import javax.persistence.EntityManager;

import racom.be.utils.Constant;

public class GetDataScheduler extends SchedulableActor {

	private EntityManager entityManager;

	private static final Integer TICK_TIME; // ms
	static {
		if (Constant.isLocal) {
			TICK_TIME = 5 * 60 * 1000;
		} else {
			TICK_TIME = 5 * 60 * 1000;
		}
	}

	public GetDataScheduler(EntityManager entityManager) {
		super("GetDataSchedulerActor");
		this.entityManager = entityManager;

	}

	@Override
	protected int giveTickTime() {
		return TICK_TIME;
	}

	@Override
	public void onReceive(Object message) {
		play.Logger.info("ON GetDataScheduler " + Calendar.getInstance().getTime().toString());
		try {
			if (message instanceof Tick) {
				

			} else if (message instanceof String) {

			} else {
				unhandled(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error("GetDataScheduler => onReceive ", e);
		}
	}
}

package racom.be.jobs;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;

public abstract class SchedulableActor extends UntypedActor {

	private Cancellable ticker = null;
	protected String name;
	protected static AtomicBoolean running = new AtomicBoolean(false);

	public SchedulableActor(String name) {
		this.name = name;
	}

	/**
	 * set how long actor will receive {@link Tick} message (in milliseconds)
	 * 
	 * @return Tick time interval
	 */
	protected abstract int giveTickTime();

	protected CronTime giveFirstStartAt() {
		return null;
	};

	protected boolean startJob(boolean singleton) throws Exception {
		return false;
	}

	protected void endJob() throws Exception {
	}

	@Override
	public void preStart() throws Exception {
		super.preStart();
		if (giveTickTime() > 0) {
			ticker = getContext()
					.system()
					.scheduler()
					.schedule(convertCronTimeToDuration(), Duration.create(giveTickTime(), TimeUnit.MILLISECONDS), getSelf(), new Tick(),
							getContext().dispatcher(), ActorRef.noSender());
		}
	}

	@Override
	public void postStop() throws Exception {
		if (ticker != null) {
			ticker.cancel();
			ticker = null;
		}
		super.postStop();
	}

	private FiniteDuration convertCronTimeToDuration() {
		CronTime cronTime = giveFirstStartAt();
		if (cronTime != null) {
			Calendar c = Calendar.getInstance();
			if (cronTime.getYear() != null) {
				c.set(Calendar.YEAR, cronTime.getYear());
			}
			if (cronTime.getMonth() != null) {
				c.set(Calendar.MONTH, cronTime.getMonth());
			}
			if (cronTime.getDate() != null) {
				c.set(Calendar.DAY_OF_MONTH, cronTime.getDate());
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
			}
			if (cronTime.getHour() != null) {
				c.set(Calendar.HOUR_OF_DAY, cronTime.getHour());
				c.set(Calendar.MINUTE, 0);
			}
			if (cronTime.getMinute() != null) {
				c.set(Calendar.MINUTE, cronTime.getMinute());
			}

			long delayInSeconds = (c.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 1000;
			return FiniteDuration.create(delayInSeconds, TimeUnit.SECONDS);
		} else {
			return Duration.create(500, TimeUnit.MILLISECONDS);
		}
	}
}

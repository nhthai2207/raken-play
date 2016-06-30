package racom.be.utils;

import java.util.ArrayList;
import java.util.List;

import play.Play;

import com.amazonaws.regions.Regions;

public class Constant {

	public static final String entityDataURL;
	public static final String UUID = "uuid";
	public static final Regions REGION;
	public static boolean isLocal = false;
	public static boolean isUseDocker = false;
	public static String COUNTER_CF = "counters";
	public static String COUNTER_DAILY_CF = "CounterDaily";
	public static String CLICK_TYPE_KEY = "click_type";
	public static String OFFER_KEY = "offer_key";
	public static String OFFER_COUNTER_DBKEY = "Offer_Counter";
	public static String REVENUE_DBKEY = "Revenue_Counter";

	public static String JSON_EVENT_TYPE_PARAM = "event_type";

	public static enum JSON_EVENT_TYPE {
		offer_click, lp_click, view, conversion;
	};

	public static enum EVENT_TYPE {
		CPC("Click"), CPV("View"), CPA("Conversion"), CPCO ("Click");
		private String cf;

		EVENT_TYPE(String cf) {
			this.setCf(cf);
		}

		public String getCf() {
			return cf;
		}

		public void setCf(String cf) {
			this.cf = cf;
		}
	};

	public static List<String> COUNTER_TYPE_LIST;

	static {
		entityDataURL = Play.application().configuration().getString("entityDataURL", "");				
		REGION = Regions.valueOf(Play.application().configuration().getString("region", ""));
		COUNTER_TYPE_LIST = new ArrayList<String>();
		for (EVENT_TYPE eventType : EVENT_TYPE.values()) {
			COUNTER_TYPE_LIST.add(eventType.cf);
		}
		COUNTER_TYPE_LIST.add(OFFER_COUNTER_DBKEY);
		COUNTER_TYPE_LIST.add(REVENUE_DBKEY);
	}

	public static enum CLICK_TYPE {
		LP, OFFER
	};

	public static enum EVENT_FIELD {
		UUID, timestamp, entity_id, entity_name, entity_key, event_category, event_type, event_name, event_payload, u_ip, u_device, u_ua, u_browser, u_os, u_country, u_region, u_city, u_postal_code, u_latitude, u_longitude, u_isp, u_org, u_lang, u_timezone, u_screen_size, u_screen_color, u_request_url, u_referrer, u_access, p_campaign, p_cost_unit, p_cost_per_unit, p_payout, p_source, p_offer, p_status, redirect_to, redirect_to_key
	};

}

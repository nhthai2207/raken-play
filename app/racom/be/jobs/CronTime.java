package racom.be.jobs;

public class CronTime {

	Integer year;
	Integer month;
	Integer date;
	Integer hour;
	Integer minute;
	public CronTime(int year, int month, int date, int hour, int minute) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
	}
	public CronTime(){
		
	}
	public CronTime setYear(int year){
		this.year = year;
		return this;
	}
	public CronTime setMonth(int month){
		this.month = month;
		return this;
	}
	public CronTime setDate(int date){
		this.date = date;
		return this;
	}
	public CronTime setHour(int hour){
		this.hour = hour;
		return this;
	}
	public CronTime setMinute(int minute){
		this.minute = minute;
		return this;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	
	
}

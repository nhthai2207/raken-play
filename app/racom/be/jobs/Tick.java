package racom.be.jobs;

import java.io.Serializable;

public class Tick implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6595284427411035707L;
	
	private Long id;
	
	public Tick(){}
	
	public Tick(Long id) {
		this.id = id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	};
}

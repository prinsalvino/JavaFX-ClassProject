package javaFx;

public class line {
	long id = 0;
	String text;
	
	public line(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
	public line() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

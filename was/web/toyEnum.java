package denall.admin.was.web;

public enum toyEnum {

	defaultService("전체"),
	defaultCategory("전체");

	public String defaultValue;
	toyEnum(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}

package polyForm;

class Field {
	private String label = "Undefined";
	private Object value = "";
	private Object defaultValue = "undefined";
	
	Object getZeroValue() {
		return "";
	}

	int labelDisplayLength = 20;
	int valueDisplayLength = 30;
	
	Field(String label) {
		this.label = label;
	}
	
	static String nOf(String str, int nTimes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nTimes; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	void setValue(Object value) {
		this.value = value;
	}

	void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	String getLabel() {
		return label;
	}

	String getValue() {
		String strValue = value.toString();
		if (strValue.equals(""))
			return getDefaultValue();
		else
			return value.toString();
	}

	String getDefaultValue() {
		return defaultValue.toString();
	}

	String getType() {
		return "Field";
	}

	String getFieldDefinition() {
		return getType() + "[" + "label=" + getLabel() + ", default_value=" + getDefaultValue() + ", value= "
				+ getValue() + "]";
	}

	String displayLabel() {
		return label + Field.nOf(" ", labelDisplayLength - label.length()) + ": ";
	}

	String displayValue() {
		return getValue();
	}
	
	boolean isValid() {
		return false;
	}
}

class TextField extends Field {
	TextField(String label) {
		super(label);
	}
	
	String getType() {
		return "TextField";
	}

	String displayValue() {
		return getValue() + Field.nOf("_", valueDisplayLength - getValue().length());
	}

	boolean isValid() {
		return getValue().length() <= valueDisplayLength;
	}
}

class EmailField extends TextField {
	EmailField(String label) {
		super(label);
	}
	
	String getType() {
		return "EmailField";
	}

	boolean isValid() {
		return getValue().contains("@") && getValue().contains(".");
	}
}

class PasswordField extends TextField {
	PasswordField(String label) {
		super(label);
	}
	
	String getType() {
		return "PasswordField";
	}
	
	String getValue() {
		if (super.getValue().equals(getDefaultValue()))
			return super.getValue();
		else
			return Field.nOf("*", super.getValue().length());
	}

	boolean isValid() {
		return super.getValue().contains("!") || super.getValue().contains("$");
	}
}

class NumberField extends Field {
	Object getZeroValue() {
		return 0;
	}
	
	NumberField(String label) {
		super(label);
	}
	
	String getType() {
		return "NumberField";
	}

	String displayValue() {
		return Field.nOf("_", valueDisplayLength - getValue().length()) + getValue();
	}

	boolean isValid() {
		return getNumberValue() >= 0;
	}

	long getNumberValue() {
		return Long.parseLong(super.getValue());
	}
}

class PhoneField extends NumberField {
	Object getZeroValue() {
		return 1111111111L;
	}
	
	PhoneField(String label) {
		super(label);
	}
	
	String getType() {
		return "PhoneField";
	}

	String getValue() {
		String pNo = super.getValue();
		return pNo.substring(0, 3) + "-" + pNo.substring(3, 6) + "-" + pNo.substring(6);
	}

	boolean isValid() {
		return super.isValid() && getValue().length() == 12;
	}
}

class PercentField extends NumberField {
	PercentField(String label) {
		super(label);
	}
	
	String getType() {
		return "PercentField";
	}

	String getValue() {
		return super.getValue() + "%";
	}

	boolean isValid() {
		return super.isValid() && getNumberValue() <= 100;
	}
}
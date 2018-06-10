package polyForm;

import java.util.ArrayList;
import java.util.List;

public class UserForm {

	public static void printFieldLine(Field field) {
		System.out.println(field.displayLabel() + field.displayValue());
	}

	public static void printForm(List<Field> form) {
		System.out.println("---------------- SIGN UP ---------------");
		for (Field field : form) {
			printFieldLine(field);
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}

	public static void printFormDefinition(List<Field> form) {
		System.out.println("---------------- SIGN UP ---------------");
		for (Field field : form) {
			System.out.println(field.getFieldDefinition());
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
	public static void resetForm(List<Field> form) {
		for (Field field : form) {
			field.setValue(field.getZeroValue());
		}
	}

	public static void main(String[] args) {
		Field myField = new Field("undefined");

		System.out.println(myField.getFieldDefinition());
		System.out.println(myField.displayLabel());

		System.out.println(myField.displayLabel() + myField.displayValue());

		TextField name = new TextField("Name");
		name.setDefaultValue("Full Name");

		System.out.println(name.getFieldDefinition());
		printFieldLine(name);

		TextField company = new TextField("Company");
		company.setDefaultValue("Company Name");
		printFieldLine(company);

		System.out.println("------------");
		
		// this is where the beauty of polymorphism shines
		List<Field> signUpForm = new ArrayList<>();
		signUpForm.add(name);
		signUpForm.add(company);
		
		for (Field field : signUpForm) {
			System.out.println(field.getFieldDefinition());
		}
		
		System.out.println("------------");
		for (Field field : signUpForm) {
			printFieldLine(field);
		}
		
		name.setValue("Halil Aslan");
		System.out.println("name valid: " + name.isValid());
		
		EmailField email = new EmailField("Email");
		email.setDefaultValue("user@domain.com");
		
		signUpForm.add(email);
		printForm(signUpForm);
		
		System.out.println();
		company.setValue("CyberTek");	
		printForm(signUpForm);
		
		email.setValue("halil@cybertekschool.com");
		System.out.println("email valid: " + email.isValid());
		
		printFormDefinition(signUpForm);
		
		PasswordField password = new PasswordField("Password");
		password.setDefaultValue("Enter password");
		
		signUpForm.add(password);
		printForm(signUpForm);
		
		System.out.println();
		password.setValue("12345!");
		printForm(signUpForm);
		System.out.println("password valid: " + password.isValid());  
		
		Field size = new NumberField("Company Size");
		size.setDefaultValue("Number of Employees");
		signUpForm.add(size);
		
		size.setValue(25);  // try -1
		System.out.println("Size valid: " + size.isValid()); 
		printForm(signUpForm);
		
		Field phone = new PhoneField("Phone");
		phone.setDefaultValue("Phone number");		
		signUpForm.add(phone);
		
		phone.setValue(1234567890L);		// no alphanumeric check, will crash
		System.out.println("phone valid: " + phone.isValid());
													// try with phone with one digit less
 		printForm(signUpForm);
		
		Field likely = new PercentField("Likely to buy");
		likely.setDefaultValue(99);		
		signUpForm.add(likely);
		
		System.out.println("likely valid: " + likely.isValid()); 
													
 		printForm(signUpForm);
 		
  		resetForm(signUpForm);
 		printForm(signUpForm);
 		
 		//if you still have time 
 		//implement checkForm to see if all fields are valid
	}

}
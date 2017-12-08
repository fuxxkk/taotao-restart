package com.demo.test.freemarker;

import java.io.Serializable;
import java.util.Date;

public class Empolyee implements Serializable {

	private Integer id;
	private String name;
	private Integer age;
	private Boolean gender;
	private Date birthday;
	private Integer workAge;
	public Empolyee() {
		super();
	}
	public Empolyee(Integer id, String name, Integer age, Boolean gender, Date birthday, Integer workAge) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.birthday = birthday;
		this.workAge = workAge;
	}

	public Empolyee(String name, Integer age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getWorkAge() {
		return workAge;
	}
	public void setWorkAge(Integer workAge) {
		this.workAge = workAge;
	}
	@Override
	public String toString() {
		return "Empolyee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", birthday="
				+ birthday + ", workAge=" + workAge + "]";
	}
	
}

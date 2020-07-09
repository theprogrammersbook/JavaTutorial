package com.muthyatechnology.customannotations.usage;

import javax.validation.constraints.NotNull;

import com.muthyatechnology.constraint.annotations.IssueInfo;
import com.muthyatechnology.constraint.annotations.IssueInfo.Type;
import com.muthyatechnology.constraint.annotations.OnlyStringValue;

@IssueInfo(type = Type.IMPROVEMENT, reporter = "Class")
public class IssueInfoTest {
	@IssueInfo(type = Type.BUG, reporter = "Field")
	@NotNull(message="Name can not be null")
	@OnlyStringValue
	public String name;
	@IssueInfo(type = Type.FEATURE, reporter = "Method")
	public String returnName(){
		return "Nagaraju";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}


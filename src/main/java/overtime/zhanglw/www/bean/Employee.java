package overtime.zhanglw.www.bean;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4587680282643629645L;

	private String name;

	private String EmployeeNo;

	private List<OverTimeBean> ovetTimeList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeNo() {
		return EmployeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		EmployeeNo = employeeNo;
	}

	public List<OverTimeBean> getOvetTimeList() {
		return ovetTimeList;
	}

	public void setOvetTimeList(List<OverTimeBean> ovetTimeList) {
		this.ovetTimeList = ovetTimeList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

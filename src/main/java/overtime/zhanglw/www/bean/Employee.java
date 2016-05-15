package overtime.zhanglw.www.bean;

import java.io.Serializable;
import java.util.List;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4587680282643629645L;

	private String name;

	private String emptyId;
	
	private String id;
	
	private String position;

	private List<OverTimeBean> ovetTimeList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmptyId() {
		return emptyId;
	}

	public void setEmptyId(String emptyId) {
		this.emptyId = emptyId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}

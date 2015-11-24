package entpay.model;

import entpay.util.ModelUtil;

public abstract class BaseModel {

	public String toString() {
		return  ModelUtil.toJson(this);
	}
}

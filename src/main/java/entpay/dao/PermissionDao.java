package entpay.dao;

import entpay.entity.Permission;


public interface PermissionDao {
	
	public Permission getById(int id, int type);
}

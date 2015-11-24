package entpay.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Permission implements RowMapper<Permission> {

	public static final int STATUS_ACTIVE = 0;
	public static final int STATUS_INACTIVE = 1;
	
	private int merchantId;
	private String key;
	private int type;
	private int status;
	private int create;
	private int update;
	
	@Override
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
		Permission permission = new Permission();
		permission.setMerchantId(rs.getInt("merchant_id"));
		permission.setKey(rs.getString("app_key"));
		permission.setType(rs.getInt("type"));
		permission.setStatus(rs.getInt("status"));
		permission.setCreate(rs.getInt("created"));
		permission.setUpdate(rs.getInt("updated"));
		
		return permission;
	}
	
	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreate() {
		return create;
	}

	public void setCreate(int create) {
		this.create = create;
	}

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int update) {
		this.update = update;
	}
	
}

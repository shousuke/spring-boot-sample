package entpay.dao;

import entpay.entity.Member;

public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {
	
//	private static final Logger log = Logger.getLogger("debugLogger");
	
	public MemberDaoImpl() {
		super(Member.class);
	}
	
}

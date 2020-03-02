package com.khjxiaogu.scriptengine.core.syntax.operator.p15;

import java.util.List;

import com.khjxiaogu.scriptengine.core.Exception.KSException;
import com.khjxiaogu.scriptengine.core.Object.KEnvironment;
import com.khjxiaogu.scriptengine.core.Object.KObject;
import com.khjxiaogu.scriptengine.core.Object.KVariant;
import com.khjxiaogu.scriptengine.core.syntax.AssignOperation;
import com.khjxiaogu.scriptengine.core.syntax.Assignable;
import com.khjxiaogu.scriptengine.core.syntax.CodeNode;
import com.khjxiaogu.scriptengine.core.syntax.LiteralNode;
import com.khjxiaogu.scriptengine.core.syntax.SyntaxError;
import com.khjxiaogu.scriptengine.core.syntax.operator.DoubleOperator;
import com.khjxiaogu.scriptengine.core.syntax.operator.MemberOperator;

/**
 * @author khjxiaogu
 * @time 2020年2月16日
 */
public class Member extends DoubleOperator implements MemberOperator {

	/**
	 * 
	 */
	public Member() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public KVariant eval(KEnvironment env) throws KSException {
		// TODO Auto-generated method stub
		return super.left.eval(env).asType(KObject.class).getMemberByName(((LiteralNode) super.right).getToken());
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public void getClassPath(KEnvironment env, List<String> dest) throws KSException {
		// TODO Auto-generated method stub
		if (super.left instanceof MemberOperator) {
			((MemberOperator) super.left).getClassPath(env, dest);
		}
		dest.add(((LiteralNode) super.right).getToken());
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return ".";
	}

	@Override
	public KVariant assign(KEnvironment env, KVariant val) throws KSException {
		// TODO Auto-generated method stub
		return ((Assignable) super.left).getObject(env).setMemberByName(((LiteralNode) super.right).getToken(), val);
	}

	@Override
	public KObject getObject(KEnvironment env) throws KSException {
		// TODO Auto-generated method stub
		return (KObject) ((Assignable) super.left).getObject(env)
				.getMemberByName(((LiteralNode) super.right).getToken()).toType("Object");
	}

	@Override
	public void setChildren(CodeNode... codeNodes) throws KSException {
		// TODO Auto-generated method stub
		super.setChildren(codeNodes);
		if (!(super.left instanceof Assignable && super.right instanceof LiteralNode)) {
			throw new SyntaxError("错误的表达式");
		}
	}

	@Override
	public KVariant assignOperation(KEnvironment env, KVariant val, AssignOperation op) throws KSException {
		return ((Assignable) super.left).getObject(env).DoOperatonByName(op, ((LiteralNode) super.right).getToken(),
				val);
	}

	@Override
	public KEnvironment getSuperEnvironment(KEnvironment env) throws KSException {
		return null;
	}

	@Override
	public KVariant getPointing(KEnvironment env) throws KSException {
		return ((LiteralNode) super.right).getPointing(env);
	}
}
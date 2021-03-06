package com.khjxiaogu.scriptengine.core.syntax.statement;

import java.util.List;

import com.khjxiaogu.scriptengine.core.KVariant;
import com.khjxiaogu.scriptengine.core.ParseReader;
import com.khjxiaogu.scriptengine.core.exceptions.KSException;
import com.khjxiaogu.scriptengine.core.object.Closure;
import com.khjxiaogu.scriptengine.core.object.KEnvironment;
import com.khjxiaogu.scriptengine.core.object.KObject;
import com.khjxiaogu.scriptengine.core.syntax.ASTParser;
import com.khjxiaogu.scriptengine.core.syntax.CodeNode;
import com.khjxiaogu.scriptengine.core.syntax.Visitable;
import com.khjxiaogu.scriptengine.core.syntax.operator.MemberOperator;

/**
 * @author khjxiaogu
 * @time 2020年3月2日
 *       project:khjScriptEngine
 */
public class VarStatement implements Visitable, ASTParser, MemberOperator, CodeNode {

	/**
	 *
	 */
	private ArgumentNode Child;

	public VarStatement() {
	}

	int itoken = -1;
	// List<String> tokens;
	String token;

	@Override
	public String toString() {
		return "var " + Child.toString();
	}

	@Override
	public KVariant eval(KEnvironment env) throws KSException {
		/*
		 * if (itoken != -1) {
		 * return env.setMemberByNum(itoken, new KVariant());
		 * }
		 * return env.setMemberByName(token, new KVariant());
		 */
		if (env instanceof Closure)
			return Child.evalAsVar(env);
		return Child.eval(env);
	}

	@Override
	public void Visit(List<String> parentMap) throws KSException {
		Child.VisitAsVar(parentMap);
	}

	@Override
	public CodeNode parse(ParseReader reader) throws KSException {
		Child = (ArgumentNode) new ArgumentNode(';').parse(reader);
		return this;
	}

	@Override
	public KObject getObject(KEnvironment env) throws KSException {
		return null;
	}

	@Override
	public KEnvironment getSuperEnvironment(KEnvironment env) throws KSException {
		return null;
	}

	@Override
	public KVariant getPointing(KEnvironment env) throws KSException {
		return null;
	}

	@Override
	public void VisitAsChild(List<String> parentMap) throws KSException {
		Child.VisitAsVarChild(parentMap);
	}

}

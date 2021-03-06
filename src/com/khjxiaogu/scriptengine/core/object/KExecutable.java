package com.khjxiaogu.scriptengine.core.object;

/**
 * @author khjxiaogu
 * @time 2019年8月23日 file:KExecutable.java
 */
@FunctionalInterface
public interface KExecutable {
	public Object execute(KEnvironment env);
}

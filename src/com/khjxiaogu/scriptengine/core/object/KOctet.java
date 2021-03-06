package com.khjxiaogu.scriptengine.core.object;

import com.khjxiaogu.scriptengine.core.KVariant;
import com.khjxiaogu.scriptengine.core.exceptions.AccessDeniedException;
import com.khjxiaogu.scriptengine.core.exceptions.KSException;

public final class KOctet {
	final byte[] intern;
	ExtendableClosure backed;
	public final static KOctet NullOctet=new KOctet(0);
	public KOctet(byte[] intern){
		this.intern=intern;
		this.backed=new OctetObject(intern);
	}
	public KOctet(int size) {
		this(new byte[size]);
	}
	class OctetObject extends ExtendableClosure {
		public OctetObject(byte[] intern){
			super("Octet");
			try {
				super.putNativeInstance(intern);
				this.setMemberByName("length",
					new KVariant(
						new PropertyClosure(
							new NativeProperty<byte[]>(
									byte[].class,
								(e)->{
									return new KVariant(e.length);
								},null
							)
						)
					),KEnvironment.DEFAULT
				);
			} catch (KSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public KVariant getMemberByVariant(KVariant var, int flag) throws KSException {
			if(var.getType().getType().equals(Integer.class))
				return new KVariant(this.getNativeInstance(byte[].class)[var.getInt()]);
			return super.getMemberByVariant(var, flag);
		}

		@Override
		public KVariant setMemberByVariant(KVariant var, KVariant val, int flag) throws KSException {
			if(var.getType().getType().equals(Integer.class)) {
				this.getNativeInstance(byte[].class)[var.getInt()]=(byte)(int)val.getInt();
				return val;
			}
			throw new AccessDeniedException();
		}

		@Override
		public KVariant setMemberByName(String name, KVariant val, int flag) throws KSException {
			throw new AccessDeniedException();
		}

	}

	public final byte[] getBytes() {
		return intern;
	}
	public final KObject getObject() {
		return backed;
	}
}

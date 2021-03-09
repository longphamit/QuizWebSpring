package com.longpc.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
	public static final int SALT=11;
	public static String hashPassword(String password) {
		return BCrypt.hashpw(password,BCrypt.gensalt(SALT));
	}
}

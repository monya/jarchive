package com.github.jamescarter.jarchive;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class JServiceLocator {
	private static final ArrayList<Class<?>> serviceList = new ArrayList<Class<?>>();

	static {
		try {			
			Enumeration<URL> serviceURLs = ClassLoader.getSystemResources("META-INF/services/com.github.jamescarter.jarchive.JInputStream");

			while (serviceURLs.hasMoreElements()) {
				URL serviceURL =  serviceURLs.nextElement();

				BufferedReader br = new BufferedReader(new InputStreamReader((InputStream) serviceURL.getContent()));
				String className;

				while((className = br.readLine()) != null) {
					serviceList.add(Class.forName(className));
				}

				br.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Class<?>[] getServices() {
		return serviceList.toArray(new Class<?>[serviceList.size()]);
	}

	public static Class<?> getFirstSupportedService(String name) {
		FileExtensions fileExt;

		for (Class<?> clazz : serviceList) {
			fileExt = clazz.getAnnotation(FileExtensions.class);

			if (fileExt == null) {
				continue;
			}

			for (String value : fileExt.values()) {
				if (name.endsWith("." + value)) {
					return clazz;
				}
			}
		}

		return null;
	}
}

package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

public class JRAWInputStream extends JInputStream {
	public JRAWInputStream(JFile file, InputStream is) {
		super(file, is);
	}

	@Override
	public JFile getNextEntry() throws IOException {
		JFile myJFile = getFile();

		setFile(null);

		return myJFile;
	}
}

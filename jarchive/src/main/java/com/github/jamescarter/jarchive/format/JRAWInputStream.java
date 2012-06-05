package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

public class JRAWInputStream extends JInputStream {
	private JFile file;

	public JRAWInputStream(JFile file, InputStream is) {
		super(is);
		this.file = file;
	}

	@Override
	public JFile getNextEntry() throws IOException {
		JFile myJFile = file;
		file = null;

		return myJFile;
	}
}

package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import org.tukaani.xz.XZInputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;

@FileExtensions(values = { "tar.xz" })
public class JXZInputStream extends JTARInputStream {
	public JXZInputStream(JFile file, InputStream is) throws IOException {
		super(file, new XZInputStream(is));
	}

	@Override
	public JFile getNextEntry() throws IOException {
		return super.getNextEntry();
	}
}
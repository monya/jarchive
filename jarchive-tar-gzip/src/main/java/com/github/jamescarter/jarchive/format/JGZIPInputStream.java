package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;

@FileExtensions(values = { "tar.gz" })
public class JGZIPInputStream extends JTARInputStream {
	public JGZIPInputStream(JFile file, InputStream is) throws IOException {
		super(file, new GZIPInputStream(is));
	}

	@Override
	public JFile getNextEntry() throws IOException {
		return super.getNextEntry();
	}
}
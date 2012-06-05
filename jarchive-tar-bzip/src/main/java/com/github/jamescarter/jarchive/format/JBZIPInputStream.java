package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import org.apache.tools.bzip2.CBZip2InputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;

@FileExtensions(values = { "tar.bz", "tar.bz2" })
public class JBZIPInputStream extends JTARInputStream {
	public JBZIPInputStream(InputStream is) throws IOException {
		super(new CBZip2InputStream(is));
	}

	@Override
	public JFile getNextEntry() throws IOException {
		return super.getNextEntry();
	}
}
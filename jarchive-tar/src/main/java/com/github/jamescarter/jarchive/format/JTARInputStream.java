package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

@FileExtensions(values = { "tar" })
public class JTARInputStream extends JInputStream {
	public JTARInputStream(InputStream is) throws IOException {
		super(new TarInputStream(is));
	}

	@Override
	public JFile getNextEntry() throws IOException {
		TarInputStream is = ((TarInputStream)getInnerStream());

		TarEntry tentry = is.getNextEntry();

		if (tentry == null) {
			return null;
		}

		return new JFile(tentry.getName(), (long)is.getRecordSize(), tentry.getSize(), tentry.isDirectory());
	}
}

package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

@FileExtensions(values = { "zip", "jar", "war", "ear" })
public class JZIPInputStream extends JInputStream {
	public JZIPInputStream(InputStream is) throws IOException {
		super(new ZipInputStream(is));
	}

	@Override
	public JFile getNextEntry() throws IOException {
		ZipEntry zentry = ((ZipInputStream)getInnerStream()).getNextEntry();
		
		if (zentry == null) {
			return null;
		}
		
		JFile jentry = new JFile(zentry.getName(), zentry.getCompressedSize(), zentry.getSize(), zentry.isDirectory());

		return jentry;
	}
}
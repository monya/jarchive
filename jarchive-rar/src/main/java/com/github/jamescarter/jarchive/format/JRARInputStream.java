package com.github.jamescarter.jarchive.format;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

@FileExtensions(values = { "rar" })
public class JRARInputStream extends JInputStream {
	private Archive arc;

	public JRARInputStream(InputStream is) throws IOException {
		super(null);

		try {
			arc = new Archive(is);
		} catch (RarException e) {
			throw new IOException(e);
		}
	}

	@Override
	public JFile getNextEntry() throws IOException {
		FileHeader fh = arc.nextFileHeader();

		if (fh == null) {
			return null;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			arc.extractFile(fh, baos);
		} catch (RarException e) {
			throw new IOException(e);
		}

		setInnerStream(new ByteArrayInputStream(baos.toByteArray()));

		String name = fh.getFileNameString().replace("\\", "/");
		name+= (fh.isDirectory() ? "/" : "");

		JFile jentry = new JFile(name, fh.getFullPackSize(), fh.getFullUnpackSize(), fh.isDirectory());

		return jentry;
	}
}
package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

@FileExtensions(values = { "rar" })
public class JRARInputStream extends JInputStream {
	private PipedOutputStream pos = new PipedOutputStream();
	private Archive arc;

	public JRARInputStream(InputStream is) throws IOException {
		super(null);

		setInnerStream(new PipedInputStream(pos));

		try {
			arc = new Archive(getInnerStream());
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

		try {
			arc.extractFile(fh, pos);
		} catch (RarException e) {
			throw new IOException(e);
		}

		JFile jentry = new JFile(fh.getFileNameString(), fh.getFullPackSize(), fh.getFullUnpackSize(), fh.isDirectory());

		return jentry;
	}
}
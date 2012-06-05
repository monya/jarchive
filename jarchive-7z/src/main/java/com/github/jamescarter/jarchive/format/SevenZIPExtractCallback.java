package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import SevenZip.ArchiveExtractCallback;

public class SevenZIPExtractCallback extends ArchiveExtractCallback {
	private PipedInputStream pis;

	@Override
	public int GetStream(int index, java.io.OutputStream[] outStream,
			int askExtractMode) throws IOException {

		pis = new PipedInputStream(4096000);
		outStream[0] = new PipedOutputStream(pis);

		return 0;
	}

	public InputStream getInputStream() {
		return pis;
	}
}
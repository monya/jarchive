package com.github.jamescarter.jarchive;

import java.io.IOException;
import java.io.InputStream;

public abstract class JInputStream extends InputStream {
	private InputStream is;

	public JInputStream(InputStream is) {
		this.is = is;
	}

	public InputStream getInnerStream() {
		return is;
	}

	public void setInnerStream(InputStream is) {
		this.is = is;
	}

	public abstract JFile getNextEntry() throws IOException;

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public int read() throws IOException {
		return is.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return is.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return is.read(b, off, len);
	}
	
	@Override
	public synchronized void mark(int readlimit) {
		is.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		return is.markSupported();
	}

	@Override
	public synchronized void reset() throws IOException {
		is.reset();
	}

	@Override
	public long skip(long n) throws IOException {
		return is.skip(n);
	}

	@Override
	public void close() throws IOException {
		super.close();
	}
}

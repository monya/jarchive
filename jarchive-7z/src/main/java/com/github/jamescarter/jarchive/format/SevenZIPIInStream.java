package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import com.github.jamescarter.jarchive.RandomAccessStream;

public class SevenZIPIInStream extends SevenZip.IInStream  {
	private RandomAccessStream ras;

	public SevenZIPIInStream(InputStream is) throws IOException {
		ras = new RandomAccessStream(is);
	}

	@Override
	public long Seek(long offset, int seekOrigin) throws IOException {
        if (seekOrigin == STREAM_SEEK_SET) {
            ras.seek(offset);
        } else if (seekOrigin == STREAM_SEEK_CUR) {
            ras.seek(offset + ras.getFilePointer());
        }

        return ras.getFilePointer();
	}

	@Override
	public int read() throws IOException {
		return ras.read();
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return ras.read(b, off, len);
	}

	@Override
	public int read(byte[] b) throws IOException {
		return ras.read(b);
	}

	@Override
	public void close() throws IOException {
		ras.close();
	}
}

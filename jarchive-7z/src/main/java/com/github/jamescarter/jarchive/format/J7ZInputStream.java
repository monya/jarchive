package com.github.jamescarter.jarchive.format;

import java.io.IOException;
import java.io.InputStream;

import SevenZip.Archive.IInArchive;
import SevenZip.Archive.SevenZipEntry;
import SevenZip.Archive.SevenZip.Handler;

import com.github.jamescarter.jarchive.FileExtensions;
import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

@FileExtensions(values = { "7z" })
public class J7ZInputStream extends JInputStream {
	private SevenZIPExtractCallback callback = new SevenZIPExtractCallback();
	private Handler archive = new Handler();
	private int numFiles = 0;
	private int fileIndex = 0;

	public J7ZInputStream(InputStream is) throws IOException {
		super(null);

		SevenZIPIInStream sevenZip = new SevenZIPIInStream(is);

		int ret = archive.Open(sevenZip);

		setInnerStream(sevenZip);

        if (ret != 0) {
            throw new IOException("Unable to open 7z file");
        }

        numFiles = archive.size();
	}

	@Override
	public JFile getNextEntry() throws IOException {
		if (fileIndex >= numFiles) {
			return null;
		}

		SevenZipEntry sze = archive.getEntry(fileIndex);
		String name = sze.getName() + (sze.isDirectory() ? "/" : "");
		int[] arrays = { fileIndex };
		archive.Extract(arrays, 1, IInArchive.NExtract_NAskMode_kExtract, callback);

		setInnerStream(callback.getInputStream());

		++fileIndex;

		return new JFile(name, sze.getCompressedSize(), sze.getSize(), sze.isDirectory());
	}

}

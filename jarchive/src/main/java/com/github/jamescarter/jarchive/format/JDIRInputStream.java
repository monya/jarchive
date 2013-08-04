package com.github.jamescarter.jarchive.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.github.jamescarter.jarchive.JFile;
import com.github.jamescarter.jarchive.JInputStream;

public class JDIRInputStream extends JInputStream {
	private ArrayList<File> fileList = new ArrayList<File>();
	private int fileIndex = 0;

	public JDIRInputStream(File file) {
		super(null, null);

		populateFileList(file);
	}

	private void populateFileList(File file) {
		for (File f : file.listFiles()) {
			fileList.add(f);

			if (f.isDirectory()) {
				populateFileList(f);
			}
		}
	}

	@Override
	public JFile getNextEntry() throws IOException {
		if (fileIndex >= fileList.size()) {
			return null;
		}

		File file = fileList.get(fileIndex);
		JFile jfile = new JFile(file);

		++fileIndex;

		if (!file.isDirectory()) {
			setInnerStream(new FileInputStream(file));
		}

		return jfile;
	}
}
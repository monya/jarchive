package com.github.jamescarter.jarchive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnarchive {
	private static final String ARCHIVE_PATH = "jarchive/src/test/resources/archives/";
	private static final String[] ARCHIVES = {
		"test.tar.xz",
		"test.tar.gz",
		"test.tar.bz2",
		"test.zip",
		"test.7z",
		"test.tar",
		"test.rar",
		"combined.7z"
	};
	private static final ArrayList<String> validFileList = new ArrayList<String>();
	private static String lipsum1Contents;
	private static String lipsum2Contents;

	@Before
	public void setUp() throws Exception {
		validFileList.add("foldera/");
		validFileList.add("foldera/folderb/");
		validFileList.add("foldera/folderb/lipsum2.txt");
		validFileList.add("foldera/lipsum1.txt");

		lipsum1Contents = readFile(ARCHIVE_PATH + "lipsum1.txt");
		lipsum2Contents = readFile(ARCHIVE_PATH + "lipsum2.txt");
	}

	@Test
	public void testUnarchive() throws FileNotFoundException, IOException {
		for (String arc : ARCHIVES) {
			genericOutput(
				JArchive.getJInputStream(new File(ARCHIVE_PATH + arc))
			);
		}
	}

	private void genericOutput(JInputStream is) throws IOException {
		JFile entry;
		int entryCounter = 0;

		while((entry = is.getNextEntry()) != null) {
			++entryCounter;

			if (entry.isArchive()) {
				genericOutput(
					JArchive.getJInputStream(entry, is)
				);

				continue;
			}

			// Make sure we're seeing an expected file
			assertTrue(entry.getName(), validFileList.contains(entry.getName()));

			if (entry.getName().equals("foldera/lipsum1.txt")) {
				assertTrue(lipsum1Contents.equals(readFile(is)));
			} else if (entry.getName().equals("foldera/folderb/lipsum2.txt")) {
				assertTrue(lipsum2Contents.equals(readFile(is)));
			}
		}

		// Ensure at least four entries were processed
		assertTrue(entryCounter + " >= 4", entryCounter >= 4);
	}

	private static String readFile(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);

		try {
			return readFile(fis);
		} finally {
			fis.close();
		}
	}

	private static String readFile(InputStream is) throws IOException {
		byte[] b = new byte[10240];
		int len = b.length;
		int total = 0;

		while (total < len) {
			int result = is.read(b, total, len - total);
			if (result == -1) {
				break;
			}
			total += result;
		}

		return new String(b);
	}
}

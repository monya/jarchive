package com.github.jamescarter.jarchive;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class TestZip extends TestUnarchive {
	@Test
	public void testUnzip() throws FileNotFoundException, IOException {
		testUnarchive("test.zip");
	}
}

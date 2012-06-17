package com.github.jamescarter.jarchive;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class TestBzip extends TestUnarchive {
	@Test
	public void testUnzip() throws FileNotFoundException, IOException {
		testUnarchive("test.tar.bz2");
	}
}

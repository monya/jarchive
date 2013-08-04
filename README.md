jarchive
========

JArchive is a lightweight and modular Java API for working with archives (7z, RAR, TAR, BZ2, GZ, XZ, ZIP)

```java
public class Example {
	private static void genericOutput(JInputStream is) throws IOException {
		JFile entry;

		while((entry = is.getNextEntry()) != null) {
			if (entry.isArchive()) {
				genericOutput(
					JArchive.getJInputStream(entry, is)
				);

				continue;
			}

			System.out.println("Entry: " + entry);

			// readFileContent(is);
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		genericOutput(
			JArchive.getJInputStream(new File("test.zip"))
		);
	}
}
```

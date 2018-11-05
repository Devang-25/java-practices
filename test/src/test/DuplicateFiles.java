package test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DuplicateFiles {

	private static MessageDigest messageDigest;
	static {
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("cannot initialize SHA-512 hash function", e);
		}
	}

	public static void finddups(Map<String, List<String>> lists, File dir) {
		File[] files = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return !file.isHidden();
			}

		});

		for (File child : files) {
			if (child.isDirectory()) {
				finddups(lists, child);
			} else {
				try {
					FileInputStream fileinput = new FileInputStream(child);
					byte[] data = new byte[(int) child.length()];

					fileinput.read(data);
					fileinput.close();
					String hash = new BigInteger(1, messageDigest.digest(data)).toString(32);

					List<String> list = lists.get(hash);
					if (list == null) {
						list = new LinkedList<String>();
						lists.put(hash, list);
					}
					list.add(child.getAbsolutePath());

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public static void findDuplicatedFiles(Map<String, List<String>> lists, File directory) {
		if (directory == null || !directory.isDirectory())
			return;
		// System.out.println(directory);
		File[] subdirs = directory.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isHidden() == false;
			}
		});
		for (File child : subdirs) {
			if (child.isDirectory()) {
				findDuplicatedFiles(lists, child);
			} else {
				try {
					FileInputStream fileInput = new FileInputStream(child);
					byte fileData[] = new byte[(int) child.length()];
					fileInput.read(fileData);
					fileInput.close();
					String uniqueFileHash = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
					List<String> list = lists.get(uniqueFileHash);
					if (list == null) {
						list = new LinkedList<String>();
						lists.put(uniqueFileHash, list);
					}
					list.add(child.getAbsolutePath());
				} catch (IOException e) {
					throw new RuntimeException("cannot read file " + child.getAbsolutePath(), e);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, List<String>> lists = new HashMap<String, List<String>>();
		DuplicateFiles.finddups(lists, new File("/home/preetykumari/Documents"));
		System.out.println(Character.MIN_RADIX);
		System.out.println(Character.MAX_RADIX);
		for (List<String> list : lists.values()) {

			if (list.size() > 1) {
				System.out.println("\n");
				System.out.println("duplicate files are");
				for (String file : list) {
					System.out.println(file);
				}
			}
		}
		System.out.println("\n");
	}

}

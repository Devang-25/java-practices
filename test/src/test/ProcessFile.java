package test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessFile {
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		List<String[]> list= Files.lines(Paths.get("/home/preetykumari/eclipse-java-workspace/test/src/input.txt"))
//		.map(line -> {
//			System.out.println("line: " + line);
//			String[] words=  line.;
//			System.out.println("words: " + words[0]);
//			return words;
//		})
//		.collect(Collectors.toList());
	
		
		Stream<String> lines = Files.lines(Paths.get("/home/preetykumari/eclipse-java-workspace/test/src/input.txt"), StandardCharsets.UTF_8);
	    List<String> words = lines
	    		.flatMap(line -> Stream.of(line.split(" +")))
	    		.collect(Collectors.toList());
		System.out.println("words: "+ words);
	}

}

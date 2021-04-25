import java.util.LinkedList;
import java.util.Iterator;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class unGeorge {

	public static int tabCount = 0;
	public static String cwd = System.getProperty("user.dir"); 

	public static void saveListToFile(LinkedList<String> fileLines, String filename) throws IOException{

		int lineNo = 0;
		FileWriter fr = new FileWriter(new File(filename));
		Iterator it = fileLines.listIterator();
		String curr = null,
			   prev = null;

		while (it.hasNext()) {
			
			lineNo++;
			prev = curr;
			curr = (String) it.next();

			if (curr.equals(prev)) {

				continue;
			
			}

			// System.out.println(curr);			
			if (prev != null) {

				fr.write(prev + "\n");
			
			}

		}

		fr.close();


	} // saveListToFile

	public static LinkedList readFileIntoList(String filename) throws IOException{

		int insertIndex, newLineIndex, lineNo = 0;

		StringBuffer[] consecutiveLines;
		StringBuffer currLine = null,
					 prevLine = null;

		LinkedList<String> fileList = new LinkedList<>();					 
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String oneLine = ""; 
	
		while (true) {
			//setup
			lineNo++;

			oneLine = in.readLine();
			prevLine = currLine;

			if (oneLine == null) {
				break; 
			}

			oneLine = oneLine.trim();
			currLine = new StringBuffer(oneLine);

			tabCount = tabCount + count(oneLine, "{") - count(oneLine, "}");
			//move curly braces to first line
			consecutiveLines = processLines(currLine, prevLine);
			prevLine = consecutiveLines[0];
			currLine = consecutiveLines[1];


			// move end brace to a new line
			currLine = formatClosingBrace(currLine.toString());
			// set the correct indentation
			// currLine = indentLine(currLine.toString(), tabCount);
			// System.out.printf("%d:%s\n", lineNo, currLine);

			// add a new line if it is congested

			if (lineNo > 1) {

				fileList.add(prevLine.toString());

			}//

		} // while

		in.close();

		return fileList;
		
	} // formatFile

	public static StringBuffer[] processLines (StringBuffer curr, StringBuffer pre) { 
		//moves a new line curly brace to the same line as the function/class/loop definition 
		assert curr != null;
		String aLine = curr.toString();	
		int insertIndex;

		if (aLine.contains("(") && aLine.contains(")") || aLine.contains("class")) {

			if (pre.length() > 0) {
				pre.append("\n");
			}


		}

		if (aLine.startsWith("{")) {

			// System.out.println(lineNo-1 + " " + prevLine);
			// System.out.println(lineNo + " " + currLine);
			// System.out.println();

			insertIndex = findInsertIndex(pre.toString());
			pre.insert(insertIndex+1, " {");
			pre.append("\n");
			curr = replace(curr, "{", "");

		} // if currline starts with curly brace
		
		return new StringBuffer[] {pre, curr}; 

	} // processLines;

	public static StringBuffer formatClosingBrace(String aLine) {
		int newLineIndex;

		aLine = aLine.trim();

		if (aLine.contains("}") && aLine.length() > 1) {
			aLine = aLine.replace("}", "\n}");
		}

		return new StringBuffer(aLine);

	}

	public static int findInsertIndex(String aLine) {
		int result, parenthesisIndex, commentIndex, eolIndex;

		parenthesisIndex = aLine.indexOf(")");
		commentIndex = aLine.indexOf("//"); 
		eolIndex = aLine.length() - 1;

		result = parenthesisIndex;

		if (parenthesisIndex < 0) {

			result = commentIndex;

		} 

		if (parenthesisIndex < 0 && commentIndex < 0) {

			result = eolIndex;

		}

		// System.out.printf("parenthesis \t%s\n",parenthesisIndex);
		// System.out.printf("comment \t%s\n",commentIndex);
		// System.out.printf("eol \t%s\n",eolIndex);

		// System.out.printf("insert \t%s\n",result);

		return result;
	}

	public static StringBuffer indentLine(String text, int tabCount) {
		String result = text.trim();

		return new StringBuffer(pad(tabCount,"\t")+result);
	} // indentLine

	public static StringBuffer replace(StringBuffer sb, String substr, String replacement) {
		String string = sb.toString();
		String result = string.replace(substr,replacement);

		return new StringBuffer(result);
	} 

	public static int count(String str, String substr) {

		int index = str.indexOf(substr);

		if (index == -1) {

			return 0;

		} else {

			String newString = str.substring(index + substr.length());
			return 1 + count(newString, substr);
		}

	} // count

	public static String pad(int size, String substr) {

		StringBuffer sb = new StringBuffer();
		for (int k = 0; k < size; k++) {
			sb.append(substr);
		}

		return sb.toString();

	} // pad

	public static String pad(int size) {
		return pad(size, " ");
	}//

	public static void test(String filename) {

		try {

			InputStreamReader in = new InputStreamReader(new FileInputStream(new File(filename))); 

			System.out.println(in.getEncoding());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	};

	public static void main(String[] args) {

		System.out.println(indentLine("psych", 2));

		try {
			
			LinkedList fileContents = readFileIntoList("Example.java");
			saveListToFile(fileContents, "Suprise.txt");

			System.out.printf(">> %s\n",cwd);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	} // main

} // unGeorge

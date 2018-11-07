package hw9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
        final String FILLNAME = "/Users/JackZhang/IdeaProjects/interview/MinimalMUMCUT.java";
        // HashMap<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
//        map.put("if", 0);
//        map.put("while", 0);
//        map.put("for", 0);
//        map.put("switch", 0);
//        map.put("?", 0);
//        map.put("foreach", 0);
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILLNAME))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                lineCount++;
                if (sCurrentLine.indexOf("//") != -1) {
                    continue;
                }
                if (sCurrentLine.indexOf(" if ") != -1) {
//                    map.put("if", map.get("if") + 1);
                    list.add(lineCount + sCurrentLine);

                }
                if (sCurrentLine.indexOf(" while") != -1) {
//                    map.put("while", map.get("while") + 1);
                    list.add(lineCount + sCurrentLine);
                }
                if (sCurrentLine.indexOf(" for ") != -1) {
//                    map.put("for", map.get("for") + 1);
                    list.add(lineCount + sCurrentLine);
                }
                if (sCurrentLine.indexOf(" switch") != -1) {
//                    map.put("switch", map.get("switch") + 1);
                    list.add(lineCount + sCurrentLine);
                }
                if (sCurrentLine.indexOf(" ?") != -1) {
//                    map.put("?", map.get("?") + 1);
                    list.add(lineCount + sCurrentLine);
                }
                if (sCurrentLine.indexOf(" foreach") != -1) {
//                    map.put("foreach", map.get("foreach") + 1);
                    list.add(lineCount + sCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (String s: map.keySet()) {
//            System.out.println("We got " + s + " " + "has " + map.get(s));
//        }

        list = excludeComment(list);
        
        List<String> listContainsMulLine = findMulLine(list);
        list = excludeMulLine(list, listContainsMulLine);
        System.out.println("List contains single line: " + (list.size()));
        System.out.println("List contains multiple lines: " + listContainsMulLine.size());
        
        // count clause
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, List<String>> location = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
        	String oneLine = list.get(i);
        	String[] word = oneLine.split(" ");
        	int count = 1;
        	String lineNum = word[0];
        	for (int j = 0; j < word.length; j++) {
        		if (word[j].indexOf("&&") != -1) {
        			count++;
        		}
        		if (word[j].indexOf("||") != -1) {
        			count++;
        		}
        	}
        	freq.putIfAbsent(count, 0);
        	location.putIfAbsent(count, new ArrayList<>());
        	freq.put(count, freq.get(count) + 1);
        	location.get(count).add(lineNum);
        }
        

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("output.txt");
            bw = new BufferedWriter(fw);
            bw.write("Single keyword and line: ");
            for (String s: list) {
                bw.write(s);
                bw.write("\n");
            }

            bw.write("\n");
            bw.write("Multiple keywords or lines: ");
            for (String s: listContainsMulLine) {
                bw.write(s);
                bw.write("\n");
            }
            
            bw.write("\n");
            bw.write("Clause amount: ");
            for (Integer i: freq.keySet()) {
            	bw.write(i + " clause has " + freq.get(i) + "statements:");
            	bw.write("\n");
            	for (int j = 0; j < location.get(i).size(); j++) {
            		String s = location.get(i).get(j);
            		bw.write(s);
                    bw.write("\n");
            	}
            	bw.write("\n");
            }
            
            
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
        
        
        
    }
	
	public static List<String> excludeMulLine(List<String> list, List<String> mulList) {
		List<String> res = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < list.size() && j < mulList.size()) {
			if (list.get(i).equals(mulList.get(j))) {
				j++;
			} else {
				res.add(list.get(i));
			}
			i++;
		}
		while (i < list.size()) {
			res.add(list.get(i));
			i++;
		}
		return res;
	}

    public static List<String> excludeComment(List<String> list) {
        List<String> res = new ArrayList<>();
        for (String s: list) {
            if (s.indexOf("/*") != -1) {
                continue;
            }
            if (s.indexOf('\"') != -1) {
                Stack<String> stack = new Stack<>();
                String[] words = s.split(" ");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (stack.isEmpty() && word.indexOf("\"") != -1) {
                        stack.push(word);
                        continue;
                    }

                    if (!stack.isEmpty() && word.indexOf("\"") != -1) {
                        stack.pop();
                        continue;
                    }

                    if (!stack.isEmpty()) {
                        continue;
                    }

                    if (word.equals("if")) {
                        res.add(s);
                        break;
                    }
                    if (word.equals("for")) {
                        res.add(s);
                        break;
                    }
                    if (word.equals("while")) {
                        res.add(s);
                        break;
                    }
                    if (word.equals("?")) {
                        res.add(s);
                        break;
                    }
                }
            }
            if (s.indexOf("/*") == -1 && s.indexOf('\"') == -1) {
                res.add(s);
            }

        }
        return res;
    }

    public static List<String> findMulLine(List<String> list) {
        List<String> res = new ArrayList<>();
        for (String s: list) {
            if (!isBalance(s)) {
                res.add(s);
            } else {
                // list.remove(s);
            }
        }
        return res;
    }

    public static boolean isBalance(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }


}

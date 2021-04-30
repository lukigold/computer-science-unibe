package covidgame1;


import java.util.*;

/**
 * Parses given String into Commands.
 * All input that is not a command will be ignored.
 * 
 * @author Kevin St�ckli
 * @author Lukas Ingold
 *
 */

public class CommandParser {

    protected ArrayList<String> words = new ArrayList<String>();
    protected Queue<Command> commands = new LinkedList<Command>();

    public CommandParser(String program){
        assert(program!=null);
        extractWords(program);
    }

    /**
     * Extracts words from given String.
     * @param program String which holds commands from User
     */
    private void extractWords(String program){
    	assert program.length() >= 0;
        
    	for(String val: program.split("\\s+")){
        	words.add(val);
        }
    }
    
    public ArrayList<String> getWords() {
    	return words;
    }
    
    /**
     * Checks if a String can be parsed as a Integer
     * @param s String to be checked
     * @return boolean true if String can be parsed, false otherwise
     */
    public boolean isStringInt(String s) {
    	try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    /**
     * Parses all words into commands (those which are commands).
     * ArrayList of words cannot be negative, consequently Queue with commands cannot be negative.
     * @param words ArrayList which holds words from given String
     * @return commands as a Queue
     * @see covidgame1.Command
     */
    public Queue<Command> parse(ArrayList<String> words){
    	assert words.size() >= 0;
    	
    	for(int h = 0 ; h < words.size() ; h++){
    		int value;
        	String direction;
    		String sig = words.get(h);
            switch (sig){
                case "left":
                    if(h+1 < words.size() && isStringInt(words.get(h+1))) {
                    	value = Integer.parseInt(words.get(h+1).replaceAll("[^0-9]", ""));
                    	direction = "left";
                    	Command command = new Command(value, direction);
                    	commands.add(command);
                    }
                    break;
                case "right":
                	if(h+1 < words.size() && isStringInt(words.get(h+1))) {
                		value = Integer.parseInt(words.get(h+1).replaceAll("[^0-9]", ""));
                    	direction = "right";
                    	Command command = new Command(value, direction);
                    	commands.add(command);
                	}
                    break;
                case "down":
                	if(h+1 < words.size() && isStringInt(words.get(h+1))) {
                		value = Integer.parseInt(words.get(h+1).replaceAll("[^0-9]", ""));
                    	direction = "down";
                    	Command command = new Command(value, direction);
                    	commands.add(command);
                	}
                    break;
                case "up":
                	if(h+1 < words.size() && isStringInt(words.get(h+1))) {
                		value = Integer.parseInt(words.get(h+1).replaceAll("[^0-9]", ""));
                    	direction = "up";
                    	Command command = new Command(value, direction);
                    	commands.add(command);
                	}
                default:
                    break;
            }
        }
    	assert commands.size() >= 0;
    	return commands;
    }




}

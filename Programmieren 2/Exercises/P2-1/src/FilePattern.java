package src;
//Kevin Stöckli 20-119-236
//Lukas Ingold 20-123-998
/**
 * Filters file names using command-line wildcards.
 *
 * '*' matches any number of character.
 * '?' matches exactly one character.
 *
 * Examples:
 * '*.md' matches all files with the markdown extension.
 * 'exercise_??.md' matches, for example, 'exercise_01.md'.
 *
 * @author You!
 *
 */
public class FilePattern {
	/**
	 * Creates a new instance of the FilePattern class that filters
	 * file names based on the given pattern.
	 *
	 * @param pattern the pattern used to filter file names.
	 * @see FilePattern
	 */
	private String pattern;
	public FilePattern(String pattern) {
		this.pattern = pattern;

	}


	/**
	 * Returns whether the given filename matches this pattern.
	 * @param filename
	 * @return true if filename matches the pattern
	 */
	public boolean matches(String filename) {
		int j = 0; //Index of filename
		for(int i = 0; i < pattern.length(); i++) { //loop trough characters of pattern

			switch(pattern.charAt(i)) {
				case '?':
					if(filename.length()-1 < j)
						return false;
					else
						j++;
					break;

				case '*':
					if(pattern.charAt(pattern.length()-1) == '*')
						return true;
					else
						i++; //element after * is important (this exists because previous if statement)
						for(;j < filename.length(); j++) //loop through rest of filename until match
							if(pattern.charAt(i) == filename.charAt(j))
								break;
						if(j == filename.length())
							return false; //arrived at last element without finding element after * => cannot match
						j++;
						break;

				default:
					if(filename.length()-1 < j || pattern.charAt(i) != filename.charAt(j))
						return false;
					else
						j++;
			}
		}
		if(j < filename.length()) //this means one element of filename was not reached => cannot match
			return false;
		return true;
	}
}

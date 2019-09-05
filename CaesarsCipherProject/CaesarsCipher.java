package CaesarsCipherProject;

/**
 * 
 * @author Abbey Hustis and Noah Anthony 
 *
 */
public class CaesarsCipher {
	
	
	protected char[] uppercase = new char[26];  	//array for uppercase letters
	protected char[] lowercase = new char[26];		//array for lowercase letters
	protected int [] numbKey = new int [3]; 		//array for the key
	
	/**
	 * Creates an instance of the cipher
	 * @param firstRot First rotation key
	 * @param secondRot Second rotation key
	 * @param thirdRot Third rotation key
	 */
	public CaesarsCipher(int firstRot, int secondRot, int thirdRot) {
		numbKey[0] = firstRot;
		numbKey[1] = secondRot;
		numbKey[2] = thirdRot;
	}
	
	/**
	 * Encrypts your message
	 * @param message The message that you want to encrypt
	 * @return the encrypted message
	 */
	public String encrypt(String message) {
		char[] msg = message.toCharArray();
		for(int j = 0; j < 3; j++) {
			for (int i = 0; i < 26; i++) {
				uppercase[i] = (char)('A' + (i + numbKey[j]) % 26);
				lowercase[i] = (char)('a' + (i + numbKey[j]) % 26);
			}
			for (int i = j; i < msg.length; i += 3) {
				if(Character.isUpperCase(msg[i])) {
					int k = msg[i] - 'A';
					msg[i] = uppercase[k];
				}else if(Character.isLowerCase(msg[i])) {
					int k = msg[i] - 'a';
					msg[i] = lowercase[k];
				}
			}
		}
		return new String(msg);	
	}
	
	/**
	 * Decrypts your message
	 * @param secret The message that you want decrypted
	 * @return The decrypted message
	 */
	public String decrypt(String secret) {
		char[] msg = secret.toCharArray();
		for(int j = 0; j < 3; j++) {
			for (int i = 0; i < 26; i++) {
				uppercase[i] = (char)('A' + (i - numbKey[j] + 26) % 26);
				lowercase[i] = (char)('a' + (i - numbKey[j] + 26) % 26);
			}
			for (int i = j; i < msg.length; i += 3) {
				if(Character.isUpperCase(msg[i])) {
					int k = msg[i] - 'A';
					msg[i] = uppercase[k];
				}else if(Character.isLowerCase(msg[i])) {
					int k = msg[i] - 'a';
					msg[i] = lowercase[k];
				}
			}
		}
		return new String(msg);
	}
	


}

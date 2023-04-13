package endpointServices.groovy;

import org.jivesoftware.smack.chat.Chat
import org.jivesoftware.smack.packet.Message

public class JabberGuessingGame {

    private final int MAX_NUMBER = 10;

    private String prefix = 'Martini Bot: ';

    private String CACHE_NAME = 'guessingGame';

	/**
	 * This method will start the guessing game. It checks if the provided number
	 * is greater than, less than, or equals to the cached number.
	 * @param chat the chat object
	 * @param message the message from the user
	 * @param from the user sending the message
	 * @return string the message to start the guessing game or the result of the game
	 */
    public String guess( Chat chat, Message message, String from ) {
        String mgs = message.getBody();
        if ( getUserGuessingNumber ( from ) ) {
            if( mgs.equalsIgnoreCase( 'quit' ) ) {
                removeCacheUserGuessingNumber( from )
                return prefix + 'OK. BYE :\'('
            } else if ( !(mgs =~ '[0-9]') ){
                return prefix + 'Ooooops, Invalid number'
            }
            if ( getUserGuessingNumber( from ) == mgs.toInteger() ) {
                removeCacheUserGuessingNumber( from )
                prefix + 'You got it right! :)'
            } else if ( mgs.toInteger() > getUserGuessingNumber( from ) ) {
                prefix + 'Oooppps, too high. (oops) Please Try Again'
            } else if ( mgs.toInteger() < getUserGuessingNumber( from ) ) {
                prefix + 'Oooppps, too low. (oops) Please Try Again. '
            }
        } else {
            if ( mgs.equalsIgnoreCase( 'y' ) ) {
                cacheUserGuessingNumber( from );
                prefix + 'Please guess the number from 0 - 10. Type Quit to end the game.';
            } else if ( mgs.equalsIgnoreCase( 'n' ) ) {
                prefix + 'OK. BYE :\'('
            } else {
                prefix + 'Hello, wanna play a guessing game? Type Y or N';
            }
        }
        //Other way to send reply
        //chat.sendMessage( message );
    }

	/**
	 * Saves the username to the cache
	 * @param username the username to save
	 */
    private void cacheUserGuessingNumber( String username ) {
        CACHE_NAME.cachePut( username,  new Random().nextInt(MAX_NUMBER) )
    }

	/**
	 * Removes the username from the cache
	 * @param username the username to remove
	 */
    private void removeCacheUserGuessingNumber( String username ) {
        if ( getUserGuessingNumber( username ) )
            CACHE_NAME.cacheInvalidate( username );
    }

	/**
	 * Gets the username from the cache
	 * @param username the user whose number will be fetched from the cache
	 * @return number associated to the username from the cache
	 */
    private Integer getUserGuessingNumber( String username ) {
        return CACHE_NAME.cacheGet( username );
    }

}

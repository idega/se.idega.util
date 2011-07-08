package se.idega.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Object to improve storing and logging error and warning messages.
 *
 * This object was created to replace a similar tool implemented as a StringBuffer, hence the
 * function names are similar to StringBuffer, even though the object behind it is an ArrrayList.
 *
 * The general idea is to store data here so that it is available do be displayed if an error occurs.
 * As an extension it is also possible to let this object do some logging to the console.
 *
 * It is possible to set the loggin level at creation and then all new loggings with same or lower
 * logging level will be outputed to the console at the same time
 *
 * @author Joakim
 *
 */
public class ErrorLogger {

	private List<String> message = new ArrayList<String>();
	private int loggingLevel;
	private Logger log = Logger.getLogger(ErrorLogger.class.getSimpleName());

	/**
	 * The new ErrorLogger is a copy of el
	 * @param el
	 */
	public ErrorLogger(ErrorLogger el){
		this.message = new ArrayList<String>(el.getMessage());
		loggingLevel = el.getLoggingLevel();
	}

	/**
	 * Create an empty error logger with logging level set to 4
	 */
	public ErrorLogger(){
		loggingLevel = 4;
	}

	/**
	 * Create a new error logger with initial value of string s
	 * @param s
	 */
	public ErrorLogger(String s){
		message.add(s);
		loggingLevel = 4;
	}

	/**
	 * Create an empty error logger with logging level set to l
	 * @param l
	 */
	public ErrorLogger(int l){
		loggingLevel = l;
	}

	/**
	 * Create a new error logger with initial value of string s and logging level of l
	 * @param s
	 * @param l
	 */
	public ErrorLogger(String s, int l){
		message.add(s);
		loggingLevel = l;
	}

	/**
	 * Appends a logging string
	 * @param s
	 */
	public void append(String s){
		message.add(s);
		if(loggingLevel>=5){
			log.info(s);
		}
	}

	/**
	 * Appends a logging string and outputs it to the console if the logging level is greater than
	 * or equal to i
	 * @param s
	 * @param i
	 */
	public void append(String s, int i){
		message.add(s);
		if(loggingLevel>=i){
			log.info(s);
		}
	}

	/**
	 * Appends the stacktrace of an exception to the logging string
	 * @param e
	 */
	public void append(Exception e){
		message.add("Stacktrace:"+e.toString());
		StackTraceElement[] stackTraceElement = e.getStackTrace();
		for(int i=0; i<stackTraceElement.length;i++){
			message.add(stackTraceElement[i].toString());
		}
	}

	/**
	 * Returns all the loggging strings, with linebreak between each line
	 */
	@Override
	public String toString(){
		StringBuffer ret = new StringBuffer();
		for (Iterator<String> iter = message.iterator(); iter.hasNext();) {
			ret.append(iter.next()+"\n");
		}
		return ret.toString();
	}

	/**
	 * Returns all the loggging strings, with ';' between each line
	 */
	public String toStringCompact(){
		StringBuffer ret = new StringBuffer();
		for (Iterator<String> iter = message.iterator(); iter.hasNext();) {
			ret.append(iter.next()+" ; ");
		}
		return ret.toString();
	}

	/**
	 * Returns all the loggging strings, with '<br>' between each line
	 * @return String
	 */
	public String toStringForWeb(){
		StringBuffer ret = new StringBuffer();
		for (Iterator<String> iter = message.iterator(); iter.hasNext();) {
			ret.append(iter.next()+"<br>");
		}
		return ret.toString();
	}

	/**
	 * logs the content to the console
	 */
	public void logToConsole(){
		log.info(toString());
	}

	/**
	 * logs the content to the console with compact format
	 */
	public void logToConsoleCompact(){
		log.info(toStringCompact());
	}

	@Override
	public Object clone(){
		ErrorLogger el = new ErrorLogger(this);
		return el;
	}


	/**
	 * @return
	 */
	public int getLoggingLevel() {
		return loggingLevel;
	}

	/**
	 * @return
	 */
	public List<String> getMessage() {
		return message;
	}

}
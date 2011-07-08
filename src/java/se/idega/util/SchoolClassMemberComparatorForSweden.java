/*
 * $Id: SchoolClassMemberComparatorForSweden.java,v 1.3 2005/03/31 09:48:36 laddi Exp $
 * Created on Oct 21, 2004
 *
 * Copyright (C) 2004 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package se.idega.util;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import com.idega.block.school.business.SchoolClassMemberComparator;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.User;


/**
 * Example:
 * This Comparator is used to change the behaviour of the
 * SchoolClassMemberComparator.
 *
 *  Last modified: $Date: 2005/03/31 09:48:36 $ by $Author: laddi $
 *
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class SchoolClassMemberComparatorForSweden{

	  public static final int NAME_SORT = SchoolClassMemberComparator.NAME_SORT;
	  public static final int GENDER_SORT = SchoolClassMemberComparator.GENDER_SORT;
	  public static final int ADDRESS_SORT = SchoolClassMemberComparator.ADDRESS_SORT;
	  public static final int PERSONAL_ID_SORT = SchoolClassMemberComparator.PERSONAL_ID_SORT;
	  public static final int LANGUAGE_SORT = SchoolClassMemberComparator.LANGUAGE_SORT;
	  public static final int AGE_SORT = SchoolClassMemberComparator.AGE_SORT;

	 static public Comparator<User> getComparatorSortBy(int sortBy, Locale locale, UserBusiness business, Map students) {
	  	return getComparator(sortBy, locale, business, students);
	  }

	 static public Comparator<User> getComparatorSortByName(Locale locale, UserBusiness business, Map students) {
	  	return getComparator(SchoolClassMemberComparator.NAME_SORT, locale, business, students);
	  }


	 private static Comparator<User> getComparator(int sortBy, Locale locale, UserBusiness business, Map students) {
	 	Comparator<User> comparator = new Comparator<User>() {
	 		public int compare(User u1, User u2) {
	 			return u1.getGenderID() - u2.getGenderID();
	 		}
	 	};
	 	return SchoolClassMemberComparator.getComparatorSortByUseGenderComparator(sortBy, comparator, locale, business, students);
	 }



	private SchoolClassMemberComparatorForSweden() {
		// use static  methods
	}

}
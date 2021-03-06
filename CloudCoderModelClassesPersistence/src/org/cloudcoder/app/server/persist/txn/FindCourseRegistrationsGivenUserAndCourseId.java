// CloudCoder - a web-based pedagogical programming environment
// Copyright (C) 2011-2013, Jaime Spacco <jspacco@knox.edu>
// Copyright (C) 2011-2013, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.cloudcoder.app.server.persist.txn;

import java.sql.Connection;
import java.sql.SQLException;

import org.cloudcoder.app.server.persist.util.AbstractDatabaseRunnableNoAuthException;
import org.cloudcoder.app.shared.model.CourseRegistrationList;
import org.cloudcoder.app.shared.model.User;

/**
 * Transaction to find all course registrations for given user in given course
 * (using course id).
 */
public class FindCourseRegistrationsGivenUserAndCourseId extends
		AbstractDatabaseRunnableNoAuthException<CourseRegistrationList> {
	private final User user;
	private final int courseId;

	/**
	 * Constructor.
	 * 
	 * @param user      the {@link User}
	 * @param courseId  the unique id of the course
	 */
	public FindCourseRegistrationsGivenUserAndCourseId(User user,
			int courseId) {
		this.user = user;
		this.courseId = courseId;
	}

	@Override
	public CourseRegistrationList run(Connection conn) throws SQLException {
		int userId = user.getId();
		return Queries.doGetCourseRegistrations(conn, courseId, userId, this);
	}

	@Override
	public String getDescription() {
		return " finding course registration for user/course";
	}
}
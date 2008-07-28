/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.ruon.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.ruon.model.PresenceStatus;
import com.liferay.ruon.service.PresenceStatusLocalService;
import com.liferay.ruon.service.UserCommunicationLocalService;
import com.liferay.ruon.service.UserCommunicationLocalServiceFactory;
import com.liferay.ruon.service.UserPresenceLocalService;
import com.liferay.ruon.service.UserPresenceLocalServiceFactory;
import com.liferay.ruon.service.persistence.PresenceStatusPersistence;
import com.liferay.ruon.service.persistence.PresenceStatusUtil;
import com.liferay.ruon.service.persistence.UserPresencePersistence;
import com.liferay.ruon.service.persistence.UserPresenceUtil;

import java.util.List;

/**
 * <a href="PresenceStatusLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class PresenceStatusLocalServiceBaseImpl
	implements PresenceStatusLocalService, InitializingBean {
	public PresenceStatus addPresenceStatus(PresenceStatus presenceStatus)
		throws SystemException {
		presenceStatus.setNew(true);

		return presenceStatusPersistence.update(presenceStatus, false);
	}

	public void deletePresenceStatus(long presenceStatusId)
		throws PortalException, SystemException {
		presenceStatusPersistence.remove(presenceStatusId);
	}

	public void deletePresenceStatus(PresenceStatus presenceStatus)
		throws SystemException {
		presenceStatusPersistence.remove(presenceStatus);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return presenceStatusPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return presenceStatusPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public PresenceStatus getPresenceStatus(long presenceStatusId)
		throws PortalException, SystemException {
		return presenceStatusPersistence.findByPrimaryKey(presenceStatusId);
	}

	public List<PresenceStatus> getPresenceStatuss(int start, int end)
		throws SystemException {
		return presenceStatusPersistence.findAll(start, end);
	}

	public int getPresenceStatussCount() throws SystemException {
		return presenceStatusPersistence.countAll();
	}

	public PresenceStatus updatePresenceStatus(PresenceStatus presenceStatus)
		throws SystemException {
		presenceStatus.setNew(false);

		return presenceStatusPersistence.update(presenceStatus, true);
	}

	public PresenceStatusPersistence getPresenceStatusPersistence() {
		return presenceStatusPersistence;
	}

	public void setPresenceStatusPersistence(
		PresenceStatusPersistence presenceStatusPersistence) {
		this.presenceStatusPersistence = presenceStatusPersistence;
	}

	public UserCommunicationLocalService getUserCommunicationLocalService() {
		return userCommunicationLocalService;
	}

	public void setUserCommunicationLocalService(
		UserCommunicationLocalService userCommunicationLocalService) {
		this.userCommunicationLocalService = userCommunicationLocalService;
	}

	public UserPresenceLocalService getUserPresenceLocalService() {
		return userPresenceLocalService;
	}

	public void setUserPresenceLocalService(
		UserPresenceLocalService userPresenceLocalService) {
		this.userPresenceLocalService = userPresenceLocalService;
	}

	public UserPresencePersistence getUserPresencePersistence() {
		return userPresencePersistence;
	}

	public void setUserPresencePersistence(
		UserPresencePersistence userPresencePersistence) {
		this.userPresencePersistence = userPresencePersistence;
	}

	public void afterPropertiesSet() {
		if (presenceStatusPersistence == null) {
			presenceStatusPersistence = PresenceStatusUtil.getPersistence();
		}

		if (userCommunicationLocalService == null) {
			userCommunicationLocalService = UserCommunicationLocalServiceFactory.getImpl();
		}

		if (userPresenceLocalService == null) {
			userPresenceLocalService = UserPresenceLocalServiceFactory.getImpl();
		}

		if (userPresencePersistence == null) {
			userPresencePersistence = UserPresenceUtil.getPersistence();
		}
	}

	protected PresenceStatusPersistence presenceStatusPersistence;
	protected UserCommunicationLocalService userCommunicationLocalService;
	protected UserPresenceLocalService userPresenceLocalService;
	protected UserPresencePersistence userPresencePersistence;
}